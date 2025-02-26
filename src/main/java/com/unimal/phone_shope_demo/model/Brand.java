package com.unimal.phone_shope_demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Long id;
    @Column(name = "b_name")
    private String name;
}
