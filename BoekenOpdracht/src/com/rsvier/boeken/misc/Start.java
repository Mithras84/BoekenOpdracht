/**
 * 
 */
package com.rsvier.boeken.misc;

import java.sql.SQLException;

import com.rsvier.boeken.db.CreateTables;
import com.rsvier.boeken.model.AddBoeken;
import com.rsvier.boeken.model.Boek;

/**
 * Class description 
 * Een dev klasse, bedoelt om de Database te vullen met Tabellen en Data.
 * Ook om Test-code te kunnen testen.
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
	    //Create all tables. Remove comments for exec.
	    //createTables();
	    //Add books to table. Remove comments for exec.
	    addBooksToTable();
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 
    }
    
    /**
     * Stuurt de CreateTables klasse aan.
     * @throws SQLException
     */
    protected static void createTables () throws SQLException {
	CreateTables ct = new CreateTables();
	//Create table Voorraad:
	if (ct.createTableVoorraad() )
	    System.out.println("Table 'Voorraad' Created");
	//Create table Boek
	if (ct.createTableBoek() )
	    System.out.println("Table 'Boek' Created");
	//Create table Rekeningen
	if (ct.createTableRekeningen() ) 
	    System.out.println("Table 'Rekeningen' Created");
	
	ct.closeConnection();
    }
    /**
     * Voegt boeken toe aan de DB. Stuurt de AddBoek klasse aan.
     * Ook te gebruiken om de Admin Controller te testen.
     * @throws SQLException
     */
    protected static void addBooksToTable () throws SQLException {
	AddBoeken bl = new AddBoeken();
	
	bl.voegToeAanLijst(new Boek( 90414156 , "Boek1", "Auteur1", 14.99, "Thriller", 285));
	bl.voegToeAanLijst(new Boek( 57456892 , "Boek2", "Auteur1", 17.99, "Roman", 385));
	bl.voegToeAanLijst(new Boek( 54558625 , "Boek3", "Auteur2", 11.99, "Thriller", 178));
	bl.voegToeAanLijst(new Boek( 87566214 , "Boek4", "Auteur3", 12.99, "Sci-Fi", 548));
	
	bl.lijstNaarDB();	
    }

}
