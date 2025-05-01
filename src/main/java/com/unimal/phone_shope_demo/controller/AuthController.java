package com.unimal.phone_shope_demo.controller;

import com.unimal.phone_shope_demo.mapper.AuthMapper;
import com.unimal.phone_shope_demo.model.User;
import com.unimal.phone_shope_demo.model.dto.RegisterDTO;
import com.unimal.phone_shope_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/Register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        User user = userService.createUser(registerDTO);
        return ResponseEntity.ok(user);
    }
}
