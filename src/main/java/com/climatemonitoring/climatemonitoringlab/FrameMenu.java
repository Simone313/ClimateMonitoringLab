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
 * L'istanza della classe FrameMenu permette all'utente di selezionare quale 
 * operazione si desidera svolgere
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameMenu extends JFrame{
    /**
     * <p> l'attributo <code>label</code> mostra una frase
     * <p> l'attributo <code>op1</code> rappresenta l'opzione di visualizzazione di tutti i centri
     * <p> l'attributo <code>op2</code> rappresenta l'opzione di creazione di un nuovo centro
     * <p> l'attributo <code>op3</code> rappresenta l'opzione di modifica del centro 
     * <p> l'attributo <code>op4</code> rappresenta l'opzione di assegnamento dei parametri
     * <p> l'attributo <code>gruppo</code> raggruppa tutte le opzioni
     * <p> l'attributo <code>select</code> è un intero utilizzato per indicare quale tra le quattro opzioni è stata scelta
     * <p> l'attributo <code>invio</code> è un button utilizzato per confermare la scelta
     */
    private JLabel label;
    private JRadioButton op1;
    private JRadioButton op2;
    private JRadioButton op3;
    private JRadioButton op4;
    private ButtonGroup gruppo;
    private JButton invio;
    private int select=0;
    
    /**
     * indica l'istanza della classe FrameModificaCentro che può essere eventualmente creata
     */
    private static FrameModificaCentro frameMod;

    /**
     *  Costruttore della classe FrameMenu. Inizializza la finestra di menu per
     * la scelta dell'operazione da svolgere.
     */
    public FrameMenu(){
        super("Menu");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(500,300);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        label= new JLabel("Sceglere un'opzione");
        op1= new JRadioButton("Vedi centri di monitoraggio gia' esistenti");
        op2= new JRadioButton("Crea centro di monitoraggio");
        op3= new JRadioButton("Modifica centro di monitoraggio");
        op4= new JRadioButton("Assegna parametri");
        
        gruppo= new ButtonGroup();
        
        invio= new JButton("Invio");
        gruppo.add(op1);
        gruppo.add(op2);
        gruppo.add(op3);
        gruppo.add(op4);
        
        op1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=1;
            }
        });
        
        op2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=2;
            }
        });
        
        op3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=3;
            }
        });
        
        op4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select=4;
            }
        });
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                switch(select){
                    case 1:{
                    try {
                        new FrameVisualizzazioneCentri();
                    } catch (RemoteException ex) {
                        Logger.getLogger(FrameMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        setVisible(false);
                        break;
                    }
                    case 2:{
                        new FrameCreaCentro();
                        setVisible(false);

                        break;
                    }
                    case 3:{
                        frameMod= new FrameModificaCentro();
                        setVisible(false);
                        break;
                    }
                    case 4:{
                    try {
                        new FrameAggiungiParametri();
                        setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(FrameMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(FrameMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        break;
                    }
                
                }
            }
        });
        
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=0;
        gbc.gridy=0;
        add(label,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        add(op1,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        add(op2,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        add(op3,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        add(op4,gbc);
        
        gbc.gridx=2;
        gbc.gridy=4;
        add(invio,gbc);
        
        setVisible(true);
    }
    
    /**
     * metodo che restituisce l'istanza della classe FrameModificaCentro
     * @return istanza della classe FrameModificaCentro
     */
    public static FrameModificaCentro getFrameModificaCentro(){
        return frameMod;
    }
    
    
}
