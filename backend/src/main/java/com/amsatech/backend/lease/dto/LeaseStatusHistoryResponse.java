package com.amsatech.backend.lease.dto;

import com.amsatech.backend.lease.LeaseStatus;

import java.time.Instant;

public class LeaseStatusHistoryResponse {

    private Long id;
    private Long leaseCaseId;
    private LeaseStatus fromStatus;
    private LeaseStatus toStatus;
    private Instant changedAt;
    private String reason;

    public LeaseStatusHistoryResponse() {
    }

    public LeaseStatusHistoryResponse(Long id, Long leaseCaseId, LeaseStatus fromStatus, LeaseStatus toStatus, Instant changedAt, String reason) {
        this.id = id;
        this.leaseCaseId = leaseCaseId;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.changedAt = changedAt;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public Long getLeaseCaseId() {
        return leaseCaseId;
    }

    public LeaseStatus getFromStatus() {
        return fromStatus;
    }

    public LeaseStatus getToStatus() {
        return toStatus;
    }

    public Instant getChangedAt() {
        return changedAt;
    }

    public String getReason() {
        return reason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLeaseCaseId(Long leaseCaseId) {
        this.leaseCaseId = leaseCaseId;
    }

    public void setFromStatus(LeaseStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public void setToStatus(LeaseStatus toStatus) {
        this.toStatus = toStatus;
    }

    public void setChangedAt(Instant changedAt) {
        this.changedAt = changedAt;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}