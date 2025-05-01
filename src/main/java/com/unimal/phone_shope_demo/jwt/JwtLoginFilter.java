package com.unimal.phone_shope_demo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unimal.phone_shope_demo.mapper.AuthMapper;
import com.unimal.phone_shope_demo.model.User;
import com.unimal.phone_shope_demo.repositery.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;



@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read the request body and map it to the LoginRequest object
            LoginRequest loginRequest = objectMapper.readValue(
                    request.getInputStream(),
                    LoginRequest.class
            );

            // Create an authentication object
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            return authenticationManager.authenticate(authentication);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    // This method is called when the user is successfully authenticated
    // this method is used to generate the token when the user is successfully authenticated
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String secretKey = "0123456789abcdef0123456789abcdef";
        String token = Jwts.builder()
                .setSubject(authResult.getName()) // Set the subject of the token
                .setIssuedAt(new Date())
                .claim("authorities", authResult.getAuthorities()) // Set the authorities of the token
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7))) // Set the expiration date of the token
                .setIssuer("Phone-Shop.com") // Set the issuer of the token
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes())) // Set the signature of the token
                .compact();



        // Fetch user details from the database
        User user = userRepository.findByUserName(authResult.getName()).get();

        // Map user details to LoginRespones
        LoginRespones loginResponse = AuthMapper.INSTANCE.mapUserToLoginRequest(user);
        loginResponse.setToken(token);
        loginResponse.setAuthorities(authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .toArray(String[]::new));

        // Write the LoginResponse object to the response body
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(loginResponse));

        // Set the token in the response header
        response.setHeader("Authorization", "Bearer  " + token);
    }
}
