package com.aforo.project.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.aforo.project.model.Subscription;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
public class SubscriptionDTO {
	@NotNull(message = "Product ID is required")
    private Long id;
    private Long product_id;
    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate start_date;
    @NotNull(message = "End date is required")
    private LocalDate end_date;
    @NotNull(message = "Status is required")
    private Subscription.Status status;
    private Timestamp created_at;
    private Timestamp updated_at;
    @NotNull(message = "Customer information is required")
    private CustomerDTO customer;

    public enum Status {
        ACTIVE, CANCELLED, EXPIRED
    }

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long id, Long product_id, LocalDate start_date, LocalDate end_date, Subscription.Status status, Timestamp created_at, Timestamp updated_at, CustomerDTO customer) {
        this.id = id;
        this.product_id = product_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.customer = customer;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public Subscription.Status getStatus() {
		return status;
	}

	public void setStatus(Subscription.Status status) {
		this.status = status;
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

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

    
}
