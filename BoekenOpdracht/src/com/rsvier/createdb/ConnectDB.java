/**
 * 
 */
package com.rsvier.createdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class description
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class ConnectDB {
    Connection mDBConnection;

    public ConnectDB() throws SQLException {
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
    
    public Connection getDBConnection () {
	return mDBConnection;
    }
    
    public boolean closeConnection() throws SQLException {
	mDBConnection.close();
	return mDBConnection.isClosed();
    }
    
    protected boolean createConnection (String url) throws SQLException { 
	mDBConnection = DriverManager.getConnection(url);
	
	if (mDBConnection.isValid(0))
	    return true;
	else return false;
    }
}
