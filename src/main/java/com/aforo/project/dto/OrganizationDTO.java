package com.aforo.project.dto;

import java.sql.Timestamp;
import java.util.List;

public class OrganizationDTO {
    private Long id;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
    private ProducerDTO producer;
    private List<DivisionDTO> divisions;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Long id, String name, Timestamp created_at, Timestamp updated_at, ProducerDTO producer, List<DivisionDTO> divisions) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.producer = producer;
        this.divisions = divisions;
    }

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

	public ProducerDTO getProducer() {
		return producer;
	}

	public void setProducer(ProducerDTO producer) {
		this.producer = producer;
	}

	public List<DivisionDTO> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<DivisionDTO> divisions) {
		this.divisions = divisions;
	}
	

    
}
