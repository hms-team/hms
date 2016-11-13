package com.larry.hms.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MedicineAmt {
	private int medId;
	private String name;
	private String type; 
	private int amountWarehouse;
	private int amountStorehouse;
	private String unit;
	private Date expiryDate;
	private String place;
	private BigDecimal price;
	public int getMedId() {
		return medId;
	}
	public void setMedId(int medId) {
		this.medId = medId;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmountWarehouse() {
		return amountWarehouse;
	}
	public void setAmountWarehouse(int amountWarehouse) {
		this.amountWarehouse = amountWarehouse;
	}
	public int getAmountStorehouse() {
		return amountStorehouse;
	}
	public void setAmountStorehouse(int amountStorehouse) {
		this.amountStorehouse = amountStorehouse;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Medicine [medId=" + medId + ", name=" + name + ", type=" + type + ", amountWarehouse=" + amountWarehouse
				+ ", amountStorehouse=" + amountStorehouse + ", unit=" + unit + ", expiryDate=" + expiryDate
				+ ", place=" + place + ", price=" + price + "]";
	}
	
	
}
