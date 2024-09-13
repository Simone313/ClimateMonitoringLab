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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * L'istanza della classe FrameRecuperoPassword permette all'utente di recuperare
 * la password tramite la parola di recupero fornita al momento dell'accesso
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameRecuperoPassword extends JFrame{
    /**
     * <p> l'attributo <code>label</code> mostra la frase 'Inserisci la parola per il recupero della password'
     * 
     */
    private JLabel label;
    /**
     * <p> l'attributo <code>parolajl</code> mostra la frase 'Parola di recupero' a schermo
     */
    private JLabel parolajl;
    /**
     * <p> l'attributo <code>parola</code> è un textField per acquisire la praola inserita
     */
    private JTextField parola;
    /**
     * <p> l'attributo <code>invio</code> è un button per confermare l'inserimento
     */
    private JButton invio;
    /**
     * <p> l'attributo <code>nomejl</code> mostra a schermo la frase 'Nome:'
     */
    private JLabel nomejl;
    /**
     * <p> l'attributo <code>cognomejl</code> mostra a schermo la frase 'Cognome:'
     */
    private JLabel cognomejl;
    /**
     * <p> l'attributo <code>nome</code> è un textField per acquisire il nome inserito
     */
    private JTextField nome;
    /**
     * <p> l'attributo <code>cognome</code> è un textField per acquisire il cognome inserito
     */
    private JTextField cognome;

    /**
     * Costruttore della classe FrameRecuperoPassword. Inizializza la finestra
     * per l'inserimento della parola di recupero della password.
     */
    public FrameRecuperoPassword(){
        super("Recupero password");
        setSize(500,300);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        
        label= new JLabel("Inserisci la parola per il recupero della password");
        parolajl= new JLabel("Parola di recupero");
        parola= new JTextField();
        invio= new JButton("Invio");
        nomejl= new JLabel("Nome:");
        cognomejl= new JLabel("Cognome: ");
        nome= new JTextField();
        cognome= new JTextField();
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String password= ClientCM.recuperoPassword(nome.getText(), cognome.getText(), parola.getText());
                    if(password==null){
                        JOptionPane.showMessageDialog(null, "Password non trovata", "RECUPERO FALLITO", JOptionPane.ERROR_MESSAGE);
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "La tua password è: "+password, "RECUPERO EFFETTUATO CON SUCCESSO", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameRecuperoPassword.class.getName()).log(Level.SEVERE, null, ex);
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
        add(parolajl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        add(parola,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        add(invio,gbc);
        
        setVisible(true);
    }
}
