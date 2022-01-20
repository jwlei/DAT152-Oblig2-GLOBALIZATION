package com.store.utility;

import com.store.EAO.ProductEAO;
import com.store.model.Cart;
import com.store.model.Product;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StoreVanillaJava {

	public static void main(String[] args) {

		ProductEAO productEAO = new ProductEAO();
		Cart cart = new Cart();
		System.out.println("Current Locale: " + Locale.getDefault());
		ResourceBundle storeBundle = ResourceBundle.getBundle("store");
		ArrayList<Product> products = productEAO.getProducts();
		Scanner scanner = new Scanner(System.in);
		System.out.println(storeBundle.getString("welcome_message"));
		boolean finished = false;
		while (!finished) {
			
			
			System.out.println(storeBundle.getString("new_input_message"));
			
			String input = scanner.nextLine();

			switch (input) {
			case "call":
				for (int i = 0; i < products.size(); i++) {
					System.out.println(products.get(i).toString());
				}
				break;
			case "add":
				try {
					int nr = scanner.nextInt();
					scanner.nextLine();
					for (int j = 0; j < products.size(); j++) {
						if (products.get(j).getPno() == nr) {
							cart.addItem(products.get(j));
							System.out.println(storeBundle.getString("vare_added_message"));
						}
					}

				} catch (Exception e) {
					System.out.println("Skriv vare add, enter, varetall");scanner.nextLine();
				}
				break;
			case "cart":
				System.out.println(storeBundle.getString("varer_i_kurv_message"));
				for (int i = 0; i < cart.getItems().size(); i++) {
					System.out.println(cart.getItems().get(i));
				}
				break;
			case "help":
				System.out.println(storeBundle.getString("help_message"));
				break;
			case "done":
				finished = true;
				System.out.println(storeBundle.getString("welcome_back_message"));
				break;
			case "german":
				System.out.println("Wechselt zu Deutsch");
				Locale.setDefault(new Locale("de", "DE"));
				storeBundle = ResourceBundle.getBundle("store");
				break;
				
			case "english":
				System.out.println("Changing to English");
				Locale.setDefault(new Locale("en", "GB"));
				storeBundle = ResourceBundle.getBundle("store");
				break;
			case "norsk":
				System.out.println("Bytter til norsk");
				Locale.setDefault(new Locale("nb", "NO"));
				storeBundle = ResourceBundle.getBundle("store");
				break;
			default:
				System.out.println(storeBundle.getString("uknown_command_message"));
				break;
			}

		}
		scanner.close();

	}

}
