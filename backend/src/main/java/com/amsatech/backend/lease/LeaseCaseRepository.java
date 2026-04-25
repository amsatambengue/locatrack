package com.amsatech.backend.lease;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseCaseRepository extends JpaRepository<LeaseCase, Long> {

    boolean existsByUnitIdAndStatus(Long unitId, LeaseStatus status);

    boolean existsByUnitIdAndStatusAndIdNot(Long unitId, LeaseStatus status, Long leaseCaseId);
}