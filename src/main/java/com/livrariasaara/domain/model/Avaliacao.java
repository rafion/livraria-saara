package com.livrariasaara.domain.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int nota;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id", nullable = false)
	@NotNull
	private Livro livro;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id", nullable = false)
	@NotNull
	private Cliente cliente;
	
	
	
	
	

}
