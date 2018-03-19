package com.bbva.pe.api.prestamo.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bbva.pe.api.mail.model.PrestEmail;
import com.bbva.pe.api.mail.service.IMailService;
import com.bbva.pe.api.prestamo.commons.APIConstants;
import com.bbva.pe.api.prestamo.commons.CommonsHelper;
import com.bbva.pe.api.prestamo.beans.ErrorBean;
import com.bbva.pe.api.prestamo.beans.ParametroBean;
import com.bbva.pe.api.prestamo.client.request.ReqEmail;
import com.bbva.pe.api.prestamo.client.request.ReqPrestamo;
import com.bbva.pe.api.prestamo.client.response.ResEmail;
import com.bbva.pe.api.prestamo.client.response.ResPrestamo;
import com.bbva.pe.api.prestamo.dao.IComunDAO;
import com.bbva.pe.api.prestamo.dao.IPrestamoDAO;
import com.bbva.pe.api.prestamo.domain.Parametro;
import com.bbva.pe.api.prestamo.domain.Prestamo;
import com.bbva.pe.api.prestamo.domain.PrestamoDet;
import com.bbva.pe.api.prestamo.exceptions.ValidateException;
import com.bbva.pe.api.prestamo.service.IPrestamoService;
import com.bbva.pe.api.prestamo.service.IUtilsService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service("prestamoService")
@Transactional(propagation = Propagation.SUPPORTS)
public class PrestamoService implements IPrestamoService {

	@Autowired
	private IPrestamoDAO prestamoDAO;

	@Autowired
	private IComunDAO comunDAO;

	@Autowired
	private IMailService mailService;

	@Autowired
	private IUtilsService utilsService;

