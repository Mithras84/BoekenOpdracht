/**
 * 
 */
package com.rsvier.createdb;

import java.sql.SQLException;

/**
 * Class description
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class Start {
    static CreateDB mCdb;

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	mCdb = new CreateDB();
	try {
	    //Open connection.
	    mCdb.initConnection();
	    
	    //Create all tables:
	    createTables();
	    
	    //Close connection.
	    if (mCdb.closeConnection() )
		System.out.println("Connection closed!");
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 
    }
    
    protected static void createTables () throws SQLException {
	//Create table Voorraad:
	if (mCdb.createTableVoorraad() )
	    System.out.println("Table 'Voorraad' Created");
	if (mCdb.createTableBoek() )
	    System.out.println("Table 'Boek' Created");
	if (mCdb.createTableRekeningen() ) 
	    System.out.println("Table 'Rekeningen' Created");
    }

}
