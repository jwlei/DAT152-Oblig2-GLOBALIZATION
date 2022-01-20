package com.store.model;

import java.util.ArrayList;


public class Cart {
	
	ArrayList<Product> CartItems;
	
	public Cart() {
		this.CartItems = new ArrayList<Product>();
	}
	
	public ArrayList<Product> getItems() {
		return CartItems;
	}
	public void setItems(ArrayList<Product> items) {
		CartItems = items;
	}
	public void addItem(Product product) {
		CartItems.add(product);
	}
	/**
	 * Removes an object from the cart
	 * @param product
	 */
	public void removeItem(Product product) {
		for (int i = 0; i < CartItems.size(); i++) {
			if (CartItems.get(i).getPno() == product.getPno()) {
				CartItems.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Prints all objects in the cart
	 */
	public void printAllItems() {
		for (Product p : CartItems) {
			System.out.println(p);
		}
	}
	
}
