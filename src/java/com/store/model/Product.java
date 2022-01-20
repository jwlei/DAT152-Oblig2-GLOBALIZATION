package com.store.model;

public class Product{
    
    private int pno;
    private String name;
    private double price;
    private String picture;
    
    
	public Product(int pno, String name, double price, String picture) {
		super();
		this.pno = pno;
		this.name = name;
		this.price = price;
		this.picture = picture;
	}
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
    
    
	@Override
	public String toString() {
		return "Product [pno=" + pno + ", name=" + name + ", price=" + price + ", img=" + picture + "]";
	}
}
