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
public class RekeningTest {
    Rekening mRekening;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	mRekening = new Rekening("NL73 INGB 0001234567", 
		"Boekenwinkel Groningen BV", Locatie.GRONINGEN, 1234.56);
    }

    @Test
    public void testRekening() {
	Rekening rekening2 = new Rekening("NL73 INGB 0001234567",
		"Boekenwinkel Groningen BV", Locatie.GRONINGEN, 1234.56);
	assertEquals(rekening2, mRekening);
    }

}
