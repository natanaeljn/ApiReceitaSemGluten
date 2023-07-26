package com.CulinariaRestrita.Sg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CulinariaRestrita.Sg.model.Recipes;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RecipesRepository extends JpaRepository<Recipes, Long> {

	@Query("select t from Recipes t where t.tipo = ?1")
	List<Recipes> findByTipo(String tipo);

	@Query("select t from Recipes t where t.userRecipe.id = ?1")
	List<Recipes> findByUserRecipe(Long id);
}
