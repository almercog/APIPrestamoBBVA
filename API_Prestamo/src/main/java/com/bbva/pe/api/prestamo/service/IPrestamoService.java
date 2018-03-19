package com.bbva.pe.api.prestamo.service;

import com.bbva.pe.api.prestamo.client.request.ReqEmail;
import com.bbva.pe.api.prestamo.client.request.ReqPrestamo;
import com.bbva.pe.api.prestamo.client.response.ResEmail;
import com.bbva.pe.api.prestamo.client.response.ResPrestamo;

public interface IPrestamoService {

	/**
	 * Metodo para obtener la Simulacion del Prestamo
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	ResPrestamo getPrestamo(ReqPrestamo req);

	/**
	 * Metodo para enviar email
	 * 
	 * @param req
	 * @return
	 */
	ResEmail sendEmail(ReqEmail req);
}
