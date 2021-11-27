package com.livrariasaara.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.livrariasaara.api.model.LivroModel;
import com.livrariasaara.api.model.input.LivroInput;
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Livro;
import com.livrariasaara.domain.repository.LivroRepository;
import com.livrariasaara.domain.repository.filter.LivroFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	LivroRepository livroRepository;
	
	private ModelMapper mapper; //para facilitar a converção de objeto de entrada em objeto de dominio
	
	@GetMapping
	public ResponseEntity<List<LivroModel>> findAll() {
		List<Livro> livros = livroRepository.findAll();
		if(!livros.isEmpty()) {
			return  ResponseEntity.ok(toCollectionModel(livros));
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroModel> findById(@PathVariable Long id) {
		return livroRepository.findById(id)
				.map(livro -> { //convertendo o objeto de dominio para o de modelo
					LivroModel livroModel = mapper.map(livro, LivroModel.class);
					return ResponseEntity.ok(livroModel);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<LivroModel>> filter(LivroFilter filter){
		
		
		List<Livro> livro = livroRepository.filtrar(filter);
		
		return ResponseEntity.ok(toCollectionModel(livro));
		
	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LivroModel> insert(@Valid @RequestBody LivroInput livroInput, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Livro livro = livroRepository.save(toDomainObject(livroInput));
		
		return ResponseEntity.ok().body(toModel(livro));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<LivroModel> update(@Valid @RequestBody LivroInput livroInput, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Livro livro = livroRepository.save(toDomainObject(livroInput));
		return ResponseEntity.ok().body(toModel(livro));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	/*
	 * converter Objeto de dominio para objeto model
	 */
	public LivroModel toModel(Livro livro) {
		return mapper.map(livro, LivroModel.class);
	}
	
	public Livro toDomainObject(LivroInput model) {
		return mapper.map(model, Livro.class);
	}
	
	public List<LivroModel> toCollectionModel(List<Livro> obj){
		return obj.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}
