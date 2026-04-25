package com.amsatech.backend.lease;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseStatusHistoryRepository extends JpaRepository<LeaseStatusHistory, Long> {

    List<LeaseStatusHistory> findByLeaseCaseIdOrderByChangedAtAsc(Long leaseCaseId);
}