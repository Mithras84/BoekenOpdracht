/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.SQLException;
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
 * 
 * @todo:
 * - Verkeerd gelezen. We hebben twee Databanken, namelijk 'Winkel' voor tabellen 'boek'
 *   en 'voorraad' en 'Bank' voor de tabel 'rekeningen'. Dit moet nog even worden aangepast.
 * 
 */
public class CreateTables extends ConnectDB {
    
    /**
     * Call naar parent constructor om connectie op te zetten.
     * @throws SQLException
     */
    public CreateTables () throws SQLException {
	super ();
    }
    
    /**
     * Functie die een query bevat om de tabel Voorraad op te zetten in de DB.
     * @return
     * @throws SQLException
     */
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
    
    /**
     * Functie die een query bevat om de tabel Boek op te zetten in de DB.
     * @return
     * @throws SQLException
     */
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
    
    /**
     * Functie die een query bevat om de tabel Rekeningen op te zetten in de DB.
     * @todo : Opletten dat deze tabel eigenlijk in de database "Bank" moet komen.
     * @return
     * @throws SQLException
     */
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
