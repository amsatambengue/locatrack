package com.amsatech.backend.lease;

import com.amsatech.backend.common.exception.BusinessConflictException;
import com.amsatech.backend.common.exception.NotFoundException;
import com.amsatech.backend.lease.dto.LeaseCaseRequest;
import com.amsatech.backend.lease.dto.LeaseCaseResponse;
import com.amsatech.backend.lease.dto.LeaseCaseUpdateRequest;
import com.amsatech.backend.lease.dto.LeaseStatusChangeRequest;
import com.amsatech.backend.lease.dto.LeaseStatusHistoryResponse;
import com.amsatech.backend.lease.mapper.LeaseCaseMapper;
import com.amsatech.backend.lease.mapper.LeaseStatusHistoryMapper;
import com.amsatech.backend.tenant.Tenant;
import com.amsatech.backend.tenant.TenantRepository;
import com.amsatech.backend.unit.Unit;
import com.amsatech.backend.unit.UnitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class LeaseCaseService {

    private final LeaseCaseRepository leaseCaseRepository;
    private final LeaseStatusHistoryRepository leaseStatusHistoryRepository;
    private final TenantRepository tenantRepository;
    private final UnitRepository unitRepository;
    private final LeaseCaseMapper leaseCaseMapper;
    private final LeaseStatusHistoryMapper leaseStatusHistoryMapper;

    public LeaseCaseService(LeaseCaseRepository leaseCaseRepository,
                            LeaseStatusHistoryRepository leaseStatusHistoryRepository,
                            TenantRepository tenantRepository,
                            UnitRepository unitRepository,
                            LeaseCaseMapper leaseCaseMapper,
                            LeaseStatusHistoryMapper leaseStatusHistoryMapper) {
        this.leaseCaseRepository = leaseCaseRepository;
        this.leaseStatusHistoryRepository = leaseStatusHistoryRepository;
        this.tenantRepository = tenantRepository;
        this.unitRepository = unitRepository;
        this.leaseCaseMapper = leaseCaseMapper;
        this.leaseStatusHistoryMapper = leaseStatusHistoryMapper;
    }

    @Transactional(readOnly = true)
    public List<LeaseCaseResponse> findAll() {
        return leaseCaseRepository.findAll()
                .stream()
                .map(leaseCaseMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public LeaseCaseResponse findById(Long id) {
        LeaseCase leaseCase = getLeaseCaseOrThrow(id);
        return leaseCaseMapper.toResponse(leaseCase);
    }

    @Transactional
    public LeaseCaseResponse create(LeaseCaseRequest request) {
        validateDates(request.getStartDate(), request.getEndDate());

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found: " + request.getTenantId()));

        Unit unit = unitRepository.findById(request.getUnitId())
                .orElseThrow(() -> new NotFoundException("Unit not found: " + request.getUnitId()));

        LeaseCase leaseCase = leaseCaseMapper.toEntity(request, tenant, unit);
        LeaseCase savedLeaseCase = leaseCaseRepository.save(leaseCase);

        appendStatusHistory(savedLeaseCase, null, LeaseStatus.DRAFT, "Lease case created as DRAFT");

        return leaseCaseMapper.toResponse(savedLeaseCase);
    }

    @Transactional
    public LeaseCaseResponse update(Long id, LeaseCaseUpdateRequest request) {
        validateDates(request.getStartDate(), request.getEndDate());

        LeaseCase leaseCase = getLeaseCaseOrThrow(id);

        if (leaseCase.getStatus() == LeaseStatus.TERMINATED || leaseCase.getStatus() == LeaseStatus.CANCELLED) {
            throw new BusinessConflictException("Cannot update a closed lease case");
        }

        leaseCaseMapper.updateEntity(leaseCase, request);
        LeaseCase savedLeaseCase = leaseCaseRepository.save(leaseCase);

        return leaseCaseMapper.toResponse(savedLeaseCase);
    }

    @Transactional
    public LeaseCaseResponse changeStatus(Long id, LeaseStatusChangeRequest request) {
        LeaseCase leaseCase = getLeaseCaseOrThrow(id);

        LeaseStatus currentStatus = leaseCase.getStatus();
        LeaseStatus targetStatus = request.getTargetStatus();

        validateStatusTransition(currentStatus, targetStatus);

        if (targetStatus == LeaseStatus.ACTIVE) {
            boolean unitAlreadyHasActiveLease = leaseCaseRepository.existsByUnitIdAndStatusAndIdNot(
                    leaseCase.getUnit().getId(),
                    LeaseStatus.ACTIVE,
                    leaseCase.getId()
            );

            if (unitAlreadyHasActiveLease) {
                throw new BusinessConflictException("Unit already has an active lease case");
            }
        }

        leaseCase.setStatus(targetStatus);
        LeaseCase savedLeaseCase = leaseCaseRepository.save(leaseCase);

        appendStatusHistory(savedLeaseCase, currentStatus, targetStatus, request.getReason());

        return leaseCaseMapper.toResponse(savedLeaseCase);
    }

    @Transactional(readOnly = true)
    public List<LeaseStatusHistoryResponse> findStatusHistory(Long leaseCaseId) {
        if (!leaseCaseRepository.existsById(leaseCaseId)) {
            throw new NotFoundException("Lease case not found: " + leaseCaseId);
        }

        return leaseStatusHistoryRepository.findByLeaseCaseIdOrderByChangedAtAsc(leaseCaseId)
                .stream()
                .map(leaseStatusHistoryMapper::toResponse)
                .toList();
    }

    private LeaseCase getLeaseCaseOrThrow(Long id) {
        return leaseCaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lease case not found: " + id));
    }

    private void appendStatusHistory(LeaseCase leaseCase, LeaseStatus fromStatus, LeaseStatus toStatus, String reason) {
        LeaseStatusHistory history = new LeaseStatusHistory();
        history.setLeaseCase(leaseCase);
        history.setFromStatus(fromStatus);
        history.setToStatus(toStatus);
        history.setChangedAt(Instant.now());
        history.setReason(reason);

        leaseStatusHistoryRepository.save(history);
    }

    private void validateStatusTransition(LeaseStatus currentStatus, LeaseStatus targetStatus) {
        if (currentStatus == targetStatus) {
            throw new BusinessConflictException("Lease case already has status: " + targetStatus);
        }

        boolean valid =
                (currentStatus == LeaseStatus.DRAFT && targetStatus == LeaseStatus.ACTIVE)
                        || (currentStatus == LeaseStatus.DRAFT && targetStatus == LeaseStatus.CANCELLED)
                        || (currentStatus == LeaseStatus.ACTIVE && targetStatus == LeaseStatus.TERMINATED)
                        || (currentStatus == LeaseStatus.ACTIVE && targetStatus == LeaseStatus.CANCELLED);

        if (!valid) {
            throw new BusinessConflictException(
                    "Invalid lease status transition: " + currentStatus + " -> " + targetStatus
            );
        }
    }

    private void validateDates(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new BusinessConflictException("End date cannot be before start date");
        }
    }
}