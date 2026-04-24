package com.amsatech.backend.unit.mapper;

import com.amsatech.backend.property.Property;
import com.amsatech.backend.unit.Unit;
import com.amsatech.backend.unit.dto.UnitRequest;
import com.amsatech.backend.unit.dto.UnitResponse;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public Unit toEntity(UnitRequest request, Property property) {
        Unit unit = new Unit();
        unit.setReference(request.getReference());
        unit.setMonthlyRent(request.getMonthlyRent());
        unit.setActive(request.getActive() != null ? request.getActive() : true);
        unit.setProperty(property);
        return unit;
    }

    public UnitResponse toResponse(Unit unit) {
        return new UnitResponse(
                unit.getId(),
                unit.getReference(),
                unit.getMonthlyRent(),
                unit.isActive(),
                unit.getProperty().getId(),
                unit.getProperty().getName()
        );
    }

    public void updateEntity(Unit unit, UnitRequest request, Property property) {
        unit.setReference(request.getReference());
        unit.setMonthlyRent(request.getMonthlyRent());

        if (request.getActive() != null) {
            unit.setActive(request.getActive());
        }

        unit.setProperty(property);
    }
}