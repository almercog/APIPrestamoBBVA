package com.bbva.pe.api.prestamo.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.bbva.pe.api.prestamo.commons.APIConstants;
import com.bbva.pe.api.prestamo.exceptions.ValidateException;
import com.bbva.pe.api.prestamo.service.IUtilsService;
import com.bbva.pe.api.prestamo.validate.Column;
import com.bbva.pe.api.prestamo.validate.Domain;

@Service
public class UtilsService implements IUtilsService {

	@Override
	public void validate(Object dto) throws Exception {
		Field[] properties = dto.getClass().getDeclaredFields();
		PropertyDescriptor property = null;
		Column column = null;
		Domain domain = null;
		Object value = null;

		for (Field field : properties) {
			column = field.getAnnotation(Column.class);
			if (column != null) {
				property = new PropertyDescriptor(field.getName(), dto.getClass());
				value = property.getReadMethod().invoke(dto);
				validateNull(field, value, column);
				validateLength(field, value, column);
				validateDate(field, value, column);
				validateTime(field, value, column);
				validateIp(field, value, column);
				validateTipoPersona(field, value, column);
				domain = field.getAnnotation(Domain.class);
				if (domain != null && !column.nullable()) {
					validateDomain(field, value, domain);
				}
			}
		}
	}

	private void validateDomain(Field field, Object value, Domain domain) throws ValidateException {
		if (value != null) {
			if (value instanceof String) {
				String[] values = domain.string();
				for (String str : values) {
					if (str.equals(value.toString())) {
						return;
					}
				}
				throw new ValidateException("El campo " + field.getName() + " no está definido en el dominio - String");
			}
			if (value instanceof Integer) {
				int[] values = domain.integer();
				for (int str : values) {
					if ((Integer) str == value) {
						return;
					}
				}
				throw new ValidateException(
						"El campo " + field.getName() + " no está definido en el dominio  - Integer");
			}
			if (value instanceof BigDecimal) {
				String[] values = domain.string();
				for (String str : values) {
					if ((new BigDecimal(str)).equals(value)) {
						return;
					}
				}
				throw new ValidateException(
						"El campo " + field.getName() + " no está definido en el dominio  - Big Decimal");
			}
			if (value instanceof BigInteger) {
				String[] values = domain.string();
				for (String str : values) {
					if ((new BigInteger(str)).equals(value)) {
						return;
					}
				}
				throw new ValidateException(
						"El campo " + field.getName() + " no está definido en el dominio  - Big Integer");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void validateNull(Field field, Object value, Column column) throws Exception {
		if (!column.nullable()) {
			if (value == null) {
				throw new ValidateException("El campo " + field.getName() + " no puede ser nulo");
			} else {
				if (value instanceof String) {
					String strValue = (String) value;
					strValue = strValue.trim();
					if (strValue.length() == 0) {
						throw new ValidateException("El campo " + field.getName() + " no puede estar vacío");
					}
				} else if (value instanceof List<?>) {
					if (((List<?>) value).isEmpty()) {
						throw new ValidateException("El campo " + field.getName() + " no puede estar vacío");
					} else {
						List<Object> lstObject = (List<Object>) value;
						for (Object dto : lstObject) {
							this.validate(dto);
						}
					}
				}
			}
		}
	}

	private void validateLength(Field field, Object value, Column column) throws ValidateException {
		if (column.length()) {
			if (value != null) {
				if (value instanceof String) {
					String strValue = (String) value;
					strValue = strValue.trim();
					if (strValue.length() < column.minlength() || strValue.length() > column.maxlength()) {
						throw new ValidateException(
								"El campo " + field.getName() + " debe tener " + column.maxlength() + " dígitos");
					}
				}
			}
		}
	}

	private void validateDate(Field field, Object value, Column column) throws ValidateException {
		if (column.date()) {
			if (value instanceof String) {
				String strValue = (String) value;
				strValue = strValue.trim();
				if (!column.nullable() || (column.nullable() && strValue.length() > 0)) {
					if (!validateFormatDate(strValue)) {
						throw new ValidateException("El campo " + field.getName()
								+ " debe ser una fecha válida y tener formato YYYY/MM/DD");
					}
				}
			}
		}
	}

	private void validateTime(Field field, Object value, Column column) throws ValidateException {
		if (column.time()) {
			if (value instanceof String) {
				String strValue = (String) value;
				strValue = strValue.trim();
				if (!column.nullable() || (column.nullable() && strValue.length() > 0)) {
					Pattern pattern = Pattern.compile(APIConstants.PATTERN_TIME);
					Matcher matcher = pattern.matcher(strValue);
					if (!matcher.matches()) {
						throw new ValidateException(
								"El campo " + field.getName() + " debe ser una hora válida y tener formato HH:MM:SS");
					}
				}
			}
		}
	}

	private void validateIp(Field field, Object value, Column column) throws Exception {
		if (column.ip()) {
			if (value instanceof String) {
				String strValue = (String) value;
				strValue = strValue.trim();
				if (!column.nullable() || (column.nullable() && strValue.length() > 0)) {
					Pattern pattern = Pattern.compile(APIConstants.PATTERN_IP);
					Matcher matcher = pattern.matcher(strValue);
					if (!matcher.matches()) {
						throw new ValidateException("El campo " + field.getName() + " debe ser un IP válido");
					}
				}
			}
		}
	}

	private void validateTipoPersona(Field field, Object value, Column column) throws Exception {
		if (column.tipopersona()) {
			if (value instanceof String) {
				String strValue = (String) value;
				strValue = strValue.trim();
				if (!column.nullable() || (column.nullable() && strValue.length() > 0)) {
					if (!strValue.equals(APIConstants.C_TIPPERS_PN)) {
						throw new ValidateException("El campo " + field.getName() + " debe ser Tipo Persona válido");
					}
				}
			}
		}
	}

	/**
	 * Validate date format with regular expression
	 * 
	 * @param date
	 *            date address for validation
	 * @return true valid date fromat, false invalid date format
	 */
	public boolean validateFormatDate(final String date) {
		Pattern pattern = Pattern.compile(APIConstants.PATTERN_DATE);
		Matcher matcher = pattern.matcher(date);
		if (matcher.matches()) {
			matcher.reset();
			if (matcher.find()) {
				String day = matcher.group(1);
				String month = matcher.group(2);
				int year = Integer.parseInt(matcher.group(3));
				if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9")
						|| month.equals("11") || month.equals("04") || month.equals("06") || month.equals("09"))) {
					return false; // only 1,3,5,7,8,10,12 has 31 days
				} else if (month.equals("2") || month.equals("02")) {
					if (year % 4 == 0) {
						if (day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					} else {
						if (day.equals("29") || day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
