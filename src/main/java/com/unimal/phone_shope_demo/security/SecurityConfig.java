package com.unimal.phone_shope_demo.security;

import com.unimal.phone_shope_demo.jwt.FilterChainExceptionHandler;
import com.unimal.phone_shope_demo.jwt.JwtLoginFilter;
import com.unimal.phone_shope_demo.jwt.TokenVerifyFilter;
import com.unimal.phone_shope_demo.repositery.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.unimal.phone_shope_demo.security.Permission.BRAND_READ;
import static com.unimal.phone_shope_demo.security.Permission.BRAND_WRITE;


@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final FilterChainExceptionHandler filterChainExceptionHandler;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration), userRepository)) // Add JwtLoginFilter to the filter chain
                .addFilterBefore(filterChainExceptionHandler, JwtLoginFilter.class) // Add FilterChainExceptionHandler to the filter chain
                .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class) // Add TokenVerifyFilter to the filter chain
                .sessionManagement(config -> config
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ) // Set the session creation policy to STATELESS
                .authorizeHttpRequests(auth -> auth // use authorizeHttpRequests() to configure authorization
                        .requestMatchers(
                                "/",
                                "index.html",
                                "api-docs/**",  // OpenAPI docs
                                "/swagger-ui/**",   // Swagger UI assets
                                "/swagger-ui.html",
                                "/api/auth/Register"// Swagger UI page
                                )
                                .permitAll()
//                        .requestMatchers("/model").hasRole(Role.ADMIN.name())
//                        .requestMatchers(HttpMethod.POST,"/brand").hasAuthority(BRAND_WRITE.getDescription())
//                        .requestMatchers(HttpMethod.GET,"/brand").hasAuthority(BRAND_READ.getDescription())
                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }

    //this function is used to create an authentication manager
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //this function is used to create a user with a role in memory
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
//                //.roles(Role.ADMIN.name()) // ROLE_ADMIN
//                .authorities(Role.ADMIN.getAuthorities())
//                .build();
//        UserDetails user2 = User.builder()
//                .username("sale")
//                .password(passwordEncoder.encode("sale123"))
////                .roles(Role.SALE.name()) // ROLE_SALE
//                .authorities(Role.SALE.getAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }


    //this function is used to configure the authentication manager
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    //this function is used to create an authentication provider
    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


}
