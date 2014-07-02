/**
 * 
 */
package com.rsvier.boeken.model;

/**
 * Class description
 * 
 * @version		1.00 30 mei 2014
 * @author 		Pieter
 */
public enum Locatie {
    GRONINGEN ("NL74INGB0001234"),
    UTRECHT ("NL74INGB0005678"),
    AMSTERDAM ("NL74INGB0009101");
    
    private final String REKENING_NUMMER;
    
    Locatie(String rekeningNr) {
	REKENING_NUMMER = rekeningNr;
    }

    /**
     * @return the rEKENING_NUMMER
     */
    public String getREKENING_NUMMER() {
	return REKENING_NUMMER;
    }
}
