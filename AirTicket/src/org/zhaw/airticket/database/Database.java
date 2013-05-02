package org.zhaw.airticket.database;

import static org.zhaw.airticket.database.ConnectionFactory.DELETE_TICKET;
import static org.zhaw.airticket.database.ConnectionFactory.INSERT_BENUTZER;
import static org.zhaw.airticket.database.ConnectionFactory.INSERT_ROLLEN;
import static org.zhaw.airticket.database.ConnectionFactory.INSERT_TICKET;
import static org.zhaw.airticket.database.ConnectionFactory.UPDATE_TICKET;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_BENUTZER;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FLUG;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FLUGHAFEN;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FLUGHAFEN_LISTE;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FLUGSUCHE;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FLUGZEUG;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FLUG_LISTE;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FREIE_PLAETZE_BUSINESS;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_FREIE_PLAETZE_ECONOMY;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_TICKETS_BY_BENUTZER;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_TICKETS_BY_FLUG;
import static org.zhaw.airticket.database.ConnectionFactory.SELECT_TICKETS_BY_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.zhaw.airticket.model.Benutzer;
import org.zhaw.airticket.model.Flug;
import org.zhaw.airticket.model.Flughafen;
import org.zhaw.airticket.model.Flugzeug;
import org.zhaw.airticket.model.Ticket;

public class Database {

	private ConnectionFactory factory = ConnectionFactory.getInstance();

    public Database() {
    }

    public boolean addBenutzer(Benutzer benutzer) throws SQLException {
    	Connection conn = null;
    	PreparedStatement insertBenutzer = null;
    	PreparedStatement insertRollen = null;
        try {
        	conn = factory.getConnection();
        	insertBenutzer = conn.prepareStatement(INSERT_BENUTZER);
        	insertRollen = conn.prepareStatement(INSERT_ROLLEN);
        	
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
            throw e;
        } finally {
        	if (insertBenutzer != null) insertBenutzer.close();
        	if (insertRollen != null) insertRollen.close();
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        
        return true;
    }
    
    public boolean addTicket(Ticket ticket, Benutzer benutzer) throws SQLException {
    	Connection conn = null;
    	PreparedStatement insertTicket = null;
        try {
        	conn = factory.getConnection();
        	insertTicket = conn.prepareStatement(INSERT_TICKET);
        	
            insertTicket.setDate(1, new java.sql.Date(ticket.getAbflugdatum().getTime()));
            insertTicket.setInt(2, ticket.getSitzreihe());
            insertTicket.setInt(3, ticket.getSitzspalte());
            insertTicket.setString(4, ticket.getKlasse());
            insertTicket.setString(5, ticket.getFlug().getNummer());
            insertTicket.setString(6, benutzer.getEmail());

            insertTicket.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
        	if (insertTicket != null) insertTicket.close();
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }

        return true;
    }
    
    public boolean updateTicket(int ticketId, int sitzreihe, int sitzspalte) throws SQLException{
    	Connection conn = null;
    	boolean status = false;
    	PreparedStatement updateTicket = null;
    	try {
    		conn = factory.getConnection();
	    	updateTicket = conn.prepareStatement(UPDATE_TICKET);
	        updateTicket.setInt(1, sitzreihe);
	        updateTicket.setInt(2, sitzspalte);
	        updateTicket.setInt(3, ticketId);
	        status = updateTicket.execute();
    	} catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
    		if (updateTicket != null) updateTicket.close();
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    	}
        return status;
    }
   
