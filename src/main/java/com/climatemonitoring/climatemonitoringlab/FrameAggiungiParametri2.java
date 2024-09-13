/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * L'istanza della classe FrameAggiungiiParametri2 viene utilizzata per 
 * permettere all'utente di inserire i parametri desiderati per l'area selezionata
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameAggiungiParametri2 extends JFrame{
    /**
    * <p> l'attributo <code>ventoLabel</code> visualizza la scritta 'Vento: '.
    */
   private JLabel ventoLabel;
   /**
    * <p> l'attributo <code>umiditaLabel</code> visualizza la scritta 'Umidita: '.
    */
   private JLabel umiditaLabel;
   /**
    * <p> l'attributo <code>pressioneLabel</code> visualizza la scritta 'Pressione: '.
    */
   private JLabel pressioneLabel;
   /**
    * <p> l'attributo <code>temperaturaLabel</code> visualizza la scritta 'Temperatura: '.
    */
   private JLabel temperaturaLabel;
   /**
    * <p> l'attributo <code>precipitazioniLabel</code> visualizza la scritta 'Precipitazioni: '.
    */
   private JLabel precipitazioniLabel;
   /**
    * <p> l'attributo <code>altGhiLabel</code> visualizza la scritta 'Altezza dei ghiacciai: '.
    */
   private JLabel altGhiLabel;
   /**
    * <p> l'attributo <code>masGhiLabel</code> visualizza la scritta 'Massa dei ghiacciai: '.
    */
   private JLabel masGhiLabel;
   /**
    * <p> l'attributo <code>noteLabel</code> visualizza la scritta 'Note: '.
    */
   private JLabel noteLabel;

   /**
    * <p> l'attributo <code>vento</code> permette di selezionare il parametro da assegnare al vento.
    */
   private JComboBox vento;
   /**
    * <p> l'attributo <code>umidita</code> permette di selezionare il parametro da assegnare all'umidità.
    */
   private JComboBox umidita;
   /**
    * <p> l'attributo <code>pressione</code> permette di selezionare il parametro da assegnare alla pressione.
    */
   private JComboBox pressione;
   /**
    * <p> l'attributo <code>temperatura</code> permette di selezionare il parametro da assegnare alla temperatura.
    */
   private JComboBox temperatura;
   /**
    * <p> l'attributo <code>precipitazioni</code> permette di selezionare il parametro da assegnare alle precipitazioni.
    */
   private JComboBox precipitazioni;
   /**
    * <p> l'attributo <code>altGhi</code> permette di selezionare il parametro da assegnare all'altezza dei ghiacciai.
    */
   private JComboBox altGhi;
   /**
    * <p> l'attributo <code>masGhi</code> permette di selezionare il parametro da assegnare alla massa dei ghiacciai.
    */
   private JComboBox masGhi;
   /**
    * <p> l'attributo <code>note</code> permette di inserire una spiegazione più dettagliata sui parametri inseriti.
    */
   private JTextArea note;

   /**
    * <p> l'attributo <code>invio</code> è un button per confermare gli inserimenti effettuati.
    */
   private JButton invio;


    /**
     * Costruttore della classe FrameAggiungiParametri2. Innizializza la schermata
     * per l'assegnazione dei parametri ad un luogo precedentemente selezionato
     * @param select nome del luogo precedentemente selezionato
     */
    public FrameAggiungiParametri2(String select) {
        super("Assegnamento parametri: "+select);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        
        
        Integer[] opzioni = {0,1, 2, 3, 4, 5};
        
        ventoLabel= new JLabel("Vento:");
        umiditaLabel= new JLabel("Umidita':");
        pressioneLabel= new JLabel("Pressione:");
        temperaturaLabel= new JLabel("Temperatura:");
        precipitazioniLabel= new JLabel("Precipitazioni:");
        altGhiLabel= new JLabel("Altezza Ghiacciai:");
        masGhiLabel= new JLabel("Massa Ghiacciai:");
        noteLabel= new JLabel("Note:");
        vento= new JComboBox<>(opzioni);
        umidita= new JComboBox<>(opzioni);
        pressione= new JComboBox<>(opzioni);
        temperatura= new JComboBox<>(opzioni);
        precipitazioni= new JComboBox<>(opzioni);
        altGhi= new JComboBox<>(opzioni);
        masGhi= new JComboBox<>(opzioni);
        note= new JTextArea(10,30);
        note.setLineWrap(true);
        note.setWrapStyleWord(true);
        
        invio= new JButton("Invio");
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Operatore op = null;
                    if(FrameAccessoOperatore.accessoAvvenuto()){
                        op= FrameAccessoOperatore.getOperatore();

                    }else{
                        op= FrameRegistrazioneOperatore.getOperatore();
                    }
                    /*String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
                    String user = "postgres";
                    String password = "simone03";
                    Connection conn = DriverManager.getConnection(url, user, password);
                    Statement selStmt = conn.createStatement();*/
                    String nomeCentro=op.getCentroMonitoraggio().getNome();
                    
                    int ven=(int) vento.getSelectedItem();
                    int umi=(int) umidita.getSelectedItem();
                    int pre=(int) pressione.getSelectedItem();
                    int temp=(int) temperatura.getSelectedItem();
                    int precipit=(int) precipitazioni.getSelectedItem();
                    int aG=(int) altGhi.getSelectedItem();
                    int mG=(int) masGhi.getSelectedItem();
                    String not= note.getText();
                    
                    
                    /*String stmt="SELECT * FROM parametriclimatici";
                    ResultSet reSet= selStmt.executeQuery(stmt);
                    while(reSet.next()){
                        if(reSet.getString("Luogo").equals(select)){
                            luogoGiaPresente=true;
                        }
                    }
                    
                    if(luogoGiaPresente){
                        stmt="UPDATE parametriclimatici SET \"Centro\" = '"+nomeCentro+"', \"Vento\" = '"+ven+"', \"Umidità\" = '"+umi+"', \"Pressione\" = '"+pre+"', \"Temperatura\" = '"+temp+"', \"Precipitazioni\" = '"+precipit+"', \"Altezza Ghiacciai\" = '"+aG+"', \"Massa Ghiacciai\" = '"+mG+"', \"Note\" = '"+not+"' WHERE \"Luogo\" = '"+select+"'";
                    }else{
                        stmt = "INSERT INTO parametriclimatici VALUES ('"+nomeCentro+"','"+select+"','"+data+"','"+ven+"','"+umi+"','"+pre+"','"+temp+"','"+precipit+"','"+aG+"','"+mG+"','"+not+"')";
                    }
                    
                    
                    selStmt.executeUpdate(stmt);*/
                    
                    ClientCM.inserisciParametriClimatici(select, nomeCentro, ven, umi, pre, temp, precipit, aG, mG, not);
                } catch (SQLException ex) {
                    Logger.getLogger(FrameAggiungiParametri2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameAggiungiParametri2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(null, "Parametri  di "+select+" aggiornati con successo", "MODIFICA EFFETTUATA", JOptionPane.INFORMATION_MESSAGE);
                
                //Una volta conclusa l'operazione torna al menu
                FrameMenu menu;
                if(FrameAccessoOperatore.accessoAvvenuto()){
                    menu=FrameAccessoOperatore.getframeMenu();
                }else{
                    menu=FrameRegistrazioneOperatore.getframeMenu();
                }
                
                menu.setVisible(true);
                setVisible(false);
            }
            
        });
        
        
        JScrollPane scrollPane = new JScrollPane(note);
        add(scrollPane);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=0;
        gbc.gridy=0;
        add(ventoLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        add(umiditaLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        add(pressioneLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        add(temperaturaLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        add(precipitazioniLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        add(altGhiLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=6;
        add(masGhiLabel,gbc);
        
        gbc.gridx=0;
        gbc.gridy=7;
        add(noteLabel,gbc);
        
        
        gbc.gridx=1;
        gbc.gridy=0;
        add(vento,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        add(umidita,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        add(pressione,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        add(temperatura,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        add(precipitazioni,gbc);
        
        gbc.gridx=1;
        gbc.gridy=5;
        add(altGhi,gbc);
        
        gbc.gridx=1;
        gbc.gridy=6;
        add(masGhi,gbc);
        
        gbc.gridx=2;
        gbc.gridy=7;
        add(note,gbc);
        
        gbc.gridx=3;
        gbc.gridy=8;
        add(invio,gbc);
        
        setVisible(true);
    }
    
}
