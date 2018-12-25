package com.maia.impl.services;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import com.maia.impl.entity.Usuario;
import com.maia.impl.entity.auditoria.InformacaoRevisao;
import com.maia.impl.util.UtilFramework;

/*
 * Faz Referencia a InformaçãoRevisao
 * */

@Service
public class CustomListener implements RevisionListener, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntity) {
		InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisionEntity;
		Long codUser = UtilFramework.getThreadLocal().get(); // Pega o Codigo do Usuario da Sessão

		Usuario usuario = new Usuario();
		if (codUser != null && codUser != 0L) {
			usuario.setId(null);
			informacaoRevisao.setUsuario(usuario);
		}
	}

}
