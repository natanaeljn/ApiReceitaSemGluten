package com.CulinariaRestrita.Sg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CulinariaRestrita.Sg.dto.UsersDto;
import com.CulinariaRestrita.Sg.model.Users;
import com.CulinariaRestrita.Sg.services.UsersService;

@RestController
@RequestMapping("usersPage")
public class UsersController {
	 
	@Autowired
	private UsersService usersService;
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping
	public List<UsersDto> getAll(){
		return usersService.getAll();
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("")
	public Users saveUser(Users user) {
		return usersService.save(user);
	}

}
