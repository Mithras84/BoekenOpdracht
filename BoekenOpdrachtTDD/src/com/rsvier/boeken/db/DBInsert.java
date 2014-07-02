/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Rekening;
import com.rsvier.boeken.model.Voorraad;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class DBInsert extends DBConnect {
    private PreparedStatement mPStmt;
    
    /**
     * @throws SQLException
     */
    public DBInsert() throws SQLException {
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
    public DBInsert(String host, String dbName, String userName,
	    String password) throws SQLException {
	super(host, dbName, userName, password);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param dbName
     * @throws SQLException
     */
    public DBInsert(String dbName) throws SQLException {
	super(dbName);
	// TODO Auto-generated constructor stub
    }
    /**
     * Voeg boek aan de DB toe. Velden van het boek worden via een preparedStatement toegevoegd.
     * @param boek
     * @return true|false
     * @throws SQLException
     * @throws NullPointerException
     */
    public boolean addBoekToDB (Boek boek) throws SQLException, NullPointerException {
	//The query, the question marks represent the fields in the table.
	String query = 	" INSERT INTO boek " + 
			" values(?,?,?,?,?,?)";
	
	//Prepares the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	if (boek==null) throw new NullPointerException("Geen geldig boek object!");
	
	//Values from 'Boek' are added to the prepared statement.
	mPStmt.setString(1, boek.getISBN());
	mPStmt.setString(2, boek.getTitel());
	mPStmt.setString(3, boek.getAuteur());
	mPStmt.setDouble(4, boek.getPrijs());
	mPStmt.setString(5, boek.getGenre());
	mPStmt.setInt(6, boek.getPaginas());
	
	//Execute the statement and store result in a boolean var.
	return executePreparedStatement ();
    }
    /**
     * Voeg voorraad aan de DB toe. Velden van de voorraad worden via een preparedStatement toegevoegd.
     * @param voorraad
     * @return
     * @throws SQLException
     */
    public boolean addVoorraadToDB (Voorraad voorraad) throws SQLException {
	//The query, the question marks represent the fields in the table.
	String query = 	" INSERT INTO voorraad " + 
			" values(?,?,?,?)";
	
	//Prepares the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	//Values from 'Boek' are added to the prepared statement.
	mPStmt.setString(1, voorraad.getISBN());
	mPStmt.setInt(2, voorraad.getVoorraadAmsterdam());
	mPStmt.setInt(3, voorraad.getVoorraadGroningen());
	mPStmt.setInt(4, voorraad.getVoorraadUtrecht());
	
	//Execute the statement and store result in a boolean var.
	return executePreparedStatement ();
    }
    /**
     * Voeg rekening aan de DB toe. Velden van de rekening worden via een preparedStatement toegevoegd.
     * @param rekening
     * @return
     * @throws SQLException
     */
    public boolean addRekeningToDB (Rekening rekening) throws SQLException {
	//The query, the question marks represent the fields in the table.
	String query = 	" INSERT INTO rekeningen " + 
			" values(?,?,?,?)";
	
	//Prepares the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	//Values from 'Boek' are added to the prepared statement.
	mPStmt.setString(1, rekening.getRekeningNummer() );
	mPStmt.setString(2, rekening.getRekeningHouder() );
	mPStmt.setString(3, rekening.getLocatieString() );
	mPStmt.setDouble(4, rekening.getSaldo() );
	
	//Execute the statement and store result in a boolean var.
	return executePreparedStatement ();
    }
    
    /**
     * Voer de prepared statement uit.
     * @return
     * @throws SQLException
     */
    private boolean executePreparedStatement () throws SQLException {
	mPStmt.execute();
	return true;
    }

}
