package com.unimal.phone_shope_demo.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterDTO {
    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    private String confirmPassword;
    private Set<String> roles;
}
