package com.livrariasaara.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrinhoModel {
	
	private Long id;
	private BigDecimal valorTotal;
	
	@NotNull(message = "Id do cliente não pode ser null")
	private Integer clienteId;
	
	@NotNull(message = "O Pedido deve possuir pelo menos um Item")
	private List<ItemPedidoModel> itens = new ArrayList<ItemPedidoModel>();
	
	private String status;
	private PagamentoModel pagamento;

}