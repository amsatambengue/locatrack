package com.amsatech.backend.unit.dto;

import java.math.BigDecimal;

public class UnitResponse {

    private Long id;
    private String reference;
    private BigDecimal monthlyRent;
    private boolean active;
    private Long propertyId;
    private String propertyName;

    public UnitResponse() {
    }

    public UnitResponse(Long id, String reference, BigDecimal monthlyRent, boolean active, Long propertyId, String propertyName) {
        this.id = id;
        this.reference = reference;
        this.monthlyRent = monthlyRent;
        this.active = active;
        this.propertyId = propertyId;
        this.propertyName = propertyName;
    }

    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public boolean isActive() {
        return active;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}