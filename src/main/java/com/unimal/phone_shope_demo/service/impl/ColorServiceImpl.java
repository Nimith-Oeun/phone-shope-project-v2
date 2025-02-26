package com.unimal.phone_shope_demo.service.impl;

import com.unimal.phone_shope_demo.exception.ResoureNoteFoundException;
import com.unimal.phone_shope_demo.model.Color;
import com.unimal.phone_shope_demo.repositery.ColorRepositery;
import com.unimal.phone_shope_demo.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepositery colorRepositery;

    @Override
    public Color create(Color color) {
        return null;
    }

    @Override
    public Color getById(Long id) {
        return colorRepositery.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Color",id));
    }
}
