package com.livrariasaara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
