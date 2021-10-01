package com.livrariasaara.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroModel {

	private Long id;
	private String titulo;
	private String isbn;
	private Long autorId;
	private Long editoraId;
}
