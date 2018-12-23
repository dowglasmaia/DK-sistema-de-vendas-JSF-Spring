package com.maia.impl.crud.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.maia.impl.crud.interfac.GenericCrud;
import com.maia.impl.crud.util.HibernateUtil;

/*
 * Usando a Injeção de Dependencia e controle de Transação do Spring
 * 
 * */
@SuppressWarnings("deprecation")
@Component
@Transactional(rollbackFor = Exception.class)
public class GenericCrudImpl<E> implements GenericCrud<E> {
	private static final long serialVersionUID = 1L;

	/* Pega a Sessão do Hibernate */
	private static SessionFactory sessionFactory = HibernateUtil.getFactory();

	@Autowired
	private JdbcTemplateSpring jdbcTemplate;

	/* Verificação de Transação ativa do Hibernate se não ouver, inica a mesma */
	@SuppressWarnings("unused")
	private void validarTransation() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	/*
	 * Validando a Sessão do hibernate - para Garantir a consistencia da Sessão no
	 * Sistema
	 */
	private void validarSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getFactory();
		}
		validarTransation();
	}

	/* Fazendo Commit em Transações Ajax */
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	/* Fazendo RollBack para Processo Ajax */
	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	/*
	 * Roda Instantaneamente o SQL no banco de Dados - Evitando que o Amazenamento
	 * fique só em Cache
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	/* ============ Metodos da Interface CRUD ================ */

	@Override
	public void save(E obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();

	}

	@Override
	public void SaveOrUpdate(E obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();

	}

	@Override
	public void update(E obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();

	}

	@Override
	public void delete(E obj) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();

	}

	@SuppressWarnings("unchecked")
	@Override
	public E merge(E obj) throws Exception {
		validarSessionFactory();
		obj = (E) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	// Listar Todos com HQL
	@Override
	@Transactional(readOnly = true)
	public List<E> findList(Class<E> entidade) throws Exception {
		validarSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select distinct(entity) from ").append(entidade.getSimpleName()).append(" entity ");
		List<E> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public List<E> findByListQueryDinamica(String s) throws Exception {
		validarSessionFactory();
		List<E> lista = new ArrayList<E>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validarSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	/*
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado no paramento ->inicalregistro e obtendo o maximo de
	 * resultados passados em ->maxResultado.
	 * (Carregamento po Demanda)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<E> findListByQueryDinamica(String query, int inicalregistro, int maxResultado) throws Exception {
		validarSessionFactory();

		List<E> lista = new ArrayList<>();
		lista = (List<E>) sessionFactory.getCurrentSession().createQuery(query).setFirstResult(inicalregistro)
				.setMaxResults(maxResultado);

		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object[]> getListSQLDinamicaArry(String sql) throws Exception {
		validarSessionFactory();

		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();

		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public Object findById(Class<E> entidade, Long id) throws Exception {
		validarSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	/* Para HQL */
	@Override
	@Transactional(readOnly = true)
	public void executeUpdateQueryDinamica(String s) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
		executeFlushSession();

	}

	/* Para SQL Puro */
	@Override
	@Transactional(readOnly = true)
	public void executeUpdateSQLDinamica(String s) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
		executeFlushSession();

	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();

	}

	@Override
	public void evict(E objs) throws Exception {
		validarSessionFactory();
		sessionFactory.getCurrentSession().evict(objs);

	}

	@Override
	@Transactional(readOnly = true)
	public Long totalRegistro(String table) throws Exception {

		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validarSessionFactory();

		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());

		return queryReturn;
	}

	@Override
	public Session getSession() throws Exception {
		validarSessionFactory();
		return sessionFactory.getCurrentSession();
	}

}
