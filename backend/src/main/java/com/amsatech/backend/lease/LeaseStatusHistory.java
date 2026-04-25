package com.amsatech.backend.lease;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "lease_status_history")
public class LeaseStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lease_case_id", nullable = false)
    private LeaseCase leaseCase;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private LeaseStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LeaseStatus toStatus;

    @Column(nullable = false)
    private Instant changedAt;

    @Column(length = 500)
    private String reason;

    public LeaseStatusHistory() {
    }

    public LeaseStatusHistory(Long id, LeaseCase leaseCase, LeaseStatus fromStatus, LeaseStatus toStatus, Instant changedAt, String reason) {
        this.id = id;
        this.leaseCase = leaseCase;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.changedAt = changedAt;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public LeaseCase getLeaseCase() {
        return leaseCase;
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

    public void setLeaseCase(LeaseCase leaseCase) {
        this.leaseCase = leaseCase;
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