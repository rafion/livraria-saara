package com.livrariasaara.core.security.authorizationserver;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.livrariasaara.core.config.ApplicationContextLoad;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {



	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

// JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			//System.out.println("toke REquest: " + requestTokenHeader);
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				
				//System.out.println("username do token: " + username);
				
			} catch (IllegalArgumentException e) {
				logger.warn("Unable to get JWT Token");
				//System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				//System.out.println("JWT Token has expired");
				logger.warn("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		/*atualizar token no usuario
		ApplicationContextLoad.getApplicationContext()
		.getBean(JpaUserDetailsService.class).updateTokenUser(jwtToken, username);
		*/

		
// Tendo o token, valide o.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			
			/* era:
			UserDetails userDetails = this.jpaUserDetailsService.loadUserByUsername(username);
*/
			UserDetails userDetails = ApplicationContextLoad.getApplicationContext()
					.getBean(JpaUserDetailsService.class).loadUserByUsername((username));
			
			
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}