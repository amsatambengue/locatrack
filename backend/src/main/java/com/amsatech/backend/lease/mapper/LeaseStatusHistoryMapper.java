package com.amsatech.backend.lease.mapper;

import com.amsatech.backend.lease.LeaseStatusHistory;
import com.amsatech.backend.lease.dto.LeaseStatusHistoryResponse;
import org.springframework.stereotype.Component;

@Component
public class LeaseStatusHistoryMapper {

    public LeaseStatusHistoryResponse toResponse(LeaseStatusHistory history) {
        return new LeaseStatusHistoryResponse(
                history.getId(),
                history.getLeaseCase().getId(),
                history.getFromStatus(),
                history.getToStatus(),
                history.getChangedAt(),
                history.getReason()
        );
    }
}