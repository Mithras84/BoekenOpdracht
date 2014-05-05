/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsvier.boeken.model.Boek;

/**
 * Class description
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class InsertDB {
    protected  Connection mDBConnection;
    protected PreparedStatement mPStmt;
    
    public InsertDB () {
	try {
	    ConnectDB db = new ConnectDB ();	
	    mDBConnection = db.getDBConnection();
	    
	} catch (SQLException e) {
	    
	}
    }
    /**
     * Prepares a statement for adding a book to the table.
     * The table book has 6 fields.
     * @param table
     * @param boek
     * @throws SQLException
     */
    public void prepareQueryBoek (String table, Boek boek) throws SQLException {
	//The query, the question marks represent the fields in the table.
	String query = "INSERT INTO " + table + " values(?,?,?,?,?,?)";
	
	//Prepares the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	mPStmt.setInt(1,boek.getISBN());
	mPStmt.setString(2, boek.getTitel());
	mPStmt.setString(3, boek.getAuteur());
	mPStmt.setDouble(4, boek.getPrijs());
	mPStmt.setString(5, boek.getGenre());
	mPStmt.setInt(6, boek.getPaginas());
	
	mPStmt.execute();
	
	System.out.println("Boek toegevoegd!");
    }
    
}
