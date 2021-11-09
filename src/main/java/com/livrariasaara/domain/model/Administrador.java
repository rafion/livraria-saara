package com.livrariasaara.domain.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("ADM") //tipo que vai ser gravado na tabela usuario
@EqualsAndHashCode(callSuper = true)
public class Administrador extends Usuario  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean acessoEspecial;

}
