package org.zhaw.airticket.model;

public class Flugzeug {

	private String modell;

	public Flugzeug(String modell) {
		this.modell = modell;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	@Override
	public String toString() {
		return "Flugzeug: " + modell;
	}

}
