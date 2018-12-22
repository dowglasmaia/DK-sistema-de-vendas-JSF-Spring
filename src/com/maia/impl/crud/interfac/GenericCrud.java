package com.maia.impl.crud.interfac;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public interface GenericCrud<E> extends Serializable {

	/* Salvar */
	void save(E obj) throws Exception;

	/* Salvar ou Atualiza */
	void SaveOrUpdate(E obj) throws Exception;

	/* Atualiza os Dados */
	void update(E obj) throws Exception;

	/* Deleta os Dados */
	void delete(E obj) throws Exception;

	/* Salvar ou Atualiza e Retorna o Objeto em Estado Persistente */
	E merge(E obj) throws Exception;

	/* Carregar a Lista de Dados de Determinada Class */
	List<E> findList(Class<E> objs) throws Exception;

	/* Consulta Dinamica com HQL */
	List<E> findByListQueryDinamica(String s) throws Exception;

	/* Retonar uma Lista de Qualquer Objeto com SQL - Dinamicamente */
	List<?> getListSQLDinamica(String sql) throws Exception;

	/* Faz um consulta dinamica por Demanda */
	List<E> findListByQueryDinamica(String query, int inicalregistro, int maxResultado) throws Exception;

	/* Buscar por ID */
	E findById(Class<E> entidade, Long id) throws Exception;

	/* Query Dinamica - Consulta - HQL */
	void executeUpdateQueryDinamica(String s) throws Exception;

	/* SQL Dinamica - Consulta - SQL */
	void executeUpdateSQLDinamica(String s) throws Exception;

	/* Limpar a Sessão do Hibernate */
	void clearSession() throws Exception;

	/* Retira um Objeto da Sessão do Hibernate */
	void evict(E objs) throws Exception;

	/* Obtendo a quantidade Registro na Tabela */
	Long totalRegistro(String table) throws Exception;

	/* Montando uma Consulta Dinamica com SQL */
	Query obterQuery(String query) throws Exception;

	/* retorna a sessão do Hibernate */
	Session getSession() throws Exception;

}
