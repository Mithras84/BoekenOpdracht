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
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
public class DBUpdate extends DBConnect {
    PreparedStatement mPStmt;

    /**
     * @param host
     * @param dbName
     * @param userName
     * @param password
     * @throws SQLException
     */
    public DBUpdate(String host, String dbName, String userName, String password)
	    throws SQLException {
	super(host, dbName, userName, password);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param dbName
     * @throws SQLException
     */
    public DBUpdate(String dbName) throws SQLException {
	super(dbName);
	// TODO Auto-generated constructor stub
    }

    /**
     * @throws SQLException
     */
    public DBUpdate() throws SQLException {
	// TODO Auto-generated constructor stub
    }
    
    public boolean updateBoekInDB (Boek boek) throws SQLException, NullPointerException {
	//The query, the questionmarks represent the fields in the table.
	String query = 	" UPDATE boek " + 
			" set titel = ?, auteur = ?, prijs = ?, genre = ?, paginas = ?" +
			" WHERE isbn = ?";
	
	//Prepare the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	if (boek==null) throw new NullPointerException("Geen geldig boek object!");
	
	//Values from 'Boek' are added to the prepared statement.
	//mPStmt.setString(1, boek.getISBN());
	mPStmt.setString(1, boek.getTitel());	
	mPStmt.setString(2, boek.getAuteur());
	mPStmt.setDouble(3, boek.getPrijs());
	mPStmt.setString(4, boek.getGenre());
	mPStmt.setInt(5, boek.getPaginas());
	mPStmt.setString(6, boek.getISBN() );
	
	//Execute the statement and store result in a boolean var.
	return executePreparedStatement ();
    }
    
    public boolean updateVoorraadInDB (Voorraad voorraad) throws SQLException, NullPointerException {
	//The query, the question marks represent the fields in the table.
	String query = 	" UPDATE voorraad " + 
			" set amsterdam = ?, groningen = ?, utrecht = ?" +
			" WHERE isbn = ?";
	
	//Prepare the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	if (voorraad==null) throw new NullPointerException("Geen geldig boek object!");
	
	//Values from 'Boek' are added to the prepared statement.	
	mPStmt.setInt(1, voorraad.getVoorraadAmsterdam());
	mPStmt.setInt(2, voorraad.getVoorraadGroningen());
	mPStmt.setInt(3, voorraad.getVoorraadUtrecht());
	mPStmt.setString(4, voorraad.getISBN());
	
	//Execute the statement and store result in a boolean var.
	return executePreparedStatement ();
    }
    
    public boolean updateRekeningInDB (Rekening rekening) throws SQLException, NullPointerException {
	
	if (rekening==null) throw new NullPointerException("Geen geldig boek object!");
	
	//The query, the question marks represent the fields in the table.
	String query = 	" UPDATE rekeningen " + 
			" set naam = ?, plaats = ?, saldo = ?" +
			" WHERE rekeningnummer = ?";
	
	//Prepares the query.
	mPStmt = mDBConnection.prepareStatement(query);
	
	//Values from 'Boek' are added to the prepared statement.
	mPStmt.setString(1, rekening.getRekeningHouder() );
	mPStmt.setString(2, rekening.getLocatieString() );
	mPStmt.setDouble(3, rekening.getSaldo() );
	mPStmt.setString(4, rekening.getRekeningNummer());
	//Execute the statement and store result in a boolean var.
	return executePreparedStatement ();
    }

    /**
     * Private functie om query om te zetten naar statement en uit te voeren.
     * Als query geen error geeft, zet het resultaat in mResult.
     * @param query
     * @return true | SQLException.
     * @throws SQLException
     */
/*  private boolean executeStatement (String query) throws SQLException {
	if( checkConnection() ) {
	    mStmt = mDBConnection.createStatement();
	    if ( mStmt.execute(query) ) {
		mResult = mStmt.getResultSet();
		return true;
	    
	    } else throw new SQLException("GEEN BOEKEN GEVONDEN");
	    
	} else throw new SQLException("GEEN ACTIEVE VERBINDING GEVONDEN");
    }
*/
    private boolean executePreparedStatement () throws SQLException {
	mPStmt.execute();
	int res = mPStmt.getUpdateCount();
	if (res==1)
	    return true;
	else return false;
    }

}
