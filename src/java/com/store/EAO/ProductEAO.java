package com.store.EAO;

import com.store.model.Product;

import java.util.ArrayList;

public class ProductEAO {

	ArrayList<Product> products = new ArrayList<Product>();
	
	public ProductEAO() {

		Product whitecup = new Product(1, "Black cup", 9, "img/whitecup.jpg");
		Product blackcup = new Product(2, "White cup", 29, "img/blackcup.jpg");

		products.add(whitecup);
		products.add(blackcup);
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	/**
	 * Finds and returns the product with the given product number if it exists.
	 * @param nr
	 * @return Product or null
	 */
	
	public Product findProduct(String nr) {
		for(int i = 0; i<products.size();i++) {
			try {
			if(products.get(i).getPno() == Integer.parseInt(nr)) {
				return products.get(i);
				}
			} catch(Exception e) {
				return null;
			}
		} return null;
	}
	
	public Product getProduct(int pno) {
		Product p = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getPno() == pno) {
				p = products.get(i);
				i = products.size();
			}
		} return p;
	}
}