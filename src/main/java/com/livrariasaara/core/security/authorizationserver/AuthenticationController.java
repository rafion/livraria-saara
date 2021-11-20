package com.livrariasaara.core.security.authorizationserver;

import java.net.URI;
import java.util.Collections;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livrariasaara.domain.exception.ObjectNotFoundException;
import com.livrariasaara.domain.model.Cliente;
import com.livrariasaara.domain.model.Permissao;
import com.livrariasaara.domain.model.Usuario;
import com.livrariasaara.domain.repository.PermissaoRepository;
import com.livrariasaara.domain.repository.UsuarioRepository;

//import com.rafion.github.domain.model.User;
//import com.rafion.github.domain.model.dto.UserDTO;

//import com.rafion.github.domain.service.UserService;



@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoRepository permisaoRepository;
	
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));

	}
	
	//singUpRequest alterado para cliente para conter o endereco
	 @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@RequestBody Cliente signUpRequest) {
	        if(usuarioRepository.existsByUsername(signUpRequest.getUsername())) {
	            return ResponseEntity.badRequest().body("Usuario ja cadastrado!");
	        }

	        if(usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
	            return ResponseEntity.badRequest().body("Email ja cadastrado!");
	        }

	        
	        Cliente user = new Cliente();
	        user.setNome(signUpRequest.getNome());
	        user.setUsername(signUpRequest.getUsername());
	        user.setEmail(signUpRequest.getEmail());
	        user.setPassword(signUpRequest.getPassword());
	        user.setEndereco(signUpRequest.getEndereco());
	     

	        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

	        Permissao userRole = permisaoRepository.findByDescricao("ROLE_USER")
	                .orElseThrow(() -> new ObjectNotFoundException("Permissão de usuario não encontrada"));

	        user.setPermissoes(Collections.singleton(userRole));

	        Usuario result = usuarioRepository.save(user);

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentContextPath().path("/usuarios/{username}")
	                .buildAndExpand(result.getUsername()).toUri();

	        return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado com sucesso"));
	        //return ResponseEntity.created(location).body(result);
	    }
	 

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}