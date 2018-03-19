package com.bbva.pe.api.prestamo.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.bbva.pe.api.prestamo.beans.ConstanteBean;
import com.bbva.pe.api.prestamo.beans.ParametroBean;
import com.bbva.pe.api.prestamo.dao.BaseJDBCDAO;
import com.bbva.pe.api.prestamo.dao.IComunDAO;
import com.bbva.pe.api.prestamo.domain.Constante;
import com.bbva.pe.api.prestamo.domain.Parametro;

import oracle.jdbc.OracleTypes;

@Repository("comunDAO")
public class ComunDAO extends BaseJDBCDAO implements IComunDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Constante> lstConstante(ConstanteBean param) {
		List<Constante> list = new ArrayList<Constante>();
		try {
			SimpleJdbcCall call = null;
			SqlParameterSource in = null;
			Map<String, Object> out = null;

			call = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("DBRETO").withCatalogName("PQ_IAA_BBVA")
					.withProcedureName("SP_LST_CONSTANTE").withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("a_idConstante", Types.VARCHAR),
							new SqlParameter("a_dscConctante", Types.VARCHAR), new SqlOutParameter("a_cursor",
									OracleTypes.CURSOR, new BeanPropertyRowMapper<Constante>(Constante.class)));

			in = new MapSqlParameterSource().addValue("a_idConstante", param.getIdConstante())
					.addValue("a_dscConctante", param.getDscConstante());

			out = call.execute(in);

			// Obteniendo los campos que se deben actualizar
			list = (List<Constante>) out.get("a_cursor");
			logger.info("Finalizando ejecución de método validaTercero");
		} catch (Exception e) {
			logger.error("[validaTercero]: Ocurrió un error en la consulta [SAS]: ", e.fillInStackTrace());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Constante getConstante(ConstanteBean param) {
		Constante constante = null;
		List<Constante> list = this.lstConstante(param);
		if (list != null && !list.isEmpty()) {
			constante = list.get(0);
		}
		return constante;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> lstParametro(ParametroBean param) {
		List<Parametro> list = new ArrayList<Parametro>();
		try {
			SimpleJdbcCall call = null;
			SqlParameterSource in = null;
			Map<String, Object> out = null;

			call = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("DBRETO").withCatalogName("PQ_IAA_BBVA")
					.withProcedureName("SP_LST_PARAMETRO").withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("a_idTipPar", Types.VARCHAR),
							new SqlParameter("a_abreviatura", Types.VARCHAR),
							new SqlParameter("a_descripcion", Types.VARCHAR), new SqlOutParameter("a_cursor",
									OracleTypes.CURSOR, new BeanPropertyRowMapper<Parametro>(Parametro.class)));

			in = new MapSqlParameterSource().addValue("a_idTipPar", param.getIdTipPar())
					.addValue("a_abreviatura", param.getAbreviatura())
					.addValue("a_descripcion", param.getDescripcion());

			out = call.execute(in);

			// Obteniendo los campos que se deben actualizar
			list = (List<Parametro>) out.get("a_cursor");
			logger.info("Finalizando ejecución de método validaTercero");
		} catch (Exception e) {
			logger.error("[validaTercero]: Ocurrió un error en la consulta [SAS]: ", e.fillInStackTrace());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Parametro getParametro(ParametroBean param) {
		Parametro par = null;
		List<Parametro> list = this.lstParametro(param);
		if (list != null && !list.isEmpty()) {
			par = list.get(0);
		}
		return par;
	}

	public Connection getConnectionOracle() throws SQLException {
		return getJdbcTemplate().getDataSource().getConnection();
	}
}
