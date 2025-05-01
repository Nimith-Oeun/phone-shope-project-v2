package com.unimal.phone_shope_demo.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.unimal.phone_shope_demo.security.Permission.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {
    ADMIN(Set.of(BRAND_WRITE, BRAND_READ, MODEL_WRITE, MODEL_READ)),
    SALE(Set.of(BRAND_READ, MODEL_READ));

    private Set<Permission> permissions; // Set of permissions
    public Set<SimpleGrantedAuthority> getAuthorities(){
        // Convert the permissions to SimpleGrantedAuthority
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
                .collect(Collectors.toSet());

        // Add the role to the granted authorities
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
        grantedAuthorities.add(role);
        System.out.println(grantedAuthorities);
        return grantedAuthorities;
    }
}
