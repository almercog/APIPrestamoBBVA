package com.bbva.pe.api.prestamo.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author almercog
 * @since 14.03.2018
 *
 */
public class CommonsHelper {

	public static String formatDateToString(Date date) {
		return formatDateToString(null, date);
	}

	/**
	 * Metodo que formatea la Fecha de tipo Date, segun el pattern enviado, en caso
	 * no se envie el pattern el valor por defecto yyyy/MM/dd, retorna la fecha en
	 * String
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formatDateToString(String pattern, Date date) {
		if (pattern == null) {
			pattern = APIConstants.PATTERN_YYYY_MM_DD;
		}
		String dateformatted;
		try {
			SimpleDateFormat sdformat = new SimpleDateFormat(pattern);
			dateformatted = sdformat.format(date);
		} catch (Exception e) {
			dateformatted = "";
		}
		return dateformatted;
	}

	public static Date formatStringToDate(String key) {
		return formatStringToDate(key, null);
	}

	/**
	 * Metodo que convierte una fecha de String a un Date, segun el formato enviado
	 * 
	 * @param key
	 * @param pattern
	 * @return
	 */
	public static Date formatStringToDate(String key, String pattern) {
		if (pattern == null) {
			pattern = APIConstants.PATTERN_YYYY_MM_DD;
		}
		String fecha = key;
		Date dFecha = null;
		if (fecha != null && fecha.length() == 10) {
			try {
				long lTime = new SimpleDateFormat(pattern).parse(fecha).getTime();
				dFecha = new Date(lTime);
			} catch (Exception e) {
				return null;
			}
		}
		return dFecha;
	}

	public static Date getParameterDate(String key) {
		String fecha = key;
		Date dFecha = null;
		if (fecha != null && fecha.length() == 10) {
			try {
				long lTime = new SimpleDateFormat(APIConstants.PATTERN_DD_MM_YYYY).parse(fecha).getTime();
				dFecha = new Date(lTime);
			} catch (Exception e) {
				return null;
			}
		}
		return dFecha;
	}

	public static String getMessageORA(String message) {
		String messageORA = "";
		try {
			int indexOf = message.indexOf("ORA-") + 10;
			String msgParcial = message.substring(indexOf);
			messageORA = msgParcial.substring(0,
					(msgParcial.indexOf(APIConstants.SALTO_LINEA) == -1 ? msgParcial.length()
							: msgParcial.indexOf(APIConstants.SALTO_LINEA)));
		} catch (Exception e) {
			return null;
		}
		return messageORA;
	}

}
