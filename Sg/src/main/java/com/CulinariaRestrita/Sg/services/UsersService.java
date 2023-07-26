package com.CulinariaRestrita.Sg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CulinariaRestrita.Sg.dto.UsersDto;
import com.CulinariaRestrita.Sg.model.Users;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	/* metodo que lista todos os usuarios sem trazer a senha junto */
	public List<UsersDto> getAll() {
		List<UsersDto> UsersList = usersRepository.findAll().stream().map(UsersDto::new).toList();
		return UsersList;
	}

	public String delete(Long id) {
		usersRepository.deleteById(id);
		return "ok";
	}

	public Users save(Users users) {
		return usersRepository.save(users);

	}

}
