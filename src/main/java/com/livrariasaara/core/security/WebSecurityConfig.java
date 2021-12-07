package com.livrariasaara.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.livrariasaara.core.security.authorizationserver.JwtAuthenticationEntryPoint;
import com.livrariasaara.core.security.authorizationserver.JwtRequestFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	private String myAntMatchers[] = {"/api/**", "/signin", "/signup", "/h2-console/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**","/swagger-ui.html","/swagger-ui/**","/webjars/**"};

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder()); /*passando a codificação da senha*/
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers(myAntMatchers).permitAll() // Não cheque essas requisições
				.anyRequest().authenticated()// Qualquer outra requisição deve ser checada
				.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and().cors()
				.and().headers().frameOptions().sameOrigin() //para o h2
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
				
	}

}
