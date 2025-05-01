package com.unimal.phone_shope_demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String fistName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;
    private String password;

//    @Enumerated(EnumType.STRING) // for enum type is must be string
//    private Role role;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER) // use FetchType.EAGER to load all roles and permissions make best performance
    private Set<Role> roles;
}
