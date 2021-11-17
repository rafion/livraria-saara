package com.livrariasaara.core.security.authorizationserver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

	private String nome;
    private String username;
    private String email;
    private String password;
}