    public boolean removeTicket(int ticketId) throws SQLException{
    	Connection conn = null;
    	boolean status = false;
    	PreparedStatement deleteTicket = null;
    	try {
    		conn = factory.getConnection();
	      	deleteTicket = conn.prepareStatement(DELETE_TICKET);
	        deleteTicket.setInt(1, ticketId);
	        status = deleteTicket.execute();
    	} catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
    		if (deleteTicket != null) deleteTicket.close();
    		
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    	}
        return status;
    }

    public Benutzer getBenutzer(String email) {
    	Connection conn = null;
        if (email == null) {
            return null;
        }
        Benutzer benutzer = null;
        ResultSet benutzerRs = null;
        PreparedStatement selectBenutzer = null;
        try {
        	conn = factory.getConnection();
        	selectBenutzer = conn.prepareStatement(SELECT_BENUTZER);
            selectBenutzer.setString(1, email);
            benutzerRs = selectBenutzer.executeQuery();
            if (benutzerRs.next()) {
                benutzer = new Benutzer(benutzerRs.getString("email"), benutzerRs.getString("vorname"), benutzerRs.getString("name"), benutzerRs.getString("strasse"), benutzerRs.getString("ort"), benutzerRs.getString("postleitzahl"), benutzerRs.getString("telefonnummer"), benutzerRs.getString("land"));
            } else {
                System.out.println("Database: Benutzer NICHT gefunden. email = " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
        	if (benutzerRs != null)
				try {
					benutzerRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (selectBenutzer != null)
				try {
					selectBenutzer.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        Map<Integer, Ticket> tickets = getTickets(email);
        benutzer.setTickets(tickets);

        return benutzer;
    }

    public Map<Integer, Ticket> getTickets(String email) {
    	Connection conn = null;
        Map<Integer, Ticket> tickets = null;
        PreparedStatement selectTicketsByBenutzer = null;
        ResultSet ticketRs = null;
        try {
        	conn = factory.getConnection();
        	selectTicketsByBenutzer = conn.prepareStatement(SELECT_TICKETS_BY_BENUTZER);
            selectTicketsByBenutzer.setString(1, email);
            ticketRs = selectTicketsByBenutzer.executeQuery();
            tickets = new HashMap<Integer, Ticket>();
            while (ticketRs.next()) {
                Flug flug = getFlug(ticketRs.getString("flug"));
                Ticket ticket = new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), flug);
                tickets.put(ticket.getId(), ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if (selectTicketsByBenutzer != null)
				try {
					selectTicketsByBenutzer.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (ticketRs != null)
				try {
					ticketRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        return tickets;
    }

    public Ticket getTicket(int id) {
    	Connection conn = null;
        Ticket ticket = null;
        PreparedStatement selectTicketsById = null;
        ResultSet ticketRs = null;
        try {
        	conn = factory.getConnection();
        	selectTicketsById = conn.prepareStatement(SELECT_TICKETS_BY_ID);
            selectTicketsById.setInt(1, id);
            ticketRs = selectTicketsById.executeQuery();
            if(ticketRs.next()){
	            Flug flug = getFlug(ticketRs.getString("flug"));
	            ticket = new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), flug);
            } else {
            	System.out.println("Ticket nicht gefunden. ID="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if (selectTicketsById != null)
				try {
					selectTicketsById.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (ticketRs != null)
				try {
					ticketRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        return ticket;
    }
    
    public ArrayList<Ticket> getTicketsByFlug(String flugnr, Date geplant, String klasse) {
    	Connection conn = null;
        ArrayList<Ticket> ticketlist = new ArrayList<Ticket>();
        PreparedStatement selectTicketsByFlug = null;
        ResultSet ticketRs = null;
        try {
        	conn = factory.getConnection();
        	selectTicketsByFlug = conn.prepareStatement(SELECT_TICKETS_BY_FLUG);
            selectTicketsByFlug.setString(1, flugnr);
            selectTicketsByFlug.setString(2, klasse);
            selectTicketsByFlug.setDate(3, new java.sql.Date(geplant.getTime()));
            ticketRs = selectTicketsByFlug.executeQuery();
            while (ticketRs.next()){
            	Flug tempflug = getFlug(flugnr);
            	ticketlist.add(new Ticket(ticketRs.getInt("id"), ticketRs.getDate("abflugdatum"), ticketRs.getInt("sitzreihe"), ticketRs.getInt("sitzspalte"), ticketRs.getString("klasse"), tempflug));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	if (selectTicketsByFlug != null)
				try {
					selectTicketsByFlug.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (ticketRs != null)
				try {
					ticketRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        return ticketlist;
    }

    public Flug getFlug(String nummer) {
    	Connection conn = null;
        Flug flug = null;
        PreparedStatement selectFlug = null;
        ResultSet flugRs = null;
        try {
        	conn = factory.getConnection();
        	selectFlug = conn.prepareStatement(SELECT_FLUG);
            selectFlug.setString(1, nummer);
            flugRs = selectFlug.executeQuery();
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
        } finally {
        	if (selectFlug != null)
				try {
					selectFlug.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (flugRs != null)
				try {
					flugRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
    	Connection conn = null;
    	ArrayList<Flug> suchresultat = new ArrayList<Flug>();
    	PreparedStatement selectFlugSuche = null;
    	ResultSet flugsucheRs = null;
    	try {
    		conn = factory.getConnection();
    		selectFlugSuche = conn.prepareStatement(SELECT_FLUGSUCHE);
    		selectFlugSuche.setString(1, datum);
    		selectFlugSuche.setInt(2, tolerance);
    		selectFlugSuche.setString(3, datum);
    		selectFlugSuche.setInt(4, tolerance);
    		selectFlugSuche.setString(5, dep.getCode());
    		selectFlugSuche.setString(6, dest.getCode());
    		flugsucheRs = selectFlugSuche.executeQuery();
    		while (flugsucheRs.next()) {
                Flugzeug flugzeug = getFlugzeug(flugsucheRs.getString("flugzeug"));
                suchresultat.add(new Flug(flugsucheRs.getString("nummer"), dep, dest, flugsucheRs.getTime("abflugzeit"), flugsucheRs.getTime("ankunftzeit"), flugsucheRs.getTime("dauer"), flugsucheRs.getInt("businessPreis"), flugsucheRs.getInt("economyPreis"), flugsucheRs.getString("geplant"), flugzeug));
            } //else {
              //  System.out.println("Database: Flug NICHT gefunden. " + dep.getCode() +"-"+ dest.getCode() +"-"+ datum +"-"+ tolerance);
            //}
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if (selectFlugSuche != null)
				try {
					selectFlugSuche.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (flugsucheRs != null)
				try {
					flugsucheRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
    	return suchresultat;
    }
    
    public Flughafen getFlughafen(String code) {
    	Connection conn = null;
    	Flughafen flughafen = null;
        PreparedStatement selectFlughafen = null;
        ResultSet flughafenRs = null;
        try {
        	conn = factory.getConnection();
        	selectFlughafen = conn.prepareStatement(SELECT_FLUGHAFEN);
            selectFlughafen.setString(1, code);
            flughafenRs = selectFlughafen.executeQuery();
            if (flughafenRs.next()) {
                flughafen = new Flughafen(flughafenRs.getString("code"), flughafenRs.getString("name"), flughafenRs.getString("stadt"), flughafenRs.getString("land"));
            } else {
                System.out.println("Database: Flughafen NICHT gefunden. code = " + code);
                flughafen = new Flughafen("ER7", "ERR", "ERR", "ERR");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if (selectFlughafen != null)
				try {
					selectFlughafen.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (flughafenRs != null)
				try {
					flughafenRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        return flughafen;
    }

    public Flugzeug getFlugzeug(String modell) {
    	Connection conn = null;
        Flugzeug flugzeug = null;
        PreparedStatement selectFlugzeug = null;
        ResultSet flugzeugRs = null;
        try {
        	conn = factory.getConnection();
        	selectFlugzeug = conn.prepareStatement(SELECT_FLUGZEUG);
            selectFlugzeug.setString(1, modell);
            flugzeugRs = selectFlugzeug.executeQuery();

            if (flugzeugRs.next()) {
                flugzeug = new Flugzeug(flugzeugRs.getString("modell"), flugzeugRs.getInt("reihe_business"), flugzeugRs.getInt("sitze_business"), flugzeugRs.getInt("reihe_economy"), flugzeugRs.getInt("sitze_economy"));
            } else {
                System.out.println("Database: Flugzeug NICHT gefunden. modell = " + modell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if (selectFlugzeug != null)
				try {
					selectFlugzeug.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (flugzeugRs != null)
				try {
					flugzeugRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        return flugzeug;
    }
    
    public int freiePlaetzeEco(String flugnummer, Date geplant){
    	Connection conn = null;
		int anzPlEco = 0;
		
		PreparedStatement freiePlaetzeEconomy = null;
		ResultSet plaetzeEco = null;
    	try {
    		conn = factory.getConnection();
    		freiePlaetzeEconomy = conn.prepareStatement(SELECT_FREIE_PLAETZE_ECONOMY);
			freiePlaetzeEconomy.setString(1, flugnummer);
			freiePlaetzeEconomy.setString(2, flugnummer);
			freiePlaetzeEconomy.setDate(3, new java.sql.Date(geplant.getTime()));
			plaetzeEco = freiePlaetzeEconomy.executeQuery();
			
			if (plaetzeEco.next()) {
                anzPlEco = plaetzeEco.getInt("ANZAHLFREIEPLAETZE");
                
            } else {
                System.out.println("Fehler Berechnung freie Sitzpl�tze Economy");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	     	if (freiePlaetzeEconomy != null)
				try {
					freiePlaetzeEconomy.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (plaetzeEco != null)
				try {
					plaetzeEco.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	
		return anzPlEco;
    	
    }
    
    public int freiePlaetzeBus(String flugnummer, Date geplant){
    	Connection conn = null;
		int anzPlBus = 0;
		PreparedStatement freiePlaetzeBusiness = null;
		ResultSet plaetzeBus = null;
    	try {
    		conn = factory.getConnection();
    		freiePlaetzeBusiness = conn.prepareStatement(SELECT_FREIE_PLAETZE_BUSINESS);
			freiePlaetzeBusiness.setString(1, flugnummer);
			freiePlaetzeBusiness.setString(2, flugnummer);
			freiePlaetzeBusiness.setDate(3, new java.sql.Date(geplant.getTime()));
			plaetzeBus = freiePlaetzeBusiness.executeQuery();
			
			if (plaetzeBus.next()) {
                anzPlBus = plaetzeBus.getInt("ANZAHLFREIEPLAETZE");
                
            } else {
                System.out.println("Fehler Berechnung freie Sitzpl�tze Economy");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (freiePlaetzeBusiness != null)
				try {
					freiePlaetzeBusiness.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (plaetzeBus != null)
				try {
					plaetzeBus.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	
		return anzPlBus;
    	
    }

	public ArrayList<Flughafen> flughafenListe() {
		Connection conn = null;
		ArrayList<Flughafen> flughafen = new ArrayList<Flughafen>();
		PreparedStatement flughafenListe = null;
	    ResultSet flughafenRs = null;
		try {
			conn = factory.getConnection();
			flughafenListe = conn.prepareStatement(SELECT_FLUGHAFEN_LISTE);
            flughafenRs = flughafenListe.executeQuery();
            while (flughafenRs.next()) {
                flughafen.add(new Flughafen(flughafenRs.getString("code"), flughafenRs.getString("name"), flughafenRs.getString("stadt"), flughafenRs.getString("land")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	if (flughafenListe != null)
				try {
					flughafenListe.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (flughafenRs != null)
				try {
					flughafenRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
		return flughafen;
	}
	
    public ArrayList<Flug> flugListe() {
    	Connection conn = null;
        ArrayList<Flug> fluege = new ArrayList<Flug>();
        PreparedStatement flugListe = null;
        ResultSet flugRs = null;
        try {
        	conn = factory.getConnection();
        	flugListe = conn.prepareStatement(SELECT_FLUG_LISTE);
            flugRs = flugListe.executeQuery();
            while (flugRs.next()) {
                Flughafen FlughafenVon = getFlughafen(flugRs.getString("von"));
                Flughafen FlughafenNach = getFlughafen(flugRs.getString("nach"));
                Flugzeug flugzeug = getFlugzeug(flugRs.getString("flugzeug"));
                fluege.add(new Flug(flugRs.getString("nummer"), FlughafenVon, FlughafenNach, flugRs.getTime("abflugzeit"), flugRs.getTime("ankunftzeit"), flugRs.getTime("dauer"), flugRs.getInt("businessPreis"), flugRs.getInt("economyPreis"), "0000-00-00", flugzeug));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				flugListe.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	try {
				flugRs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }
        return fluege;
    }
}
