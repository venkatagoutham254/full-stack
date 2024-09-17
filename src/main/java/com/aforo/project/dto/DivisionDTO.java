package com.aforo.project.dto;

import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;

public class DivisionDTO {
    private Long id;
    @NotBlank(message = "Division name is required")
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
    private OrganizationDTO organization;  // Changed to single OrganizationDTO

    public DivisionDTO() {}

    public DivisionDTO(Long id, String name, Timestamp created_at, Timestamp updated_at, OrganizationDTO organization) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.organization = organization;  // Set the organization DTO
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }
}
