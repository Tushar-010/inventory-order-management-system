package com.ioms.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity 
@Table(name = "categories")
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Category {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=100, name="name")
    private String name;

    @Column(columnDefinition = "TEXT", name="description")
    private String description;

    @Column(nullable=false, name="created_at")
    private Date createdAt;

    @Column(nullable=false,name="updated_at")
    private Date updatedAt;
    
    @Column(name="status")
    private String status;
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createdAt = now;
        updatedAt = now;
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}