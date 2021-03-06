package com.livrariasaara.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class LivroModel {

	private Long id;
	private String titulo;
	private String isbn;
	
	@JsonProperty("autor")
	private String autorNome;
	
	@JsonProperty("editora")
	private String editoraNome;
	
	private BigDecimal precoUnitario;
	
	@JsonProperty("url_img")
	private String urlImage;
	private boolean disponivel;
	
}
