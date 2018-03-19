package com.bbva.pe.api.prestamo.service;

public interface IUtilsService {

	/**
	 * Metodo que valida los campos enviados desde el Cliente
	 * 
	 * @param dto
	 * @throws Exception
	 */
	void validate(Object dto) throws Exception;

}
