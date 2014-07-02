/**
 * 
 */
package com.rsvier.boeken.db;

import static org.junit.Assert.*;

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
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
public class DBUpdateTest {
    DBUpdate mDBU;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	mDBU = new DBUpdate();
    }

    /**
     * Test method for {@link com.rsvier.boeken.db.DBUpdate#updateBoekInDB(com.rsvier.boeken.model.Boek)}.
     */
    @Test
    public void testUpdateBoekInDB() {
	Boek boek1 = new Boek ("1234567891011", "Een Beter Boek", "R. B. Schrijver", 19.99, "Roman", 155);
	try {
	    assertTrue("True if no error", mDBU.updateBoekInDB(boek1) );
	} catch (NullPointerException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

    /**
     * Test method for {@link com.rsvier.boeken.db.DBUpdate#updateVoorraadInDB(com.rsvier.boeken.model.Voorraad)}.
     */
    @Test
    public void testUpdateVoorraadInDB() {
	Voorraad voorraad1 = new Voorraad("1234567891011", 10, 23, 15);
	try {
	    assertTrue("True if no error", mDBU.updateVoorraadInDB(voorraad1) );
	} catch (NullPointerException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

    /**
     * Test method for {@link com.rsvier.boeken.db.DBUpdate#updateRekeningInDB(com.rsvier.boeken.model.Rekening)}.
     */
    @Test
    public void testUpdateRekeningInDB() {
	Rekening rekening1 = new Rekening("NL73INGB0001234567",
		"Boekenwinkel Groningen BV", Locatie.GRONINGEN, 1534.56);
	try {
	    assertTrue("True if no error", mDBU.updateRekeningInDB(rekening1) );
	} catch (NullPointerException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

}
