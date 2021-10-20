package com.livrariasaara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
