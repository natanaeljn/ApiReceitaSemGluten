package com.CulinariaRestrita.Sg.model;



import java.util.List;




import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Recipes {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nameRecipe;
	@NotBlank
	private String tipo;
	
	@NotBlank
	@Column(name="PREPARATION", length = 3000 , nullable = false)
	private String preparation;
	
	
	
	@JoinColumn(foreignKey = @ForeignKey(name = "user_id"))
	@ManyToOne
	private Users userRecipe;

	
	
	@OneToMany(mappedBy = "recipies" , orphanRemoval = true , cascade = CascadeType.ALL)
	private List<Ingredients>ingredients;
	
	

	

	public Recipes(String string, String string2, String string3) {
		this.nameRecipe = string;
		this.tipo = string2;
		this.preparation=string3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameRecipe() {
		return nameRecipe;
	}

	public void setNameRecipe(String nameRecipe) {
		this.nameRecipe = nameRecipe;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public Users getUserRecipe() {
		return userRecipe;
	}

	public void setUserRecipe(Users userRecipe) {
		this.userRecipe = userRecipe;
	}
	
	
	

}
