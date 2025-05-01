package com.unimal.phone_shope_demo.service;

import com.unimal.phone_shope_demo.model.User;
import com.unimal.phone_shope_demo.model.dto.RegisterDTO;
import com.unimal.phone_shope_demo.security.AuthUser;

import java.util.Optional;

public interface UserService {
    Optional<AuthUser> findByUsername(String username);
    User createUser(RegisterDTO registerDTO);

}
