package com.bbva.pe.api.mail.service;

public interface IMailService {

	/**
	 * 
	 * @param object
	 */
	void sendEmail(final Object object);

	/**
	 * 
	 * @param object
	 */
	void sendEmailPrest(Object object);

}
