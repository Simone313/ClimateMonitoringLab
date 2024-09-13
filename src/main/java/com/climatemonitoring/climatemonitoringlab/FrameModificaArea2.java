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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * L'istanza della classe FrameModificaArea2 permette all'utente di inserire 
 * le nuove informazioni da assegnare all'area selezionata
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameModificaArea2 extends JFrame{
    /**
    * <p> L'attributo <code>nomeAreaCercata</code> è una stringa che contiene il nome dell'area da modificare.
    */
   private String nomeAreaCercata;
   /**
    * <p> L'attributo <code>invio</code> è un button utilizzato per confermare la modifica.
    */
   private JButton invio;


    /**
     * Costruttore della classe FrameModificaArea2. Innizializza la schermata
     * per effettuare la modifica dell'area precedentemente selezionata.
     * @param nome nome dell'area precedentemente selezionata
     */
    public FrameModificaArea2(String nome){
        super("Assegnamento parametri");
        nomeAreaCercata=nome;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        
        invio= new JButton("invio");
        JLabel ln= new JLabel("Nuovo nome:");
        JLabel llat=new JLabel("Latitudine:");
        JLabel llon= new JLabel("Longitudine:");

        JTextField n= new JTextField(10);
        JTextField lat= new JTextField(10);
        JTextField lon= new JTextField(10);
        
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Operatore op;
                    if(FrameRegistrazioneOperatore.isForRegistration){
                        op=FrameRegistrazioneOperatore.getOperatore();
                    }else{
                        
                        op=FrameAccessoOperatore.getOperatore();
                    }
                     
                    String nome=n.getText();
                    String coordinate= lat.getText()+","+lon.getText();
                    
                    /*Connection conn = DriverManager.getConnection(url, user, password);
                    Statement selStmt = conn.createStatement();
                    String nomeCorrente=op.getCentroMonitoraggio().getNome();
                    String stmt = "UPDATE centromonitoraggio "
                            + "SET \"luogo\" = '" + nome + "', \"coordinate\" = '"+ coordinate+"' "
                            + "WHERE \"luogo\" = '" + nomeAreaCercata + "'";
                    
                    selStmt.executeUpdate(stmt);*/
                    
                    ClientCM.updateNomeCoordinateCentroMonitoraggio(op, nome, coordinate, nomeAreaCercata);
                } catch (SQLException ex) {
                    Logger.getLogger(FrameModificaArea2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameModificaArea2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(null, "Modifica avvenuta con successo", "MODIFICA EFFETTUATA", JOptionPane.INFORMATION_MESSAGE);

                FrameMenu.getFrameModificaCentro().setVisible(true);
                setVisible(false);
            }
        });
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=0;
        gbc.gridy=0;
        add(ln,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        add(n,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        add(llat,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        add(lat,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        add(llon,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        add(lon,gbc);
        
        gbc.gridx=3;
        gbc.gridy=3;
        add(invio,gbc);
        
        setVisible(true);
    }
}
