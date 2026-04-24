package com.amsatech.backend.property.dto;

public class PropertyResponse {

    private Long id;
    private String name;
    private String address;
    private boolean active;

    public PropertyResponse() {
    }

    public PropertyResponse(Long id, String name, String address, boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}