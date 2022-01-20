package com.store.utility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class TagShortenDesc extends SimpleTagSupport {

	private StringWriter sw = new StringWriter();
	private String maxChars;

	public void setMaxChars(String maxChars) {
		this.maxChars = maxChars;
	}

	public String getMaxChars() {
		return maxChars;
	}

	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		if (maxChars == null) {
			out.println("Error");
		} else {
			getJspBody().invoke(sw);
			String toBePrinted = sw.toString();
			int max = Integer.parseInt(maxChars);
			toBePrinted = toBePrinted.substring(0, max) + " ...";
			out.println(toBePrinted);
		}
	}
}
