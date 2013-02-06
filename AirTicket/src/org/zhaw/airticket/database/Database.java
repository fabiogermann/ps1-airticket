package org.zhaw.airticket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zhaw.airticket.model.Benutzer;
import org.zhaw.airticket.model.Flug;
import org.zhaw.airticket.model.Flughafen;
import org.zhaw.airticket.model.Flugzeug;
import org.zhaw.airticket.model.Ticket;

public class Database {

	private Connection conn;
	private PreparedStatement insertBenutzer;
	private PreparedStatement insertRollen;
	private PreparedStatement selectBenutzer;
	private PreparedStatement selectTicketsByBenutzer;
	private PreparedStatement selectTicketsById;
	private PreparedStatement selectFlug;
	private PreparedStatement selectFlughafen;
	private PreparedStatement selectFlugzeug;

	public Database() {
		try {

			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/airdb");
			// conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
			conn = ds.getConnection();

			insertBenutzer = conn.prepareStatement("" + "INSERT INTO " + "Benutzer	(email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) " + "values	(?,?,?,?,?,?,?,?,?)");

			insertRollen = conn.prepareStatement("" + "INSERT INTO " + "Rolle		(email, rollen) " + "values	(?,'user') ");

			selectBenutzer = conn.prepareStatement("" + "SELECT email, vorname, name, strasse, ort, postleitzahl, telefonnummer, land " + "FROM Benutzer " + "WHERE email = ? ");

			selectTicketsByBenutzer = conn.prepareStatement("" + "SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM Ticket " + "WHERE benutzer = ? ");

			selectTicketsById = conn.prepareStatement("" + "SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM Ticket " + "WHERE id = ? ");

			selectFlug = conn.prepareStatement("" + "SELECT nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , geplant , flugzeug " + "FROM Flug " + "WHERE nummer = ? ");

			selectFlughafen = conn.prepareStatement("" + "SELECT code, name, stadt, land " + "FROM Flughafen " + "WHERE code = ? ");

			selectFlugzeug = conn.prepareStatement("" + "SELECT modell " + "FROM Flugzeug " + "WHERE modell = ? ");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public boolean addBenutzer(Benutzer benutzer) {
		try {
			insertBenutzer.setString(1, benutzer.getEmail());
			insertBenutzer.setString(2, benutzer.getPasswort());
			insertBenutzer.setString(3, benutzer.getVorname());
			insertBenutzer.setString(4, benutzer.getName());
			insertBenutzer.setString(5, benutzer.getStrasse());
			insertBenutzer.setString(6, benutzer.getOrt());
			insertBenutzer.setInt(7, benutzer.getPostleitzahl());
			insertBenutzer.setLong(8, benutzer.getTelefonnummer());
			insertBenutzer.setString(9, benutzer.getLand());

			insertRollen.setString(1, benutzer.getEmail());

			insertBenutzer.executeUpdate();
			insertRollen.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return true;
	}

	public Benutzer getBenutzer(String email) {
		Benutzer benutzer = null;
		try {

			selectBenutzer.setString(1, email);
			ResultSet benutzerRs = selectBenutzer.executeQuery();
			if (benutzerRs.next()) {
				benutzer = new Benutzer(benutzerRs.getString("email"), benutzerRs.getString("vorname"), benutzerRs.getString("name"), benutzerRs.getString("strasse"), benutzerRs.getString("ort"), benutzerRs.getInt("postleitzahl"), benutzerRs.getLong("telefonnummer"), benutzerRs.getString("land"));
			} else {
				System.out.println("Database: Benutzer NICHT gefunden. email = " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		Map<Integer, Ticket> tickets = getTickets(email);
		benutzer.setTickets(tickets);
		
		return benutzer;
	}

	public Map<Integer, Ticket> getTickets(String email) {
		Map<Integer, Ticket> tickets = null;
		try {
			selectTicketsByBenutzer.setString(1, email);
			ResultSet ticketRs = selectTicketsByBenutzer.executeQuery();
			tickets = new HashMap<Integer, Ticket>();
			while (ticketRs.next()) {
				Flug flug = getFlug(ticketRs.getString("flug"));
				Ticket ticket = new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), flug);
				tickets.put(ticket.getId(), ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public Ticket getTicket(int id) {
		Ticket ticket = null;
		try {
			selectTicketsById.setInt(1, id);
			ResultSet ticketRs = selectTicketsById.executeQuery();
			Flug flug = getFlug(ticketRs.getString("flug"));
			ticket = new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), flug);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public Flug getFlug(String nummer) {
		Flug flug = null;
		try {
			selectFlug.setString(1, nummer);
			ResultSet flugRs = selectFlug.executeQuery();
			if (flugRs.next()) {
				Flughafen FlughafenVon = getFlughafen(flugRs.getString("von"));
				Flughafen FlughafenNach = getFlughafen(flugRs.getString("nach"));
				Flugzeug flugzeug = getFlugzeug(flugRs.getString("flugzeug"));
				flug = new Flug(flugRs.getString("nummer"), FlughafenVon, FlughafenNach, flugRs.getTime("abflugzeit"), flugRs.getTime("ankunftzeit"), flugRs.getTime("dauer"), flugRs.getInt("businessPreis"), flugRs.getInt("economyPreis"), flugRs.getString("geplant"), flugzeug);
			} else {
				System.out.println("Database: Flug NICHT gefunden. nummer = " + nummer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flug;
	}

	public Flughafen getFlughafen(String code) {
		Flughafen flughafen = null;
		try {
			selectFlughafen.setString(1, code);
			ResultSet flughafenRs = selectFlughafen.executeQuery();
			if (flughafenRs.next()) {
				flughafen = new Flughafen(flughafenRs.getString("code"), flughafenRs.getString("name"), flughafenRs.getString("stadt"), flughafenRs.getString("land"));
			} else {
				System.out.println("Database: Flughafen NICHT gefunden. code = " + code);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flughafen;
	}

	public Flugzeug getFlugzeug(String modell) {
		Flugzeug flugzeug = null;
		try {
			selectFlugzeug.setString(1, modell);
			ResultSet flugzeugRs = selectFlugzeug.executeQuery();

			if (flugzeugRs.next()) {
				flugzeug = new Flugzeug(flugzeugRs.getString("modell"));
			} else {
				System.out.println("Database: Flugzeug NICHT gefunden. modell = " + modell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flugzeug;
	}

}
