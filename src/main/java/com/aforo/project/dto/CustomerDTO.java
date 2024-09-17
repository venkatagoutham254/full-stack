package com.aforo.project.dto;

import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    private Long id;
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String first_name;
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String last_name;
    @Size(max = 50, message = "Company name must not exceed 50 characters")
    private String company_name;
    @Size(max = 50, message = "Phone must not exceed 50 characters")
    private String phone;
    private Timestamp created_at;
    private Timestamp updated_at;
    private UserDTO user;
    private List<SubscriptionDTO> subscriptions;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String first_name, String last_name, String company_name, String phone, Timestamp created_at, Timestamp updated_at, UserDTO user, List<SubscriptionDTO> subscriptions) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company_name = company_name;
        this.phone = phone;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
        this.subscriptions = subscriptions;
    }

	public CustomerDTO(Long id) {
		this.id=id;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<SubscriptionDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}
    

    
}
