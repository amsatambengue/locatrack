package com.amsatech.backend.unit;

import com.amsatech.backend.common.exception.BusinessConflictException;
import com.amsatech.backend.common.exception.NotFoundException;
import com.amsatech.backend.property.Property;
import com.amsatech.backend.property.PropertyRepository;
import com.amsatech.backend.unit.dto.UnitRequest;
import com.amsatech.backend.unit.dto.UnitResponse;
import com.amsatech.backend.unit.mapper.UnitMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final PropertyRepository propertyRepository;
    private final UnitMapper unitMapper;

    public UnitService(UnitRepository unitRepository,
                       PropertyRepository propertyRepository,
                       UnitMapper unitMapper) {
        this.unitRepository = unitRepository;
        this.propertyRepository = propertyRepository;
        this.unitMapper = unitMapper;
    }

    @Transactional(readOnly = true)
    public List<UnitResponse> findAll() {
        return unitRepository.findAll()
                .stream()
                .map(unitMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UnitResponse> findByPropertyId(Long propertyId) {
        if (!propertyRepository.existsById(propertyId)) {
            throw new NotFoundException("Property not found: " + propertyId);
        }

        return unitRepository.findByPropertyId(propertyId)
                .stream()
                .map(unitMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UnitResponse findById(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unit not found: " + id));

        return unitMapper.toResponse(unit);
    }

    @Transactional
    public UnitResponse create(UnitRequest request) {
        Property property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found: " + request.getPropertyId()));

        if (unitRepository.existsByPropertyIdAndReference(request.getPropertyId(), request.getReference())) {
            throw new BusinessConflictException(
                    "Unit reference already exists for this property: " + request.getReference()
            );
        }

        Unit unit = unitMapper.toEntity(request, property);
        Unit savedUnit = unitRepository.save(unit);

        return unitMapper.toResponse(savedUnit);
    }

    @Transactional
    public UnitResponse update(Long id, UnitRequest request) {
        Unit existingUnit = unitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unit not found: " + id));

        Property property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found: " + request.getPropertyId()));

        boolean referenceChanged = !existingUnit.getReference().equals(request.getReference());
        boolean propertyChanged = !existingUnit.getProperty().getId().equals(request.getPropertyId());

        if ((referenceChanged || propertyChanged)
                && unitRepository.existsByPropertyIdAndReference(request.getPropertyId(), request.getReference())) {
            throw new BusinessConflictException(
                    "Unit reference already exists for this property: " + request.getReference()
            );
        }

        unitMapper.updateEntity(existingUnit, request, property);
        Unit savedUnit = unitRepository.save(existingUnit);

        return unitMapper.toResponse(savedUnit);
    }
}