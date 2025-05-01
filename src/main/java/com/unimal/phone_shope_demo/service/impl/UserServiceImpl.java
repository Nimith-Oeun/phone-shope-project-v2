package com.unimal.phone_shope_demo.service.impl;

import com.unimal.phone_shope_demo.exception.ApiException;
import com.unimal.phone_shope_demo.model.Role;
import com.unimal.phone_shope_demo.model.User;
import com.unimal.phone_shope_demo.model.dto.RegisterDTO;
import com.unimal.phone_shope_demo.repositery.RoleRepository;
import com.unimal.phone_shope_demo.repositery.UserRepository;
import com.unimal.phone_shope_demo.security.AuthUser;
import com.unimal.phone_shope_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Optional<AuthUser> findByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));

        AuthUser authUser = AuthUser.builder()
                .username(user.getUserName())
                .password(user.getPassword())
//                .authorities(user.getRole().getAuthorities()) // for enum type
                .authorities(getAuthorities(user.getRoles()))
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .enabled(user.isEnabled())
                .build();
        return Optional.ofNullable(authUser);
    }

    @Override
    public User createUser(RegisterDTO registerDTO) {
        if (userRepository.existsByUserName(registerDTO.getUserName())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        User user = new User();
        user.setUserName(registerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFistName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        Set<Role> roles = registerDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Role not found: " + roleName)))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<SimpleGrantedAuthority> authorities1 = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> authorities = roles.stream().flatMap(role -> toStream(role))
                .collect(Collectors.toSet());
        authorities.addAll(authorities1);
        return authorities;
    }

    private Stream<SimpleGrantedAuthority> toStream(Role role) {
        SimpleGrantedAuthority roleAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
        return role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()));
    }
}
