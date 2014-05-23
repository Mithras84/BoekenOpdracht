/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsvier.boeken.model.Boek;

/**
 * Class description
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class InsertDB extends ConnectDB{
    
    protected PreparedStatement mPStmt;
    
    /**
     * Roept de parent aan. (Hoeft niet op deze manier, de call 'super()' wordt automatisch toegevoegd).
     * 
     * @throws SQLException
     */
    public InsertDB () throws SQLException{
	super ();	
    }    
    
    /**
     * In: een Boek object.
     * De tabel boek heeft 6 velden, net als een boek object. Deze functie genereeert een prepared statement, 
     * en koppelt de velden van de database aan de variabelen van het Boek-object.
     * Vervolgens wordt de query uitgevoerd. 
     * 
     * Voegt 
     * @param table
     * @param boek
     * @throws SQLException
     */
    public void prepareQueryBoek (Boek boek) throws SQLException {
	//The query, the question marks represent the fields in the table.
	String query = 	" INSERT INTO boek " + 
			" values(?,?,?,?,?,?)";
	
	//Prepares the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	//Values from 'Boek' are added to the prepared statement.
	mPStmt.setInt(1,boek.getISBN());
	mPStmt.setString(2, boek.getTitel());
	mPStmt.setString(3, boek.getAuteur());
	mPStmt.setDouble(4, boek.getPrijs());
	mPStmt.setString(5, boek.getGenre());
	mPStmt.setInt(6, boek.getPaginas());
	
	//Execute the statement and store result in a boolean var.
	boolean result = mPStmt.execute();
	
	if (result) System.out.println("Boek toegevoegd!");
	else System.out.println("Boek niet toegevoegd!");
    }
    
}
