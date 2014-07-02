/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class description
 * 
 * @version		1.00 29 mei 2014
 * @author 		Pieter
 */
public class DBConnect {
    
    protected Connection mDBConnection;
    
    private String mHost;
    private String mDBName;
    private String mUserName;
    private String mPassword;
    
    /**
     * 
     * @param host: Host address -> Format 'mysql://xxx.xxx.xxx.xxx:yyyy/' where x = IP and y = Port
     * @param dbName: Name of the Databank
     * @param userName: Username
     * @param Password: Password (If empty use "")
     * @throws SQLException 
     */
    DBConnect (String host, String dbName, String userName, String password) throws SQLException {
	mHost = "jdbc:" + host;
	mDBName = dbName;
	mUserName = "user=" + userName;
	mPassword = "password=" + password;
	connectToDB();
    }
    
    /**
     * single-arg constructor met default host, user and pass.
     * @param dbName: Name of Databank
     * @throws SQLException 
     */
    DBConnect (String dbName) throws SQLException {
	this("mysql://127.0.0.1:3306/", dbName, "root", "");
    }
    
    /**
     * no-args constructor for lazy people.
     * Connects to Winkel with default host, user and pass. 
     * @throws SQLException 
     */
    DBConnect () throws SQLException {
	this("mysql://127.0.0.1:3306/", "winkel", "root", "");
    }
   
    /**
     * Een kort functie om de Connectie op te zetten, en een snelle check om te kijken of deze geldig is.
     * 
     * @param url
     * @return
     * @throws SQLException
     */
    protected void connectToDB () throws SQLException {
	String url = mHost + mDBName + "?" + mUserName + "&" + mPassword;
	mDBConnection = DriverManager.getConnection(url);	
    }

    /**
     * Test if connection is open, using a time-out of 10
     * @throws SQLException
     */
    protected boolean checkConnection() throws SQLException {
	if (mDBConnection.isValid(10))
	    return true;
	else 
	    return false;
    }
    /** 
     * Function to manually close the DB connection.
     * @return
     * @throws SQLException
     */
    protected boolean closeConnection () throws SQLException {
	mDBConnection.close();	 
	
	if ( checkConnection() )
	    return false;
	else 
	    return true;
    }

}
