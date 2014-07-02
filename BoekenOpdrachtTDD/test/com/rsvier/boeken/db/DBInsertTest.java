/**
 * 
 */
package com.rsvier.boeken.db;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.model.Locatie;
import com.rsvier.boeken.model.Rekening;
import com.rsvier.boeken.model.Voorraad;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class DBInsertTest {
    DBInsert mDBI;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() {
	try {
	    mDBI = new DBInsert();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public void testAddBoekToDB() {
	Boek boek1 = new Boek ("1234567891011", "Een Goed Boek", "R. B. Schrijver", 19.99, "Roman", 155);
	try {
	    assertTrue("True if no error", mDBI.addBoekToDB(boek1) );
	} catch (NullPointerException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test
    public void testAddVoorraadToDB() {
	Voorraad voorraad1 = new Voorraad("1234567891011", 10, 23, 0);
	try {
	    assertTrue("True if no error", mDBI.addVoorraadToDB(voorraad1) );
	} catch (NullPointerException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test
    public void testAddRekeningToDB() {
	Rekening rekening1 = new Rekening("NL73INGB0001234567",
		"Boekenwinkel Groningen BV", Locatie.GRONINGEN, 1234.56);
	try {
	    assertTrue("True if no error", mDBI.addRekeningToDB(rekening1) );
	} catch (NullPointerException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test(expected = SQLException.class)
    public void testAddBoekToDBSQLError() throws SQLException {
	mDBI = new DBInsert("niet_bestaand");
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddBoekToDBNullError() throws NullPointerException, SQLException {
	Boek boek = null;
	mDBI.addBoekToDB(boek);
    }
    
    
    
    

}
