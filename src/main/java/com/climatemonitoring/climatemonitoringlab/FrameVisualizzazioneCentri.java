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
import javax.swing.*;

/**
 * L'istanza della classe FrameVisualizazzioneCentri mostra tutti i centri 
 * di monitoraggio presenti all'interno del database
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameVisualizzazioneCentri extends JFrame {
    /**
    * <p> L'attributo <code>label</code> viene utilizzato per mostrare a schermo una frase.
    */
   private JLabel label;

   /**
    * <p> L'attributo <code>centriCreati</code> è una <code>JTextArea</code> che mostra tutti i centri creati.
    */
   private JTextArea centriCreati;

   /**
    * <p> L'attributo <code>indietro</code> è un <code>JButton</code> che permette all'utente di tornare alla schermata precedente.
    */
   private JButton indietro;


    /**
     * Costruttore della classe FrameAggiungiParametri2. Innizializza la schermata
     * per La visualizzazione dei centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    public FrameVisualizzazioneCentri() throws RemoteException{
        super("Centri di monitoraggio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        label= new JLabel("I centri di monitoraggio creati sono:");
        String centri= ClientCM.visualizzaCentri();
        centriCreati= new JTextArea(centri);
        indietro= new JButton("Indietro");
        
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=1;
        gbc.gridy=0;
        add(label,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        add(centriCreati,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        add(indietro,gbc);
        setVisible(true);
    }
}
