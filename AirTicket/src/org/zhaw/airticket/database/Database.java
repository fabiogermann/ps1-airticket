package org.zhaw.airticket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private PreparedStatement insertTicket;
    private PreparedStatement selectBenutzer;
    private PreparedStatement selectTicketsByBenutzer;
    private PreparedStatement selectTicketsById;
    private PreparedStatement selectTicketsByFlug;
    private PreparedStatement selectFlug;
    private PreparedStatement selectFlughafen;
    private PreparedStatement selectFlugzeug;
    private PreparedStatement updateTicket;
    private PreparedStatement deleteTicket;
    private PreparedStatement selectFlugSuche;
    private PreparedStatement freiePlaetzeEconomy;
    private PreparedStatement freiePlaetzeBusiness;
    private PreparedStatement flughafenListe;
    private PreparedStatement flugListe;

    public Database() {
        try {

            //GRANT ALL PRIVILEGES ON *.* TO 'airdb'@'localhost' IDENTIFIED BY 'airdb' WITH GRANT OPTION;
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/airdb");
            conn = ds.getConnection();
            
//          Class.forName("com.mysql.jdbc.Driver");
//          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airdb", "airdb", "airdb");
        
            insertBenutzer = conn.prepareStatement("INSERT INTO benutzer (email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) " + "values  (?,?,?,?,?,?,?,?,?)");

            insertRollen = conn.prepareStatement("INSERT INTO rolle (email, rollen) " + "values (?, 'user') ");
            
            insertTicket = conn.prepareStatement("INSERT INTO ticket (abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer) " + "values (?,?,?,?,?,?)" );

            selectBenutzer = conn.prepareStatement("SELECT email, vorname, name, strasse, ort, postleitzahl, telefonnummer, land " + "FROM benutzer " + "WHERE email = ? ");

            selectTicketsByBenutzer = conn.prepareStatement("SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM ticket " + "WHERE benutzer = ? ");

            selectTicketsById = conn.prepareStatement("SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM ticket " + "WHERE id = ? ");

            selectTicketsByFlug = conn.prepareStatement("SELECT id, abflugdatum, sitzreihe, sitzspalte, klasse, flug, benutzer " + "FROM ticket " + "WHERE flug = ? ");
            
            selectFlug = conn.prepareStatement("SELECT nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , flugzeug " + "FROM flug " + "WHERE nummer = ? ");

            selectFlughafen = conn.prepareStatement("SELECT code, name, stadt, land " + "FROM flughafen " + "WHERE code = ? ");

            selectFlugzeug = conn.prepareStatement("SELECT modell, reihe_business, sitze_business, reihe_economy, sitze_economy " + "FROM flugzeug " + "WHERE modell = ? ");
            
            //TODO #MO ale hesh drin gha   updateTicket = selectTicketsById = conn.prepareStatemen... somit natürli select mit update überschribe;
            updateTicket = conn.prepareStatement("UPDATE ticket " + "SET sitzreihe = ?, sitzspalte = ? " + "WHERE id = ? ");
            
            deleteTicket = conn.prepareStatement("DELETE FROM ticket WHERE id = ?");
            
            selectFlugSuche = conn.prepareStatement("SELECT f.nummer AS nummer, f.von AS von , f.nach AS nach , f.abflugzeit AS abflugzeit , f.ankunftzeit AS ankunftzeit , f.dauer AS dauer , f.businesspreis AS businesspreis , f.economypreis AS economypreis , g.geplant AS geplant, f.flugzeug AS flugzeug FROM flug f, geplant g, (SELECT DATE(ADDDATE(?, INTERVAL -? DAY)) AS date) AS a, (SELECT DATE(ADDDATE(?, INTERVAL ? DAY)) AS date) AS b WHERE g.geplant BETWEEN a.date AND b.date AND f.von = ? AND f.nach = ? AND f.nummer = g.nummer ORDER BY g.geplant ASC");
            
            freiePlaetzeEconomy = conn.prepareStatement("SELECT(SELECT (reihe_economy*sitze_economy) AS ANZAHLPL FROM flugzeug JOIN flug ON flugzeug.modell = flug.flugzeug WHERE flug.nummer = ?) - (SELECT COUNT(*) FROM `ticket` WHERE flug = ? AND klasse = 'economy') AS ANZAHLFREIEPLAETZE");
            
            freiePlaetzeBusiness = conn.prepareStatement("SELECT(SELECT (reihe_business*sitze_business) AS ANZAHLPL FROM flugzeug JOIN flug ON flugzeug.modell = flug.flugzeug WHERE flug.nummer = ?) - (SELECT COUNT(*) FROM `ticket` WHERE flug = ? AND klasse = 'business') AS ANZAHLFREIEPLAETZE");
            
            flughafenListe = conn.prepareStatement("SELECT code, name, stadt, land " + "FROM flughafen ");            
            
            flugListe = conn.prepareStatement("SELECT nummer, von , nach , abflugzeit , ankunftzeit , dauer , businesspreis , economypreis , flugzeug " + "FROM flug ");
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } 

    }

    public boolean addBenutzer(Benutzer benutzer) throws SQLException {
        try {
            insertBenutzer.setString(1, benutzer.getEmail());
            insertBenutzer.setString(2, benutzer.getPasswort());
            insertBenutzer.setString(3, benutzer.getVorname());
            insertBenutzer.setString(4, benutzer.getName());
            insertBenutzer.setString(5, benutzer.getStrasse());
            insertBenutzer.setString(6, benutzer.getOrt());
            insertBenutzer.setInt(7, benutzer.getPostleitzahlInt());
            insertBenutzer.setString(8, benutzer.getTelefonnummer());
            insertBenutzer.setString(9, benutzer.getLand());

            insertRollen.setString(1, benutzer.getEmail());

            insertBenutzer.executeUpdate();
            insertRollen.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
            throw e;
        }

        return true;
    }
    
    public boolean addTicket(Ticket ticket, Benutzer benutzer) throws SQLException {
        try {
            insertTicket.setDate(1, new java.sql.Date(ticket.getAbflugdatum().getTime()));
            insertTicket.setInt(2, ticket.getSitzreihe());
            insertTicket.setInt(3, ticket.getSitzspalte());
            insertTicket.setString(4, ticket.getKlasse());
            insertTicket.setString(5, ticket.getFlug().getNummer());
            insertTicket.setString(6, benutzer.getEmail());

            insertTicket.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
            throw e;
        }

        return true;
    }
    
    public boolean updateTicket(int ticketId, int sitzreihe, int sitzspalte) throws SQLException{
        updateTicket.setInt(1, sitzreihe);
        updateTicket.setInt(2, sitzspalte);
        updateTicket.setInt(3, ticketId);
        return updateTicket.execute();
    }
   
    public boolean removeTicket(int ticketId) throws SQLException{
        deleteTicket.setInt(1, ticketId);
        return deleteTicket.execute();
    }

    public Benutzer getBenutzer(String email) {
        if (email == null) {
            return null;
        }
        Benutzer benutzer = null;
        try {

            selectBenutzer.setString(1, email);
            ResultSet benutzerRs = selectBenutzer.executeQuery();
            if (benutzerRs.next()) {
                benutzer = new Benutzer(benutzerRs.getString("email"), benutzerRs.getString("vorname"), benutzerRs.getString("name"), benutzerRs.getString("strasse"), benutzerRs.getString("ort"), benutzerRs.getString("postleitzahl"), benutzerRs.getString("telefonnummer"), benutzerRs.getString("land"));
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
            if(ticketRs.next()){
	            Flug flug = getFlug(ticketRs.getString("flug"));
	            ticket = new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), flug);
            } else {
            	System.out.println("Ticket nicht gefunden. ID="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }
    
    public ArrayList<Ticket> getTicketsByFlug(String flugnr) {
        ArrayList<Ticket> ticketlist = new ArrayList<Ticket>();
        try {
            selectTicketsByFlug.setString(1, flugnr);
            ResultSet ticketRs = selectTicketsByFlug.executeQuery();
            if (ticketRs.next()){
            	Flug flug = getFlug(flugnr);
            	ticketlist.add(new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), flug));
            } else {
            	System.out.println("Ticket nicht gefunden, Flugnr="+flugnr);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return ticketlist;
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
                flug = new Flug(flugRs.getString("nummer"), FlughafenVon, FlughafenNach, flugRs.getTime("abflugzeit"), flugRs.getTime("ankunftzeit"), flugRs.getTime("dauer"), flugRs.getInt("businessPreis"), flugRs.getInt("economyPreis"), "0000-00-00", flugzeug);
            } else {
                System.out.println("Database: Flug NICHT gefunden. nummer = " + nummer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flug;
    }

    public ArrayList<Flug> lookupFlugNummer(String nummer, String datum, int tolerance) {
    	ArrayList<Flug> suchresultat = new ArrayList<Flug>();
    	Flug flugN = null;
    	flugN = this.getFlug(nummer);
		suchresultat = this.lookupFlug(flugN.getVon(), flugN.getNach(), datum, tolerance);
    	return suchresultat;
    }
    
    public ArrayList<Flug> lookupFlug(Flughafen dep, Flughafen dest, String datum, int tolerance) {
    	ArrayList<Flug> suchresultat = new ArrayList<Flug>();
    	try {
    		selectFlugSuche.setString(1, datum);
    		selectFlugSuche.setInt(2, tolerance);
    		selectFlugSuche.setString(3, datum);
    		selectFlugSuche.setInt(4, tolerance);
    		selectFlugSuche.setString(5, dep.getCode());
    		selectFlugSuche.setString(6, dest.getCode());
    		ResultSet flugsucheRs = selectFlugSuche.executeQuery();
    		while (flugsucheRs.next()) {
                Flugzeug flugzeug = getFlugzeug(flugsucheRs.getString("flugzeug"));
                suchresultat.add(new Flug(flugsucheRs.getString("nummer"), dep, dest, flugsucheRs.getTime("abflugzeit"), flugsucheRs.getTime("ankunftzeit"), flugsucheRs.getTime("dauer"), flugsucheRs.getInt("businessPreis"), flugsucheRs.getInt("economyPreis"), flugsucheRs.getString("geplant"), flugzeug));
            } //else {
              //  System.out.println("Database: Flug NICHT gefunden. " + dep.getCode() +"-"+ dest.getCode() +"-"+ datum +"-"+ tolerance);
            //}
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	return suchresultat;
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
                flughafen = new Flughafen("ER7", "ERR", "ERR", "ERR");
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
                flugzeug = new Flugzeug(flugzeugRs.getString("modell"), flugzeugRs.getInt("reihe_business"), flugzeugRs.getInt("sitze_business"), flugzeugRs.getInt("reihe_economy"), flugzeugRs.getInt("sitze_economy"));
            } else {
                System.out.println("Database: Flugzeug NICHT gefunden. modell = " + modell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flugzeug;
    }
    
    public int freiePlaetzeEco(String flugnummer){
		int anzPlEco = 0;
    	try {
			freiePlaetzeEconomy.setString(1, flugnummer);
			freiePlaetzeEconomy.setString(2, flugnummer);
			ResultSet plaetzeEco = freiePlaetzeEconomy.executeQuery();
			
			if (plaetzeEco.next()) {
                anzPlEco = plaetzeEco.getInt("ANZAHLFREIEPLAETZE");
                
            } else {
                System.out.println("Fehler Berechnung freie Sitzplï¿½tze Economy");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return anzPlEco;
    	
    }
    
    public int freiePlaetzeBus(String flugnummer){
		int anzPlBus = 0;
    	try {
			freiePlaetzeBusiness.setString(1, flugnummer);
			freiePlaetzeBusiness.setString(2, flugnummer);
			ResultSet plaetzeBus = freiePlaetzeBusiness.executeQuery();
			
			if (plaetzeBus.next()) {
                anzPlBus = plaetzeBus.getInt("ANZAHLFREIEPLAETZE");
                
            } else {
                System.out.println("Fehler Berechnung freie Sitzplï¿½tze Economy");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return anzPlBus;
    	
    }

	public ArrayList<Flughafen> flughafenListe() {
		ArrayList<Flughafen> flughafen = new ArrayList<Flughafen>();
		try {
            ResultSet flughafenRs = flughafenListe.executeQuery();
            while (flughafenRs.next()) {
                flughafen.add(new Flughafen(flughafenRs.getString("code"), flughafenRs.getString("name"), flughafenRs.getString("stadt"), flughafenRs.getString("land")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return flughafen;
	}
	
    public ArrayList<Flug> flugListe() {
        ArrayList<Flug> fluege = new ArrayList<Flug>();
        try {
            ResultSet flugRs = flugListe.executeQuery();
            while (flugRs.next()) {
                Flughafen FlughafenVon = getFlughafen(flugRs.getString("von"));
                Flughafen FlughafenNach = getFlughafen(flugRs.getString("nach"));
                Flugzeug flugzeug = getFlugzeug(flugRs.getString("flugzeug"));
                fluege.add(new Flug(flugRs.getString("nummer"), FlughafenVon, FlughafenNach, flugRs.getTime("abflugzeit"), flugRs.getTime("ankunftzeit"), flugRs.getTime("dauer"), flugRs.getInt("businessPreis"), flugRs.getInt("economyPreis"), "0000-00-00", flugzeug));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fluege;
    }
}
