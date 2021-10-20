package com.livrariasaara.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoModel {

	private Long id;
	private BigDecimal valorTotal;
	private Long meioPagamentoId;
}
