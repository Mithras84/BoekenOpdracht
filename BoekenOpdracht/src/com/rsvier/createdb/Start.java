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

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	try {
	    //Create all tables:
	    createTables();
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 
    }
    
    protected static void createTables () throws SQLException {
	CreateTables ct = new CreateTables();
	//Create table Voorraad:
	if (ct.createTableVoorraad() )
	    System.out.println("Table 'Voorraad' Created");
	if (ct.createTableBoek() )
	    System.out.println("Table 'Boek' Created");
	if (ct.createTableRekeningen() ) 
	    System.out.println("Table 'Rekeningen' Created");
    }

}
