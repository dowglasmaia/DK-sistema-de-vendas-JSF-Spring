package com.maia.impl.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Class para auxiliar nas pesquisa de forma Generica
 * */

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {
	
	String descricaoCampo(); /* Descrição do Campo para a Tela*/
	
	String campoConsulta(); /* Campo do Banco de Dados*/
	
	int principal() default 1000; /* Posição default que aparece no Combo de Opções*/

}
