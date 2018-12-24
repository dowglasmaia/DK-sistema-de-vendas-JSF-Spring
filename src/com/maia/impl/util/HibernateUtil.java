package com.maia.impl.util;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * Responsavel por estabelecer a conexão com o Hibernate
 * @author Dowglas Maia
 * */

public class HibernateUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";

	private static SessionFactory sessionFactory = buildSessionFactory();

	/* Responsavel por ler o arquivo de configuração hibernate.cfg.xml */
	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}
	}

	/* retorna a sessionFactory corrente */
	public static SessionFactory getFactory() {
		return sessionFactory;
	}

	/* Retorna a Sessão do SessionFactory */
	public static Session getCurrentSession() {
		return getFactory().getCurrentSession();
	}

	/* abre uma nova sessão no SessionFactory e retorna uma Session */
	public static Session openSession() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory.openSession();
	}

	/* retorna um DataSource JNDI Tomcat */
	public DataSource getDataSourceJndi() throws NamingException {
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(ConexaoUtilJNDI.JAVA_COMP_ENV_JDBC_DATA_SOURCE);

	}

}
