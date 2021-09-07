package com.livrariasaara.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*Spring passa a gerenciar a instancia
 * Classe responsavel por criar a instancia de modelMapper para converter os objetos evitando tantos DTOs
 */

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		//var modelMapper = new ModelMapper();
		/*
		modelMapper.createTypeMap(Produto.class, ProdutoSumaryModel.class)
		.<String>addMapping(src -> src.getCodBarras()
									.stream().findAny()
									.filter(codBarra -> codBarra.getFator().equals(1)).orElse(null) ,
				(dest, value) -> dest.setCodBarra(value));
		*/
		
		return new ModelMapper();
		
	}
	
	

}
