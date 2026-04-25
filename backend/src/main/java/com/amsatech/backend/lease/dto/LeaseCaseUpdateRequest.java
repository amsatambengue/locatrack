package com.amsatech.backend.lease.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LeaseCaseUpdateRequest {

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    public LeaseCaseUpdateRequest() {
    }

    public LeaseCaseUpdateRequest(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}