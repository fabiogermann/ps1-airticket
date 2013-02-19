package org.zhaw.airticket.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket {
	private int id;
	private Date abflugdatum;
	private int sitzreihe;
	private int sitzspalte;
	private String klasse;
	private Flug flug;
	private static final String[] alpha = { " ", "A", "B", "C", "D", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q" };
	public static final String datePattern = "dd.MM.yyyy";

	public Ticket(int id, Date abflugsdatum, int sitzreihe, int sitzspalte, String klasse, Flug flug) {
		super();
		this.id = id;
		this.abflugdatum = abflugsdatum;
		this.sitzreihe = sitzreihe;
		this.sitzspalte = sitzspalte;
		this.klasse = klasse;
		this.flug = flug;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSitzreihe() {
		return sitzreihe;
	}

	public void setSitzreihe(int sitzreihe) {
		this.sitzreihe = sitzreihe;
	}

	public int getSitzspalte() {
		return sitzspalte;
	}

	public String getSitzspalteAlpha() {
		return alpha[getSitzspalte()];
	}

	public void setSitzspalte(int sitzspalte) {
		this.sitzspalte = sitzspalte;
	}

	public String getKlasse() {
		return klasse;
	}

	public void setKlasse(String klasse) {
		this.klasse = klasse;
	}

	public Flug getFlug() {
		return flug;
	}

	public void setFlug(Flug flug) {
		this.flug = flug;
	}

	@Override
	public String toString() {
		String flugNummer = null;
		if (flug != null) {
			flugNummer = flug.getNummer();
		}
		return "Ticket: " + id + " " + abflugdatum + " " + sitzreihe + " " + sitzspalte + " " + klasse + " " + flugNummer;
	}

	public Date getAbflugdatum() {
		return abflugdatum;
	}

	public String getAbflugdatum(String pattern) {
		if (pattern == null || pattern.equals(""))
			pattern = datePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(abflugdatum);
	}

	public Date getAnkunftdatum() {
		Date ankunftsdatum = abflugdatum;

		if (flug.getAbflugzeit().getTime() > flug.getAnkunftzeit().getTime()) {
			Calendar c = Calendar.getInstance();
			c.setTime(ankunftsdatum);
			c.add(Calendar.DATE, 1);
			ankunftsdatum = c.getTime();
		}

		return ankunftsdatum;
	}

	public String getAnkunftdatum(String pattern) {
		if (pattern == null || pattern.equals(""))
			pattern = datePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(getAnkunftdatum());
	}

	public void setAbflugdatum(Date abflugdatum) {
		this.abflugdatum = abflugdatum;
	}

}
