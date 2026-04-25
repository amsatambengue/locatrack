package com.amsatech.backend.lease;

import com.amsatech.backend.lease.dto.LeaseCaseRequest;
import com.amsatech.backend.lease.dto.LeaseCaseResponse;
import com.amsatech.backend.lease.dto.LeaseCaseUpdateRequest;
import com.amsatech.backend.lease.dto.LeaseStatusChangeRequest;
import com.amsatech.backend.lease.dto.LeaseStatusHistoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lease-cases")
public class LeaseCaseController {

    private final LeaseCaseService leaseCaseService;

    public LeaseCaseController(LeaseCaseService leaseCaseService) {
        this.leaseCaseService = leaseCaseService;
    }

    @GetMapping
    public List<LeaseCaseResponse> findAll() {
        return leaseCaseService.findAll();
    }

    @GetMapping("/{id}")
    public LeaseCaseResponse findById(@PathVariable Long id) {
        return leaseCaseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaseCaseResponse create(@Valid @RequestBody LeaseCaseRequest request) {
        return leaseCaseService.create(request);
    }

    @PatchMapping("/{id}")
    public LeaseCaseResponse update(@PathVariable Long id, @Valid @RequestBody LeaseCaseUpdateRequest request) {
        return leaseCaseService.update(id, request);
    }

    @PostMapping("/{id}/status-changes")
    public LeaseCaseResponse changeStatus(@PathVariable Long id,
                                          @Valid @RequestBody LeaseStatusChangeRequest request) {
        return leaseCaseService.changeStatus(id, request);
    }

    @GetMapping("/{id}/status-history")
    public List<LeaseStatusHistoryResponse> findStatusHistory(@PathVariable Long id) {
        return leaseCaseService.findStatusHistory(id);
    }
}