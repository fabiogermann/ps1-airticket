package org.zhaw.airticket.model;

import java.util.HashMap;
import java.util.Map;

public class Benutzer {

	private String email = "";
	private String passwort = "";
	private String vorname = "";
	private String name = "";
	private String strasse = "";
	private String ort = "";
	private String postleitzahl = "";
	private String telefonnummer = "";
	private String land = "";
	private Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();
	
	public Benutzer() {
		
	}

	public Benutzer(String email, String passwort, String vorname, String name, String strasse, String ort, String postleitzahl, String telefonnummer, String land) {
		super();
		this.email = email;
		this.passwort = passwort;
		this.vorname = vorname;
		this.name = name;
		this.strasse = strasse;
		this.ort = ort;
		this.postleitzahl = postleitzahl;
		this.telefonnummer = telefonnummer;
		this.land = land;
	}
	
	public Benutzer(String email, String vorname, String name, String strasse, String ort, String postleitzahl, String telefonnummer, String land) {
		super();
		this.email = email;
		this.passwort = "";
		this.vorname = vorname;
		this.name = name;
		this.strasse = strasse;
		this.ort = ort;
		this.postleitzahl = postleitzahl;
		this.telefonnummer = telefonnummer;
		this.land = land;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPassword(String passwort) {
		this.passwort = passwort;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getPostleitzahl() {
		return postleitzahl;
	}

	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	
	public int getPostleitzahlInt() {
		return Integer.parseInt(postleitzahl);
	}
	
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public void addTicket(Ticket t) {
		tickets.put(t.getId(), t);
	}

	public Ticket getTicketById(int id) {
		return tickets.get(id);
	}
	
	public Ticket removeTicket(int key) {
		return tickets.remove(key);
	}

	public Map<Integer, Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Map<Integer, Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Benutzer: " + email + " " + vorname + " " + name + " " + strasse + " " + ort + " " + postleitzahl + " " + telefonnummer + " " + land;
	}

	
}
