package org.zhaw.airticket.debug;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.zhaw.airticket.util.Crypto;

@WebServlet("/initdb")
public class InitTestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitTestDB() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doIt(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doIt(request, response);
	}

	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// try {
		// InputStream in =
		// getClass().getClassLoader().getSystemResourceAsStream("db.sql");
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(in));
		// TODO

		// File f = new
		// File("C:/Users/Mo/Dropbox/ZHAW/workspace/WBDIT/ps1-airticket/AirTicket/WebContent/WEB-INF/classes/db.sql");
		// System.out.println(f.getAbsolutePath());
		//
		// BufferedReader reader = new BufferedReader(new
		// FileReader("C:/Users/Mo/Dropbox/ZHAW/workspace/WBDIT/ps1-airticket/AirTicket/WebContent/WEB-INF/classes/db.sql"));
		//
		// String sql = "";
		// String line;
		// while ((line = reader.readLine()) != null){
		// sql += line;
		// }

		System.out.println("INITDB START");

		Statement stmt = null;
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/airdb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (NamingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("INITDB DROP");
		try {
			stmt.executeUpdate("DROP TABLE Ticket");
			conn.commit();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		}
		
		try {
			stmt.executeUpdate("DROP TABLE Rolle");
			conn.commit();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		}

		try {
			stmt.executeUpdate("DROP TABLE Benutzer");
			conn.commit();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		}

		try {
			stmt.executeUpdate("DROP TABLE Flug");
			conn.commit();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		}

		try {
			stmt.executeUpdate("DROP TABLE Flughafen");
			conn.commit();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		}

		try {
			stmt.executeUpdate("DROP TABLE Flugzeug");
			conn.commit();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		}

		System.out.println("INITDB Benutzer");
		


		
		try {
			stmt.executeUpdate("CREATE TABLE Benutzer (email VARCHAR(50) NOT NULL, passwort VARCHAR(32) NOT NULL, vorname VARCHAR(50) NOT NULL, name VARCHAR(50) NOT NULL, strasse VARCHAR(50) NOT NULL, ort VARCHAR(50) NOT NULL, postleitzahl INTEGER NOT NULL, telefonnummer BIGINT NOT NULL ,land VARCHAR(50) NOT NULL,PRIMARY KEY(email))");
			conn.commit();
			stmt.executeUpdate("CREATE TABLE Rolle (email VARCHAR(50) NOT NULL, Rollen  VARCHAR(50) NOT NULL, PRIMARY KEY (email), FOREIGN KEY(email) REFERENCES Benutzer  )");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.executeUpdate("INSERT INTO Benutzer (email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) VALUES ('root@air.com', '"+ Crypto.hashMD5("root@air.com") +"', 'Vorname','Name','Strasse','Ort',0,0,'Land')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Rolle (email, Rollen) VALUES ('root@air.com','manager-gui')");
			conn.commit();

			stmt.executeUpdate("INSERT INTO  Benutzer (email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) VALUES ('user@air.com', '"+ Crypto.hashMD5("user@air.com") +"' ,'UserVorname','UserName','UserStrasse','UserOrt', 8000, 052261000000,'UserLand')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO  Rolle (email, Rollen) VALUES ('user@air.com','user')");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("INITDB Flughafen");
		try {
			stmt.executeUpdate("CREATE TABLE Flughafen( code VARCHAR(50) NOT NULL, name VARCHAR(50) NOT NULL, stadt VARCHAR(50) NOT NULL, land VARCHAR(50) NOT NULL, PRIMARY KEY(code) )");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.executeUpdate("INSERT INTO Flughafen (code, name, stadt, land) VALUES ('ZRH','Zürich','Zürich','Schweiz')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flughafen (code, name, stadt, land) VALUES ('NRT','Narita','Tokyo','Japan')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flughafen (code, name, stadt, land) VALUES ('LHR','Heathrow','London','England')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flughafen (code, name, stadt, land) VALUES ('LGW','Gatwick','London','England')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flughafen (code, name, stadt, land) VALUES ('LCY','London City','London','England')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flughafen (code, name, stadt, land) VALUES ('SYD','Sydney','Sydney','Australia')");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("INITDB Flugzeug ");
		try {
			stmt.executeUpdate("CREATE TABLE Flugzeug(modell VARCHAR(50) NOT NULL, PRIMARY KEY(modell))");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.executeUpdate("INSERT INTO Flugzeug (modell) VALUES ('A380') ");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flugzeug (modell) VALUES ('B787') ");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flugzeug (modell) VALUES ('A343') ");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("INITDB Flug");
		try {
			stmt.executeUpdate("CREATE TABLE Flug(nummer VARCHAR(50) NOT NULL, von VARCHAR(50) NOT NULL, nach VARCHAR(50) NOT NULL, abflugzeit TIME NOT NULL, ankunftzeit TIME NOT NULL, dauer TIME NOT NULL, businesspreis INT NOT NULL, economypreis INT NOT NULL, geplant VARCHAR(50) NOT NULL, flugzeug VARCHAR(50) NOT NULL, PRIMARY KEY(nummer), FOREIGN KEY(flugzeug) REFERENCES Flugzeug, FOREIGN KEY(von) REFERENCES Flughafen, FOREIGN KEY(nach) REFERENCES Flughafen)");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.executeUpdate("INSERT INTO Flug (nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , geplant , flugzeug ) " + "VALUES ('LX161', 'NRT', 'ZRH', '13:45', '08:00', '12:00', 5000, 1200, 'DAILY', 'B787')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flug (nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , geplant , flugzeug ) " + "VALUES ('LX160', 'ZRH', 'NRT', '13:45', '18:00', '12:00', 5000, 1200, 'DAILY', 'B787')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flug (nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , geplant , flugzeug ) " + "VALUES ('QF542', 'ZRH', 'SYD', '10:15', '20:30', '24:00', 10000, 2000, 'DAILY', 'A380')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flug (nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , geplant , flugzeug ) " + "VALUES ('QF548', 'SYD', 'ZRH', '15:15', '09:30', '24:00', 10000, 2000, 'DAILY', 'A380')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO Flug (nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , geplant , flugzeug ) " + "VALUES ('BA8730', 'ZRH', 'LHR', '13:45', '08:00', '12:00', 2500, 300, 'DAILY', 'A343')");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("INITDB Ticket");
		try {
			stmt.executeUpdate("CREATE TABLE Ticket (id	INTEGER NOT NULL, abflugdatum  DATE NOT NULL, sitzreihe INTEGER NOT NULL, sitzspalte INTEGER NOT NULL, klasse VARCHAR(50) NOT NULL, flug VARCHAR(50) NOT NULL, benutzer VARCHAR(50) NOT NULL, PRIMARY KEY(id), FOREIGN KEY(benutzer) REFERENCES Benutzer, FOREIGN KEY(flug) REFERENCES Flug )");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.executeUpdate("INSERT INTO  Ticket (id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) VALUES (1, '2000-01-01', 0, 0,'economy','LX161','user@air.com')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO  Ticket (id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) VALUES (2, '2002-01-01', 1, 2,'economy','LX160','user@air.com')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO  Ticket (id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) VALUES (3, '2003-01-01', 4, 5,'economy','BA8730','user@air.com')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO  Ticket (id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) VALUES (4, '2004-01-01', 6, 6,'business','QF542','user@air.com')");
			conn.commit();
			stmt.executeUpdate("INSERT INTO  Ticket (id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) VALUES (5, '2005-01-01', 5, 1,'business','QF548','user@air.com')");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("INITDB END");

	}

}
