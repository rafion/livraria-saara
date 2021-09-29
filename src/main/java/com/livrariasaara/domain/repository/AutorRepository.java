package com.livrariasaara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
