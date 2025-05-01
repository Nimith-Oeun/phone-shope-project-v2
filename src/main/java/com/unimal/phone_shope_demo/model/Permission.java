package com.unimal.phone_shope_demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;
    @Column(name = "permission_name")
    private String name;
}
