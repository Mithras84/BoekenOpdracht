/**
 * 
 */
package com.rsvier.boeken.model;

/**
 * Class description
 * Class to contain all info a book should have.
 * 
 * @version		1.00 5 mei 2014
 * @author 		Pieter
 */
public class Boek {
    private int mISBN;
    private String mTitel;
    private String mAuteur;
    private double mPrijs;
    private String mGenre;
    private int mPaginas;
    
    public Boek (int ISBN, String titel, String auteur, double prijs, String genre, int paginas) {
	mISBN = ISBN;
	mTitel = titel;
	mAuteur = auteur;
	mPrijs = prijs;
	mGenre = genre;
	mPaginas = paginas;
    }

    public int getISBN() {
        return mISBN;
    }

    public String getTitel() {
        return mTitel;
    }

    public String getAuteur() {
        return mAuteur;
    }

    public double getPrijs() {
        return mPrijs;
    }

    public String getGenre() {
        return mGenre;
    }

    public int getPaginas() {
        return mPaginas;
    }

    public void setISBN(int iSBN) {
        mISBN = iSBN;
    }

    public void setTitel(String titel) {
        mTitel = titel;
    }

    public void setAuteur(String auteur) {
        mAuteur = auteur;
    }

    public void setPrijs(double prijs) {
        mPrijs = prijs;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public void setPaginas(int paginas) {
        mPaginas = paginas;
    }
    
    

}
