package com.store.servlets;

import com.store.EAO.DescriptionEAO;
import com.store.EAO.ProductEAO;
import com.store.model.Cart;
import com.store.model.Description;
import com.store.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	ProductEAO productEAO = new ProductEAO();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		HttpSession session = null;

		String localeString = "";

		Cookie[] cookies = request.getCookies();
		if (request.getCookies() != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("locale")) {
					localeString = cookie.getValue();
				}
			}
		}
		if (localeString.equals("")) {
			Locale locale = request.getLocale();
			localeString = locale.toString();
		}

		request.getSession().setAttribute("language", localeString);

		String langCode = "";
		double currencyMultiplier = 1;
		
		if (localeString.equals("nb_NO")) {
			langCode = "NB";
			currencyMultiplier = 9.97;
			
		} else if (localeString.equals("en_GB")) {
			langCode = "EN";
			currencyMultiplier = 0.85;
			
		} else {
			langCode = "DE";
		}

		
		DescriptionEAO descriptionEAO = new DescriptionEAO();
		ArrayList<Description> allDescriptions = descriptionEAO.getDescriptions();
		Cart cart;
		
		if (request.getSession().getAttribute("cart") == null) {
			cart = new Cart();
		} else {
			cart = (Cart) request.getSession().getAttribute("cart");
		}
		
		ArrayList<Product> cartItems = cart.getItems();
		ArrayList<Description> descriptions = new ArrayList<Description>();
		
		for (Product p : cartItems) {
			for (int i = 0; i < allDescriptions.size(); i++) {
				if (p.getPno() == allDescriptions.get(i).getPno()
					&& allDescriptions.get(i).getLangCode().equals(langCode)) {
						descriptions.add(allDescriptions.get(i));
						i = allDescriptions.size();
				}
			}
		}

		//Converting currency from Euro
		productEAO = new ProductEAO();
		ArrayList<Product> fromEuroPrice = cartItems;
		
		for (Product p : fromEuroPrice) {
			Product defaultPrice = productEAO.getProduct(p.getPno());
			p.setPrice(defaultPrice.getPrice());
			p.setPrice(p.getPrice() * currencyMultiplier);
		}
		cart.setItems(fromEuroPrice);
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("descriptions", descriptions);
		request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String removeThis = "";

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		String newLanguage = request.getParameter("language");
		if (newLanguage != null) {
			if (request.getCookies() != null) {

				Cookie[] cookies = request.getCookies();
				boolean found = false;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("locale")) {
						found = true;
						cookie.setValue(newLanguage);
						response.addCookie(cookie);
					}
				}
				if (!found) {
					Cookie newLang = new Cookie("locale", newLanguage);
					response.addCookie(newLang);
				}
			}
		}

		
		if (request.getParameter("pno") != null) {
			removeThis = request.getParameter("pno");
			if (productEAO.findProduct(removeThis) != null) {
				cart.removeItem(productEAO.findProduct(removeThis));

				request.getSession().setAttribute("cart", cart);
			} else {
				response.sendRedirect("cart");
			}
		} response.sendRedirect("cart");
	}
}
