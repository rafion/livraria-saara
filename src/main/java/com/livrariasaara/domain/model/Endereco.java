package com.livrariasaara.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;

@Entity
@Data
public class Endereco implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 8)
	private String cep;

	@Column(length = 100)
	@NotBlank(message = "logradouro deve ser preenchido")
	@Size(min = 2, max = 100, message = "logradouro deve ter entre 2 e 100 letras")
	private String logradouro;

	@Digits(integer = 5, fraction = 0, message = "Número deve ser preenchido com um valor inteiro")
	private Integer numero;

	@Column(length = 50)
	private String complemento;

	@Column(length = 100)
	private String municipio;

	@Column(length = 100)
	private String bairro;
	
	@Column(length = 50)
	private String estado;
	
	
}
