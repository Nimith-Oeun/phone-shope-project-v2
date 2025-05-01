package com.unimal.phone_shope_demo.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRespones {
    private String userName;
    private String fistName;
    private String lastName;
    private String token;
    private String[] authorities;
}
