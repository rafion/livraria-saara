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

import com.livrariasaara.api.model.EditoraModel;
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Editora;
import com.livrariasaara.domain.repository.EditoraRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

	 @Autowired
	 EditoraRepository editoraRepository;
	 
	 private ModelMapper mapper;
	 
	 @GetMapping
		public ResponseEntity<List<EditoraModel>> findAll() {
			List<Editora> editoras = editoraRepository.findAll();
			if(!editoras.isEmpty()) {
				return  ResponseEntity.ok(toCollectionModel(editoras));
			}
			return ResponseEntity.noContent().build();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<EditoraModel> findById(@PathVariable Long id) {
			return editoraRepository.findById(id)
					.map(editora -> {
						EditoraModel EditoraModel = mapper.map(editora, EditoraModel.class);
						return ResponseEntity.ok(EditoraModel);
					}).orElse(ResponseEntity.notFound().build());
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<EditoraModel> insert(@Valid @RequestBody EditoraModel editoraModel, BindingResult br) {
			if (br.hasErrors())
				throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
			Editora editora = editoraRepository.save(toDomainObject(editoraModel));
			
			return ResponseEntity.ok().body(toModel(editora));
		}
		
		@PutMapping("{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public ResponseEntity<EditoraModel> update(@Valid @RequestBody EditoraModel editoraModel, BindingResult br) {
			if (br.hasErrors())
				throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
			Editora autor = editoraRepository.save(toDomainObject(editoraModel));
			return ResponseEntity.ok().body(toModel(autor));
		}
		
		@DeleteMapping("{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			editoraRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		
		/*
		 * converter Objeto de dominio para objeto model
		 */
		public EditoraModel toModel(Editora editora) {
			return mapper.map(editora, EditoraModel.class);
		}
		
		public Editora toDomainObject(EditoraModel model) {
			return mapper.map(model, Editora.class);
		}
		
		public List<EditoraModel> toCollectionModel(List<Editora> obj){
			return obj.stream()
					.map(this::toModel)
					.collect(Collectors.toList());
		}
	 
}
