package com.unimal.phone_shope_demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_name")
    private String colorName;
}
