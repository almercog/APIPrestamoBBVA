package com.bbva.pe.api.prestamo.dao;

import java.util.List;

import com.bbva.pe.api.prestamo.domain.Prestamo;
import com.bbva.pe.api.prestamo.domain.PrestamoDet;

public interface IPrestamoDAO {

	void saveUpdatePrestamo(Prestamo par);

	void saveUpdatePrestamoDet(PrestamoDet par);

	Prestamo getPrestamo(Prestamo par);

	List<PrestamoDet> lstPrestamo(Prestamo par);
}
