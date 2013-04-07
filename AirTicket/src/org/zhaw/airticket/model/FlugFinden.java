package org.zhaw.airticket.model;

public class FlugFinden {

	private String departure = "";
	private String departure_code = "";
	private String destination = "";
	private String destination_code = "";
	private String date_flight = "";
	private String date_returnflight = "";
	private String date_tolerance = "0";
	private String flightnr = "";
	private String flightnr_code = "";

	public FlugFinden() {

	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDeparture_code() {
		return departure_code;
	}

	public void setDeparture_code(String departure_code) {
		this.departure_code = departure_code;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination_code() {
		return destination_code;
	}

	public void setDestination_code(String destination_code) {
		this.destination_code = destination_code;
	}

	public String getDate_flight() {
		return date_flight;
	}

	public void setDate_flight(String date_flight) {
		this.date_flight = date_flight;
	}

	public String getDate_returnflight() {
		return date_returnflight;
	}

	public void setDate_returnflight(String date_returnflight) {
		this.date_returnflight = date_returnflight;
	}

	public String getDate_tolerance() {
		return date_tolerance;
	}

	public void setDate_tolerance(String date_tolerance) {
		this.date_tolerance = date_tolerance;
	}

	public String getFlightnr() {
		return flightnr;
	}

	public void setFlightnr(String flightnr) {
		this.flightnr = flightnr;
	}

	public String getFlightnr_code() {
		return flightnr_code;
	}

	public void setFlightnr_code(String flightnr_code) {
		this.flightnr_code = flightnr_code;
	}
}
