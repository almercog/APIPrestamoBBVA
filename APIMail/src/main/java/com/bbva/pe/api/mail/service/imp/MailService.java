package com.bbva.pe.api.mail.service.imp;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.bbva.pe.api.mail.model.PrestEmail;
import com.bbva.pe.api.mail.service.IMailService;

import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
@Service("mailService")
public class MailService implements IMailService {

	Logger logger = Logger.getLogger(MailService.class.getName());

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	Configuration freemarkerConfiguration;

	@Override
	public void sendEmail(Object object) {
		PrestEmail prestEmail = (PrestEmail) object;
		MimeMessagePreparator preparator = getMessagePreparator(prestEmail);
		try {
			mailSender.send(preparator);
			logger.info("Message has been sent.............................");
		} catch (MailException ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void sendEmailPrest(Object object) {
		PrestEmail prestEmail = (PrestEmail) object;
		String from = "giancarlo.almerco.rsr@viaexperis.pe";
		String subject = "Sistema de amortización Francés para simulación de Préstamos";
		MimeMessagePreparator preparator = getMessagePreparator(prestEmail, subject, from , prestEmail.getEmail());
		try {
			mailSender.send(preparator);
			logger.info("Message has been sent.............................");
		} catch (MailException ex) {
			logger.error(ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final PrestEmail prestEmail) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("Your prestEmail on Demoapp with Templates");
				helper.setFrom("giancarlo.almerco.rsr@viaexperis.pe");
				helper.setTo(prestEmail.getEmail());

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("prestEmail", prestEmail);

				String text = geFreeMarkerTemplateContent(model);
				logger.info("Template content : " + text);

				// use the true flag to indicate you need a multipart message
				helper.setText(text, true);

				// Additionally, let's add a resource as an attachment as well.
				helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));

			}
		};
		return preparator;
	}

	public MimeMessagePreparator getMessagePreparator(PrestEmail prest, String subject, String from, String to) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject(subject);
				helper.setFrom(from);
				helper.setTo(to);

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("datos", prest);

				String text = geVelocityTemplateContent(model);
				logger.info("Template content : " + text);

				// use the true flag to indicate you need a multipart message
				helper.setText(text, true);
			}
		};
		return preparator;
	}

	public String geVelocityTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
					"/vmtemplates/velocity_mailTemplate.vm", model));
			return content.toString();
		} catch (Exception e) {
			logger.error("Exception occured while processing velocity template:" + e.getMessage());
		}
		return "";
	}

	public String geFreeMarkerTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_mailTemplate.txt"), model));
			return content.toString();
		} catch (Exception e) {
			logger.error("Exception occured while processing fmtemplate:" + e.getMessage());
		}
		return "";
	}

}
