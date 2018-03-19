package com.bbva.pe.api.prestamo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.bbva.pe.api.prestamo.client.request.ReqEmail;
import com.bbva.pe.api.prestamo.client.request.ReqPrestamo;
import com.bbva.pe.api.prestamo.client.response.ResEmail;
import com.bbva.pe.api.prestamo.client.response.ResPrestamo;
import com.bbva.pe.api.prestamo.service.IPrestamoService;

@Component
@Path("/api")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class PrestamoREST {

	private static final Logger logger = Logger.getLogger(PrestamoREST.class);

	@Autowired
	private IPrestamoService prestamoService;

	@POST
	@Path("/prestamos")
	public Response getPrestamo(@RequestBody ReqPrestamo req) throws Exception {
		logger.info("[INI] ::getPrestamo:: " + req.toString());
		ResPrestamo res = prestamoService.getPrestamo(req);
		return Response.ok(res).build();
	}

	@POST
	@Path("/email")
	public Response sendEmail(@RequestBody ReqEmail req) throws Exception {
		logger.info("[INI] ::sendEmail:: " + req.toString());
		ResEmail res = prestamoService.sendEmail(req);
		return Response.ok(res).build();
	}

}
