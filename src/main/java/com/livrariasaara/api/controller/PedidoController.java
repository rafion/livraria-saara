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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.livrariasaara.api.model.PedidoModel;
import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Pedido;
import com.livrariasaara.domain.repository.PedidoRepository;
import com.livrariasaara.domain.service.PedidoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService service;
	
	@Autowired
	PedidoRepository repository;
	
	private ModelMapper mapper;
	
	
	@GetMapping
	public ResponseEntity<List<PedidoModel>> findAll() {
		List<Pedido> pedidos = service.findAll();
		if(!pedidos.isEmpty()) {
			return  ResponseEntity.ok(toCollectionModel(pedidos));
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoModel> findById(@PathVariable Long id) {
		 Pedido pedido = service.findById(id);
		 
		 return ResponseEntity.ok(toModel(pedido));
				
	}
	
	@GetMapping("/findAllByClienteId")
	public ResponseEntity<List<PedidoModel>> findAllByClienteId(@RequestParam(value = "clienteId") Integer id) {
		 List<Pedido> pedidos = repository.findAllByClienteId(id);
		 
		 return ResponseEntity.ok(toCollectionModel(pedidos));
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PedidoModel> insert(@Valid @RequestBody PedidoModel pedidoModel, BindingResult br) {
		
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		
		Pedido pedido = service.save(toDomainObject(pedidoModel));
		
		return ResponseEntity.ok().body(toModel(pedido));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<PedidoModel> update(@Valid @RequestBody PedidoModel pedidoModel, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		Pedido pedido = service.save(toDomainObject(pedidoModel));
		return ResponseEntity.ok().body(toModel(pedido));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	/*
	 * converter Objeto de dominio para objeto model
	 */
	public PedidoModel toModel(Pedido pedido) {
		return mapper.map(pedido, PedidoModel.class);
	}
	
	public Pedido toDomainObject(PedidoModel model) {
		return mapper.map(model, Pedido.class);
	}
	
	public List<PedidoModel> toCollectionModel(List<Pedido> obj){
		return obj.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	

}
