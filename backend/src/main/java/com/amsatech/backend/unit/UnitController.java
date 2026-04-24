package com.amsatech.backend.unit;

import com.amsatech.backend.unit.dto.UnitRequest;
import com.amsatech.backend.unit.dto.UnitResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<UnitResponse> findAll(@RequestParam(required = false) Long propertyId) {
        if (propertyId != null) {
            return unitService.findByPropertyId(propertyId);
        }

        return unitService.findAll();
    }

    @GetMapping("/{id}")
    public UnitResponse findById(@PathVariable Long id) {
        return unitService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnitResponse create(@Valid @RequestBody UnitRequest request) {
        return unitService.create(request);
    }

    @PatchMapping("/{id}")
    public UnitResponse update(@PathVariable Long id, @Valid @RequestBody UnitRequest request) {
        return unitService.update(id, request);
    }
}