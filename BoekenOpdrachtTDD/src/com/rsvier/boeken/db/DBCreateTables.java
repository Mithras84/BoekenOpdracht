/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class DBCreateTables extends DBConnect {
    Statement mStmt;
    
    /**
     * @throws SQLException 
     * 
     */
    public DBCreateTables() throws SQLException {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @param host
     * @param dbName
     * @param userName
     * @param password
     * @throws SQLException 
     */
    public DBCreateTables(String host, String dbName, String userName,
	    String password) throws SQLException {
	super(host, dbName, userName, password);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param dbName
     * @throws SQLException 
     */
    public DBCreateTables(String dbName) throws SQLException {
	super(dbName);
	// TODO Auto-generated constructor stub
    }


    
    /**
     * Functie die een query bevat om de tabel Boek op te zetten in de DB.
     * @return
     * @throws SQLException
     */
    public boolean createTableBoek () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS boek "
		+ "("
		+ "isbn VARCHAR(16) NOT NULL PRIMARY KEY,"
		+ "titel VARCHAR(255),"
		+ "auteur VARCHAR(255),"
		+ "prijs DOUBLE,"
		+ "genre VARCHAR(255),"
		+ "paginas INT"
		+ ")";
	return executeStatement(query);
    }
    
    /**
     * Functie die een query bevat om de tabel Rekeningen op te zetten in de DB.
     * @todo : Opletten dat deze tabel eigenlijk in de database "Bank" moet komen.
     * @return
     * @throws SQLException
     */
    public boolean createTableRekeningen () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS rekeningen "
		+ "("
		+ "rekeningnummer VARCHAR(25) NOT NULL PRIMARY KEY,"
		+ "naam VARCHAR(255),"
		+ "plaats VARCHAR(255),"
		+ "saldo DOUBLE"
		+ ")";
	return executeStatement(query);

    }
    
    /**
     * Functie die een query bevat om de tabel Voorraad op te zetten in de DB.
     * @return
     * @throws SQLException
     */
    public boolean createTableVoorraad () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS voorraad "
		+ "("
		+ "isbn VARCHAR(16) NOT NULL PRIMARY KEY,"
		+ "amsterdam INT,"
		+ "groningen INT,"
		+ "utrecht INT"
		+ ")";
	
	return executeStatement(query);
    }
    
    /**
     * Private functie om query om te zetten naar statement en uit te voeren.
     * @param query
     * @return
     * @throws SQLException
     */
    private boolean executeStatement (String query) throws SQLException {
	if( checkConnection() ) {
	    mStmt = mDBConnection.createStatement();
	    mStmt.execute(query);	    
	    mStmt.close();
	    return true;
	} else return false;
    }
}
