package com.maia.impl.crud.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/*
 * Class Utilitaria para Trabalhar em Conjunto com o Hibernate Envers, 
 * para Captuarar o usuario da Sessão e o que o mesmo estar Fazendo, Garantindo um Auditoria eficaz!
 */

@Component
public class UtilFramework implements Serializable {
	private static final long serialVersionUID = 1L;

	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return threadLocal;
	}
}
