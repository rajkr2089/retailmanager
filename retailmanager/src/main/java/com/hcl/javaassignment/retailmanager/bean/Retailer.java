package com.hcl.javaassignment.retailmanager.bean;

public class Retailer {
	
	//postal code is String just to make sure that it can accept hyphen. Hyphen can be take care on screen also
	private String shopName	=	null;
	private Integer number	=	null;
	
	//postal code is String just to make sure that it can accept hyphen. Hyphen can be take care on screen also
	private String postCode	=	null;
	private String longtitude	=	null;
	private String lattitude	=	null;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	
	
}
