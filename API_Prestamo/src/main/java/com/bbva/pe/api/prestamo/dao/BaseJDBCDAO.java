/*
 * Created on 15/05/2012
 * Copyright (c) Rimac Seguros S.A.
 */
package com.bbva.pe.api.prestamo.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 
 * @author Rimac Seguros - Hilmer Aliaga
 */
public abstract class BaseJDBCDAO extends JdbcDaoSupport {

	@Autowired
	public void loadDatasource(DataSource dataSource) {
		setDataSource(dataSource);
	}
}
