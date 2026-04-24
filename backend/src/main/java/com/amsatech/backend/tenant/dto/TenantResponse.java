package com.amsatech.backend.tenant.dto;

public class TenantResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean active;

    public TenantResponse() {
    }

    public TenantResponse(Long id, String firstName, String lastName, String email, String phone, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}