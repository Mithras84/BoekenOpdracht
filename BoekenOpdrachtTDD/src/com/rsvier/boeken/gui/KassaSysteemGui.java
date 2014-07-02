package com.rsvier.boeken.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.rsvier.boeken.db.DBRead;
import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.controller.Kassa;


/**
 * Visuele klasse van het kassasysteem waarin men boeken kan zoeken in de voorraad en toe kan voegen aan een bestelling/reservering
 * @author Uppe
 *
 */

public class KassaSysteemGui extends JFrame {
	
	private static final int FRAME_BREEDTE = 950;
	private static final int FRAME_HOOGTE = 350;
	private static final int SCHERM_BREEDTE = 1920;
	private static final int SCHERM_HOOGTE = 1080;
	private static DecimalFormat df = new DecimalFormat("#0.00");
	
	private JFileChooser fileKiezer = null;
	private ArrayList<Boek> boekenlijst = null;
	private Kassa kassa;
	private DefaultListModel voorraadModel = new DefaultListModel();
	private JList voorraadLijst = null;
	private DefaultListModel selectieModel = new DefaultListModel();
	private JTextField titelVeld = new JTextField("");
	private JTextField auteurVeld = new JTextField("");
	private JTextField isbnVeld = new JTextField("");
	private JLabel voorraadLabel = new JLabel("Boeken op voorraad");
	private JLabel locatieLabel = new JLabel("Locatie:");
	private JLabel locatieLabel2 = new JLabel("Geen selectie");
	private JLabel totaalPrijsLabel = new JLabel();
	private Container pane = getContentPane();
	
	/**
	 * Constructor van de grafische interface.
	 */
	public KassaSysteemGui(){
		super();
		initialize();		
	}
	
	/**
	 * Verzorgt de layout van het venster en koppelt listeners aan de objecten waar nodig.
	 */
	public void initialize(){
		// afmetingen frame
		this.setTitle("Display kassa");
		this.setSize(FRAME_BREEDTE, FRAME_HOOGTE);
		this.setLocation((SCHERM_BREEDTE - FRAME_BREEDTE) / 2,(SCHERM_HOOGTE - FRAME_HOOGTE) / 2);
		pane.setLayout(null);
		
		// menubalk, menu's + listeners
		JMenuBar menubalk = new JMenuBar();
		setJMenuBar(menubalk);
		  JMenu bestand = new JMenu("Bestand");
		  menubalk.add(bestand);
		    JMenuItem openenLijst = new JMenuItem("Voorraad openen");
		    JMenuItem opslaanLijst = new JMenuItem("Bestelling opslaan");
		    bestand.add(openenLijst);
		      //opent en toont de voorraad in de voorraadLijst.
		      openenLijst.addActionListener(new OpenListener());
		    bestand.add(opslaanLijst);
		      //slaat de bestelling in de bestellingLijst op.
		      //opslaanLijst.AddActionListener(new OpslaanListener());
		 JMenu bewerken = new JMenu("Bewerken");
                 menubalk.add(bewerken);
                   JMenuItem leegmaken = new JMenuItem("Bestelling verwijderen");
                   bewerken.add(leegmaken);
                     //maakt bestellingLijst leeg
                     leegmaken.addActionListener(new LeegmaakListener());
                   bewerken.addSeparator();
                   JMenu locatie = new JMenu("Verander locatie");
                   bewerken.add(locatie);
                     JRadioButtonMenuItem locatie1 = new JRadioButtonMenuItem("Amsterdam");
                     locatie1.addItemListener(new AmsterdamListener());                     
                     JRadioButtonMenuItem locatie2 = new JRadioButtonMenuItem("Groningen");
                     locatie2.addItemListener(new GroningenListener());
                     JRadioButtonMenuItem locatie3 = new JRadioButtonMenuItem("Utrecht");
                     locatie3.addItemListener(new UtrechtListener());
                     locatie.add(locatie1);
                     locatie.add(locatie2);
                     locatie.add(locatie3);
                     ButtonGroup groep = new ButtonGroup();
                     groep.add(locatie1);
                     groep.add(locatie2);
                     groep.add(locatie3);
		
		// Aanmaken lijsten + schuifbalken
		voorraadLijst = new JList(voorraadModel);
		JScrollPane schuifbalk1 = new JScrollPane(voorraadLijst);
		schuifbalk1.setBounds(40, 110, 300, 150);
		pane.add(schuifbalk1);
		
		JList SelectieLijst = new JList(selectieModel);
		JScrollPane schuifbalk2 = new JScrollPane(SelectieLijst);
		schuifbalk2.setBounds(480, 110, 300, 150);
		pane.add(schuifbalk2);
		
		
		// JTextFields (titel, auteur, isbn)
		titelVeld.setBounds(40, 30, 100, 20);
		pane.add(titelVeld);
		auteurVeld.setBounds(160, 30, 100, 20);
		pane.add(auteurVeld);
		isbnVeld.setBounds(280, 30, 100, 20);
		pane.add(isbnVeld);
				
		//JLabels (titel, auteur, isbn, totaalprijs)
		JLabel titelLabel = new JLabel("Titel:");
		titelLabel.setBounds(40, 10, 100, 20);
		pane.add(titelLabel);
		
		JLabel auteurLabel = new JLabel("Auteur:");
		auteurLabel.setBounds(160, 10, 100, 20);
		pane.add(auteurLabel);
		
		JLabel isbnLabel = new JLabel("Isbn:");
		isbnLabel.setBounds(280, 10, 100, 20);
		pane.add(isbnLabel);

		totaalPrijsLabel.setBounds(800, 170, 100, 60);
		pane.add(totaalPrijsLabel);
		
		voorraadLabel.setBounds(40, 70, 120, 60);
		pane.add(voorraadLabel);
		
		JLabel bestellingLabel = new JLabel("Bestelling: ");
		bestellingLabel.setBounds(480, 70, 120, 60);
		pane.add(bestellingLabel);
		
		locatieLabel.setBounds(700, 10, 200, 20);
		pane.add(locatieLabel);
		locatieLabel2.setBounds(700, 30, 200, 20);
		pane.add(locatieLabel2);
		
		//Aanmaken knoppen en koppeling met Listeners
		JButton printknop = new JButton("Print bon");
		printknop.setBounds(800, 220, 100, 40);
		pane.add(printknop);
		printknop.addActionListener(new PrintKnopListener());
		
		JButton voegToeKnop = new JButton("Voeg toe");
		voegToeKnop.setBounds(360, 165, 100, 40);
		pane.add(voegToeKnop);
		voegToeKnop.addActionListener(new ToonBoekListener());
		
		              // Voegtoeknop2 (kan later weg)
		              JButton voegToeKnop2 = new JButton("Voeg toe2");
		              voegToeKnop2.setBounds(400, 10, 100, 40);
		              pane.add(voegToeKnop2);
		              voegToeKnop2.addActionListener(new ToonBoekListener2());
		
		JButton zoekKnop = new JButton("Zoeken");
		zoekKnop.setBounds(550, 10, 100, 40);
		pane.add(zoekKnop);
		zoekKnop.addActionListener(new ZoekKnopListener());		              
		
		//Standaardlocatie met boekenlijsten-files
		fileKiezer = new JFileChooser("./Documenten");
	}
	
