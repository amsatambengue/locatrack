package com.amsatech.backend.lease.mapper;

import com.amsatech.backend.lease.LeaseCase;
import com.amsatech.backend.lease.LeaseStatus;
import com.amsatech.backend.lease.dto.LeaseCaseRequest;
import com.amsatech.backend.lease.dto.LeaseCaseResponse;
import com.amsatech.backend.lease.dto.LeaseCaseUpdateRequest;
import com.amsatech.backend.tenant.Tenant;
import com.amsatech.backend.unit.Unit;
import org.springframework.stereotype.Component;

@Component
public class LeaseCaseMapper {

    public LeaseCase toEntity(LeaseCaseRequest request, Tenant tenant, Unit unit) {
        LeaseCase leaseCase = new LeaseCase();
        leaseCase.setTenant(tenant);
        leaseCase.setUnit(unit);
        leaseCase.setMonthlyRent(unit.getMonthlyRent());
        leaseCase.setStartDate(request.getStartDate());
        leaseCase.setEndDate(request.getEndDate());
        leaseCase.setStatus(LeaseStatus.DRAFT);
        return leaseCase;
    }

    public LeaseCaseResponse toResponse(LeaseCase leaseCase) {
        Tenant tenant = leaseCase.getTenant();
        Unit unit = leaseCase.getUnit();

        return new LeaseCaseResponse(
                leaseCase.getId(),
                tenant.getId(),
                tenant.getFirstName() + " " + tenant.getLastName(),
                unit.getId(),
                unit.getReference(),
                leaseCase.getMonthlyRent(),
                leaseCase.getStartDate(),
                leaseCase.getEndDate(),
                leaseCase.getStatus()
        );
    }

    public void updateEntity(LeaseCase leaseCase, LeaseCaseUpdateRequest request) {
        leaseCase.setStartDate(request.getStartDate());
        leaseCase.setEndDate(request.getEndDate());
    }
}