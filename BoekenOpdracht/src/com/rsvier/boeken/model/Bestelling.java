/**
 * 
 */
package com.rsvier.boeken.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class description
 * Alle functies die belangrijk zijn voor een bestelling.
 * 
 * 
 * @version		1.00 23 mei 2014
 * @author 		Pieter
 */
public class Bestelling {
    
    private ArrayList<Boek> mBoekenLijst;
    private Locatie mLocatie;
    
    private static int BESTEL_NR;

    
    public Bestelling (Locatie locatie) {
	mLocatie = locatie;
	mBoekenLijst = new ArrayList<>();
	BESTEL_NR ++;
    }
    
    //public Boek getBoekInfo (int isbn) {
	
    //}
    
    public void addBoekToBestelling (Boek boek) {
	mBoekenLijst.add(boek);
    }
    
    public double getTotaalBedrag () {
	double som = 0;
	for (Boek boek : mBoekenLijst) {
	    som += boek.getPrijs();
	}
	
	return som;
    }
    
    public String printBon () {
	String bon = "Bestelling " + BESTEL_NR + "Datum " + new Date().toString() + "\n";
	for (Boek boek : mBoekenLijst) {
	    bon+= boek.getTitel() + " " + boek.getAuteur() + " " + boek.getPrijs() + " \n";
	}
	bon += "\n   Totaal bedrag:   " + getTotaalBedrag () ;
	return bon;
    }
    
    public ArrayList<Boek> getBoekenLijst () {
	return mBoekenLijst;
    }

    /**
     * @return the locatie
     */
    public Locatie getLocatie() {
	return mLocatie;
    }
}
