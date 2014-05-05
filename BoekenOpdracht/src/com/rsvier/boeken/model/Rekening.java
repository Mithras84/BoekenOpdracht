/**
 * 
 */
package com.rsvier.boeken.model;

/**
 * Class description
 * 
 * @version		1.00 5 mei 2014
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

    public int getRekeningNummer() {
        return mRekeningNummer;
    }

    public String getNaam() {
        return mNaam;
    }

    public Locatie getLocatie() {
        return mLocatie;
    }

    public double getSaldo() {
        return mSaldo;
    }

    public void setRekeningNummer(int rekeningNummer) {
        mRekeningNummer = rekeningNummer;
    }

    public void setNaam(String naam) {
        mNaam = naam;
    }

    public void setLocatie(Locatie locatie) {
        mLocatie = locatie;
    }

    public void setSaldo(double saldo) {
        mSaldo = saldo;
    }
}
