package org.zhaw.airticket.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Sitzplatz {

	private String klasse;
	private int reihe;
	private int sitz;
	private boolean reserviert;
	private static final String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q" };
	
	private PropertyChangeSupport props = new PropertyChangeSupport(this);
	
	public Sitzplatz(String klasse, int reihe, int sitz) {
		this.klasse = klasse;
		this.reihe = reihe;
		this.sitz = sitz;
		reserviert = false;
	}

	public void setReserviert(boolean reserviert) {
		this.reserviert = reserviert;
		props.firePropertyChange(klasse + " | " + reihe + " | " + sitz, false, true);
	}
	
	public boolean isReserviert() {
		return reserviert;
	}
	
	public String getKlasse() {
		return klasse;
	}

	public int getReihe() {
		return reihe;
	}

	public int getSitz() {
		return sitz;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		props.addPropertyChangeListener(listener);
	}
	
	public String getSitznummer() {
		return (reihe + 1) + alpha[sitz];		
	}
	
}
