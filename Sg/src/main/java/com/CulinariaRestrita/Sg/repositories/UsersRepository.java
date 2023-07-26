package com.CulinariaRestrita.Sg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CulinariaRestrita.Sg.model.Users;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByemail(String email);

}
