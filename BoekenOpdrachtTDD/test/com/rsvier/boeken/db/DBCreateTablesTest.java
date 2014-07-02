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
 * Test om basis operaties te evalueren van de class CBCreate.
 * Moet samenwerken met andere test-classes om een zogenaamde CRUD-test uit te voeren
 * (Create, Read, Update, Delete)
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class DBCreateTablesTest {
    DBCreateTables mDBCreate;
    
    @Before 
    public void setUpDBCreateTables () {
	//Set instance var mDBCreate to hold a ref to DBCreateTables (Calling to no arg constructor)
	try {
	    mDBCreate = new DBCreateTables();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Test
    public void test() {
	assertNotNull("Not null if object has been created", mDBCreate);	
    }
    
    @Test
    public void createTableRekeningenTest () {	
	try {
	    assertTrue ("True if table has been created", mDBCreate.createTableRekeningen() );
	} catch (SQLException e) {
	    fail();
	    e.printStackTrace();
	}
    }
    
    @Test 
    public void createTableBoekTest () {	
	try {
	    assertTrue ("True if table has been created", mDBCreate.createTableBoek() );
	} catch (SQLException e) {
	    fail();
	    e.printStackTrace();
	}
    }
    
    @Test
    public void createTableVoorraadTest () {	
	try {
	    assertTrue ("True if table has been created", mDBCreate.createTableVoorraad() );
	} catch (SQLException e) {
	    fail();
	    e.printStackTrace();
	}
    }

}
