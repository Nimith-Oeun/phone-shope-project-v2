package com.unimal.phone_shope_demo.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Permission {
    BRAND_WRITE("brand:write"),
    BRAND_READ("brand:read"),
    MODEL_WRITE("model:write"),
    MODEL_READ("model:read"),;

    private String description;
}
