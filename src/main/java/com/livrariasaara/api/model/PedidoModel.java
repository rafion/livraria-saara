package com.livrariasaara.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PedidoModel {
	
	private Long id;
	private BigDecimal valorTotal;
	
	private LocalDate data = LocalDate.now();
	
	@NotNull(message = "Id do cliente não pode ser null")
	private Integer clienteId;
	
	@NotNull(message = "O Pedido deve possuir pelo menos um Item")
	private List<ItemPedidoModel> itens = new ArrayList<ItemPedidoModel>();
	
	private String status;
	
	private PagamentoModel pagamento;

}
