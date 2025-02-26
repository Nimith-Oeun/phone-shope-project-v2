package com.unimal.phone_shope_demo.repositery;

import com.unimal.phone_shope_demo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepositery extends JpaRepository<Color, Long> {

}
