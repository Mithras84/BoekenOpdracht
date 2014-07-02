/**
 * 
 */
package com.rsvier.boeken.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Locatie;
import com.rsvier.boeken.model.Rekening;
import com.rsvier.boeken.db.DBRead;

/**
 * Class description
 * 
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
public class Kassa {
    
    ArrayList<Boek> mAlleBoekenLijst;

    ArrayList<Boek> mBestelLijst;
    
    ArrayList<Boek> mBestelLijstAanwezigeBoeken;
    double mTotaalBedrag;
    
    Locatie mLocatie;
    Rekening mRekening;
    
    /**
     * Constructor. Geef een locatie op.
     * Op basis van de locatie wordt een kassa-bject aangemaakt.
     * De rekening die bij deze locatie hoort wordt automatisch aangemaakt vanuit
     * de database, op basis van een rekening nummer (Check de enum klasse van locatie,
     * daar staat het rekening nummer in).
     * @param locatie
     */
    public Kassa (String locatie) {
	mTotaalBedrag = 0.0;
	mBestelLijst = new ArrayList<Boek>();
	mLocatie = Locatie.valueOf(locatie.toUpperCase());
	try {
	    mRekening = new Rekening (mLocatie.getREKENING_NUMMER());
	    setAlleBoekenLijst();
	} catch (SQLException s) {
	    System.out.println ("Kan rekening niet toevoegen! Voeg handmatig toe met de setter");
	    mRekening = null;
	}
    }
    
    /**
     * @return the alleBoekenLijst
     */
    public ArrayList<Boek> getAlleBoekenLijst() {
        return mAlleBoekenLijst;
    }
    
    /**
     * @return the alleBoekenLijst
     */
    public ArrayList<Boek> getAlleBoekenLijstOpLocatie() {
	ArrayList<Boek> tempLijst = new ArrayList<Boek>();
        for (Boek boek : mAlleBoekenLijst) {
            try {
		if ( boek.getVoorraad().getVoorraadFromLocatie(mLocatie.toString() ) != 0) {
		    tempLijst.add(boek);
		}
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Error in de Database in methode getAlleBoekenLijstOpLocatie");
	    }
        }
        return tempLijst;
    }

    /**
     * @param alleBoekenLijst the alleBoekenLijst to set
     */
    public void setAlleBoekenLijst() {
	try {
	    DBRead dbr = new DBRead ();
	    mAlleBoekenLijst = dbr.getAllBooks();
	} catch (SQLException e) {
	    System.out.println ("Error in de Database!");
	    mAlleBoekenLijst = null;
	}
    }
    
    /**
     * Voeg een boek toe aan de bestellijst. 
     * De hele lijst is gemakkelijk op te halen via de getter.
     * @param isbn
     */
    public void addBoekToBestelling (String isbn) {
	try {
	    Boek boek = new Boek(isbn);
	    mBestelLijst.add(boek);
	    updateTotaalBedrag(boek.getPrijs());	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    System.out.println ("Kan Boek niet toevoegen vanuit de Database!");
	}
    }
    
    /**
     * Deze functie wordt automatisch aangeroepen na het toevoegen van een boek aan de bestellijst.
     * Gebruik getter om het totaalBedrag op ieder moment te kunnen uitlezen.
     * @param bedrag
     */
    private void updateTotaalBedrag(double bedrag) {
	mTotaalBedrag += bedrag;
    }
    
    /**
     * Functie om bestelling af te ronden.
     * Checked voorraad van de hele lijst, als er een voorraad gevonden is (al dan niet
     * op een andere locatie), ga naar het volgende boek. Als boek niet op voorraad is, trek dan 
     * de prijs van het boek van het totaalBedrag af, en haal het boek uit de bestellijst.
     * 
     * >> Helaas kan je in een enhanced for-loop niet de ArrayList aanpassen, ik heb dus een <<
     * >> nieuwe lijst aangemaakt (mBestelLijstAanwezigeBoeken) waarin alleen de aanwezige   <<
     * >> worden opgenomen. Dit moet eigenlijk ook gebeuren bij de klasse rekeningen         <<
     * 
     * Als voorraad gechecked is, maak een bon. Vervolgens het totaalbedrag aan de rekening toevoegen,
     * en de bestellijst en totaalbedrag weer op 0 zettne.
     * Geef String bon terug.
     * @return String bon. String met alle boeken die gekocht zijn, en de prijs.
     */
    public String rekenBestellingAf () {
	mBestelLijstAanwezigeBoeken = new ArrayList<Boek>();
	for (Boek boek : mBestelLijst) {
	    if (!updateVoorraad(boek)) {
		updateTotaalBedrag(-(boek.getPrijs()));
	    } else {
		mBestelLijstAanwezigeBoeken.add(boek);
	    }
	}
	String bon = printBon();
	mRekening.addToSaldo(mTotaalBedrag);
	mBestelLijst.clear();
	mBestelLijstAanwezigeBoeken.clear();
	mTotaalBedrag = 0.0;
	return bon;
    }
    

    /**
     * Functie die de Voorraad functies kan aansturen.
     * Als boek lokaal op voorraad is, dan lokale voorraad -1 (in de VoorraadKlasse).
     * Zoniet, dan locatie met hoogste voorraad opzoeken, en deze -1 doen.
     * Als het boek op geen enkele locatie op voorraad is, false teruggeven.
     * @param boek
     * @return boolean
     */
    private boolean updateVoorraad(Boek boek) {
	try {
	    int voorraadBoek = boek.getVoorraad().getVoorraadFromLocatie(mLocatie.toString());
	    if ( voorraadBoek == 0 ) {
		voorraadBoek = boek.getVoorraad().getHoogsteVoorraad();
		if (voorraadBoek == 0) {
		    System.out.println("Boek niet op voorraad!");
		    //mBestelLijst.remove(boek);
		    return false;
		} 
	    }
	    return true;
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    System.out.println("Fout in de SQL opdracht!");
	    return false;
	}
    }
    /**
     * Spreek voor zich: Loop door de mBestelLijstAanwezigeBoeken heen, en voeg de titel, auteur en prijs
     * van het boek aan de bon-String toe.
     * Na de loop het totaalbedrag toevoegen.
     * 
     * @return String met bon
     */
    private String printBon () {
	String bon = "Boekenhandel " + mLocatie + "\n";
	for (Boek boek : mBestelLijstAanwezigeBoeken) {
	    bon += boek.getTitel() + ", " + boek.getAuteur() + " \t Prijs: " + boek.getPrijs() + " \n";
	}
	bon += "\nTotaalbedrag: \t \t \t" + mTotaalBedrag + "\n";
	return bon;
    }
    
    /**
     * @return the bestelLijst
     */
    public ArrayList<Boek> getBestelLijst() {
        return mBestelLijst;
    }

    /**
     * @param bestelLijst the bestelLijst to set
     */
    public void setBestelLijst(ArrayList<Boek> bestelLijst) {
        mBestelLijst = bestelLijst;
    }

    /**
     * @return the totaalBedrag
     */
    public double getTotaalBedrag() {
        return mTotaalBedrag;
    }

    /**
     * @param totaalBedrag the totaalBedrag to set
     */
    public void setTotaalBedrag(double totaalBedrag) {
        mTotaalBedrag = totaalBedrag;
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
}
