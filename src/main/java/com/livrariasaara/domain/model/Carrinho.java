package com.livrariasaara.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("CARRINHO")
@EqualsAndHashCode(callSuper = true)
public class Carrinho extends Pedido {
	
	private static final long serialVersionUID = 1L;
	
	
}
