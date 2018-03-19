package com.bbva.pe.api.mail.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.bbva.pe.api.mail.model.PrestEmail;
import com.bbva.pe.api.mail.service.IMailService;

public class SampleEmailApplication {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		IMailService orderService = (IMailService) context.getBean("mailService");
		orderService.sendEmailPrest(getDummyPrestamo());
		((AbstractApplicationContext) context).close();
	}

	public static PrestEmail getDummyPrestamo() {
		PrestEmail prest = new PrestEmail();
		prest.setPlazo("4");
		prest.setTipoPlazo("Meses");
		prest.setTasa("0.08");
		prest.setPrestamo("7,000,00.00");
		prest.setEmail("almercog@gmail.com");
		prest.setFecIniPrestamo("18/03/2018");
		prest.setFecFinPrestamo("18/07/2018");
		return prest;
	}

}
