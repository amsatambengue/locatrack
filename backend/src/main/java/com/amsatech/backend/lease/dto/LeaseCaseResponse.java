package com.amsatech.backend.lease.dto;

import com.amsatech.backend.lease.LeaseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeaseCaseResponse {

    private Long id;

    private Long tenantId;
    private String tenantName;

    private Long unitId;
    private String unitReference;

    private BigDecimal monthlyRent;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaseStatus status;

    public LeaseCaseResponse() {
    }

    public LeaseCaseResponse(Long id, Long tenantId, String tenantName, Long unitId, String unitReference,
                             BigDecimal monthlyRent, LocalDate startDate, LocalDate endDate, LeaseStatus status) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.unitId = unitId;
        this.unitReference = unitReference;
        this.monthlyRent = monthlyRent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public String getUnitReference() {
        return unitReference;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LeaseStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public void setUnitReference(String unitReference) {
        this.unitReference = unitReference;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(LeaseStatus status) {
        this.status = status;
    }
}