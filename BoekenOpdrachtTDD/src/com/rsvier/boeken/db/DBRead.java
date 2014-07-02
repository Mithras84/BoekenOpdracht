/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Rekening;
import com.rsvier.boeken.model.Voorraad;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class DBRead extends DBConnect {
    ResultSet mResult;
    Statement mStmt;

    /**
     * @param host
     * @param dbName
     * @param userName
     * @param password
     * @throws SQLException
     */
    public DBRead(String host, String dbName, String userName, String password)
	    throws SQLException {
	super(host, dbName, userName, password);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param dbName
     * @throws SQLException
     */
    public DBRead(String dbName) throws SQLException {
	super(dbName);
	// TODO Auto-generated constructor stub
    }

    /**
     * @throws SQLException
     */
    public DBRead() throws SQLException {
	// TODO Auto-generated constructor stub
    }
    /**
     * Korte beschrijving: Zoek op basis van ISBN naar een boek in de DB. Als gevonden, maak een 
     * nieuw boek object aan op basis van de resultaten van de query. Geef boek resultaat terug.
     * @param isbn
     * @return Boek
     * @throws SQLException
     */
    public Boek getBoekFromDB (String isbn) throws SQLException {
	Boek boek;	
	String query = 	"SELECT * FROM boek " +
			"WHERE isbn = " + isbn;
	
	if ( executeStatement(query) ) {
	    if ( mResult.first() ) {
		boek = new Boek (mResult.getString(1), mResult.getString(2),
		    mResult.getString(3), mResult.getDouble(4), mResult.getString(5),
		    mResult.getInt(6) );
	    } else {
		throw new SQLException ("No results found!");
	    }
	} else boek = null;
	
	return boek;
    }
    /**
     * Functie om alle ISBN nummers uit de database te halen.
     * @return
     * @throws SQLException
     */
    public ArrayList<Boek> getAllBooks () throws SQLException {
	ArrayList<Boek> alleBoeken = new ArrayList<Boek>();
	
	String query = "SELECT * FROM boek ";
	if (executeStatement (query) ) {
	while (mResult.next()) {
	    Boek boek = new Boek ( mResult.getString(1) );
	    alleBoeken.add(boek);
	    }
	}	
	if (alleBoeken.size() != 0 ) {
	    return alleBoeken;
	} else return null;
    }
    
    /**
     * Korte beschrijving: Zoek op basis van ISBN naar een voorraad in de DB. Als gevonden, maak een 
     * nieuw voorraad object aan op basis van de resultaten van de query. Geef voorraad resultaat terug.
     * @param isbn
     * @return Voorraad
     * @throws SQLException
     */
    public Voorraad getVoorraadFromDB (String isbn) throws SQLException {
	Voorraad voorraad;
	String query = 	"SELECT * FROM voorraad " +
			"WHERE isbn = " + isbn;
	
	if ( executeStatement(query) ) {
	    if ( mResult.first() ) {
		voorraad = new Voorraad ( mResult.getString(1) ,
			mResult.getInt(2) , mResult.getInt(3) , mResult.getInt(4) );
	    } else {
		throw new SQLException ("No results found!");
	    }
	} else voorraad = null;
	
	return voorraad;
    }
    
    /**
     * Korte beschrijving: Zoek op basis van Rekening NR naar een rekening in de DB. Als gevonden, maak een 
     * nieuw Rekening object aan op basis van de resultaten van de query. Geef Rekening resultaat terug.
     * @param RekeningNr
     * @return Rekening
     * @throws SQLException
     */
    public Rekening getRekeningFromDB (String rekeningNr) throws SQLException {
	Rekening rekening;
	String query = 	"SELECT * FROM rekeningen " +
			"WHERE rekeningnummer = '" + rekeningNr + "'";
	if ( executeStatement(query) ) {
	    if ( mResult.first() ) {
		rekening = new Rekening ( mResult.getString(1) ,
			mResult.getString(2) , mResult.getString(3) , mResult.getDouble(4) );
	    } else {
		throw new SQLException ("No results found!");
	    }
	} else rekening = null;
	
	return rekening;
    }
    
    /**
     * Private functie om query om te zetten naar statement en uit te voeren.
     * Als query geen error geeft, zet het resultaat in mResult.
     * @param query
     * @return true | SQLException.
     * @throws SQLException
     */
    private boolean executeStatement (String query) throws SQLException {
	if( checkConnection() ) {
	    mStmt = mDBConnection.createStatement();
	    if ( mStmt.execute(query) ) {		
		mResult = mStmt.getResultSet();
		return true;
	    
	    } else throw new SQLException("GEEN BOEKEN GEVONDEN");
	    
	} else throw new SQLException("GEEN ACTIEVE VERBINDING GEVONDEN");
    }
}
