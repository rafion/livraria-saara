package com.livrariasaara.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // vai ser criado uma tabela unica para usuario, administrador, cliente
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING) //tipo de entidade na tabela "CLIENTE", "ADMINISTRADOR"
public abstract class Usuario implements Serializable{
	
	/*
	 * a classe é abstrata por que nunca vai ser instanciado um usuario, sim um cliente ou administrador
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	protected Integer id;
	
	@NotNull
	@NotEmpty(message = "campo username obrigatorio")
	@Column(unique = true, name = "username")
	protected String username;
	
	@NotNull
	@NotEmpty(message = "campo password obrigatorio")
	@Column(name = "password")
	protected String password;

}
