/*
This class is used to create a fake user for testing purposes.
 */

package com.unimal.phone_shope_demo.security;

import com.unimal.phone_shope_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class FakeUserDrtail /*implements UserService*/ {
    private final PasswordEncoder passwordEncoder;
    //@Override
    public Optional<AuthUser> findByUsername(String username) {
//        List<AuthUser> user = List.of(
//                new AuthUser("seyha", passwordEncoder.encode("seyha678"), Role.SALE.getAuthorities(), true, true, true, true),
//                new AuthUser("cheata", passwordEncoder.encode("cheata123"), Role.ADMIN.getAuthorities(), true, true, true, true)
//        );
//        return user.stream()
//                .filter(u -> u.getUsername().equals(username))
//                .findFirst();//return the first element in the stream
        return Optional.empty();
    }
}
