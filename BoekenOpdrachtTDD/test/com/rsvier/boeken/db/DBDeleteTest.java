/**
 * 
 */
package com.rsvier.boeken.db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

/**
 * Class description
 * 
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
public class DBDeleteTest {
    DBDelete mDBD;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	mDBD = new DBDelete ();
    }

    /**
     * Test method for {@link com.rsvier.boeken.db.DBDelete#deleteBoekFromDB(java.lang.String)}.
     */
    @Test
    public void testDeleteBoekFromDB() {
	try {
	    assertTrue( mDBD.deleteBoekFromDB("1234567891011") );
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail("SQL Exception");
	}
    }

    /**
     * Test method for {@link com.rsvier.boeken.db.DBDelete#deleteVoorraadFromDB(java.lang.String)}.
     */
    @Test
    public void testDeleteVoorraadFromDB() {
	try {
	    assertTrue( mDBD.deleteVoorraadFromDB("1234567891011") );
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail("SQL Exception");
	}
    }

    /**
     * Test method for {@link com.rsvier.boeken.db.DBDelete#deleteRekeningFromDB(java.lang.String)}.
     */
    @Test
    public void testDeleteRekeningFromDB() {
	try {
	    assertTrue( mDBD.deleteRekeningFromDB("NL73INGB0001234567") );
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail("SQL Exception");
	}
    }

}
