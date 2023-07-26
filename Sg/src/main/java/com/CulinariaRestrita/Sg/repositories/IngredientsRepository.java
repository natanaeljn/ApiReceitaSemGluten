package com.CulinariaRestrita.Sg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CulinariaRestrita.Sg.model.Ingredients;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {

	@Query("select t from Ingredients t where t.recipies.id = ?1")
	List<Ingredients> findByRecipeId(Long id);

}
