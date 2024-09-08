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
 * L'istanza della classe FrameModificaIndirizzo permette di inserire un nuovo 
 * indirizzo da assegnare al centro associato
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameModificaIndirizzo extends JFrame {
    /**
     * <p> l'attributo <code>label</code> viene utilizzato per mostrare una frase
     * <p> l'attributo <code>nuovoIndirizzo</code> è un TextField utilizzato per far inserire il nuovo indirizzo del centro
     * <p> l'attributo <code>invio</code> è un button utilizzato per confermare la modifica
     */
    private JLabel label;
    private JTextField nuovoIndirizzo;
    private JButton invio;

    /**
     * Costruttore della classe FrameModificaIndirizzo. Inizializza la finestra
     * per l'inserimento del nuovo indirizzo del centro.
     */
    public FrameModificaIndirizzo (){
        super("Modifica nome");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        label= new JLabel("Inserisci un nuovo indirizzo per il centro associato");
        nuovoIndirizzo= new JTextField();
        invio= new JButton("Invio");
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(FrameRegistrazioneOperatore.isForRegistration){
                        ClientCM.modificaIndirizzoCentro(FrameAccessoOperatore.getOperatore(), nuovoIndirizzo.getText());
                    }else{
                        ClientCM.modificaIndirizzoCentro(FrameRegistrazioneOperatore.getOperatore(), nuovoIndirizzo.getText());
                    }                } catch (SQLException ex) {
                    Logger.getLogger(FrameModificaNome.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameModificaIndirizzo.class.getName()).log(Level.SEVERE, null, ex);
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
        add(label,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        add(nuovoIndirizzo,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        add(invio,gbc);
        
        setVisible(true);
    }
}
