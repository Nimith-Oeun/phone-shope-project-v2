package com.unimal.phone_shope_demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long id;
    @Column(name = "m_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
