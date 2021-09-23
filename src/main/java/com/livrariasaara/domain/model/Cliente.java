package com.livrariasaara.domain.model;

import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("CLIENTE") //tipo que vai ser gravado na tabela usuario
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Usuario implements Serializable{

	
	private static final long serialVersionUID = 1L;


	@Column(length = 100)
	@NotBlank(message = "Nome deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 100 letras")
	protected String nome;
	
	
	@Column(name = "cpf")
	@NotBlank(message="CPF é obrigatorio")
	@CPF
	protected String cpf;
	
	@OneToOne( cascade = CascadeType.PERSIST )
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
}
