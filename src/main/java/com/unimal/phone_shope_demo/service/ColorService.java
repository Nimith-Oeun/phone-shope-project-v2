package com.unimal.phone_shope_demo.service;

import com.unimal.phone_shope_demo.model.Color;

public interface ColorService {
    Color create (Color color);
    Color getById(Long id);
}
