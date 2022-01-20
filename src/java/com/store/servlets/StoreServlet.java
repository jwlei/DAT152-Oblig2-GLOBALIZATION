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
 * Servlet implementation class WebShopServlet
 */
@WebServlet("/products")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductEAO productEAO = new ProductEAO();
	Cart cart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public StoreServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesjon = null;
		if (request.getSession(false) == null) {
			sesjon = request.getSession(true);
		} else {
			sesjon = request.getSession(false);
		}

		if (request.getSession().getAttribute("cart") == null) {
			cart = new Cart();
		} else {
			cart = (Cart) request.getSession().getAttribute("cart");
		}


		request.getSession().setAttribute("cart", cart);

		String localeString = "";
		
		Cookie[] cookies = request.getCookies();
		if(request.getCookies() != null) {

		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("locale")) {
				localeString = cookie.getValue();
				}
			}
		}
		
		if(localeString.equals("")) {
			Locale locale = request.getLocale();
			localeString = locale.toString();
		}
		
		
		request.getSession().setAttribute("language", localeString);

		
		String langCode = "";
		double currencyMultiplier = 1;
		if (localeString.equals("nb_NO")) {
			langCode = "NB";
			currencyMultiplier = 9.936;
		} else if (localeString.equals("en_GB")) {
			langCode = "EN";
			currencyMultiplier = 1.108;
		} else {
			langCode = "DE";
			//No multiplier since euro is standard.
		}
		

		DescriptionEAO descriptionEAO = new DescriptionEAO();
		ArrayList<Description> descriptions = descriptionEAO.getDescriptionsByLangCode(langCode);
		productEAO = new ProductEAO();
		ArrayList<Product> rightPriceTiles = productEAO.getProducts();
		for (Product p : rightPriceTiles) {
			p.setPrice(p.getPrice()*currencyMultiplier);
		}
		request.getSession().setAttribute("products", rightPriceTiles);
		request.getSession().setAttribute("descriptions", descriptions);

		request.getRequestDispatcher("WEB-INF/products.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		cart = (Cart) request.getSession().getAttribute("cart");
		Product cartItem;

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
			try {
				cartItem = productEAO.getProduct(Integer.parseInt(request.getParameter("pno")));

				cart.addItem(cartItem);
			} catch (Exception e) {
				System.out.println("Error");
			}
		} request.getSession().setAttribute("cart", cart);
		  response.sendRedirect("products");
	}
}