	public ResPrestamo getPrestamo(ReqPrestamo req) {
		ResPrestamo res = new ResPrestamo();
		try {
			utilsService.validate(req);

			Prestamo prest = new Prestamo();
			if (req.getIdPrestamo() != null) {
				prest.setIdPrestamo(new BigDecimal(req.getIdPrestamo()));
				// Prestamo prestamoOld = prestamoDAO.getPrestamo(prest);
			}

			int scale = 4;
			MathContext mc = new MathContext(8);
			BigDecimal prestamo = new BigDecimal(req.getPrestamo());
			BigDecimal tasa = new BigDecimal(req.getTasa());
			Integer periodo = Integer.valueOf(req.getPlazo());
			String tipoPlazo = req.getTipoPlazo();
			DateTime fchHoy = new DateTime(new Date());
			DateTime fchDesembolso = new DateTime(
					CommonsHelper.formatStringToDate(APIConstants.PATTERN_DD_MM_YYYY, req.getFecDesembolso()));
			Integer periodoGracia = 0;
			if (fchDesembolso.compareTo(fchHoy) > 0) {
				throw new ValidateException("La fecha de Desembolso no debe ser menor a la fecha actual.");
			}

			// Validacion de Rango de Prestamo
			BigDecimal temp = null;
			ParametroBean parBean = new ParametroBean();
			parBean.setIdTipPar(APIConstants.PAR_PRESTAMO);
			List<Parametro> lstPar = comunDAO.lstParametro(parBean);
			for (Parametro par : lstPar) {
				if (par.getAbreviatura().equals(APIConstants.PAR_MIN)) {
					temp = par.getCodigoN();
					if (!(temp.compareTo(prestamo) <= 0)) {
						throw new ValidateException("El campo Prestamo es menor al configurado.");
					}
				} else if (par.getAbreviatura().equals(APIConstants.PAR_MAX)) {
					temp = par.getCodigoN();
					if (!(temp.compareTo(prestamo) >= 0)) {
						throw new ValidateException("El campo Prestamo es mayor al configurado.");
					}
				}
			}

			// Validacion de Rango de Tasa
			parBean = new ParametroBean();
			parBean.setIdTipPar(APIConstants.PAR_TASA);
			lstPar = comunDAO.lstParametro(parBean);
			for (Parametro par : lstPar) {
				if (par.getAbreviatura().equals(APIConstants.PAR_MIN)) {
					temp = par.getCodigoN();
					if (!(temp.compareTo(tasa) <= 0)) {
						throw new ValidateException("El campo Tasa es menor al configurado.");
					}
				} else if (par.getAbreviatura().equals(APIConstants.PAR_MAX)) {
					temp = par.getCodigoN();
					if (!(temp.compareTo(tasa) >= 0)) {
						throw new ValidateException("El campo Tasa es mayor al configurado.");
					}
				}
			}

			// Validacion de Rango de Periodo
			parBean = new ParametroBean();
			parBean.setIdTipPar(APIConstants.PAR_PERIODO);
			lstPar = comunDAO.lstParametro(parBean);
			for (Parametro par : lstPar) {
				if (par.getAbreviatura().equals(APIConstants.PAR_MIN)) {
					temp = par.getCodigoN();
					if (temp.intValue() >= periodo) {
						throw new ValidateException("El campo Periodo es menor al configurado.");
					}
				} else if (par.getAbreviatura().equals(APIConstants.PAR_MAX)) {
					temp = par.getCodigoN();
					if (temp.intValue() <= periodo) {
						throw new ValidateException("El campo Periodo es mayor al configurado.");
					}
				}
			}

			// Validacion de Rango de Periodo
			if (req.getIndPeriodoGracia() != null && req.getIndPeriodoGracia().equals("S")) {
				periodoGracia = Integer.valueOf(req.getPeriodoGracia());
				parBean = new ParametroBean();
				parBean.setIdTipPar(APIConstants.PAR_PERIODOGRACIA);
				lstPar = comunDAO.lstParametro(parBean);
				for (Parametro par : lstPar) {
					if (par.getAbreviatura().equals(APIConstants.PAR_MIN)) {
						temp = par.getCodigoN();
						if (temp.intValue() >= periodoGracia) {
							throw new ValidateException("El campo Periodo de Gracia es menor al configurado.");
						}
					} else if (par.getAbreviatura().equals(APIConstants.PAR_MAX)) {
						temp = par.getCodigoN();
						if (temp.intValue() <= periodoGracia) {
							throw new ValidateException("El campo Periodo de Gracia es mayor al configurado.");
						}
					}
				}
			}

			DateTime fchIniVig = fchDesembolso;
			DateTime fchFinVig = fchIniVig.plusYears(0);
			if (tipoPlazo.equals("M")) {
				fchFinVig = fchIniVig.plusMonths(periodo);
			} else if (tipoPlazo.equals("A")) {
				fchFinVig = fchIniVig.plusYears(periodo);
			}
			prest.setPrestamo(prestamo);
			prest.setTasa(tasa);
			prest.setPlazo(periodo);
			prest.setTipoPlazo(tipoPlazo);
			prest.setPeriodoGracia(periodoGracia);
			prest.setFecDesembolso(fchDesembolso.toDate());
			prest.setIndPeriodoGracia(req.getIndPeriodoGracia());
			prest.setIndCapInteres(req.getIndCapitalInteres());
			prest.setFecIniPrestamo(fchIniVig.toDate());
			prest.setFecFinPrestamo(fchFinVig.toDate());
			prestamoDAO.saveUpdatePrestamo(prest);

			BigDecimal uno = new BigDecimal("1");
			BigDecimal interes;
			BigDecimal amortizacion;
			BigDecimal capitalPendiente = prestamo;
			BigDecimal totalAmortizado = new BigDecimal("0");

			// Fecha de Vencimiento Cuota
			DateTime fecVencPagoCuota = fchIniVig;
			// Tasa en Porcentaje
			tasa = tasa.divide(new BigDecimal("100"));
			BigDecimal calCuota = tasa.add(uno);
			calCuota = calCuota.pow(-periodo, mc);
			calCuota = uno.subtract(calCuota);
			calCuota = calCuota.divide(tasa, 8, RoundingMode.HALF_EVEN);
			BigDecimal cuota = prestamo.divide(calCuota, scale, RoundingMode.CEILING);
			List<PrestamoDet> lstPrestamoDet = new LinkedList<PrestamoDet>();

			for (int n = 0; n <= periodo + periodoGracia; n++) {
				PrestamoDet prestDet = new PrestamoDet();
				prestDet.setIdPrestamo(prest.getIdPrestamo());
				prestDet.setPeriodo(n);
				if (tipoPlazo.equals("M")) {
					fecVencPagoCuota = fecVencPagoCuota.plusMonths(periodo);
				} else if (tipoPlazo.equals("A")) {
					fecVencPagoCuota = fecVencPagoCuota.plusYears(periodo);
				}
				prestDet.setFecVencPagoCuota(fecVencPagoCuota.toDate());
				if (n > 0 && n > periodoGracia) {
					interes = capitalPendiente.multiply(tasa, new MathContext(8, RoundingMode.HALF_EVEN));
					amortizacion = cuota.subtract(interes);
					totalAmortizado = totalAmortizado.add(amortizacion);
					capitalPendiente = capitalPendiente.subtract(amortizacion);
					prestDet.setCuota(cuota);
					prestDet.setInteres(interes);
					prestDet.setAmortizacion(amortizacion);
					prestDet.setTotalAmortizado(totalAmortizado);
					prestDet.setCapitalPendiente(capitalPendiente);
				}
				prestDet.setCapitalPendiente(capitalPendiente);
				// Guaradar Detalle del Prestamo
				prestamoDAO.saveUpdatePrestamoDet(prestDet);
				lstPrestamoDet.add(prestDet);
			}

			// Generar PDF
			prest.setUrl(this.getUrl(prest.getIdPrestamo()));
			prestamoDAO.saveUpdatePrestamo(prest);

			res.setCodMensaje(APIConstants.API_COD_RESP_OK);
			res.setDscMensaje(APIConstants.API_DSC_RESP_OK);
			res.setPrestamo(prest);
			res.setLstPrestamoDet(lstPrestamoDet);
		} catch (ValidateException e) {
			e.printStackTrace();
			res.setCodMensaje(APIConstants.API_COD_RESP_ERROR);
			res.setDscMensaje(APIConstants.API_DSC_RESP_ERROR);
			ErrorBean error = new ErrorBean();
			error.setCodigo(APIConstants.API_COD_RESP_ERROR);
			error.setMensajeDev(e.getMessage());
			error.setMensajeUx("Ocurrrio un error al validar Prestamo");
			res.setError(error);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCodMensaje(APIConstants.API_COD_RESP_ERROR);
			res.setDscMensaje(APIConstants.API_DSC_RESP_ERROR);
			ErrorBean error = new ErrorBean();
			error.setCodigo(APIConstants.API_COD_RESP_ERROR);
			error.setMensajeDev(e.getMessage());
			error.setMensajeUx("Ocurrrio un error al calcular Prestamo");
			res.setError(error);
		}

		return res;
	}

