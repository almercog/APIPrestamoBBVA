package com.bbva.pe.api.prestamo.validate;

public @interface Domain {

	String[] string() default {};

	int[] integer() default {};
}