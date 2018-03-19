package com.bbva.pe.api.prestamo.commons;

public class APIConstants {

	public static final String IND_SI = "S";
	public static final String IND_NO = "N";

	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CT_FORM = "application/x-www-form-urlencoded";
	public static final String HEADER_CT_FORM_UTF8 = HEADER_CT_FORM + ";charset=UTF-8";
	public static final String CT_CHARSET_UTF8 = ";charset=UTF-8";
	public static final String HEADER_CT_JSON = "application/json";
	public static final String HEADER_CT_JSON_UTF8 = HEADER_CT_JSON + CT_CHARSET_UTF8;

	public static final String CODE_OK = "0";
	public static final String CODE_ERROR = "1";
	public static final String CODE_VALIDATION = "2";

	public static final int API_COD_RESP_OK = 1;
	public static final int API_COD_RESP_ERROR = 0;
	public static final String API_DSC_RESP_OK = "OK";
	public static final String API_DSC_RESP_ERROR = "Error";

	public static final String CADENA_VACIA = "";
	public static final String SALTO_LINEA = "\n";

	public static final String PAR_MIN = "MIN";
	public static final String PAR_MAX = "MAX";
	public static final String PAR_TASA = "PAR_TASA";
	public static final String PAR_PERIODO = "PAR_PERIODO";
	public static final String PAR_PRESTAMO = "PAR_PRESTAMO";
	public static final String PAR_PERIODOGRACIA = "PAR_PERIODOGRACIA";

	public static final String PATTERN_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String PATTERN_YYYY_MM_DD = "yyyy/MM/dd";
	public static final String PATTERN_YYYY_MM_DD_AX = "yyyy-MM-dd";

	public static final int HTTP_STATUS_CODE_OK = 200;
	public static final int HTTP_STATUS_CODE_CREATED = 201;
	public static final int HTTP_STATUS_CODE_BAD_REQUEST = 400;
	public static final int HTTP_STATUS_CODE_NOT_FOUND = 404;
	public static final int HTTP_STATUS_CODE_INTERNAL_ERROR = 500;
	
	public static final String PATH_FILES = "C:\\Project\\Files\\prestamo-";
	public static final String EXT_FILES = ".pdf";

	public static final String PU_RETOBBVA = "retobbvaPU";
	public static final String PATTERN_TIME = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	public static final String PATTERN_DATE = "^((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])$";
	public static final String PATTERN_IP = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	public static final String PATTERN_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	public static final String C_TIPPERS_PN = "PN";
}
