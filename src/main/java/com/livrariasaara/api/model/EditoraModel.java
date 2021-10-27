package com.livrariasaara.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditoraModel {

	private Long id;
	private String nome;
	private List<LivroModel> livros;
}
