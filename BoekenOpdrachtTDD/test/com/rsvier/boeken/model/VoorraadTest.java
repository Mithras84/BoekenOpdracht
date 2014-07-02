/**
 * 
 */
package com.rsvier.boeken.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public class VoorraadTest {
    Voorraad mVoorraad;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	mVoorraad = new Voorraad("1234567891011", 10, 23, 0);
    }

    @Test
    public void test() {
	Voorraad voorraad1 = new Voorraad("1234567891011", 10, 23, 0);
	assertEquals(voorraad1, mVoorraad);
    }

}
