/**
 * 
 */
package com.rsvier.boeken.db;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Class description
 * Attempt to do a CRUD test on the DB operations:
 * Create (tables, fields)
 * Read (from fields)
 * Update (update fields)
 * Delete (delete created fields)
 * 
 * @version		1.00 31 mei 2014
 * @author 		Pieter
 */
@RunWith(Suite.class)
@SuiteClasses({ DBConnectTest.class, DBCreateTablesTest.class,
	DBInsertTest.class, DBReadTest.class,
	DBUpdateTest.class, DBDeleteTest.class })
public class AllDBTests {    
    
}
