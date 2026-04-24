package com.amsatech.backend.property;

import com.amsatech.backend.common.exception.NotFoundException;
import com.amsatech.backend.property.dto.PropertyRequest;
import com.amsatech.backend.property.dto.PropertyResponse;
import com.amsatech.backend.property.mapper.PropertyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    public List<PropertyResponse> findAll() {
        return propertyRepository.findAll()
                .stream()
                .map(propertyMapper::toResponse)
                .toList();
    }

    public PropertyResponse findById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found: " + id));

        return propertyMapper.toResponse(property);
    }

    public PropertyResponse create(PropertyRequest request) {
        Property property = propertyMapper.toEntity(request);
        Property savedProperty = propertyRepository.save(property);
        return propertyMapper.toResponse(savedProperty);
    }

    public PropertyResponse update(Long id, PropertyRequest request) {
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found: " + id));

        propertyMapper.updateEntity(existingProperty, request);
        Property savedProperty = propertyRepository.save(existingProperty);
        return propertyMapper.toResponse(savedProperty);
    }
}