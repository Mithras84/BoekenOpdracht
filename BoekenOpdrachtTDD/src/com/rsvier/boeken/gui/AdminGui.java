package com.rsvier.boeken.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import com.rsvier.boeken.model.Boek;
import com.rsvier.boeken.controller.Admin;
import com.rsvier.boeken.db.DBInsert;


/**
 * Visuele klasse voor de administrator waarin boeken toegevoegd kunnen worden aan de boekendatabase
 * @author Uppe
 *
 */
public class AdminGui extends JFrame {
  
  private static final int FRAME_BREEDTE = 560;
  private static final int FRAME_HOOGTE = 250;
  private static final int SCHERM_BREEDTE = 1920;
  private static final int SCHERM_HOOGTE = 1080;
  
  private Container pane = null;
  private JTextField titelVeld = null;
  private JTextField auteurVeld = null;
  private JTextField isbnVeld = null;
  private JTextField paginaVeld = null;
  private JTextField genreVeld = null;
  private JTextField prijsVeld = null;
  private JLabel locatieLabel = new JLabel("Locatie:");
  private JLabel locatieLabel2 = new JLabel("Geen selectie");  
  private Admin admin = new Admin();
  private DBInsert database = null;
  
  // constructor
  public AdminGui(){
          super();
          initialize();           
  }
  
  public void initialize(){
          // afmetingen frame
          this.setTitle("Display kassa");
          this.setSize(FRAME_BREEDTE, FRAME_HOOGTE);
          this.setLocation((SCHERM_BREEDTE - FRAME_BREEDTE) / 2,(SCHERM_HOOGTE - FRAME_HOOGTE) / 2);
          
          //aanmaken container
          pane = getContentPane();
          pane.setLayout(null);
          
          //aanmaken menubalk
          JMenuBar menubalk = new JMenuBar();
          setJMenuBar(menubalk);
          JMenu bewerken = new JMenu("Bewerken");
          menubalk.add(bewerken);
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
               
          // JTextFields (titel, auteur, isbn)
          titelVeld = new JTextField("");
          titelVeld.setBounds(40, 30, 100, 20);
          pane.add(titelVeld);
              
          auteurVeld = new JTextField("");
          auteurVeld.setBounds(160, 30, 100, 20);
          pane.add(auteurVeld);
              
          isbnVeld = new JTextField("");
          isbnVeld.setBounds(280, 30, 100, 20);
          pane.add(isbnVeld);
              
          paginaVeld = new JTextField("");
          paginaVeld.setBounds(40, 90, 100, 20);
          pane.add(paginaVeld);
              
          genreVeld = new JTextField("");
          genreVeld.setBounds(160, 90, 100, 20);
          pane.add(genreVeld);
              
          prijsVeld = new JTextField("");
          prijsVeld.setBounds(280, 90, 100, 20);
          pane.add(prijsVeld);
              
          //JLabels (titel,auteur, isbn, totaalprijs)
          JLabel titelLabel = new JLabel("Titel:");
          titelLabel.setBounds(40, 10, 100, 20);
          pane.add(titelLabel);
              
          JLabel auteurLabel = new JLabel("Auteur:");
          auteurLabel.setBounds(160, 10, 100, 20);
          pane.add(auteurLabel);
              
          JLabel isbnLabel = new JLabel("Isbn:");
          isbnLabel.setBounds(280, 10, 100, 20);
          pane.add(isbnLabel);
              
          JLabel paginaLabel = new JLabel("Aantal pagina's:");
          paginaLabel.setBounds(40, 70, 100, 20);
          pane.add(paginaLabel);
              
          JLabel genreLabel = new JLabel("Genre:");
          genreLabel.setBounds(160, 70, 100, 20);
          pane.add(genreLabel);
              
          JLabel prijsLabel = new JLabel("Prijs (€):");
          prijsLabel.setBounds(280, 70, 100, 20);
          pane.add(prijsLabel);
          
          locatieLabel.setBounds(40, 130, 100, 20);
          pane.add(locatieLabel);
          locatieLabel2.setBounds(40, 150, 100, 20);
          pane.add(locatieLabel2);
              
          //Knoppen
          JButton opslaanKnop = new JButton("Sla op");
          opslaanKnop.setBounds(400, 50, 100, 40);
          pane.add(opslaanKnop);
          opslaanKnop.addActionListener(new SlaBoekOpListener());
              
  }               
      

