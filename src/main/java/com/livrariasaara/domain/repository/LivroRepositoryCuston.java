package com.livrariasaara.domain.repository;

import java.util.List;

import com.livrariasaara.domain.model.Livro;
import com.livrariasaara.domain.repository.filter.LivroFilter;


public interface LivroRepositoryCuston {

	List<Livro> filtrar(LivroFilter filter);
}
