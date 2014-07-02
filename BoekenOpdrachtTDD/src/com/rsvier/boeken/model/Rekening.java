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
public class Rekening {
    private String mRekeningNummer;
    private String mRekeningHouder;
    private Locatie mLocatie;
    private double mSaldo;
    
    /**
     * @param rekeningNummer
     * @param rekeningHouder
     * @param locatie
     * @param saldo
     */
    public Rekening(String rekeningNummer, String rekeningHouder,
	    Locatie locatie, double saldo) {
	
	mRekeningNummer = rekeningNummer;
	mRekeningHouder = rekeningHouder;
	mLocatie = locatie;
	mSaldo = saldo;
    }
    
    /**
     * Haal een bestaande rekening uit de DB, op basis van RekeningNummer.
     * Moet precies overeenkomen met het rekeningNummer in de DB!
     * @param rekeningNummer
     * @throws SQLException
     */
    public Rekening(String rekeningNummer) throws SQLException {
	DBRead dbr = new DBRead ();
	Rekening tmp = dbr.getRekeningFromDB(rekeningNummer);
	mRekeningNummer = rekeningNummer;
	mRekeningHouder = tmp.getRekeningHouder();
	mLocatie = tmp.getLocatie();
	mSaldo = tmp.getSaldo();	
    }
    
    /**
     * Additional constructor to deal with String input.
     * @param rekeningNummer
     * @param rekeningHouder
     * @param locatie -> input is String, convert to Enum.
     * @param saldo
     * 
     * @todo: Add exception when convertion String to Enum goes wrong.
     */
    public Rekening(String rekeningNummer, String rekeningHouder,
	    String locatie, double saldo) {
	
	mRekeningNummer = rekeningNummer;
	mRekeningHouder = rekeningHouder;
	mLocatie = Locatie.valueOf( locatie.toUpperCase() );
	mSaldo = saldo;
    }
    /**
     * Functie die zichzelf doorgeeft aan de updateRekening-functie van de klasse DBUpdate
     * Alle gegevens van de rekening worden geupdate. Als RekeningNummer wordt aangepast, dan zal 
     * de functie niet werken. Creeer dan een nieuwe rekening.
     */
    public void updateRekening () {
	try {
	    DBUpdate dbu = new DBUpdate();
	    dbu.updateRekeningInDB(this);
	} catch (SQLException e) {
	    System.out.println("Update van rekening is niet gelukt!");
	    e.printStackTrace();
	}
    }
    
    /**
     * Voeg een bedrag toe aan het saldo van deze rekening.
     * De wijziging wordt meteen doorgevoerd in de DB.
     * @param amountToAdd
     */
    public void addToSaldo (double amountToAdd) {
	mSaldo += amountToAdd;
	updateRekening();
    }
    /**
     * Verminder het saldo van deze rekening.
     * De wijziging wordt meteen doorgevoerd in de DB.
     * @param amountToWithdraw
     */
    public void withdrawFromSaldo (double amountToWithdraw) {
	mSaldo -= amountToWithdraw;
	updateRekening();
    }
        
    /**
     * @return the rekeningNummer
     */
    public String getRekeningNummer() {
        return mRekeningNummer;
    }
    /**
     * @param rekeningNummer the rekeningNummer to set
     */
    void setRekeningNummer(String rekeningNummer) {
        mRekeningNummer = rekeningNummer;
    }
    /**
     * @return the rekeningHouder
     */
    public String getRekeningHouder() {
        return mRekeningHouder;
    }
    /**
     * @param rekeningHouder the rekeningHouder to set
     */
    public void setRekeningHouder(String rekeningHouder) {
        mRekeningHouder = rekeningHouder;
    }
    /**
     * @return the locatie toString
     */
    public String getLocatieString() {
        return mLocatie.toString();
    }
    /**
     * @return the locatie
     */
    public Locatie getLocatie() {
        return mLocatie;
    }
    /**
     * @param locatie the locatie to set
     */
    public void setLocatie(Locatie locatie) {
        mLocatie = locatie;
    }
    /**
     * @return the saldo
     */
    public double getSaldo() {
        return mSaldo;
    }
    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        mSaldo = saldo;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(mSaldo);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result
		+ ((mLocatie == null) ? 0 : mLocatie.hashCode());
	result = prime * result
		+ ((mRekeningHouder == null) ? 0 : mRekeningHouder.hashCode());
	result = prime * result
		+ ((mRekeningNummer == null) ? 0 : mRekeningNummer.hashCode());
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
	if (!(obj instanceof Rekening))
	    return false;
	Rekening other = (Rekening) obj;
	if (Double.doubleToLongBits(mSaldo) != Double
		.doubleToLongBits(other.mSaldo))
	    return false;
	if (mLocatie != other.mLocatie)
	    return false;
	if (mRekeningHouder == null) {
	    if (other.mRekeningHouder != null)
		return false;
	} else if (!mRekeningHouder.equals(other.mRekeningHouder))
	    return false;
	if (mRekeningNummer == null) {
	    if (other.mRekeningNummer != null)
		return false;
	} else if (!mRekeningNummer.equals(other.mRekeningNummer))
	    return false;
	return true;
    }
}