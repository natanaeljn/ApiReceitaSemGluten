package com.CulinariaRestrita.Sg.dto;

import com.CulinariaRestrita.Sg.model.Users;

public record UsersDto(Long id , String name , String email ) {
	
	public UsersDto(Users users) {
		this(users.getId(), users.getName() , users.getEmail());
	}

}
