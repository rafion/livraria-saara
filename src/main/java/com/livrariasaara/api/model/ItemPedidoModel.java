package com.livrariasaara.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemPedidoModel {

	private Long id;
	
	@NotNull
	protected Short item;
	@NotNull
	protected Long livroId;

	@PositiveOrZero
    @NotNull
	protected BigDecimal quantidade;

	@Positive
    @NotNull
	protected BigDecimal precoUnitario;
	
	@PositiveOrZero
	protected BigDecimal precoTotal;
}
