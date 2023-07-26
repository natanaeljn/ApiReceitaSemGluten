package com.CulinariaRestrita.Sg.services;

import com.CulinariaRestrita.Sg.model.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	/* ira gerar o token */
	public String gerarToken(Users usuario) {
		return JWT.create().withIssuer("Receitas").withSubject(usuario.getUsername()).withClaim("id", usuario.getId())
				.withExpiresAt(LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("leonatan"));
	}

	/* aqui verificamos o token e pegamos o subject dele */
	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("leonatan")).withIssuer("Receitas").build().verify(token).getSubject();

	}
}