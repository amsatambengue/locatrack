package com.amsatech.backend.unit.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class UnitRequest {

    @NotBlank
    @Size(max = 50)
    private String reference;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal monthlyRent;

    private Boolean active;

    @NotNull
    private Long propertyId;

    public UnitRequest() {
    }

    public UnitRequest(String reference, BigDecimal monthlyRent, Boolean active, Long propertyId) {
        this.reference = reference;
        this.monthlyRent = monthlyRent;
        this.active = active;
        this.propertyId = propertyId;
    }

    public String getReference() {
        return reference;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}