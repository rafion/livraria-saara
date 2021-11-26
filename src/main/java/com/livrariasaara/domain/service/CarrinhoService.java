package com.livrariasaara.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.livrariasaara.domain.exception.DataIntegrityException;
import com.livrariasaara.domain.exception.ObjectNotFoundException;
import com.livrariasaara.domain.model.Cliente;
import com.livrariasaara.domain.model.Livro;
import com.livrariasaara.domain.model.Carrinho;
import com.livrariasaara.domain.repository.ClienteRepository;
import com.livrariasaara.domain.repository.LivroRepository;
import com.livrariasaara.domain.repository.PedidoRepository;
import com.livrariasaara.domain.repository.CarrinhoRepository;



@Service
public class CarrinhoService {
	
	@Autowired
	CarrinhoRepository carrinhoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	LivroRepository livroRepository;
	

	public Carrinho findById(Long id) {
		try {
			Carrinho obj = carrinhoRepository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Carrinho.class.getName());
		}
	}
	
	public List<Carrinho> findAll( ) {
	
		return carrinhoRepository.findAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)														
	public Carrinho save(Carrinho carrinho) {
		
		validarCarrinho(carrinho);
		validarItens(carrinho);
		
		try {
			if (carrinho.getPagamento() != null ) {
				carrinho.setPagamento(carrinho.getPagamento()); //feito dessa forma para que o objeto pagamento tenha uma referencia de carrinho
			}
			
			return carrinhoRepository.save(carrinho);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Carrinho não foi(foram) preenchido(s)");
		}

	}
	
private void validarCarrinho(Carrinho carrinho) {
		
		Cliente cliente = clienteRepository.findById(carrinho.getCliente().getId()).orElseThrow(() -> new DataIntegrityException(
				"Cliente não encontrado! Id: " + carrinho.getCliente().getId()) );
		
		carrinho.setCliente(cliente);	
	}


	private final void validarItens(Carrinho carrinho) {
		carrinho.getItens().forEach(item -> {
		
				Livro livro = livroRepository.findById(item.getLivro().getId()).orElseThrow(() -> new DataIntegrityException(
						"Livro não encontrado! Id: " + item.getLivro().getId() + ", Tipo: " + Livro.class.getName()));
				
				if (livro !=null) {
					item.setPedido(carrinho);
					item.setLivro(livro);
					item.setPrecoUnitario(livro.getPrecoUnitario());
				}
		}
		);
	}
	
	public void deleteById(Long id) {
		livroRepository.deleteById(id);
		
	}
	
}
