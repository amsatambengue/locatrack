package com.amsatech.backend.unit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    List<Unit> findByPropertyId(Long propertyId);

    Optional<Unit> findByPropertyIdAndReference(Long propertyId, String reference);

    boolean existsByPropertyIdAndReference(Long propertyId, String reference);
}