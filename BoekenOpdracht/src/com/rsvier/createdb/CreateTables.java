/**
 * 
 */
package com.rsvier.createdb;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Class description
 * Create a new databank, create new tables and fill with data.
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 * Instellingen
 * 	host: '127.0.0.1'
 * 	port: '3306'
 * 	databank: 'boeken_opdracht'
 * 	username: 'root'
 * 	password: <<GEEN WW NODIG>> 
 */



public class CreateTables {
    protected  Connection mDBConnection;
    
    CreateTables () throws SQLException {
	ConnectDB db = new ConnectDB ();	
	mDBConnection = db.getDBConnection();
    }
    
    public boolean createTableVoorraad () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS voorraad "
		+ "("
		+ "isbn INT NOT NULL PRIMARY KEY,"
		+ "amsterdam INT,"
		+ "groningen INT,"
		+ "utrecht INT"
		+ ")";
	
	Statement stmt = mDBConnection.createStatement();
	stmt.execute(query);
	stmt.close();
	return true;
    }
    
    public boolean createTableBoek () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS boek "
		+ "("
		+ "isbn INT NOT NULL PRIMARY KEY,"
		+ "titel VARCHAR(255),"
		+ "auteur VARCHAR(255),"
		+ "prijs INT,"
		+ "genre VARCHAR(255),"
		+ "paginas INT"
		+ ")";
	
	Statement stmt = mDBConnection.createStatement();
	stmt.execute(query);
	stmt.close();
	return true;
    }
    
    public boolean createTableRekeningen () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS rekeningen "
		+ "("
		+ "rekeningnummer INT NOT NULL PRIMARY KEY,"
		+ "naam VARCHAR(255),"
		+ "plaats VARCHAR(255),"
		+ "saldo DOUBLE"
		+ ")";
	
	Statement stmt = mDBConnection.createStatement();
	stmt.execute(query);
	stmt.close();
	return true;
    }
}
