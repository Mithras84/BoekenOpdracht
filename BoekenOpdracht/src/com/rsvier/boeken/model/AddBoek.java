/**
 * 
 */
package com.rsvier.boeken.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.rsvier.boeken.db.InsertDB;

/**
 * Class description
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class AddBoek {
    private ArrayList<Boek> mBoekenLijst;
    
    AddBoek () {
	mBoekenLijst = new ArrayList<> () ;
    }
    
    public void lijstNaarDB () {
	InsertDB idb = new InsertDB();
	
	for (Boek boek : mBoekenLijst) {	    
	    try {
		idb.prepareQueryBoek("boek", boek);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
    
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
     * @param boekenLijst the boekenLijst to set
     */
    public void setBoekenLijst(ArrayList<Boek> boekenLijst) {
	mBoekenLijst = boekenLijst;
    }
}
