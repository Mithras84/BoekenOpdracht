/**
 * 
 */
package com.rsvier.createdb;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

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
    
    public void initConnection() {
	// TODO Auto-generated method stub
	String host = "jdbc:mysql://127.0.0.1:3306/";
	String dbName = "boeken_opdracht";
	String userName = "user=root";
	String passWord = "password=";
	
	if (createConnection(host + dbName + "?" + userName + "&" + passWord) ) {
	    System.out.println("Connected!");
	} else {
	    System.out.println("Failed!");
	}	
    }
    
    protected boolean createConnection (String url) {
	try {
	    //Class.forName("com.oracle.jdbc.Driver");
	    mDBConnection = DriverManager.getConnection(url);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
		
	return true;
    }
    
    

}
