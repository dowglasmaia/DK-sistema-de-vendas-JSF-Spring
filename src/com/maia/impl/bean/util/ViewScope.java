package com.maia.impl.bean.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

/*Reimplementando o Scope de Sessão - ViewScope - do JSF para o Spring*/
@SuppressWarnings("unchecked")
public class ViewScope implements Scope, Serializable {
	private static final long serialVersionUID = 1L;

	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

	// Retorna o Ojeto de Escopo
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Object instance = getViewMap().get(name);
		if (instance == null) {
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}

	/* Romeve o Objeto da Sessão de Retonar o Objeto Removido */
	@Override
	public Object remove(String name) {
		Object instance = getViewMap().remove(name);
		if (instance != null) {			
			Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if (callBacks != null) {
				callBacks.remove(name);
			}
		}
		return instance;
	}

	// Registra a destruuição do scope
	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {
		Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		if (callbacks != null) {
			callbacks.put(VIEW_SCOPE_CALLBACKS, runnable);
		}
	}

	// Resolve as Referencias do Scope
	@Override
	public Object resolveContextualObject(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes attributes = new FacesRequestAttributes(facesContext);
		return attributes.resolveReference(key);
	}

	// Carrega o ID do Scope
	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes attributes = new FacesRequestAttributes(facesContext);

		// retorna o ID da sessão mais o ID da view do JSF - assim Mantem um unico scope
		// de View
		return attributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

	/*
	 * getViewRoot() Retonar o componete raiz que está associado a esta
	 * solicitação(request). getViewMap Retorna um Map que atua como a interface
	 * para o armazenamento de dados
	 * 
	 */
	private Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance() != null ? FacesContext.getCurrentInstance().getViewRoot().getViewMap()	: new HashMap<>();
	}

}
