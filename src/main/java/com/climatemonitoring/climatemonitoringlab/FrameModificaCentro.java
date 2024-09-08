/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;

import climatemonitoring.*;
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
 * L'istanza della classe FrameModificaCentro permette di selezionare cosa andare
 * a modificare nello specifico (Nome, Indirizzo, Aree)
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */

public class FrameModificaCentro extends JFrame {
    /**
     * <p> l'attributo <code>label</code> viene utilizzato per mostrare una frase
     * <p> l'attributo <code>modificaNome</code> indica l'opzione per la modifica del nome del centro
     * <p> l'attributo <code>modificaIndirizzo</code> indica l'opzione per la modifica dell'indirizzo del centro
     * <p> l'attributo <code>modificaAree</code> indica l'opzione per la modifica delle aree del centro
     * <p> l'attributo <code>gruppo</code> raggruppa gli attributi <code>modificaNome</code>, <code>modificaIndirizzo</code>, <code>modificaAree</code> che sono utilizzati per selezionare quale modifica apportare
     * <p> l'attributo <code>invio</code> è un button utilizzato per confermare la scelta
     * <p> l'attributo <code>indietro</code> è un button utilizzato per tornare alla schermata precedente
     * <p> l'attributo <code>select</code> serve per indicare quale opzione è stata scelta
     */
    private JLabel label;
    private JRadioButton modificaNome;
    private JRadioButton modificaIndirizzo;
    private JRadioButton modificaAree;
    private ButtonGroup gruppo;
    private JButton invio;
    private JButton indietro;
    private int select=0;

    /**
     * Costruttore della classe FrameModificaCentro. Inizializza  la finestra 
     * per la scelta dell'opzione da modificare
     */
    public FrameModificaCentro(){
        super("Modifica del centro di monitoraggio");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        label= new JLabel("Seleziona parametro da modificare");
        modificaNome= new JRadioButton("Modifica nome");
        modificaIndirizzo= new JRadioButton("Modifica indirizzo");
        modificaAree= new JRadioButton("Modifica aree");
        invio= new JButton("Invio");
        indietro= new JButton("Indietro");
        gruppo= new ButtonGroup();
        gruppo.add(modificaNome);
        gruppo.add(modificaIndirizzo);
        gruppo.add(modificaAree);
        
        modificaNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=1;
            }
        });
        
        modificaIndirizzo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=2;
            }
        });
        
        modificaAree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=3;
            }
        });
        
        
        invio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch(select){
                            case 1:{
                                new FrameModificaNome();
                                setVisible(false);
                                break;
                            }
                            case 2:{
                                new FrameModificaIndirizzo();
                                setVisible(false);
                                break;
                            }
                            case 3:{
                                try {
                                    new FrameModificaArea();
                                    setVisible(false);
                                } catch (SQLException ex) {
                                    Logger.getLogger(FrameModificaCentro.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (RemoteException ex) {
                                Logger.getLogger(FrameModificaCentro.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                break;
                            }
                            default:{
                                JOptionPane.showMessageDialog(null, "Selezionare un'opzione", "ERRORE", JOptionPane.ERROR_MESSAGE);

                            }
                        }
                    }
                });
        
        
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
        add(modificaNome,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        add(modificaIndirizzo,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        add(modificaAree,gbc);
        
        gbc.gridx=2;
        gbc.gridy=4;
        add(invio,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        add(indietro,gbc);
        
        setVisible(true);
    }
    
    
    
}
