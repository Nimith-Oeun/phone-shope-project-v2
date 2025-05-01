package com.unimal.phone_shope_demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products",uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})}) // on unique constraint use for protect duplicate data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;
    @Column(name = "p_name",unique = true)
    private String name ;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "available_units")
    private Integer availableUnits;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;



}
