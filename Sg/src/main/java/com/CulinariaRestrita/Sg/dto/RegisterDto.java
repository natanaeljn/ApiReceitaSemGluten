package com.CulinariaRestrita.Sg.dto;

import com.CulinariaRestrita.Sg.model.UserRole;

public record RegisterDto(String name,String emailRegister , String passwordRegister , UserRole role) {

}
