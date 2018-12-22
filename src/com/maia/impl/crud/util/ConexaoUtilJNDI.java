package com.maia.impl.crud.util;

import java.io.Serializable;

/*
 * Nome do caminho do JNDI datasource Tomcat
 * 
 * @author Dowglas Maia*/

public class ConexaoUtilJNDI implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";

}
