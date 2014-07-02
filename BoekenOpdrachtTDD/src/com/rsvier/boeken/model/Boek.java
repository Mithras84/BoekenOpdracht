/**
 * 
 */
package com.rsvier.boeken.model;

import java.sql.SQLException;

import com.rsvier.boeken.db.DBRead;
import com.rsvier.boeken.db.DBUpdate;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class Boek {
    private String mISBN;
    private String mTitel;
    private String mAuteur;
    private double mPrijs;
    private String mGenre;
    private int mPaginas;
    private Voorraad mVoorraad;
    
    /**
     * Constructor voor Boek. Voorraad wordt niet meegegeven, deze wordt automatisch aangemaakt (met de waarden 0).
     * Als je een specifieke voorraad wilt gebruiken, roep dan de andere constructor aan.
     * @param ISBN
     * @param titel
     * @param auteur
     * @param prijs
     * @param genre
     * @param paginas
     */
    public Boek (String ISBN, String titel, String auteur, double prijs, String genre, int paginas) {
	mISBN = ISBN;
	mTitel = titel;
	mAuteur = auteur;
	mPrijs = prijs;
	mGenre = genre;
	mPaginas = paginas;
	mVoorraad = new Voorraad(ISBN,0,0,0);
    }
    
    /**
     * Constructor met voorraad variable.
     * @param ISBN
     * @param titel
     * @param auteur
     * @param prijs
     * @param genre
     * @param paginas
     * @param voorraad
     */
    public Boek (String ISBN, String titel, String auteur, double prijs, String genre, int paginas, Voorraad voorraad) {
	mISBN = ISBN;
	mTitel = titel;
	mAuteur = auteur;
	mPrijs = prijs;
	mGenre = genre;
	mPaginas = paginas;
	mVoorraad = voorraad;
    }
    
    /**
     * Constructor om nieuw boek vanuit de DB aan te maken obv een ISBN nummer.
     * Voorraad wordt ook automatisch aangemaakt vanuit de DB.
     * @param isbn
     */
    public Boek (String isbn) throws SQLException {
    DBRead dbr = new DBRead();
    Boek tmp = dbr.getBoekFromDB(isbn);
    	mISBN = isbn;
    	mTitel = tmp.getTitel();
    	mAuteur = tmp.getAuteur();
    	mPrijs = tmp.getPrijs();
    	mGenre = tmp.getGenre();
    	mPaginas = tmp.getPaginas();
    	mVoorraad = dbr.getVoorraadFromDB(isbn);
    }
    
    /**
     * Connect to DB and call function updateBoek(boek) with self (this).
     * Also updates Voorraad.
     * @throws SQLException
     */
    public void updateBoek () {	
	try {
	    DBUpdate dbu = new DBUpdate ();
	    dbu.updateBoekInDB(this);
	    dbu.updateVoorraadInDB(mVoorraad);
	} catch (NullPointerException e) {
	    System.out.println("Geen voorraad-object gevonden!");
	} catch (SQLException s) {
	    System.out.println("Update is niet gelukt!");
	}
    }

    /**
     * @return the iSBN
     */
    public String getISBN() {
        return mISBN;
    }

    /**
     * @param iSBN the iSBN to set
     */
    void setISBN(String iSBN) {
        mISBN = iSBN;
    }

    /**
     * @return the titel
     */
    public String getTitel() {
        return mTitel;
    }

    /**
     * @param titel the titel to set
     */
    public void setTitel(String titel) {
        mTitel = titel;
    }

    /**
     * @return the auteur
     */
    public String getAuteur() {
        return mAuteur;
    }

    /**
     * @param auteur the auteur to set
     */
    public void setAuteur(String auteur) {
        mAuteur = auteur;
    }

    /**
     * @return the prijs
     */
    public double getPrijs() {
        return mPrijs;
    }

    /**
     * @param prijs the prijs to set
     */
    public void setPrijs(double prijs) {
        mPrijs = prijs;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return mGenre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        mGenre = genre;
    }

    /**
     * @return the paginas
     */
    public int getPaginas() {
        return mPaginas;
    }

    /**
     * @param paginas the paginas to set
     */
    public void setPaginas(int paginas) {
        mPaginas = paginas;
    }

    /**
     * @return the voorraad
     */
    public Voorraad getVoorraad() {
        return mVoorraad;
    }

    /**
     * @param voorraad the voorraad to set
     */
    public void setVoorraad(Voorraad voorraad) {
        mVoorraad = voorraad;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((mAuteur == null) ? 0 : mAuteur.hashCode());
	result = prime * result + ((mGenre == null) ? 0 : mGenre.hashCode());
	result = prime * result + ((mISBN == null) ? 0 : mISBN.hashCode());
	result = prime * result + mPaginas;
	long temp;
	temp = Double.doubleToLongBits(mPrijs);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((mTitel == null) ? 0 : mTitel.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Boek))
	    return false;
	Boek other = (Boek) obj;
	if (mAuteur == null) {
	    if (other.mAuteur != null)
		return false;
	} else if (!mAuteur.equals(other.mAuteur))
	    return false;
	if (mGenre == null) {
	    if (other.mGenre != null)
		return false;
	} else if (!mGenre.equals(other.mGenre))
	    return false;
	if (mISBN == null) {
	    if (other.mISBN != null)
		return false;
	} else if (!mISBN.equals(other.mISBN))
	    return false;
	if (mPaginas != other.mPaginas)
	    return false;
	if (Double.doubleToLongBits(mPrijs) != Double
		.doubleToLongBits(other.mPrijs))
	    return false;
	if (mTitel == null) {
	    if (other.mTitel != null)
		return false;
	} else if (!mTitel.equals(other.mTitel))
	    return false;
	return true;
    }
}