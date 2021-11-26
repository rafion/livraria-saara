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


import com.livrariasaara.api.model.CarrinhoModel;
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Carrinho;
import com.livrariasaara.domain.service.CarrinhoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {
	
	@Autowired
	CarrinhoService serviceCarrinho;
	
	private ModelMapper mapper;
	
	
	@GetMapping
	public ResponseEntity<List<CarrinhoModel>> findAll() {
		List<Carrinho> carrinhos = serviceCarrinho.findAll();
		if(!carrinhos.isEmpty()) {
			return  ResponseEntity.ok(toCollectionModel(carrinhos));
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarrinhoModel> findById(@PathVariable Long id) {
		 Carrinho carrinho = serviceCarrinho.findById(id);
		 
		 return ResponseEntity.ok(toModel(carrinho));
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CarrinhoModel> insert(@Valid @RequestBody CarrinhoModel carrinhoModel, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Carrinho carrinho = serviceCarrinho.save(toDomainObject(carrinhoModel));
		
		return ResponseEntity.ok().body(toModel(carrinho));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<CarrinhoModel> update(@Valid @RequestBody CarrinhoModel carrinhoModel, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Carrinho carrinho = serviceCarrinho.save(toDomainObject(carrinhoModel));
		return ResponseEntity.ok().body(toModel(carrinho));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceCarrinho.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	/*
	 * converter Objeto de dominio para objeto model
	 */
	public CarrinhoModel toModel(Carrinho carrinho) {
		return mapper.map(carrinho, CarrinhoModel.class);
	}
	
	public Carrinho toDomainObject(CarrinhoModel model) {
		return mapper.map(model, Carrinho.class);
	}
	
	public List<CarrinhoModel> toCollectionModel(List<Carrinho> obj){
		return obj.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	

}
