package com.amsatech.backend.lease.dto;

import com.amsatech.backend.lease.LeaseStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LeaseStatusChangeRequest {

    @NotNull
    private LeaseStatus targetStatus;

    @Size(max = 500)
    private String reason;

    public LeaseStatusChangeRequest() {
    }

    public LeaseStatusChangeRequest(LeaseStatus targetStatus, String reason) {
        this.targetStatus = targetStatus;
        this.reason = reason;
    }

    public LeaseStatus getTargetStatus() {
        return targetStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setTargetStatus(LeaseStatus targetStatus) {
        this.targetStatus = targetStatus;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}