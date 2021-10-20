package com.livrariasaara.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PositiveOrZero
	@Column(columnDefinition = "NUMERIC(10,2) default 0")
	private BigDecimal valorTotal;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "meio_pagamento_id", referencedColumnName="id", nullable = false)
	private MeioPagamento meioPagamento;
	
}
