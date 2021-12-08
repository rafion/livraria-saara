package com.livrariasaara.domain.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariasaara.domain.exception.ObjectNotFoundException;
import com.livrariasaara.domain.model.Cliente;
import com.livrariasaara.domain.model.Endereco;
import com.livrariasaara.domain.repository.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente updateEndereco(Integer id, Endereco endereco) {
		try {

			Cliente cliente = clienteRepository.findById(id).orElse(null);
				cliente.setEndereco(endereco);	
			return clienteRepository.save(cliente);

		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(
					"Registro não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
	}
		
}
