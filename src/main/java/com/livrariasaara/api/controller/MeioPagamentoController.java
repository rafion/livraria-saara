package com.livrariasaara.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livrariasaara.domain.model.MeioPagamento;
import com.livrariasaara.domain.repository.MeioPagamentoRepository;

@RestController
@RequestMapping("/api/meiosPagamento")
public class MeioPagamentoController {
	
	@Autowired
	MeioPagamentoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<MeioPagamento>> findAll() {
		List<MeioPagamento> meiosPagamento = repository.findAll();
		if(!meiosPagamento.isEmpty()) {
			return  ResponseEntity.ok(meiosPagamento);
		}
		return ResponseEntity.noContent().build();
	}

}
