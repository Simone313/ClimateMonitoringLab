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
import java.util.LinkedList;
import javax.swing.*;

/**
 * L'istanza della classe FrameAggiungiParametri permette all'utente di 
 * selezionare a quale delle aree presenti nel centro di monitoraggio associato
 * andare a modificare i parametri
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameAggiungiParametri extends JFrame{
    /**
    * <p> l'attributo <code>label</code> visualizza la scritta 'Seleziona l'area da modificare'.
    */
   private JLabel label;
   /**
    * <p> l'attributo <code>gruppo</code> contiene tutte le aree associate ad un centro di monitoraggio.
    */
   private ButtonGroup gruppo;
   /**
    * <p> l'attributo <code>select</code> è una stringa che indica l'area selezionata.
    */
   private String select;
   /**
    * <p> l'attributo <code>invio</code> è un button che serve per confermare la scelta.
    */
   private JButton invio;


    /**
     * Costruttore della classe FrameAggiungiParametri. Inizializza la schermata
     * per la selezione del luogo al quale assegnare i parametri
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public FrameAggiungiParametri() throws SQLException, RemoteException{
        super("Modifica area");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        label= new JLabel("Seleziona l'area da modificare");
        gruppo = new ButtonGroup();
        invio= new JButton("Invio");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=0;
        gbc.gridy=0;
        add(label,gbc);
        
        
        
        
        /*Operatore op= FrameAccessoOperatore.getOperatore();
        
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String user = "postgres";
        String password = "simone03";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement selStmt = conn.createStatement();
        String nomeCentro=op.getCentroMonitoraggio().getNome();
        String stmt = "SELECT * FROM centromonitoraggio WHERE \"NomeCentro\" = '"+nomeCentro+"'";

        ResultSet reS= selStmt.executeQuery(stmt);*/
        LinkedList<String> luoghi= ClientCM.getListaNomiCentromonitoraggio();
        
        for(int i=1; i<=luoghi.size();i++){
            JRadioButton btn= new JRadioButton(luoghi.get(i-1));
            gruppo.add(btn);
            
            gbc.gridx=i;
            gbc.gridy=0;
            add(btn,gbc);
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    select= btn.getText();
                }
            });
            
        }
        
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(select!=null){
                    new FrameAggiungiParametri2(select);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Selezionare un'opzione", "ERRORE", JOptionPane.ERROR_MESSAGE);

                }
                
            }
        });
        
        gbc.gridx=0;
        gbc.gridy=1;
        add(invio,gbc);
        
        
        setVisible(true);
    }
}
