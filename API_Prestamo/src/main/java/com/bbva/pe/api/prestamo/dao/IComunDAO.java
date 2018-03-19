package com.bbva.pe.api.prestamo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bbva.pe.api.prestamo.beans.ConstanteBean;
import com.bbva.pe.api.prestamo.beans.ParametroBean;
import com.bbva.pe.api.prestamo.domain.Constante;
import com.bbva.pe.api.prestamo.domain.Parametro;

public interface IComunDAO {
	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Constante> lstConstante(ConstanteBean param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	Constante getConstante(ConstanteBean param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Parametro> lstParametro(ParametroBean param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	Parametro getParametro(ParametroBean param);

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	Connection getConnectionOracle() throws SQLException;

}