	public static void main(String[] args){
		KassaSysteemGui frame = new KassaSysteemGui();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * (Luisteraar) Selecteert de locatie Amsterdam
	 * Pie: Kassa object aanmaken.
	 */
	public class AmsterdamListener implements ItemListener {
	  public void itemStateChanged(ItemEvent e){
	    if(e.getStateChange() == ItemEvent.SELECTED){
	    locatieLabel2.setText("Amsterdam");
	    kassa = new Kassa ("Amsterdam");
	    }
	  }
	}
	
	/**
         * (Luisteraar) Selecteert de locatie Groningen
         * Pie: Kassa object aanmaken.
         */
        public class GroningenListener implements ItemListener {
          public void itemStateChanged(ItemEvent e){
            if(e.getStateChange() == ItemEvent.SELECTED){
              locatieLabel2.setText("Groningen");
              kassa = new Kassa ("Groningen");
            }
          }
        }
        
        /**
         * (Luisteraar) Selecteert de locatie Utrecht
         * Pie: Kassa object aanmaken.
         */
        public class UtrechtListener implements ItemListener {
          public void itemStateChanged(ItemEvent e){
            if(e.getStateChange() == ItemEvent.SELECTED){
              locatieLabel2.setText("Utrecht");
              kassa = new Kassa ("Utrecht");
            }
          }
        }
	
	/**
	 * (Luisteraar) Selecteert het boek in de voorraadLijst wanneer de titel, auteur of ISBN matcht met de woorden van de zoekvensters.
	 * Nog te verwerken: Wat als er maar 1 veld is ingevuld?
	 */
	public class ZoekKnopListener implements ActionListener {
	  public void actionPerformed(ActionEvent e){
	    if(boekenlijst == null){
	      JOptionPane.showMessageDialog(pane, "Boekenlijst niet gevonden", "Fout bij het openen", JOptionPane.ERROR_MESSAGE);
	    }
	    else{
	      for (Boek b: boekenlijst){
	        if(titelVeld.getText().equals(b.getTitel()) || auteurVeld.getText().equals(b.getAuteur()) || isbnVeld.getText().equals(b.getISBN())){
	        voorraadLijst.setSelectedValue(b, true);
	        }
	        else{
	        voorraadLijst.setSelectedIndex(-1);
	        JOptionPane.showMessageDialog(pane, "Geen match met de ingevoerde waarden", "Fout bij het zoeken", JOptionPane.ERROR_MESSAGE); 
	        }        
	      }
	    }
	  }
	}
	
	/**
	 * (Luisteraar) Kopiëert de geselecteerde voorraaditems naar de bestellinglijst
	 * 
	 * Pie: Deze fucntie kan je denk ik beter met behulp van de Kassa Controller doen. 
	 * De Kassa Controller heeft een bestellijst. Voeg het geselecteerde boek aan deze bestellijst toe,
	 * en toon deze bestellijst vervolgens in het bestellingsselectie-venster.
	 * Wijziging: Het boek haalt eerst het ISBN nummer uit het geselecteerde object.
	 * Vervoglens op basis van ISBN het boek aan de bestelling toevoegen.
	 * 
	 */
	public class ToonBoekListener implements ActionListener {
	  public void actionPerformed(ActionEvent e){
	      Object selectie = voorraadLijst.getSelectedValue();
	      String isbn = selectie.toString().substring(0, selectie.toString().indexOf(":"));
	      System.out.println(isbn);
	      kassa.addBoekToBestelling(isbn);
	      toonBestelLijst();
	      /*
	      Boek selectieBoek = (Boek)selectie;
	      selectieModel.add(0, selectieBoek);
	      totaalPrijs = totaalPrijs + selectieBoek.getPrijs();
	      totaalPrijsLabel.setText("Totaalprijs: €" + df.format(totaalPrijs));
	      */
	      }
	             
	    }
	        // Optionele listener voor VoegToeknop2 (kan later weg)
	        public class ToonBoekListener2 implements ActionListener {
	            public void actionPerformed(ActionEvent e){
	              String informatie = "" + titelVeld.getText() + " , " + auteurVeld.getText() + " (" + isbnVeld.getText() + ")";
	              voorraadModel.add(0, informatie);             
	            }
	        }
	
	/**
	 * (Luisteraar) Rondt de bestelling af en print de bon.
	 * Pie: Misschien een optie alle locaties toevoegen? 
	 */
	public class PrintKnopListener implements ActionListener {
	  public void actionPerformed(ActionEvent e){
	    String bon = kassa.rekenBestellingAf();
	    System.out.print(bon);
	  }
	}
	        
	/**
	 * (Luisteraar) Verwijdert de bestelling uit de bestellingLijst.
	 */
	public class LeegmaakListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
	    selectieModel.clear();
	  }
	}
	
	/**
	 * (Luisteraar) Opent de boekenvoorraad van een locatie en toont de informatie per boek in de voorraadLijst.
	 */
	public class OpenListener implements ActionListener {
	  public void actionPerformed(ActionEvent e){
	    voorraadModel.clear();
	    openBoekenVoorraad();
	    toonVoorraadLijst();
	  }
	}
	
	/**
	 * Hulpmethode voor het openen van een boekenlijst (onder constructie).
	 */
	private void openBoekenVoorraad(){
	  String databaseLocatie = locatieLabel2.getText();
	  if(databaseLocatie.equals("Geen selectie")){
	    JOptionPane.showMessageDialog(pane, "Geen locatie gekozen", "Fout bij het openen", JOptionPane.ERROR_MESSAGE);
	  }
	  else{
	        //methode in DBRead aanmaken voor inlezen alle boeken (dus zonder invoer van isbn)
	        //pie: Ik heb een functie in de Kassa controller gemaakt. Eigenlijk mag de view niet direct
	        //in de DB rondneuzen in een MCV design.

	        boekenlijst = kassa.getAlleBoekenLijstOpLocatie();
	        for (Boek boek : boekenlijst) {
	            System.out.println (boek.getAuteur());
	        }
	    }
	  }
	
	/**
	 * Hulpmethode voor het tonen van een boekenlijst (onder constructie).
	 * Pie; Kleine aanpassing gedaan. Eerst ISBN tonen, zodat we die er gemakkelijk uit kunnen filteren
	 * Dat is handig bij het toevoegen van een geslecteerd boek aan de bestellijst.
	 */
	private void toonVoorraadLijst(){
	  for(Boek b: boekenlijst){
  	    String boekInVoorraad = b.getISBN() + ": " + b.getTitel() + " - " + b.getAuteur() + "    € " + Double.toString(b.getPrijs());
  	    voorraadModel.add(0, boekInVoorraad);
	  }
	}
	/**
	 * 
	 */
	private void toonBestelLijst () {
	    selectieModel.clear();
	    for(Boek b: kassa.getBestelLijst() ){
		String boekInVoorraad = b.getISBN() + ": " + b.getTitel() + " - " + b.getAuteur() + "    € " + Double.toString(b.getPrijs());
		selectieModel.add(0, boekInVoorraad);
	    }
	    totaalPrijsLabel.setText("Totaalprijs: €" + df.format( kassa.getTotaalBedrag() ) ); 
	}
 }