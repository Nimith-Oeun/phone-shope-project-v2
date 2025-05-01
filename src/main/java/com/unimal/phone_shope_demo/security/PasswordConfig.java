package com.unimal.phone_shope_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
    * This class is used to configure the password encoder
 */
@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder getpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
