/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class description
 * Hier wordt de database connectie gemaakt. Eenmaal verbonden dan kunnen de child-classes 
 * operaties uitvoeren.
 * Elke klasse die een query uitvoert moet deze klasse extenden.
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class ConnectDB {
    Connection mDBConnection;

    /**
     * Maak een connectie met de Database. Parameters moeten hier worden ingesteld.
     * Eventueel de constructor aanpassen en een parameter "DBName" meegeven omdat we
     * gebruik moeten maken van twee databanken (Winkel en Bank).
     * @throws SQLException
     */
    protected ConnectDB() throws SQLException {
	// TODO Auto-generated method stub
	String host = "jdbc:mysql://127.0.0.1:3306/";
	String dbName = "winkel";
	String userName = "user=root";
	String passWord = "password=";
	
	if (createConnection(host + dbName + "?" + userName + "&" + passWord) ) {
	    System.out.println("Connected!");
	} else {
	    System.out.println("Failed!");
	    throw new SQLException("Kan geen verbinding maken met de Database! Check de parameters"
	    	+ " in de klasse com.rsvier.boeken.db.ConnectDB");
	}	
    }
    
    /**
     * Sluit de verbinding met de DB. Misschien overbodig..
     */
    public void closeConnection() throws SQLException {
	mDBConnection.close();
	if (! mDBConnection.isClosed() ) {
	    throw new SQLException ("Databse verbinding kan niet worden afgesloten!");
	};
    }
    
    /**
     * Een kort functie om de Connectie op te zetten, en een snelle check om te kijken of deze geldig is.
     * 
     * @param url
     * @return
     * @throws SQLException
     */
    protected boolean createConnection (String url) throws SQLException { 
	mDBConnection = DriverManager.getConnection(url);	
	if (mDBConnection.isValid(0))
	    return true;
	else 
	    return false;
    }
}
