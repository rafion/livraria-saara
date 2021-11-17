package com.livrariasaara.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.livrariasaara.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
	
}
