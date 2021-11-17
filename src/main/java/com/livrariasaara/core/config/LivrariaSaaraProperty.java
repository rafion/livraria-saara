package com.livrariasaara.core.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("livraria-saara")
@Getter
@Setter
@Component
public class LivrariaSaaraProperty {

	private String originPermitida = "http://localhost:4200";
	
	private final Seguranca seguranca = new Seguranca();
	
	@Getter
	@Setter
	public static class Seguranca {

		private List<String> redirectsPermitidos;
		private String authServerUrl;
		}
	
}
