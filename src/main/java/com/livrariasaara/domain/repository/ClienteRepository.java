package com.livrariasaara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
