package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Advertise Model")
public class Advertisement {
	@ApiModelProperty("Unique indentifier of the Advertise")
	private int id;
	@ApiModelProperty("Title of the Advertise")
	private String title;
	@ApiModelProperty("price of the Advertise")
	private double price;
	@ApiModelProperty("Category of the Advertise")
	private String category;
	@ApiModelProperty("Description of the Advertise")
	private String description;
	@ApiModelProperty("Username who added the product")
	private String username;
	@ApiModelProperty("Advertise created date")
	private String createdDate;
	@ApiModelProperty("Advertise modificatio date")
	private String modifiedDate;
	@ApiModelProperty("Status of the Advertise")
	private String status;
	
	public Advertisement() {
		super();
	}
	
	public Advertisement(int id, String title, double price, String category, 
			String description, String username,
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
		return "Advertise [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category
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