    public static void main(String[] args) {
      AdminGui frame = new AdminGui();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
  
  /**
   * (Luisteraar) Creeërt een nieuw boek met de ingevulde waarden en slaat deze op in de database. 
   */
  public class SlaBoekOpListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      try{
        String isbn = isbnVeld.getText();
        String titel = titelVeld.getText();
        String auteur = auteurVeld.getText();
        double prijs = 0.0;
        if(!prijsVeld.getText().equals("")){
          prijs = Double.parseDouble(prijsVeld.getText());
        }
        String genre = genreVeld.getText();
        int paginas = 0;
        if(!paginaVeld.getText().equals("")){
          paginas = Integer.parseInt(paginaVeld.getText());
        }
        if(!isbn.equals("") && !titel.equals("") && !auteur.equals("") && prijs != 0.0 && !genre.equals("") && paginas != 0){
          Boek nieuwBoek = new Boek(isbn, titel, auteur, prijs, genre, paginas);
          admin.addBoekToLijst(nieuwBoek); //Deze methode moet nog wel worden aangepast in de admin class, hij maakt nu bij deze methode een nieuwe DBInsert aan maar moet degene gebruiken die geselecteerd is in deze GUI.
          admin.addBoekenLijstToDB();
        }
        else{
          JOptionPane.showMessageDialog(pane, "Een of meer velden niet ingevuld", "Missende waarden", JOptionPane.ERROR_MESSAGE);
        }
      }
      catch(NumberFormatException NumberError){
        JOptionPane.showMessageDialog(pane, "Foute invoer bij prijs en/of aantal pagina's", "Foutieve invoer", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  /**
   * (Luisteraar) Selecteert de locatie Amsterdam
   */
  public class AmsterdamListener implements ItemListener {
    public void itemStateChanged(ItemEvent e){
      if(e.getStateChange() == ItemEvent.SELECTED){
        try {
          database = new DBInsert("Amsterdam");
        }
        catch(SQLException exp){
          JOptionPane.showMessageDialog(pane, "Database niet gevonden", "Fout bij het openen database", JOptionPane.ERROR_MESSAGE);
        }
        finally{
          locatieLabel2.setText("Amsterdam");
        }
      }
    }
  }
  
  /**
   * (Luisteraar) Selecteert de locatie Groningen
   */
  public class GroningenListener implements ItemListener {
    public void itemStateChanged(ItemEvent e){
      if(e.getStateChange() == ItemEvent.SELECTED){
        try {
          database = new DBInsert("Amsterdam");
        }
        catch(SQLException exp){
          JOptionPane.showMessageDialog(pane, "Database niet gevonden", "Fout bij het openen database", JOptionPane.ERROR_MESSAGE);
        }
        finally{
        locatieLabel2.setText("Groningen");
        }
      }
    }
  }
  
  /**
   * (Luisteraar) Selecteert de locatie Utrecht
   */
  public class UtrechtListener implements ItemListener {
    public void itemStateChanged(ItemEvent e){
      if(e.getStateChange() == ItemEvent.SELECTED){
        try {
          database = new DBInsert("Amsterdam");
        }
        catch(SQLException exp){
          JOptionPane.showMessageDialog(pane, "Database niet gevonden", "Fout bij het openen database", JOptionPane.ERROR_MESSAGE);
        }
        finally{
          locatieLabel2.setText("Utrecht");
        }
      }
    }
  }
}  
