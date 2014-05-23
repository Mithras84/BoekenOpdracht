/**
 * 
 */
package com.rsvier.boeken.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.rsvier.boeken.db.InsertDB;

/**
 * Class description
 * Controller functie opgeroepen door het AdminSysteem (Of Start).
 * Laat de Admin boeken toevoegen aan de Database.
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class AddBoeken {
    
    private ArrayList<Boek> mBoekenLijst;
    
    public AddBoeken () {
	mBoekenLijst = new ArrayList<> () ;
    }
    
    /**
     * Deze functie voegt alle boeken in de ArrayList<Boek> BoekenLijst toe
     * aan de database. Daarvoor wordt de class InsertDB gebruikt, die functies bevat om
     * SQL opdrachten uit te voeren die data aan de DB te kunnen toevoegen.
     */
    public void lijstNaarDB () {	
	try {
	    InsertDB idb = new InsertDB();
	    
	    for (Boek boek : mBoekenLijst) {		
		idb.prepareQueryBoek(boek);
	    }
	    idb.closeConnection();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    System.out.println("Error! Boeken kunnen niet worden toegevoegd!");
	    System.out.println(e.getMessage());
	} 	
    }
    
    /**
     * Voeg een boek toe aan de ArrayList.
     */
    public void voegToeAanLijst (Boek boek) {
	mBoekenLijst.add(boek);
	System.out.println("Boek toegevoegd");
    }

    /**
     * @return the boekenLijst
     */
    public ArrayList<Boek> getBoekenLijst() {
	return mBoekenLijst;
    }

    /**
     * @param Init boekenlijst.
     */
    public void setBoekenLijst(ArrayList<Boek> boekenLijst) {
	mBoekenLijst = boekenLijst;
    }
}
