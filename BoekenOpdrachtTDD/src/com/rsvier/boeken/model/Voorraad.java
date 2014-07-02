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
 * @version 1.00 30 mei 2014
 * @author Pieter
 */
public class Voorraad {
    private String mISBN;
    private int mVoorraadAmsterdam;
    private int mVoorraadGroningen;
    private int mVoorraadUtrecht;
    

    /**
     * @param iSBN
     * @param voorraadGroningen
     * @param voorraadUtrecht
     */
    public Voorraad(String iSBN, int voorraadAmsterdam, int voorraadGroningen, int voorraadUtrecht) {
	mISBN = iSBN;
	mVoorraadAmsterdam = voorraadAmsterdam;
	mVoorraadGroningen = voorraadGroningen;
	mVoorraadUtrecht = voorraadUtrecht;
    }
    /**
     * Get voorraad from DB using ISBN.
     * @param isbn
     * @throws SQLException
     */
    public Voorraad(String isbn) throws SQLException {
	DBRead dbr = new DBRead();
	Voorraad tmp = dbr.getVoorraadFromDB(isbn);
	mISBN = isbn;
	mVoorraadAmsterdam = tmp.getVoorraadAmsterdam();
	mVoorraadGroningen = tmp.getVoorraadGroningen();
	mVoorraadUtrecht = tmp.getVoorraadUtrecht();
    }
    
    public void updateVoorraad () throws SQLException {
	DBUpdate dbu = new DBUpdate ();
	dbu.updateVoorraadInDB(this);
    }
    
    public int getVoorraadFromLocatie (String locatie) throws SQLException {
	locatie = locatie.toLowerCase();
	switch (locatie) {	
		case "amsterdam" : 
		    if (mVoorraadAmsterdam == 0) return 0;
		    mVoorraadAmsterdam--;
		    updateVoorraad ();
		    return mVoorraadAmsterdam;
		case "groningen": 
		    if (mVoorraadGroningen == 0) return 0;
		    mVoorraadGroningen--;
		    updateVoorraad ();
		    return mVoorraadGroningen;
		case "utrecht": 
		    if (mVoorraadUtrecht == 0) return 0;
		    mVoorraadUtrecht--;
		    updateVoorraad ();
		    return mVoorraadUtrecht;
		default: return 0;
	}
    }
    
    public int getHoogsteVoorraad () throws SQLException {
	if (mVoorraadAmsterdam != 0 || ( mVoorraadGroningen != 0 || mVoorraadUtrecht != 0) ) {
	
	    if (mVoorraadAmsterdam >= mVoorraadGroningen && mVoorraadAmsterdam >= mVoorraadUtrecht) {
		return getVoorraadFromLocatie ("amsterdam");
	    } else if (mVoorraadGroningen >= mVoorraadAmsterdam && mVoorraadGroningen >= mVoorraadUtrecht) {
		return getVoorraadFromLocatie ("groningen");
	    } else if (mVoorraadUtrecht >= mVoorraadAmsterdam && mVoorraadUtrecht >= mVoorraadGroningen) {
		return getVoorraadFromLocatie ("utrecht");
	    } else return 0;
	    
	} else return 0;
    }

    /**
     * @return the iSBN
     */
    public String getISBN() {
	return mISBN;
    }

    /**
     * @param iSBN
     *            the iSBN to set
     */
    public void setISBN(String iSBN) {
	mISBN = iSBN;
    }

    /**
     * @return the voorraadGroningen
     */
    public int getVoorraadGroningen() {
	return mVoorraadGroningen;
    }

    /**
     * @param voorraadGroningen
     *            the voorraadGroningen to set
     */
    public void setVoorraadGroningen(int voorraadGroningen) {
	mVoorraadGroningen = voorraadGroningen;
    }

    /**
     * @return the voorraadUtrecht
     */
    public int getVoorraadUtrecht() {
	return mVoorraadUtrecht;
    }

    /**
     * @param voorraadUtrecht
     *            the voorraadUtrecht to set
     */
    public void setVoorraadUtrecht(int voorraadUtrecht) {
	mVoorraadUtrecht = voorraadUtrecht;
    }

    /**
     * @return the voorraadAmsterdam
     */
    public int getVoorraadAmsterdam() {
	return mVoorraadAmsterdam;
    }

    /**
     * @param voorraadAmsterdam
     *            the voorraadAmsterdam to set
     */
    public void setVoorraadAmsterdam(int voorraadAmsterdam) {
	mVoorraadAmsterdam = voorraadAmsterdam;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((mISBN == null) ? 0 : mISBN.hashCode());
	result = prime * result + mVoorraadAmsterdam;
	result = prime * result + mVoorraadGroningen;
	result = prime * result + mVoorraadUtrecht;
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Voorraad))
	    return false;
	Voorraad other = (Voorraad) obj;
	if (mISBN == null) {
	    if (other.mISBN != null)
		return false;
	} else if (!mISBN.equals(other.mISBN))
	    return false;
	if (mVoorraadAmsterdam != other.mVoorraadAmsterdam)
	    return false;
	if (mVoorraadGroningen != other.mVoorraadGroningen)
	    return false;
	if (mVoorraadUtrecht != other.mVoorraadUtrecht)
	    return false;
	return true;
    }
}
