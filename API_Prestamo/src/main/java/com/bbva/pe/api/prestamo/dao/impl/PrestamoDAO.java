package com.bbva.pe.api.prestamo.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.bbva.pe.api.prestamo.dao.BaseJDBCDAO;
import com.bbva.pe.api.prestamo.dao.IPrestamoDAO;
import com.bbva.pe.api.prestamo.domain.Prestamo;
import com.bbva.pe.api.prestamo.domain.PrestamoDet;

import oracle.jdbc.OracleTypes;

@Repository("prestamoDAO")
public class PrestamoDAO extends BaseJDBCDAO implements IPrestamoDAO {

	@Override
	public void saveUpdatePrestamo(Prestamo param) {
		try {
			SimpleJdbcCall call = null;
			SqlParameterSource in = null;
			Map<String, Object> out = null;

			call = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("DBRETO").withCatalogName("PQ_IAA_BBVA")
					.withProcedureName("SP_MNT_PRESTAMO").withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlInOutParameter("a_idPrestamo", Types.NUMERIC),
							new SqlParameter("a_prestamo", Types.DECIMAL), new SqlParameter("a_tasa", Types.DECIMAL),
							new SqlParameter("a_plazo", Types.NUMERIC), new SqlParameter("a_tipoPlazo", Types.VARCHAR),
							new SqlParameter("a_fecDesembolso", Types.DATE),
							new SqlParameter("a_fecIniPrestamo", Types.DATE),
							new SqlParameter("a_fecFinPrestamo", Types.DATE),
							new SqlParameter("a_indPeriodoGracia", Types.VARCHAR),
							new SqlParameter("a_periodoGracia", Types.NUMERIC),
							new SqlParameter("a_indCapInteres", Types.VARCHAR),
							new SqlParameter("a_email", Types.VARCHAR), new SqlParameter("a_url", Types.VARCHAR),
							new SqlParameter("a_origentrans", Types.VARCHAR),
							new SqlParameter("a_usuario", Types.VARCHAR));

			in = new MapSqlParameterSource().addValue("a_idPrestamo", param.getIdPrestamo())
					.addValue("a_prestamo", param.getPrestamo()).addValue("a_tasa", param.getTasa())
					.addValue("a_plazo", param.getPlazo()).addValue("a_tipoPlazo", param.getTipoPlazo())
					.addValue("a_fecDesembolso", param.getFecDesembolso())
					.addValue("a_fecIniPrestamo", param.getFecIniPrestamo())
					.addValue("a_fecFinPrestamo", param.getFecFinPrestamo())
					.addValue("a_indPeriodoGracia", param.getIndPeriodoGracia())
					.addValue("a_periodoGracia", param.getPeriodoGracia())
					.addValue("a_indCapInteres", param.getIndCapInteres()).addValue("a_email", param.getEmail())
					.addValue("a_url", param.getUrl()).addValue("a_origentrans", param.getOrigenTrans())
					.addValue("a_usuario", param.getUsuCreacion());
			out = call.execute(in);
			BigDecimal idPrestamo = (BigDecimal) out.get("a_idPrestamo");
			param.setIdPrestamo(idPrestamo);
			logger.info("Finalizando ejecución de método validaTercero");
		} catch (Exception e) {
			logger.error("[validaTercero]: Ocurrió un error en la consulta [SAS]: ", e.fillInStackTrace());
			e.printStackTrace();
		}
	}

	@Override
	public void saveUpdatePrestamoDet(PrestamoDet param) {
		try {
			SimpleJdbcCall call = null;
			SqlParameterSource in = null;
			Map<String, Object> out = null;

			call = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("DBRETO").withCatalogName("PQ_IAA_BBVA")
					.withProcedureName("SP_MNT_PRESTAMODET").withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlInOutParameter("a_idPrestamoDet", Types.NUMERIC),
							new SqlParameter("a_idPrestamo", Types.NUMERIC),
							new SqlParameter("a_fecVencPagoCuota", Types.DATE),
							new SqlParameter("a_periodo", Types.NUMERIC), new SqlParameter("a_cuota", Types.VARCHAR),
							new SqlParameter("a_interes", Types.VARCHAR),
							new SqlParameter("a_amortizacion", Types.NUMERIC),
							new SqlParameter("a_totalAmortizado", Types.VARCHAR),
							new SqlParameter("a_capitalPendiente", Types.VARCHAR),
							new SqlParameter("a_usuario", Types.VARCHAR));

			in = new MapSqlParameterSource().addValue("a_idPrestamoDet", param.getIdPrestamoDet())
					.addValue("a_idPrestamo", param.getIdPrestamo())
					.addValue("a_fecVencPagoCuota", param.getFecVencPagoCuota())
					.addValue("a_periodo", param.getPeriodo()).addValue("a_cuota", param.getCuota())
					.addValue("a_interes", param.getInteres()).addValue("a_amortizacion", param.getAmortizacion())
					.addValue("a_totalAmortizado", param.getTotalAmortizado())
					.addValue("a_capitalPendiente", param.getCapitalPendiente())
					.addValue("a_usuario", param.getUsuCreacion());
			out = call.execute(in);
			BigDecimal idPrestamo = (BigDecimal) out.get("a_idPrestamoDet");
			param.setIdPrestamo(idPrestamo);
			logger.info("Finalizando ejecución de método validaTercero");
		} catch (Exception e) {
			logger.error("[validaTercero]: Ocurrió un error en la consulta [SAS]: ", e.fillInStackTrace());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Prestamo getPrestamo(Prestamo param) {
		Prestamo prestamo = null;
		try {
			List<Prestamo> list = new ArrayList<Prestamo>();
			SimpleJdbcCall call = null;
			SqlParameterSource in = null;
			Map<String, Object> out = null;

			call = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("DBRETO").withCatalogName("PQ_IAA_BBVA")
					.withProcedureName("SP_GET_PRESTAMO").withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("a_idPrestamo", Types.VARCHAR), new SqlOutParameter("a_cursor",
							OracleTypes.CURSOR, new BeanPropertyRowMapper<Prestamo>(Prestamo.class)));

			in = new MapSqlParameterSource().addValue("a_idPrestamo", param.getIdPrestamo());

			out = call.execute(in);
			// Obteniendo los campos que se deben actualizar
			list = (List<Prestamo>) out.get("a_cursor");
			if (list != null && !list.isEmpty()) {
				prestamo = list.get(0);
			}
			logger.info("Finalizando ejecución de método validaTercero");
		} catch (Exception e) {
			logger.error("[validaTercero]: Ocurrió un error en la consulta [SAS]: ", e.fillInStackTrace());
			e.printStackTrace();
		}
		return prestamo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrestamoDet> lstPrestamo(Prestamo param) {
		List<PrestamoDet> list = new ArrayList<PrestamoDet>();
		try {
			SimpleJdbcCall call = null;
			SqlParameterSource in = null;
			Map<String, Object> out = null;

			call = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("DBRETO").withCatalogName("PQ_IAA_BBVA")
					.withProcedureName("SP_LST_PRESTAMODET").withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("a_idPrestamo", Types.VARCHAR), new SqlOutParameter("a_cursor",
							OracleTypes.CURSOR, new BeanPropertyRowMapper<PrestamoDet>(PrestamoDet.class)));

			in = new MapSqlParameterSource().addValue("a_idPrestamo", param.getIdPrestamo());

			out = call.execute(in);
			// Obteniendo los campos que se deben actualizar
			list = (List<PrestamoDet>) out.get("a_cursor");
			logger.info("Finalizando ejecución de método validaTercero");
		} catch (Exception e) {
			logger.error("[validaTercero]: Ocurrió un error en la consulta [SAS]: ", e.fillInStackTrace());
			e.printStackTrace();
		}
		return list;
	}
}
