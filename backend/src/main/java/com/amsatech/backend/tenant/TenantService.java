package com.amsatech.backend.tenant;

import com.amsatech.backend.common.exception.NotFoundException;
import com.amsatech.backend.tenant.dto.TenantRequest;
import com.amsatech.backend.tenant.dto.TenantResponse;
import com.amsatech.backend.tenant.mapper.TenantMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    public TenantService(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    @Transactional(readOnly = true)
    public List<TenantResponse> findAll() {
        return tenantRepository.findAll()
                .stream()
                .map(tenantMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TenantResponse findById(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tenant not found: " + id));

        return tenantMapper.toResponse(tenant);
    }

    @Transactional
    public TenantResponse create(TenantRequest request) {
        Tenant tenant = tenantMapper.toEntity(request);
        Tenant savedTenant = tenantRepository.save(tenant);
        return tenantMapper.toResponse(savedTenant);
    }

    @Transactional
    public TenantResponse update(Long id, TenantRequest request) {
        Tenant existingTenant = tenantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tenant not found: " + id));

        tenantMapper.updateEntity(existingTenant, request);
        Tenant savedTenant = tenantRepository.save(existingTenant);

        return tenantMapper.toResponse(savedTenant);
    }
}