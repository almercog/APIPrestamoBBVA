package com.bbva.pe.api.prestamo.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	boolean nullable() default true;

	boolean length() default false;

	int minlength() default 0;

	int maxlength() default 0;

	boolean date() default false;

	boolean time() default false;

	boolean ip() default false;

	boolean tipodoc() default false;

	boolean tipopersona() default false;

	boolean num() default false;

	boolean email() default false;

	boolean alphanum() default false;

}