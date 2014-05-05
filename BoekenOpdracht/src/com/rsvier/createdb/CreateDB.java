/**
 * 
 */
package com.rsvier.createdb;
import java.sql.DriverManager;
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



public class CreateDB {
    protected  Connection mDBConnection;
    
    public void initConnection() throws SQLException {
	// TODO Auto-generated method stub
	String host = "jdbc:mysql://127.0.0.1:3306/";
	String dbName = "winkel";
	String userName = "user=root";
	String passWord = "password=";
	
	if (createConnection(host + dbName + "?" + userName + "&" + passWord) ) {
	    System.out.println("Connected!");
	} else {
	    System.out.println("Failed!");
	}	
    }
    
    public boolean closeConnection() throws SQLException {
	mDBConnection.close();
	return mDBConnection.isClosed();
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
	return stmt.execute(query);
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
	return stmt.execute(query);
    }
    
    public boolean createTableRekeningen () throws SQLException {
	String query = "CREATE TABLE IF NOT EXISTS rekeningen "
		+ "("
		+ "rekeningnummer INT NOT NULL PRIMARY KEY,"
		+ "naam VARCHAR(255),"
		+ "plaats VARCHAR(255),"
		+ "saldo CURRENCY"
		+ ")";
	
	Statement stmt = mDBConnection.createStatement();
	return stmt.execute(query);
    }
    
    protected boolean createConnection (String url) throws SQLException { 
	mDBConnection = DriverManager.getConnection(url);
	
	if (mDBConnection.isValid(0))
	    return true;
	else return false;
    }
    
    

}
