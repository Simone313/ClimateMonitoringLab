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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * L'istanza della classe FrameAccessoOperatore permette all'utente di inserire
 * i dati per identificarsi ed accedere alla sezione riservata agli operatori 
 * autorizzati
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameAccessoOperatore extends JFrame {
    /**
    * <p> l'attributo <code>accedi</code> mostra la scritta 'Inserisci le credenziali'.
    */
   private JLabel accedi;
   /**
    * <p> l'attributo <code>nomejl</code> mostra la scritta 'Nome: '.
    */
   private JLabel nomejl;
   /**
    * <p> l'attributo <code>cognomejl</code> mostra la scritta 'Cognome: '.
    */
   private JLabel cognomejl;
   /**
    * <p> l'attributo <code>passwordjl</code> mostra la scritta 'Password: '.
    */
   private JLabel passwordjl;
   /**
    * <p> l'attributo <code>nome</code> è un textField utilizzato per l'inserimento del nome.
    */
   private JTextField nome;
   /**
    * <p> l'attributo <code>cognome</code> è un textField utilizzato per l'inserimento del cognome.
    */
   private JTextField cognome;
   /**
    * <p> l'attributo <code>psw</code> è un passwordField utilizzato per l'inserimento della password.
    */
   private JPasswordField psw;
   /**
    * <p> l'attributo <code>invia</code> è un pulsante utilizzato per inviare i dati.
    */
   private JButton invia;
   /**
    * <p> l'attributo <code>accesso</code> è un booleano che risulta true se l'apertura del frame menu è avvenuta a seguito di un accesso.
    */
   private static boolean accesso = false;
   /**
    * <p> l'attributo <code>menu</code> tiene traccia dell'istanza di FrameMenu che si viene a creare.
    */
   private static FrameMenu menu;
   /**
    * <p> l'attributo <code>nom</code> è una stringa dentro la quale viene salvato il nome dell'operatore.
    */
   public static String nom;
   /**
    * <p> l'attributo <code>con</code> è una stringa dentro la quale viene salvato il cognome dell'operatore.
    */
   public static String con;
   /**
    * <p> l'attributo <code>pass</code> è una stringa dentro la quale viene salvata la password dell'operatore.
    */
   public static String pass;
   /**
    * <p> l'attributo <code>count</code> è un contatore utilizzato per gestire le operazioni.
    */
   private static int count;


    /**
    * Costruttore della classe FrameAccessoOperatore. Inizializza la finestra per l'accesso 
    * degli operatori inserendo nome, cognome e password.
    */
    public FrameAccessoOperatore(){
        super("ClimateMonitoring");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        count=0;
        accedi= new JLabel("Inserisci le credenziali");
        nomejl= new JLabel("Nome:");
        cognomejl= new JLabel("Cognome: ");
        passwordjl= new JLabel("Password: ");
        nome= new JTextField();
        cognome= new JTextField();
        psw= new JPasswordField();
        invia= new JButton("Invio");
        
        
        
        invia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nom= nome.getText();
                    con= cognome.getText();
                    char[] p=psw.getPassword();
                    pass= arrayCharToString(p);
                    
                    Boolean risultato= ClientCM.controlloCredenzialiAccesso(nom, con, pass);
                    if(risultato){
                        menu=new FrameMenu();
                        accesso=true;
                        setVisible(false);
                    }else{
                        if(count==5){
                            new FrameRecuperoPassword();
                            nome.setText("");
                            cognome.setText("");
                            psw.setText("");
                            count++;
                        }else if(count==10){
                            JOptionPane.showMessageDialog(null, "Troppi tentativi falliti, il programma termina", "ACCESSO NEGATO", JOptionPane.ERROR_MESSAGE);
                            dispose();
                        }else{
                            nome.setText("");
                            cognome.setText("");
                            psw.setText("");
                            count++;
                            JOptionPane.showMessageDialog(null, "Credenziali errate, riprovare", "ACCESSO NEGATO", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameAccessoOperatore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=1;
        gbc.gridy=0;
        add(accedi,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        add(nomejl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        add(nome,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        add(cognomejl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        add(cognome,gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        add(passwordjl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        add(psw,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        add(invia,gbc);
        
        setVisible(true);
    }
    /**
     * metodo che prende un array di caratteri e lo restituisce come stringa senza
     * parentesi quadre e virgole
     * @param c array di caratteri
     * @return stringa risultante
     */
    private String arrayCharToString(char[] c){
        String ris= Arrays.toString(c);
        ris= ClientCM.rimuovi(ris, '[');
        ris= ClientCM.rimuovi(ris, ']');
        ris=ClientCM.rimuovi(ris, ',');
        ris=ClientCM.rimuovi(ris, ' ');
        return ris;
    }
    //metodo che serve per capire se si è arrivati al menu tramite una registrazione o un accesso

    /**
     *  metodo che restituisce un booleano che specifica se è avvenuto l'accesso
     * @return booleano che specifica se è avvenuto l'accesso
     */
    public static boolean accessoAvvenuto(){
        return accesso;
    }
    
    /**
     * metodo che restituisce l'istanza della classe FrameMenu
     * @return istanza della classe FrameMenu
     */
    public static FrameMenu getframeMenu(){
        return menu;
    }
    
    /**
     * metodo che restituisce l'oggetto operatore 
     * @return oggetto operatore 
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public static Operatore getOperatore() throws SQLException, RemoteException{
        return ClientCM.getOperatore(nom, con, pass);
        /*String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String user = "postgres";
        String password = "simone03";
        Connection conn = DriverManager.getConnection(url, user, password);
        
        String rigaFile;
        Statement selStmt = conn.createStatement();
       /* String stmt= "SELECT *"
                + "FROM operatori"
                + "WHERE \"nome\" = '"+nom+"' AND \"cognome\" = '"+con+"' AND \"password\" = '"+pass+"'";*/
       /*String sql = "SELECT * FROM operatori WHERE nome = ? AND cognome = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nom);
        stmt.setString(2, con);
        stmt.setString(3, pass);

        ResultSet reStmt = stmt.executeQuery();

        //ResultSet reStmt= selStmt.executeQuery(stmt);
        Operatore o = null;
        while(reStmt.next()){
            o= new Operatore(reStmt.getString("nome"), reStmt.getString("cognome"), reStmt.getString("cf"), reStmt.getString("email"), reStmt.getString("id"), reStmt.getString("password"), getCentroMonitoraggio(reStmt.getString("centromonitoraggio")), reStmt.getString("recuperopassword"));
        }
        
        return o;*/
    }
    
    
}
