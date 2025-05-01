package com.unimal.phone_shope_demo.controller;


import com.unimal.phone_shope_demo.mapper.ModelEntityMapper;
import com.unimal.phone_shope_demo.model.Model;
import com.unimal.phone_shope_demo.model.dto.ModelDTO;
import com.unimal.phone_shope_demo.service.ModelService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final ModelEntityMapper modelMapper;

    @RolesAllowed("ROLE_ADMIN") //this annotation is used to specify the roles that are allowed to access the method
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO) {
        Model modelMap = modelMapper.mapToModel(modelDTO);
        Model modelCreate=modelService.save(modelMap);
        return ResponseEntity.ok(modelMapper.mapToModelDTO(modelCreate));
    }
}

