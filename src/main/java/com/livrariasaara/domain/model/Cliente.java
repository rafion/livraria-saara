package com.livrariasaara.domain.model;

import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Data
public class Cliente implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer id;

	@Column(length = 100)
	@NotBlank(message = "Nome deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 100 letras")
	protected String nome;
	
	
	@Column(name = "cpf_cnpj")
	@NotBlank(message="CPF é obrigatorio")
	@CPF
	protected String cpfCnpj;
}
