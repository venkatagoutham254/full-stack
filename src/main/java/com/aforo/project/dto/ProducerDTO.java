package com.aforo.project.dto;

import java.sql.Timestamp;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ProducerDTO {
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;
    private Timestamp updated_at;
   

    public ProducerDTO() {
    }

    public ProducerDTO(Long id, String name, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
       
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

	
	//timeStamp generation
	public void timeStampGeneration() {
	    this.created_at = new Timestamp(System.currentTimeMillis());
	}


    
}
