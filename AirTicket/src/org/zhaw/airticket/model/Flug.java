package org.zhaw.airticket.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Flug {

	private String nummer;
	private Flughafen von;
	private Flughafen nach;
	private Date abflugzeit;
	private Date ankunftzeit;
	private Date dauer;
	private int businessPreis;
	private int economyPreis;
	private String geplant;
	private Flugzeug flugzeug;
	public static final String timePattern = "HH:mm";
	public static final String dateConvertPattern = "yyyy-MM-dd";
	public static final String dateFormatPattern = "dd.MM.yyyy";

	public Flug(String nummer, Flughafen von, Flughafen nach, Date abflugzeit, Date ankunftzeit, Date dauer, int businessPreis, int economyPreis, String geplant, Flugzeug flugzeug) {
		super();
		this.nummer = nummer;
		this.von = von;
		this.nach = nach;
		this.abflugzeit = abflugzeit;
		this.ankunftzeit = ankunftzeit;
		this.dauer = dauer;
		this.businessPreis = businessPreis;
		this.economyPreis = economyPreis;
		this.geplant = geplant;
		this.flugzeug = flugzeug;
	}

	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public Flughafen getVon() {
		return von;
	}

	public void setVon(Flughafen von) {
		this.von = von;
	}

	public Flughafen getNach() {
		return nach;
	}

	public void setNach(Flughafen nach) {
		this.nach = nach;
	}

	public Date getDauer() {
		return dauer;
	}

	public String getDauer(String pattern) {
		if (pattern == null || pattern.equals(""))
			pattern = timePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(dauer);
	}

	public void setDauer(Date dauer) {
		this.dauer = dauer;
	}

	public int getBusinessPreis() {
		return businessPreis;
	}

	public void setBusinessPreis(int businessPreis) {
		this.businessPreis = businessPreis;
	}

	public int getEconomyPreis() {
		return economyPreis;
	}

	public void setEconomyPreis(int economyPreis) {
		this.economyPreis = economyPreis;
	}

	public String getGeplant() {
		return geplant;
	}

	public void setGeplant(String geplant) {
		this.geplant = geplant;
	}

	public Flugzeug getFlugzeug() {
		return flugzeug;
	}

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	public Date getAbflugzeit() {
		return abflugzeit;
	}

	public void setAbflugzeit(Date abflugzeit) {
		this.abflugzeit = abflugzeit;
	}

	public Date getAnkunftzeit() {
		return ankunftzeit;
	}

	public void setAnkunftzeit(Date ankunftzeit) {
		this.ankunftzeit = ankunftzeit;
	}
	
	public Date getGeplantDate(String pattern) {		
		if (pattern == null || pattern.equals(""))
			pattern = dateConvertPattern;
		SimpleDateFormat converter = new SimpleDateFormat(dateConvertPattern);	
		try {
			return converter.parse(geplant);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}		
	}
	
	public String getAbflugdatum(String pattern) {
		SimpleDateFormat converter = new SimpleDateFormat(dateConvertPattern);
		if (pattern == null || pattern.equals(""))
			pattern = dateFormatPattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);	
		try {
			Date abflugdatum = converter.parse(geplant);
			return formatter.format(abflugdatum);
		} catch (ParseException e) {
			e.printStackTrace();
			return geplant;
		}		
	}
	
	public String getAnkunftdatum(String pattern) {
		SimpleDateFormat converter = new SimpleDateFormat(dateConvertPattern);
		if (pattern == null || pattern.equals(""))
			pattern = dateFormatPattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);		
		try {
			Date ankunftdatum = converter.parse(geplant);
			if (abflugzeit.getTime() > ankunftzeit.getTime()) {
				Calendar c = Calendar.getInstance();
				c.setTime(ankunftdatum);
				c.add(Calendar.DATE, 1);
				ankunftdatum = c.getTime();
			}			
			return formatter.format(ankunftdatum);
		} catch (ParseException e) {
			e.printStackTrace();
			return geplant;
		}
	}

	public String getAbflugzeit(String pattern) {
		if (pattern == null || pattern.equals(""))
			pattern = timePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(abflugzeit);
	}

	public String getAnkunftzeit(String pattern) {
		if (pattern == null || pattern.equals(""))
			pattern = timePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(ankunftzeit);
	}

	@Override
	public String toString() {

		String flughafenVon = null;
		if (von != null) {
			flughafenVon = von.getCode();
		}

		String flughafenNach = null;
		if (von != null) {
			flughafenNach = nach.getCode();
		}

		String flugzeugModell = null;
		if (flugzeug != null) {
			flugzeugModell = flugzeug.getModell();
		}

		return "Flug: " + nummer + " " + flughafenVon + " " + flughafenNach + " " + abflugzeit + " " + ankunftzeit + " " + dauer + " " + businessPreis + " " + economyPreis + " " + geplant + " " + flugzeugModell;

	}

}
