package com.olx.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ADVERTISMENT")
public class AdvertisementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="price")
    private double price;
	@Column(name="category")
	private String category;
	@Column(name="description")
	private String description;
	@Column(name="username")
	private String username;
	@Column(name="createdDate")
	private String createdDate;
	@Column(name="modifiedDate")
	private String modifiedDate;
	@Column(name="status")
	private String status;
	
	public AdvertisementEntity() {
		super();
	}
	public AdvertisementEntity(int id, String title, double price, String category, String description, String username,
			String createdDate, String modifiedDate, String status) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.username = username;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}
	@Override
	public String toString() {
		return "AdvertisementEntity [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category
				+ ", description=" + description + ", username=" + username + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", status=" + status + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
}
