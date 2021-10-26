package com.livrariasaara.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Livro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Titulo não pode ser vazio")
	@Size(min = 3, max = 80)
	private String titulo;
	
	private String isbn;
	
	private String urlImage; 
	
	private BigDecimal precoUnitario;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id", nullable = false)
	private Autor autor;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id", nullable = false)
	private Editora editora;
	
}
