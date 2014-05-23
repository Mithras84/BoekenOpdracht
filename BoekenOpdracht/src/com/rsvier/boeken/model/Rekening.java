/**
 * 
 */
package com.rsvier.boeken.model;

/**
 * Class description
 * 
 * @version		1.1 23 mei 2014
 * @author 		Peter
 * @author 		Pieter
 */
public class Rekening {
    private int mRekeningNummer;
    private String mNaam;
    private Locatie mLocatie;
    private double mSaldo;
    
    public Rekening (int rekeningNummer, String naam, Locatie locatie, double saldo) {
	mRekeningNummer = rekeningNummer;
	mNaam = naam;
	mLocatie = locatie;
	mSaldo = saldo;
    }

    /**
     * Geeft het rekeningnummer van het filiaal.
     */
    public int getRekeningNummer() {
        return mRekeningNummer;
    }
    
    /**
     * Geeft de naam van het filiaal.
     */
    public String getNaam() {
        return mNaam;
    }
    
    /**
     * Geeft de locatie van het filiaal.
     */
    public Locatie getLocatie() {
        return mLocatie;
    }
    
    /**
     * Geeft het huidge saldo van de rekening.
     */
    public double getSaldo() {
        return mSaldo;
    }
    
    /**
     * Geeft een nieuw rekeningnummer.
     * @param rekeningNummer het nieuwe rekeningnummer
     */
    public void setRekeningNummer(int rekeningNummer) {
        mRekeningNummer = rekeningNummer;
    }
    
    /**
     * Geeft het filiaal een nieuwe naam.
     * @param naam de nieuwe naam
     */
    public void setNaam(String naam) {
        mNaam = naam;
    }
    
    /**
     * Stelt een nieuwe locatie in van het filiaal.
     * @param locatie de nieuwe locatie
     */
    public void setLocatie(Locatie locatie) {
        mLocatie = locatie;
    }
    
    /**
     * Stelt een bedrag in als nieuw saldo.
     * @param saldo het nieuwe saldo
     */
    public void setSaldo(double saldo) {
        mSaldo = saldo;
    }
    
    /**
     * Telt het totaalbedrag van de bestelling/reservering op bij het saldo van de filiaalrekening.
     * @param lijst de lijst met bestelde/gereserveerde boeken
     * @param rekening de rekening van het filiaal
     */
    public void addToSaldo(Bestelling bestelling, Rekening rekening){
    	double saldo = rekening.getSaldo();
    	double bedrag = bestelling.getTotaalBedrag();
    	mSaldo = saldo + bedrag;
    }
}