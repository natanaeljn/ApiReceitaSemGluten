package com.CulinariaRestrita.Sg.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CulinariaRestrita.Sg.dto.AttDto;
import com.CulinariaRestrita.Sg.dto.AuthenticationDto;
import com.CulinariaRestrita.Sg.dto.RegisterDto;
import com.CulinariaRestrita.Sg.model.UserRole;
import com.CulinariaRestrita.Sg.model.Users;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;
import com.CulinariaRestrita.Sg.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthenticatedController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsersRepository usersRepository;

	@CrossOrigin("*")
	@PostMapping(value = "/login")
	public String login(@RequestBody AuthenticationDto login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.email(), login.password());

		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		var usuario = (Users) authenticate.getPrincipal();

		String token= tokenService.gerarToken(usuario);
		return token;

	}
	@CrossOrigin("*")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
		if (this.usersRepository.findByemail(data.emailRegister()) != null) {
			return ResponseEntity.badRequest().build();
		} else {
			String encryptedPassword = new BCryptPasswordEncoder().encode(data.passwordRegister());
			Users users = new Users(data.emailRegister(), encryptedPassword, data.name(), data.role());
			this.usersRepository.save(users);
		}
		return ResponseEntity.ok().build();

	}
	
	@GetMapping("/searchUser/{email}")
	public Users user(@PathVariable("email")String email) {
		 Users users =  this.usersRepository.findByemail(email);
		 return users;
	}
	
	@PostMapping("/att")
	public ResponseEntity att(@RequestBody @Valid AttDto att ) {
		Users user= this.usersRepository.findById(att.id()).get();
		user.setEmail(att.email());
		String encryptedPassword = new BCryptPasswordEncoder().encode(att.password());
		user.setPassword(encryptedPassword);
		this.usersRepository.save(user);
		return ResponseEntity.ok().build();
	}
		
	
	
}