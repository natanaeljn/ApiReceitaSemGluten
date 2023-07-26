package com.CulinariaRestrita.Sg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CulinariaRestrita.Sg.dto.AuthenticationDto;
import com.CulinariaRestrita.Sg.dto.RegisterDto;
import com.CulinariaRestrita.Sg.model.Users;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;
import com.CulinariaRestrita.Sg.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticatedController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsersRepository usersRepository;

	@PostMapping("/login")
	public String login(@RequestBody AuthenticationDto login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.email(), login.password());

		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		var usuario = (Users) authenticate.getPrincipal();

		return tokenService.gerarToken(usuario);

	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
		if (this.usersRepository.findByemail(data.email()) != null) {
			return ResponseEntity.badRequest().build();
		} else {
			String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
			Users users = new Users(data.email(), encryptedPassword, data.name(), data.role());
			this.usersRepository.save(users);
		}
		return ResponseEntity.ok().build();

	}
}