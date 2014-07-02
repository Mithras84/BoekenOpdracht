/**
 * 
 */
package com.rsvier.boeken.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class BoekTest {
    Boek mBoek;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUpBeforeClass() throws Exception {
	mBoek = new Boek("1234567891011", "Een Goed Boek", "R. B. Schrijver", 19.99, "Roman", 155);
    }

    @Test
    public void testBoekEqual() {
	Boek boek2 = new Boek ("1234567891011", "Een Goed Boek", "R. B. Schrijver", 19.99, "Roman", 155);
	assertEquals(boek2, mBoek);
    }

}
