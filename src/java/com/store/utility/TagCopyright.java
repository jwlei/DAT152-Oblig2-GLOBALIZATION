package com.store.utility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.TreeMap;

public class TagCopyright extends SimpleTagSupport {
	
	
	private String since;
	private StringWriter sw = new StringWriter();
	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }
	
	public  String getSince() {
		return since;
	}
	public void setSince(String since) {
	      this.since = since;
	}
	
	
   /* Prints the custom tag  */
public void doTag() throws JspException, IOException {
	   
	   JspWriter out = getJspContext().getOut();
	   if(since == null) {
		   out.println("Error");
	   } else {
		   getJspBody().invoke(sw);
		   int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		   int sinceYear = Integer.parseInt(since);
		   String toPrint = "© " + toRoman(sinceYear) + "-" + toRoman(currentYear) + " " + sw.toString();
		   out.println(toPrint);
	   }
       
      
      
   }
   /**
    * Takes an integer and converts to to roman numerals
 * @param number
 * @return
 */
public final static String toRoman(int number) {
       int l =  map.floorKey(number);
       if ( number == l ) {
           return map.get(number);
       }
       return map.get(l) + toRoman(number-l);
   }

}
