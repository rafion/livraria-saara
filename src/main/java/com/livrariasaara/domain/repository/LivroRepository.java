package com.livrariasaara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>, LivroRepositoryCuston {

}
