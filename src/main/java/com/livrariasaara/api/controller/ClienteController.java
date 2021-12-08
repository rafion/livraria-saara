package com.livrariasaara.api.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

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

import com.livrariasaara.domain.exception.ConstraintException;
import com.livrariasaara.domain.model.Cliente;
import com.livrariasaara.domain.model.Endereco;
import com.livrariasaara.domain.repository.ClienteRepository;
import com.livrariasaara.domain.service.ClienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	/*se for ter regra de negocio, instanciar injetar uma classe service
	 * */
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteService service;
	
	@GetMapping
	public ResponseEntity<Collection<Cliente>> findAll() {
		Collection<Cliente> collection = clienteRepository.findAll();
		if(!collection.isEmpty()) {
			return ResponseEntity.ok().body(collection);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		
		return ResponseEntity.ok().body(clienteRepository.save(cliente));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.ok().body(clienteRepository.save(cliente));
	}
	
	@PutMapping("updateEndereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Cliente> updateEndereco(@Valid@RequestParam(value = "clienteId") Integer clienteId, @RequestBody Endereco endereco, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.ok().body(service.updateEndereco(clienteId, endereco));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/findByUsername")
	public ResponseEntity<Cliente> findByUsername(@RequestParam(value = "username") String username) {
	
		Optional<Cliente> obj = clienteRepository.findByUsername(username);
		
		if (obj.isPresent()) {
			return new ResponseEntity<Cliente>(obj.get(), HttpStatus.OK);
		} 
		return ResponseEntity.noContent().build();
	}
	
}
