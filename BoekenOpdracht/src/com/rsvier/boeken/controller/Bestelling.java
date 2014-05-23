/**
 * 
 */
package com.rsvier.boeken.controller;

import java.util.ArrayList;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Locatie;

/**
 * Class description
 * 
 * @version		1.00 23 mei 2014
 * @author 		Pieter
 */
public class Bestelling {
    
    private ArrayList<Boek> mBoekenLijst;
    private Locatie mLocatie;
    

    
    public Bestelling (Locatie locatie) {
	mLocatie = locatie;
	mBoekenLijst = new ArrayList<>();
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

    /**
     * @return the locatie
     */
    public Locatie getLocatie() {
	return mLocatie;
    }
}
