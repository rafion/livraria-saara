package com.livrariasaara.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PEDIDO", discriminatorType = DiscriminatorType.STRING) 
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "NUMERIC(10,2) default 0")
	private BigDecimal valorTotal;
	
	protected LocalDate data = LocalDate.now();
	
	@JoinColumn(referencedColumnName = "id", nullable = false)
	@NotNull(message = "Id do cliente não pode ser null")
	@ManyToOne
	private Cliente cliente;
	
	@NotNull(message = "O Pedido deve possuir pelo menos um item")
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("item ASC")
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.ABERTO;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Pagamento pagamento;
	
	
	
}
