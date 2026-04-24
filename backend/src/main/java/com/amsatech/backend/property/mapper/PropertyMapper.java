package com.amsatech.backend.property.mapper;

import com.amsatech.backend.property.Property;
import com.amsatech.backend.property.dto.PropertyRequest;
import com.amsatech.backend.property.dto.PropertyResponse;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public Property toEntity(PropertyRequest request) {
        Property property = new Property();
        property.setName(request.getName());
        property.setAddress(request.getAddress());
        property.setActive(request.getActive() != null ? request.getActive() : true);
        return property;
    }

    public PropertyResponse toResponse(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getName(),
                property.getAddress(),
                property.isActive()
        );
    }

    public void updateEntity(Property property, PropertyRequest request) {
        property.setName(request.getName());
        property.setAddress(request.getAddress());

        if (request.getActive() != null) {
            property.setActive(request.getActive());
        }
    }
}