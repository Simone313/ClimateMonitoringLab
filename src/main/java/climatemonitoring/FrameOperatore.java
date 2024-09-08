/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;


import java.awt.BorderLayout;
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
 *  L'istanza della classe FrameOperatore permette di selezionare se effettuare 
 * una registrazione oppure un accesso al programma
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameOperatore extends JFrame{
    /**
     * <p> l'attributo <code>label</code> viene tuilizzato per mostrare a schermo una frase
     * <p> l'attributo <code>accediBtn</code> è un button che se premuto crea un istanza della classe FrameAccessoOperatore
     * <p> l'attributo <code>registratiBtn</code> è un button che se premuto crea un istanza della classe FrameRegistrazioneOperatore
     * <p> l'attributo <code>frameRegistrazione</code> viene utilizzato per tenere traccia dell'istanza della classe FrameRegistrazioneOperatore che viene creata
     * 
     */
    private static FrameRegistrazioneOperatore frameRegistrazione;
    private JLabel label;
    private JButton accediBtn;
    private JButton registratiBtn;

    /**
     * Costruttore della classe FrameOperatore. Inizializza la finestra per 
     * scegliere se procedere con la registrazione o con l'accesso.
     */
    public FrameOperatore(){
        super("ClimateMonitoring");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,125);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        label= new JLabel("Operatore autorizzato:");
        accediBtn= new JButton("Accedi");
        registratiBtn= new JButton("Registrati");
        
        
        accediBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameAccessoOperatore frameAccesso= new FrameAccessoOperatore();
                setVisible(false);
            }
        });
        
        registratiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frameRegistrazione= new FrameRegistrazioneOperatore();
                } catch (SQLException ex) {
                    Logger.getLogger(FrameOperatore.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameOperatore.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        
        gbc.gridx=0;
        gbc.gridy=1;
        add(accediBtn,gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        add(registratiBtn,gbc);
        
        setVisible(true);
    }
    
    /**
     * Metodo che restituisce l'istanza della classe FrameRegistrazioneOperatore
     * @return l'istanza della classe FrameRegistrazioneOperatore
     */
    public static FrameRegistrazioneOperatore getFrameRegistrazione(){
        return frameRegistrazione;
    }
}
