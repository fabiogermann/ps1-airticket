package org.zhaw.airticket.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	private static ConnectionFactory instance = new ConnectionFactory();
	private static final String CONTEXT = "java:comp/env";
	private static final String DATABASE_PATH = "jdbc/airdb";

	protected static final String SELECT_BENUTZER = "SELECT email, vorname, name, strasse, ort, postleitzahl, telefonnummer, land " + "FROM benutzer " + "WHERE email = ? ";
	protected static final String INSERT_BENUTZER = "INSERT INTO benutzer (email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) " + "values  (?,?,?,?,?,?,?,?,?)";
	protected static final String INSERT_ROLLEN = "INSERT INTO rolle (email, rollen) " + "values (?, 'user') ";
	protected static final String SELECT_TICKETS_BY_BENUTZER = "SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM ticket " + "WHERE benutzer = ? ";
	protected static final String SELECT_TICKETS_BY_ID = "SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM ticket " + "WHERE id = ? ";
	protected static final String SELECT_TICKETS_BY_FLUG = "SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM ticket, geplant " + "WHERE flug = ? " + "AND klasse = ? " + "AND geplant = ? " + "AND flug = nummer " + "AND geplant = abflugdatum";
	protected static final String INSERT_TICKET = "INSERT INTO ticket (abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) " + "values (?,?,?,?,?,?)";
	protected static final String UPDATE_TICKET = "UPDATE ticket " + "SET sitzreihe = ?, sitzspalte = ? " + "WHERE id = ? ";
	protected static final String DELETE_TICKET = "DELETE FROM ticket WHERE id = ?";
	protected static final String SELECT_FLUG = "SELECT nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , flugzeug " + "FROM flug " + "WHERE nummer = ? ";
	protected static final String SELECT_FLUGHAFEN = "SELECT code, name, stadt, land " + "FROM flughafen " + "WHERE code = ? ";
	protected static final String SELECT_FLUGZEUG = "SELECT modell, reihe_business, sitze_business, reihe_economy, sitze_economy " + "FROM flugzeug " + "WHERE modell = ? ";
	protected static final String SELECT_FLUGSUCHE = "SELECT f.nummer AS nummer, f.von AS von , f.nach AS nach , f.abflugzeit AS abflugzeit , f.ankunftzeit AS ankunftzeit , f.dauer AS dauer , f.businesspreis AS businesspreis , f.economypreis AS economypreis , g.geplant AS geplant, f.flugzeug AS flugzeug FROM flug f, geplant g, (SELECT DATE(ADDDATE(?, INTERVAL -? DAY)) AS date) AS a, (SELECT DATE(ADDDATE(?, INTERVAL ? DAY)) AS date) AS b WHERE g.geplant BETWEEN a.date AND b.date AND f.von = ? AND f.nach = ? AND f.nummer = g.nummer ORDER BY g.geplant ASC";
	protected static final String SELECT_FREIE_PLAETZE_ECONOMY = "SELECT(SELECT (reihe_economy*sitze_economy) AS ANZAHLPL FROM flugzeug JOIN flug ON flugzeug.modell = flug.flugzeug WHERE flug.nummer = ?) - (SELECT COUNT(*) FROM `ticket`, `geplant` WHERE flug = ? AND geplant = ? AND flug = nummer AND geplant = abflugdatum AND klasse = 'economy') AS ANZAHLFREIEPLAETZE";
	protected static final String SELECT_FREIE_PLAETZE_BUSINESS = "SELECT(SELECT (reihe_business*sitze_business) AS ANZAHLPL FROM flugzeug JOIN flug ON flugzeug.modell = flug.flugzeug WHERE flug.nummer = ?) - (SELECT COUNT(*) FROM `ticket`, `geplant` WHERE flug = ? AND geplant = ? AND flug = nummer AND geplant = abflugdatum AND klasse = 'business') AS ANZAHLFREIEPLAETZE";
	protected static final String SELECT_FLUGHAFEN_LISTE = "SELECT code, name, stadt, land " + "FROM flughafen ";
	protected static final String SELECT_FLUG_LISTE = "SELECT nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , flugzeug " + "FROM flug ";

	private DataSource dataSource;

	private ConnectionFactory() {
		// DataSource Lookup
		Connection conn = null;
		try {
			// GRANT ALL PRIVILEGES ON *.* TO 'airdb'@'localhost' IDENTIFIED BY' airdb' WITH GRANT OPTION;
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup(CONTEXT);
			dataSource = (DataSource) envCtx.lookup(DATABASE_PATH);
			conn = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			System.out.println("Datasource Looup Failed");
			e.printStackTrace();
			return;
		}

		// Initialize and Compile the Statements on the DataBase
		try {
			conn.prepareStatement(SELECT_BENUTZER);
			conn.prepareStatement(INSERT_BENUTZER);
			conn.prepareStatement(INSERT_ROLLEN);
			conn.prepareStatement(SELECT_TICKETS_BY_BENUTZER);
			conn.prepareStatement(SELECT_TICKETS_BY_ID);
			conn.prepareStatement(SELECT_TICKETS_BY_FLUG);
			conn.prepareStatement(INSERT_TICKET);
			conn.prepareStatement(UPDATE_TICKET);
			conn.prepareStatement(DELETE_TICKET);
			conn.prepareStatement(SELECT_FLUG);
			conn.prepareStatement(SELECT_FLUGHAFEN);
			conn.prepareStatement(SELECT_FLUGZEUG);
			conn.prepareStatement(SELECT_FLUGSUCHE);
			conn.prepareStatement(SELECT_FREIE_PLAETZE_ECONOMY);
			conn.prepareStatement(SELECT_FREIE_PLAETZE_BUSINESS);
			conn.prepareStatement(SELECT_FLUGHAFEN_LISTE);
			conn.prepareStatement(SELECT_FLUG_LISTE);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw e;
		} 
		return conn;
	}
	
	protected static ConnectionFactory getInstance() {
		return instance;
	}
}
