package org.zhaw.airticket.util;

import java.util.ArrayList;
import org.zhaw.airticket.database.Database;
import org.zhaw.airticket.model.Flug;
import org.zhaw.airticket.model.Flughafen;

public class Autocomplete {
	
	private Database db = new Database();
	private ArrayList<Flug> fluege = db.flugListe();
	private ArrayList<Flughafen> flughafen = db.flughafenListe();
	
    public Autocomplete(){
    	super();
    }
    
    public String setFllughafenAutocomplete(String elementId) {
    	String out = new String();
    	out += "$(function() { var flughafen = [";
    	for(Flughafen f : flughafen) {
    		out += "{ value: \""+f.getCode()+"\", label: \""+f.getName()+" ("+f.getCode()+")"+"\", description: \""+f.getStadt()+", "+f.getLand()+"\"},";
    	}
    	out += "];";
    	out += " $( \"#"+elementId+"\" ).autocomplete({ minLength: 0, source: flughafen, focus: function( event, ui ) {";
    	out += " $( \"#"+elementId+"\" ).val( ui.item.label ); return false; }, select: function( event, ui ) {";
    	out += "$( \"#"+elementId+"\" ).val( ui.item.label );";
    	out += "$( \"#"+elementId+"_code\" ).val( ui.item.value );";
    	out += " return false; } }) .data( \"ui-autocomplete\" )._renderItem = function( ul, item ) { return $( \"<li>\" ) .append( \"<a>\" + item.label + \"<br>\" + item.description + \"</a>\" ) .appendTo( ul ); };});";
    	return out;
    }
    
    public String setFlugnummerAutocomplete() {
    	String out = new String();
    	out += "$(function() { var fluege = [";
    	for(Flug f : fluege) {
    		out += "{ value: \""+f.getNummer().toString()+"\", label: \""+f.getNummer().toString()+"\", fromc: \""+f.getVon().getCode()+"\", toc: \""+f.getNach().getCode()+"\", from: \""+f.getVon().getName()+"\", to: \""+f.getNach().getName()+"\"},";
    	}
    	out += "];";
    	out += " $( \"#flightnr\" ).autocomplete({ minLength: 0, source: fluege, focus: function( event, ui ) {";
    	out += " $( \"#flightnr\" ).val( ui.item.label ); return false; }, select: function( event, ui ) {";
    	out += "$( \"#flightnr\" ).val( ui.item.label );";
    	out += "$( \"#flightnr_code\" ).val( ui.item.value );";
    	out += "$( \"#departure\" ).val( ui.item.from );";
    	out += "$( \"#departure_code\" ).val( ui.item.fromc );";
    	out += "$( \"#destination\" ).val( ui.item.to );";
    	out += "$( \"#destination_code\" ).val( ui.item.toc );";
    	out += " return false; } }) .data( \"ui-autocomplete\" )._renderItem = function( ul, item ) { return $( \"<li>\" ) .append( \"<a>\" + item.label + \"<br>\" + item.from + \" -> \" + item.to + \"</a>\" ) .appendTo( ul ); };});";
    	return out;
    }
    
    public String setDatepicker(String inputId) {
    	String out = new String();
    	out += " $(function() { var picker = { dateFormat:\"dd.mm.yy\" }; $( \"#"+inputId+"\" ).datepicker(picker); });";
    	return out;
    }
    
    public String setDatepickerDependent(String groundId, String dependentId) {
    	String out = new String();
    	//changeMonth: true to the var to enable manual picking
    	out += " $(function() { var gd = { dateFormat:\"dd.mm.yy\", minDate: 0, maxDate: \"+1Y\", numberOfMonths: 2, onSelect: function(selected) { $(\"#"+dependentId+"\").datepicker(\"option\",\"minDate\", selected) }}; $( \"#"+groundId+"\" ).datepicker(gd); });";
    	out += " $(function() { var dp = { dateFormat:\"dd.mm.yy\", minDate: 0, maxDate: \"+1Y\", numberOfMonths: 2, onSelect: function(selected) { $(\"#"+groundId+"\").datepicker(\"option\",\"maxDate\", selected) }}; $( \"#"+dependentId+"\" ).datepicker(dp); });";
    	return out;
    }
}
