package com.amsatech.backend.unit;

import com.amsatech.backend.property.Property;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "units",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_unit_property_reference",
                        columnNames = {"property_id", "reference"}
                )
        }
)
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String reference;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monthlyRent;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    public Unit() {
    }

    public Unit(Long id, String reference, BigDecimal monthlyRent, boolean active, Property property) {
        this.id = id;
        this.reference = reference;
        this.monthlyRent = monthlyRent;
        this.active = active;
        this.property = property;
    }

    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public boolean isActive() {
        return active;
    }

    public Property getProperty() {
        return property;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}