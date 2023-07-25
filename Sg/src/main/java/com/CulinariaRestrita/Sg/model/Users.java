
package com.CulinariaRestrita.Sg.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Users implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	@OneToMany(mappedBy ="userRecipe", orphanRemoval = true , cascade = CascadeType.ALL)
	private List<Recipes>favoriteRecipes;
	
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	

	public Users() {
		super();
	}
	




	public Users(Long id, String name, String email, String password , UserRole role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role= role;
	}

	public Users(String email2, String encryptedPassword, String name) {
		this.email= email2;
		this.password= encryptedPassword;
		this.name= name;
	}
	
	

	public Users(String email, String encryptedPassword, String name, UserRole role) {
		this.email= email;
		this.password = encryptedPassword;
		this.name= name;
		
	}





	public UserRole getRole() {
		return role;
	}





	public void setRole(UserRole role) {
		this.role = role;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
	 return true;
	}

}
