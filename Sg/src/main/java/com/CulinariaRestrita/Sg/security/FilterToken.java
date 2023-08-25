package com.CulinariaRestrita.Sg.security;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.CulinariaRestrita.Sg.repositories.UsersRepository;
import com.CulinariaRestrita.Sg.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{
	
	@Autowired
    private TokenService tokenService;

    @Autowired
    private UsersRepository usuarioRepository;
    
    /*aqui configurei um filter que ira ser passado antes do webConfig*/

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token;
        
        /*aqui pegamos o token que esta vindo na reuqisiçao*/
        var authorizationHeader = request.getHeader("Authorization");

        /*verificamos se ele nao esta nulo e substituimos o bearer por vazio  e setamos ele para o usuario logado*/
        if(authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer ", "");
            var subject = this.tokenService.getSubject(token);

            var usuario = this.usuarioRepository.findByemail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario,
                    null, usuario.getAuthorities());
           

            /*aqui mostro para o spring que o usuario esta autenticado*/
            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.addHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, PATCH");
        }

        response.addHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, PATCH");
        filterChain.doFilter(request, response);

    }
    private void liberaCorsUm(HttpServletResponse response) {
		if(response.getHeader("Access-Control-Allow-Origin")==null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			
		}
		if(response.getHeader("Access-Control-Allow-Headers")==null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
			
		}
		if(response.getHeader("Access-Control-Allow-Methods")==null) {
			response.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
		}
		response.setStatus(200);
	}
}