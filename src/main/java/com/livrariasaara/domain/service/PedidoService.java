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
import com.livrariasaara.domain.model.Pedido;
import com.livrariasaara.domain.repository.ClienteRepository;
import com.livrariasaara.domain.repository.LivroRepository;
import com.livrariasaara.domain.repository.PedidoRepository;



@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	LivroRepository livroRepository;
	

	public Pedido findById(Long id) {
		try {
			Pedido obj = pedidoRepository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
	}
	
	public List<Pedido> findAll( ) {
	
		return pedidoRepository.findAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)														
	public Pedido save(Pedido pedido) {
		
		validarPedido(pedido);
		validarItens(pedido);
		
		try {
			if (pedido.getPagamento() != null ) {
				pedido.setPagamento(pedido.getPagamento()); //feito dessa forma para que o objeto pagamento tenha uma referencia de pedido
			}
			
			return pedidoRepository.save(pedido);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Pedido não foi(foram) preenchido(s)");
		}

	}
	
private void validarPedido(Pedido pedido) {
		
		Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElseThrow(() -> new DataIntegrityException(
				"Cliente não encontrado! Id: " + pedido.getCliente().getId()) );
		
		pedido.setCliente(cliente);	
	}


	private final void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
		
				Livro livro = livroRepository.findById(item.getLivro().getId()).orElseThrow(() -> new DataIntegrityException(
						"Livro não encontrado! Id: " + item.getLivro().getId() + ", Tipo: " + Livro.class.getName()));
				
				if (livro !=null) {
					item.setPedido(pedido);
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
