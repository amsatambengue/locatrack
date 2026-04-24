package com.amsatech.backend.tenant.mapper;

import com.amsatech.backend.tenant.Tenant;
import com.amsatech.backend.tenant.dto.TenantRequest;
import com.amsatech.backend.tenant.dto.TenantResponse;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

    public Tenant toEntity(TenantRequest request) {
        Tenant tenant = new Tenant();
        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setEmail(request.getEmail());
        tenant.setPhone(request.getPhone());
        tenant.setActive(request.getActive() != null ? request.getActive() : true);
        return tenant;
    }

    public TenantResponse toResponse(Tenant tenant) {
        return new TenantResponse(
                tenant.getId(),
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getPhone(),
                tenant.isActive()
        );
    }

    public void updateEntity(Tenant tenant, TenantRequest request) {
        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setEmail(request.getEmail());
        tenant.setPhone(request.getPhone());

        if (request.getActive() != null) {
            tenant.setActive(request.getActive());
        }
    }
}