package com.amsatech.backend.lease.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LeaseCaseRequest {

    @NotNull
    private Long tenantId;

    @NotNull
    private Long unitId;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    public LeaseCaseRequest() {
    }

    public LeaseCaseRequest(Long tenantId, Long unitId, LocalDate startDate, LocalDate endDate) {
        this.tenantId = tenantId;
        this.unitId = unitId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}