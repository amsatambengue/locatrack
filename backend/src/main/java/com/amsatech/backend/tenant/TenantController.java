package com.amsatech.backend.tenant;

import com.amsatech.backend.tenant.dto.TenantRequest;
import com.amsatech.backend.tenant.dto.TenantResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<TenantResponse> findAll() {
        return tenantService.findAll();
    }

    @GetMapping("/{id}")
    public TenantResponse findById(@PathVariable Long id) {
        return tenantService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantResponse create(@Valid @RequestBody TenantRequest request) {
        return tenantService.create(request);
    }

    @PatchMapping("/{id}")
    public TenantResponse update(@PathVariable Long id, @Valid @RequestBody TenantRequest request) {
        return tenantService.update(id, request);
    }
}