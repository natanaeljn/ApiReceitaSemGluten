package com.CulinariaRestrita.Sg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingredients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nameIngredient;
	
	private Double value;

	private double quantityG;
	
	private double quantityKg;
	
	private int quantity;
	
	
	
	@JoinColumn(foreignKey = @ForeignKey(name = "recipe_id"))
	@ManyToOne
	private Recipes recipies;
	
	
	
	public Ingredients() {
		super();
	}

	public Ingredients(Long id, String nameIngredient, Double value, double quantityKg, double quantityG,
			int quantidade) {
		this.id=id;
		this.nameIngredient = nameIngredient;
		this.value = value;
		this.quantityKg = quantityKg;
		this.quantityG = quantityG;
		this.quantity= quantidade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameIngredient() {
		return nameIngredient;
	}
	public void setNameIngredient(String nameIngredient) {
		this.nameIngredient = nameIngredient;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public double getQuantityG() {
		return quantityG;
	}
	public void setQuantityG(double quantityG) {
		this.quantityG = quantityG;
	}
	public double getQuantityKg() {
		return quantityKg;
	}
	public void setQuantityKg(double quantityKg) {
		this.quantityKg = quantityKg;
	}
	
	
	public int getQuantidade() {
		return quantity;
	}
	public void setQuantidade(int quantity) {
		this.quantity = quantity;
	}
	public double totalG () {
		double total  = this.value * quantityG ;
		return total;
	}
	public double totalKg () {
		double total  = this.value * quantityKg ;
		return total;
	}
	public double totalQuant() {
		double total  = this.value * quantity ;
		return total;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Recipes getRecipies() {
		return recipies;
	}

	public void setRecipies(Recipes recipies) {
		this.recipies = recipies;
	}
	

}
