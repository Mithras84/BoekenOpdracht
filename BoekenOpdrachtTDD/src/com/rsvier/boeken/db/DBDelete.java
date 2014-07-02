/**
 * 
 */
package com.rsvier.boeken.db;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class description
 * 
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
public class DBDelete extends DBConnect {
    Statement mStmt;

    /**
     * @param host
     * @param dbName
     * @param userName
     * @param password
     * @throws SQLException
     */
    public DBDelete(String host, String dbName, String userName, String password)
	    throws SQLException {
	super(host, dbName, userName, password);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param dbName
     * @throws SQLException
     */
    public DBDelete(String dbName) throws SQLException {
	super(dbName);
	// TODO Auto-generated constructor stub
    }

    /**
     * @throws SQLException
     */
    public DBDelete() throws SQLException {
	// TODO Auto-generated constructor stub
    }
    
    public boolean deleteBoekFromDB (String isbn) throws SQLException {
	String query =  "DELETE FROM boek " +
			"WHERE isbn = '" + isbn + "'";
	return executeStatement (query);	
    }
    
    public boolean deleteVoorraadFromDB (String isbn) throws SQLException {
	String query =  "DELETE FROM voorraad " +
			"WHERE isbn = '" + isbn + "'";
	
	return executeStatement (query);	
    }
    
    public boolean deleteRekeningFromDB (String rekeningNr) throws SQLException {
	String query =  "DELETE FROM rekeningen " +
			"WHERE rekeningnummer = '" + rekeningNr + "'";
	
	return executeStatement (query);	
    }
    
    private boolean executeStatement (String sql) throws SQLException {
	mStmt = mDBConnection.createStatement();
	mStmt.execute(sql);
	return true;
    }

}
