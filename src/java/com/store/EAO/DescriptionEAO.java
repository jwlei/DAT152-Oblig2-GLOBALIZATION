package com.store.EAO;

import com.store.model.Description;

import java.util.ArrayList;
import java.util.Collections;

public class DescriptionEAO {
	
	ArrayList<Description> descriptions;
	
	
	public DescriptionEAO() {
		descriptions = new ArrayList<Description>();
		
		Description desc1 = new Description(1, "NB", "Svart Kopp - Vakker svart kopp for den anonyme.");
		Description desc2 = new Description(2, "NB", "Hvit Kopp- Vakker hvit kopp med fin glans.");
		Description desc3 = new Description(1, "EN", "Black Cup - Beautiful black cup for the anonymous.");
		Description desc4 = new Description(2, "EN", "White Cup - Beautiful white cup with nice shine.");
		Description desc5 = new Description(1, "DE", "Schwarze Tasse - Sch�ne schwarze Tasse f�r Anonyme.");
		Description desc6 = new Description(2, "DE", "Wei�e Tasse - Sch�ne wei�e Tasse mit sch�nem Glanz.");
		
		
		descriptions.add(desc1);
		descriptions.add(desc2);
		descriptions.add(desc3);
		descriptions.add(desc4);
		descriptions.add(desc5);
		descriptions.add(desc6);
	}


	public ArrayList<Description> getDescriptions() {
		return descriptions;
	}
	
	/**
	 * Get descriptions
	 * @param langCode
	 * @return returns the correct descriptions based on locale
	 */
	
	public ArrayList<Description> getDescriptionsByLangCode(String langCode) {
		ArrayList<Description> languageDescriptions = new ArrayList<Description>();
		for (Description d : descriptions) {
			if (d.getLangCode().equals(langCode)) {
				languageDescriptions.add(d);
			}
		}
		Collections.sort(languageDescriptions);
		return languageDescriptions;
	}
	
	
	
}