package com.livrariasaara.domain.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;

import com.livrariasaara.domain.model.Autor;
import com.livrariasaara.domain.model.Editora;
import com.livrariasaara.domain.model.Livro;
import com.livrariasaara.domain.repository.filter.LivroFilter;

public class LivroRepositoryCustonImpl implements LivroRepositoryCuston {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	@Override
	public List<Livro> filtrar(LivroFilter filter) {
		
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Livro> query = cBuilder.createQuery(Livro.class);//o que vai ser retornado na consulta
		
		Root<Livro> livro = query.from(Livro.class); //alias da tabela
		Join<Livro, Autor> joinAutor = livro.join("autor");
		Join<Livro, Editora> joinEditora = livro.join("editora");
				
		query.select(livro);
		
		if(filter != null) {
		List<Predicate> predicates = new ArrayList<>(); // = restricoes(filter, cBuilder, livro); //where
		
		
		if(filter.getTitulo() != null) {
			predicates.add(cBuilder.or(
					cBuilder.like(cBuilder.upper(livro.<String>get("titulo")), "%" + filter.getTitulo().toUpperCase() + "%"))); 
		}
		
		if(filter.getAutor() != null) {
			predicates.add(cBuilder.or(
					cBuilder.like(cBuilder.upper(joinAutor.<String>get("nome")), "%" + filter.getAutor().toUpperCase() + "%")));
		}
		
		if(filter.getEditora() != null) {
			predicates.add(cBuilder.or(
					cBuilder.like(cBuilder.upper(joinEditora.<String>get("nome")), "%" + filter.getEditora().toUpperCase() + "%")));
		}
		
		query.where(cBuilder.or(predicates.stream().toArray(Predicate[]::new)));
		
		}
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
	@SuppressWarnings("unused")
	private Predicate[] restricoes(LivroFilter filter, CriteriaBuilder cBuilder, Root<Livro> livroRoot) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getTitulo() != null) {
			predicates.add(cBuilder.or(
					cBuilder.like(livroRoot.<String>get("titulo"), "%" + filter.getTitulo() + "%"))); 
		}
		
		if(filter.getAutor() != null) {
			predicates.add(cBuilder.or(
					cBuilder.like(livroRoot.<String>get("autor.nome"), "%" + filter.getAutor() + "%")));
		}
		
		if(filter.getEditora() != null) {
			predicates.add(cBuilder.or(
					cBuilder.like(livroRoot.<String>get("editora"), "%" + filter.getEditora() + "%")));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	@SuppressWarnings("unused")
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

}
