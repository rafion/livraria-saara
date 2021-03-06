package com.livrariasaara.api.model.input;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroInput {

	private Long id;
	private String titulo;
	private String isbn;
	private Long autorId;
	private Long editoraId;
	private BigDecimal precoUnitario;
	
	@JsonProperty("url_img")
	private String urlImage;
	private boolean disponivel;
	
}
