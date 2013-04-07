package org.zhaw.airticket.model;

public class Flugzeug {

	private String modell;
	private int reihe_business;
	private int sitze_business;
	private int reihe_economy;
	private int sitze_economy;
	
	private Sitzplatz[][] business;
	private Sitzplatz[][] economy;

	public Flugzeug(String modell, int reihe_business, int sitze_business, int reihe_economy, int sitze_economy) {
		this.modell = modell;
		this.reihe_business = reihe_business;
		this.sitze_business = sitze_business;
		this.reihe_economy = reihe_economy;
		this.sitze_economy = sitze_economy;
		
		createSitzplaetze();
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}
	
	public int getReihe_business() {
		return reihe_business;
	}

	public void setReihe_business(int reihe_business) {
		this.reihe_business = reihe_business;
	}

	public int getSitze_business() {
		return sitze_business;
	}

	public void setSitze_business(int sitze_business) {
		this.sitze_business = sitze_business;
	}

	public int getReihe_economy() {
		return reihe_economy;
	}

	public void setReihe_economy(int reihe_economy) {
		this.reihe_economy = reihe_economy;
	}

	public int getSitze_economy() {
		return sitze_economy;
	}

	public void setSitze_economy(int sitze_economy) {
		this.sitze_economy = sitze_economy;
	}	

	public Sitzplatz[][] getBusinessSitzplaetze() {
		return business;
	}

	public Sitzplatz[][] getEconomySitzplaetze() {
		return economy;
	}

	@Override
	public String toString() {
		return "Flugzeug: " + modell + "Reihe Business" + reihe_business + "Sitze Business" + sitze_business + "Reihe Economy" + reihe_economy + "Sitze Economy" + sitze_economy;
	}
	
	private void createSitzplaetze() {
		business = new Sitzplatz[reihe_business][sitze_business];
		economy = new Sitzplatz[reihe_economy][sitze_economy];
		
		for (int reihe = 0; reihe < reihe_business; reihe++) {
			for (int sitz = 0; sitz < sitze_business; sitz++) {
				business[reihe][sitz] = new Sitzplatz("Business", reihe, sitz);
			}
		}
		
		for (int reihe = 0; reihe < reihe_economy; reihe++) {
			for (int sitz = 0; sitz < sitze_economy; sitz++) {
				economy[reihe][sitz] = new Sitzplatz("Economy", reihe, sitz);
			}
		}
	}


}
