package com.livrariasaara.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findAllByClienteId(Integer id);

}
