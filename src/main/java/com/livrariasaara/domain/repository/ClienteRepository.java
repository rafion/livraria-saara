package com.livrariasaara.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Optional<Cliente> findByUsername(String username);
}
