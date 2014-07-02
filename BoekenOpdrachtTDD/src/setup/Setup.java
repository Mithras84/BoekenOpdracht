/**
 * 
 */
package setup;

import java.sql.SQLException;
import java.util.ArrayList;

import com.rsvier.boeken.controller.Admin;
import com.rsvier.boeken.controller.Kassa;
import com.rsvier.boeken.db.*;
import com.rsvier.boeken.gui.AdminGui;
import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Rekening;
import com.rsvier.boeken.model.Voorraad;
/**
 * Class description
 * 
 * @version		1.00 1 jun. 2014
 * @author 		Pieter
 */
public class Setup {
    /**
     * Test:
     * Eerst addTables uitvoeren.
     * Dan addBoekenToBoek (maar 1 keer!!)
     * vervolgens addRekeningenToRekeningen (ook maar 1 keer!)
     * gebruik testKassa om te testen of orders goed verwerkt worden (out is de bon)
     * @param args
     */
    public static void main(String[] args) {
	//addTables();
	//addBoekenToBoek();
	//addRekeningenToRekeningen();
	//testKassa();
	//testAlleBoeken ();
	
    }
    
    static void testKassa () {
	Kassa kassa = new Kassa("Groningen");
	
	//Standaard bestelling
	kassa.addBoekToBestelling("1234567891011");
	System.out.println (kassa.rekenBestellingAf() );
	
	//Nog een bestelling, van een boek die niet op voorraad is:
	kassa.addBoekToBestelling("1234567891015");
	System.out.println (kassa.rekenBestellingAf() );
	
	kassa.addBoekToBestelling("1234567891011");
	kassa.addBoekToBestelling("1234567891012");
	kassa.addBoekToBestelling("1234567891013");
	
	System.out.println (kassa.rekenBestellingAf() );	
    }
    
    static void addTables () {
	try {
	    DBCreateTables dbc = new DBCreateTables ();
	    dbc.createTableBoek();
	    dbc.createTableRekeningen();
	    dbc.createTableVoorraad();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}	
    }
    
    static void addRekeningenToRekeningen () {
	Admin admin = new Admin ();
	Rekening rek1 = new Rekening("NL74INGB0001234", "Boekenwinkel Amsterdam", "Amsterdam" , 1000.00);
	Rekening rek2 = new Rekening("NL74INGB0005678", "Boekenwinkel Groningen", "Groningen" , 2000.00);
	Rekening rek3 = new Rekening("NL74INGB0009101", "Boekenwinkel Utrecht", "Utrecht" , 3000.00);
	
	admin.addRekeningToLijst(rek1);
	admin.addRekeningToLijst(rek2);
	admin.addRekeningToLijst(rek3);
	
	admin.addRekeningenLijstToDB();
    }
    
    static void addBoekenToBoek () {
	Admin admin = new Admin ();
	Boek boek1 = new Boek(
		"1234567891011" , "Titel1" , "Auteur1" ,
		19.99, "Roman", 150,
		new Voorraad("1234567891011", 10, 10, 10)
		);
	Boek boek2 = new Boek(
		"1234567891012" , "Titel2" , "Auteur2" ,
		29.99, "Roman", 250,
		new Voorraad("1234567891012", 20, 20, 20)
		);
	Boek boek3 = new Boek(
		"1234567891013" , "Titel3" , "Auteur3" ,
		39.99, "Roman", 350,
		new Voorraad("1234567891013", 30, 30, 30)
		);
	Boek boek4 = new Boek(
		"1234567891014" , "Titel4" , "Auteur4" ,
		49.99, "Roman", 450,
		new Voorraad("1234567891014", 0, 10, 0)
		);
	Boek boek5 = new Boek(
		"1234567891015" , "Titel5" , "Auteur5" ,
		59.99, "Roman", 550,
		new Voorraad("1234567891015", 0, 0, 10)
		);
	Boek boek6 = new Boek(
		"1234567891016" , "Titel6" , "Auteur6" ,
		69.99, "Roman", 650,
		new Voorraad("1234567891016", 0, 0, 0)
		);
	admin.addBoekToLijst(boek1);
	admin.addBoekToLijst(boek2);
	admin.addBoekToLijst(boek3);
	admin.addBoekToLijst(boek4);
	admin.addBoekToLijst(boek5);
	admin.addBoekToLijst(boek6);
	
	
	admin.addBoekenLijstToDB();	
    }
    
    public static void testAlleBoeken () {
	ArrayList<Boek> boeken = new ArrayList<Boek>();
	Kassa kassa = new Kassa ("Groningen");
	boeken = kassa.getAlleBoekenLijstOpLocatie();    
	for (Boek boek : boeken) {
	    System.out.println(boek.getAuteur());
	}
    }

}
