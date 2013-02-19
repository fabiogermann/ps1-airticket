package org.zhaw.airticket.util;

import java.util.HashMap;
import java.util.Map;

public class Errors {
	
	public static int TYPE_EMAIL = 1;
	public static int TYPE_NUMBER = 0;
	
	private static Map<Integer, String> msgs = new HashMap<Integer, String>();
	private transient Map<String, String> errors = new HashMap<String, String>();
	
	{
		msgs.put(TYPE_EMAIL, "Bitte geben sie eine korrekte E-Mail Adresse an.");
		msgs.put(TYPE_NUMBER, "Bitte geben sie eine Zahl ein.");
	}
	

	public Map<String, String> getErrorMessages() {
		return errors;
	}

	public boolean isEmpty() {
		return errors.isEmpty();
	}

	public void putMsg(String property, String message) {
		errors.put(property, message);
	}
	
	public void putMsg(String property, int type) {
		errors.put(property, msgs.get(type));
	}

	public String getMsg(String property) {
		String message = errors.get(property);
		return message == null ? "" : message;
	}
}