	public String getUrl(BigDecimal id) {
		String pdfFileName = APIConstants.PATH_FILES + id + APIConstants.EXT_FILES;

		String path = this.getClass().getClassLoader().getResource("jasper").getPath();
		try {
			Connection conn = comunDAO.getConnectionOracle();
			Map<String, Object> hm = new HashMap<String, Object>();
			hm.put("IDPRESTAMO", id);
			hm.put("URL_LOGO", path + File.separator);
			// Generate jasper print
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(path + File.separator + "reporte.jasper",
					hm, conn);
			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pdfFileName;
	}

	@Override
	public ResEmail sendEmail(ReqEmail req) {
		ResEmail res = new ResEmail();
		try {
			utilsService.validate(req);

			Prestamo prest = new Prestamo();
			prest.setIdPrestamo(new BigDecimal(req.getIdPrestamo()));
			Prestamo prestamo = prestamoDAO.getPrestamo(prest);
			prestamo.setEmail(req.getEmail());
			// Actualizar Prestamo
			prestamoDAO.saveUpdatePrestamo(prestamo);

			// Enviar Email al Cliente
			String tipoPlazo = null;
			if (prestamo.getTipoPlazo().equals("M")) {
				tipoPlazo = "Meses";
			} else {
				tipoPlazo = "Años";
			}
			PrestEmail prestEmail = new PrestEmail();
			prestEmail.setPlazo(prestamo.getPlazo().toString());
			prestEmail.setTipoPlazo(tipoPlazo);
			prestEmail.setTasa(prestamo.getTasa().toString());
			prestEmail.setPrestamo(prestamo.getPrestamo().toString());
			prestEmail.setEmail(prestamo.getEmail());
			prestEmail.setFecIniPrestamo(CommonsHelper.formatDateToString(prestamo.getFecIniPrestamo()));
			prestEmail.setFecFinPrestamo(CommonsHelper.formatDateToString(prestamo.getFecFinPrestamo()));
			prestEmail.setUrl(prestamo.getUrl());
			mailService.sendEmailPrest(prestEmail);

			res.setCodMensaje(APIConstants.API_COD_RESP_OK);
			res.setDscMensaje(APIConstants.API_DSC_RESP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCodMensaje(APIConstants.API_COD_RESP_ERROR);
			res.setDscMensaje(APIConstants.API_DSC_RESP_ERROR);
			ErrorBean error = new ErrorBean();
			error.setCodigo(APIConstants.API_COD_RESP_ERROR);
			error.setMensajeDev(e.getMessage());
			error.setMensajeUx("Ocurrrio un error al enviar email");
			;
			res.setError(error);
		}

		return res;
	}

}
