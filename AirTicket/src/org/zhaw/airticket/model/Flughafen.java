package org.zhaw.airticket.model;

public class Flughafen {

	private String code;
	private String name;
	private String stadt;
	private String land;

	public Flughafen(String code, String name, String stadt, String land) {
		super();
		this.code = code;
		this.name = name;
		this.stadt = stadt;
		this.land = land;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		return "Flughafen: " + code + " " + name + " " + stadt + " " + land;
	}

}
