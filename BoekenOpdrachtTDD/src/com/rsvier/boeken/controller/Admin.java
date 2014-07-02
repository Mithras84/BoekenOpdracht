/**
 * 
 */
package com.rsvier.boeken.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Rekening;
import com.rsvier.boeken.db.DBInsert;

/**
 * Class description
 * Controller voor Admin. Dit zijn de basis Admin functies. 
 * Deze klasse heeft een aantal functies voor het toevoegen van boeken, rekeningen, etc.
 * De UI kan deze functies aanroepen, maar ze worden ook gebruikt in package setup om de Database te 
 * vullen met gegevens.
 * 
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
public class Admin {
    private ArrayList<Boek> mBoekenLijst;
    private ArrayList<Rekening> mRekeningenLijst;
    
    public Admin () {
	mBoekenLijst = new ArrayList<Boek>();	
	mRekeningenLijst = new ArrayList<Rekening>();
    }
    
     /**
     * Functie om boek uit de Database te halen.
     * Gebruik de getters en setters van het boek-object om informatie te lezen,
     * en te schrijven. 
     * De voorraad is ook via het boek-object aan te passen (bijv:  boek.getVoorraad().setVoorraadGroningen(19) )
     * Wijzigingen kan je doorvoeren in de Database door de functie boek.updateBoek() aan te roepen.
     * 
     * @param isbn
     * @return Boek of null in het geval van SQLException.
     */
    public Boek editBoek (String isbn) {
	try {
	    return new Boek(isbn);
	} catch (SQLException e) {
	    System.out.println ("Boek niet gevonden!");
	    return null;
	}
    }
    
    /**
     * Functie om rekening uit de Database te halen op basis van een rekening nummer.
     * Rekening nummer moet precies overeenkomen met het nummer in de DB.
     * (misschien handig om een rekening-nummer aan de locaties in de Enum Locatie toe te voegen?)
     * Het rekening object is aan te passen dmv de getters en de setters.
     * @param rekeningNummer
     * @return
     */
    public Rekening editRekening (String rekeningNummer) {
	try {
	    return new Rekening(rekeningNummer);
	} catch (SQLException e) {
	    System.out.println ("Rekening niet gevonden!");
	    return null;
	}
    }
    
    
    
    /*
     *	DE VOLGENDE FUNCTIES ZIJN ER PUUR VOOR HET TOEVOEGEN VAN GEGEVENS.
     *	DEZE WORDEN IN PACKAGE "SETUP" GEBRUIKT OM DE DATABASE TE VULLEN MET 
     *	BOEKEN, VOORRADEN EN REKENINGEN. DE GUI KAN DEZE FUNCTIES OOK AANROEPEN OM
     *	NIEUWE BOEKEN TOE TE VOEGEN:
     *	1. VOEG EEN BOEK/VOORRAAD OF REKENING TOE AAN DE LIJST.
     *	2. ROEP DE FUNCTIE addBoekenLijstToDB AAN. (OF addRekeningenToDB)
     *	3. DE LIJST WIST ZICH VANZELF NA HET AANROEPEN VAN DEZE FUNCTIE.
     *
     *	
     *	BELANGRIJK: BOEK EN VOORRAAD ZIJN AAN ELKAAR GEKOPPELD. ALS JE EEN BOEK TOEVOEGT,
     *	ZORG DAN DAT HET BOEK OOK EEN VOORRAAD HEEFT, ALS ER GEEN VOORRAAD WORDT MEEGEGEVEN 
     *	BIJ HET AANMAKEN VAN EEN NIEUW BOEK, DAN WORDT DEZE AUTOMATISCH AANGEMAAKT MET DE
     *	WAARDEN 0 VOOR ELKE LOKATIE.     *	
     */
    
    /**
     * Voor het toevoegen van nieuwe boeken aan de (tijdelijke) lijst.
     * 
     * @param boek
     */
    public void addBoekToLijst (Boek boek) {
	mBoekenLijst.add(boek);
    }
    
    /**
     * Voeg alle boeken in de Lijst toe aan de Database.
     */
    public void addBoekenLijstToDB () {
	DBInsert dbi;
	try {
	    dbi = new DBInsert();	    
	    if (mBoekenLijst.size() != 0) {		
		for (Boek boek : mBoekenLijst) {
			dbi.addBoekToDB(boek);
			dbi.addVoorraadToDB(boek.getVoorraad());
			//mBoekenLijst.remove(boek);
		    }
	    }	    
    	} catch (SQLException e) {
    	    System.out.println("Kan boek niet toevoegen");
	}
    }
    /**
     * Voeg een rekening toe aan de Lijst met rekeningen
     * @param rekening
     */
    public void addRekeningToLijst (Rekening rekening) {
	mRekeningenLijst.add(rekening);
    }
    
    /**
     * Voeg de hele lijst met rekeningen toe aan de DB.
     */
    public void addRekeningenLijstToDB () {
	DBInsert dbi;
	try {
	    dbi = new DBInsert();
	    
	    if (mRekeningenLijst.size() != 0) {		
		for (Rekening rekening : mRekeningenLijst) {
			dbi.addRekeningToDB(rekening);
		    }
	    }	    
	    mRekeningenLijst.clear();
    	} catch (SQLException e) {
    	    System.out.println("Kan rekening niet toevoegen");
    	    
	}
    }
 
}
