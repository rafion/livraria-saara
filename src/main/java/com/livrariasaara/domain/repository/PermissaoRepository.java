package com.livrariasaara.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariasaara.domain.model.Permissao;

public interface PermissaoRepository  extends JpaRepository<Permissao, Long>{
	
	public Optional<Permissao> findByDescricao(String descricao);

}
