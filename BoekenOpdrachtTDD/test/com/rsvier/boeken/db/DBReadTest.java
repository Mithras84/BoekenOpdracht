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
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class DBReadTest {
    DBRead mDBR;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	mDBR = new DBRead ();
    }

    @Test
    public void testDBReadFromBoek() {
	Boek boek1 = new Boek ("1234567891011", "Een Goed Boek", "R. B. Schrijver", 19.99, "Roman", 155);
	try {
	    Boek boek2 = mDBR.getBoekFromDB( "1234567891011" );
	    assertEquals( boek1 , boek2 );
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test
    public void testDBReadFromVoorraad () {
	Voorraad voorraad1 = new Voorraad( "1234567891011" , 10 , 23 , 0 );
	try {
	    Voorraad voorraad2 = mDBR.getVoorraadFromDB( "1234567891011" );
	    assertEquals( voorraad1 , voorraad2 );
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test
    public void testDBReadFromRekening () {
	Rekening rekening1 = new Rekening("NL73INGB0001234567",
		"Boekenwinkel Groningen BV", Locatie.GRONINGEN, 1234.56);
	try {
	    Rekening rekening2 = mDBR.getRekeningFromDB ( "NL73INGB0001234567" );
	    assertEquals( rekening1 , rekening2 );
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

}
