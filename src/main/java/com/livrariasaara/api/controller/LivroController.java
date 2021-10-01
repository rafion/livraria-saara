package com.livrariasaara.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Livro;
import com.livrariasaara.domain.repository.LivroRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	LivroRepository livroRepository;
	
	private ModelMapper modelMapper; //para facilitar a converção de objeto de entrada em objeto de dominio
	
	@GetMapping
	public ResponseEntity<List<LivroModel>> findAllCst() {
		List<Livro> livros = livroRepository.findAll();
		if(!livros.isEmpty()) {
			List<LivroModel> livrosModel = new ArrayList<LivroModel>();
			//livros.stream().map(livro ->  livrosModel.add(modelMapper.map(livro, LivroModel.class))) ;
			for (Livro livro : livros) {
				livrosModel.add(modelMapper.map(livro, LivroModel.class));
			}
			return  ResponseEntity.ok(livrosModel);
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroModel> findById(@PathVariable Long id) {
		return livroRepository.findById(id)
				.map(livro -> { //convertendo o objeto de dominio para o de modelo
					LivroModel livroModel = modelMapper.map(livro, LivroModel.class);
					return ResponseEntity.ok(livroModel);
				}).orElse(ResponseEntity.notFound().build());
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LivroModel> insert(@Valid @RequestBody LivroModel livroModel, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Livro livro = livroRepository.save(modelMapper.map(livroModel, Livro.class));
		
		return ResponseEntity.ok().body(modelMapper.map(livro, LivroModel.class));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Livro> update(@Valid @RequestBody Livro livro, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.ok().body(livroRepository.save(livro));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
