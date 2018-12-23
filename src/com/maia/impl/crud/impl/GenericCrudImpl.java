package com.maia.impl.crud.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.maia.impl.crud.interfac.GenericCrud;

public class GenericCrudImpl<E> implements GenericCrud<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public void save(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void SaveOrUpdate(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public E merge(E obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findList(Class<E> objs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findByListQueryDinamica(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findListByQueryDinamica(String query, int inicalregistro, int maxResultado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E findById(Class<E> entidade, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void evict(E objs) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getSession() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
