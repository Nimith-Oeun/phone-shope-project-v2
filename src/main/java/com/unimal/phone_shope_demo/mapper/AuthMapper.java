package com.unimal.phone_shope_demo.mapper;

import com.unimal.phone_shope_demo.jwt.LoginRequest;
import com.unimal.phone_shope_demo.jwt.LoginRespones;
import com.unimal.phone_shope_demo.model.Role;
import com.unimal.phone_shope_demo.model.User;
import com.unimal.phone_shope_demo.model.dto.RegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    LoginRespones mapUserToLoginRequest(User user);

    @Mapping(target = "roles", source = "roles")
    RegisterDTO mapUserToRegisterDTO(User user);

    default Set<String> rolesToRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
