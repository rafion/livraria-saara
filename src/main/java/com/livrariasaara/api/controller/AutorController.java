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

import com.livrariasaara.api.model.AutorModel;
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Autor;
import com.livrariasaara.domain.repository.AutorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/autores")
public class AutorController {
    
    @Autowired
	AutorRepository autorRepository;
	
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<AutorModel>> findAll() {
		List<Autor> livros = autorRepository.findAll();
		if(!livros.isEmpty()) {
			return  ResponseEntity.ok(toCollectionModel(livros));
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorModel> findById(@PathVariable Long id) {
		return autorRepository.findById(id)
				.map(autor -> { //convertendo o objeto de dominio para o de modelo
					AutorModel AutorModel = mapper.map(autor, AutorModel.class);
					return ResponseEntity.ok(AutorModel);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AutorModel> insert(@Valid @RequestBody AutorModel AutorModel, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Autor autor = autorRepository.save(toDomainObject(AutorModel));
		
		return ResponseEntity.ok().body(toModel(autor));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<AutorModel> update(@Valid @RequestBody AutorModel AutorModel, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Autor autor = autorRepository.save(toDomainObject(AutorModel));
		return ResponseEntity.ok().body(toModel(autor));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		autorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	/*
	 * converter Objeto de dominio para objeto model
	 */
	public AutorModel toModel(Autor autor) {
		return mapper.map(autor, AutorModel.class);
	}
	
	public Autor toDomainObject(AutorModel model) {
		return mapper.map(model, Autor.class);
	}
	
	public List<AutorModel> toCollectionModel(List<Autor> obj){
		return obj.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
}
