package com.CulinariaRestrita.Sg.dto;

import com.CulinariaRestrita.Sg.model.UserRole;

public record RegisterDto(String name,String email , String password , UserRole role) {

}
