package com.store.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/home")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			currencyMultiplier = 9.936;
			
		} else if (localeString.equals("en_GB")) {
			langCode = "EN";
			currencyMultiplier = 1.108;
			
		} else {
			langCode = "DE";
		}
		request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		} response.sendRedirect("home");
	}
}
