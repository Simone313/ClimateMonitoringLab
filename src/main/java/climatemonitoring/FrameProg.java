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
import javax.swing.*;

/**
 *
 *  L'istanza della classe FrameProg si presenta all'avvio del programma e consente
 * di selezionare se accedere come cittadino o come operatore autorizzato
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameProg extends JFrame{
    /**
     * <p> l'attributo <code>label</code> viene tuilizzato per mostrare a schermo una frase
     * <p> l'attributo <code>cittadinoBtn</code> è un button che se premuto crea un istanza della classe FrameCittadino
     * <p> l'attributo <code>operatoreBtn</code> è un button che se premuto crea un istanza della classe FrameOperatore
     */
    private JLabel label;
    private JButton cittadinoBtn;
    private JButton operatoreBtn;

    /**
     * Costruttore della classe FrameProg. Inizializza la finestra per scegliere
     * se procedere come cittadino o come operatore
     */
    public FrameProg(){
        super("ClimateMonitoring");
        
        setSize(500,125);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        

        label= new JLabel("Accedi come:");
        cittadinoBtn= new JButton("Cittadino");
        operatoreBtn= new JButton("Operatore");
        
        cittadinoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameCittadino frameC= new FrameCittadino();
                setVisible(false);
            }
        });
        
        
        operatoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameOperatore frameO= new FrameOperatore();
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
        add(cittadinoBtn,gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        add(operatoreBtn,gbc);
        
        setVisible(true);
    }
}
