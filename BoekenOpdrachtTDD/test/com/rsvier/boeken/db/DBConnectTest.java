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
 * @version		1.00 29 mei 2014
 * @author 		Pieter
 */
public class DBConnectTest {
    public DBConnect mDBC;

    @Before 
    public void DBConnectInitTest () {
	try {
	    mDBC = new DBConnect("mysql://127.0.0.1:3306/" , "winkel" , "root" , "");
	} catch (SQLException e) {	    
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test
    public void testDBConnectCreation() {
	assertNotNull("A DBConnect Object must have been created", mDBC);
    }
    
    @Test
    public void testDBConnectConnectToDB() {
	try {
	    assertTrue("Returns true when connection is valid", mDBC.checkConnection() );
	} catch (SQLException e) {
	    System.out.println(e);
	    fail();
	}
    }
    
    @Test(expected = SQLException.class)
    public void testDBConnectConnectToDBError() throws SQLException{
	//Create error, logging in with wrong info.
	mDBC = new DBConnect("mysql://111.00.220.1:5555" , "test" , "wronguser" , "wrongpass");
    }
    
    @Test
    public void testCloseDB () {
	try {
	    assertTrue("True if connection is closed",mDBC.closeConnection());
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    fail();
	}
    }

}
