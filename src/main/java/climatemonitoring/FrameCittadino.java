/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * L'istanza della classe FrameCittadino permette a tutti gli utenti di cercare
 * informazioni relative ad un luogo
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameCittadino extends JFrame{
    /**
     * <p> l'attributo <code>label</code> visualizza la scritta 'cerca luogo'
     * <p> l'attributo <code>cerca</code> è un textField nel quale va inserito il nome dell'luogo da cercare
     * <p> l'attributo <code>invio</code> è un button che avvia la ricerca
     * <p> l'attributo <code>panel</code> contiene tutti gli elementi elencati
     * 
     */
    private JLabel label;
    private JTextField cerca;
    private JButton invio;
    private JTextArea risposta;
    private JPanel panel;

    /**
    * Crea un'istanza della finestra di monitoraggio climatico per permettere agli utenti di cercare
    * informazioni relative a un luogo specifico.
    */
    public FrameCittadino(){
        super("ClimateMonitoring");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        
        label= new JLabel("Cerca un luogo");
        cerca= new JTextField();
        invio= new JButton("Invio");
        risposta= new JTextArea();
        panel= new JPanel();
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String luogo= cerca.getText();
                try {
                    risposta.setText(cercaLuogo(luogo));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FrameCittadino.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FrameCittadino.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(panel));
        gbc.gridx=0;
        gbc.gridy=0;
        panel.add(label,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(cerca,gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        panel.add(invio,gbc);
        
        gbc.gridx=3;
        gbc.gridy=2;
        panel.add(risposta,gbc);
        
        
        
        setVisible(true);
        
    }
    
    /**
     * cerca informazioni relative al luogo passato come parametro
     * @param ris nome del luogo
     * @return informazioni relative al luogo cercato
     * @throws IOException
     * @throws SQLException 
     */
    private static String cercaLuogo(String ris) throws IOException, SQLException{
            if(!isNumero(ris)) {
                    System.out.println(ClientCM.cercaAreaGeografica(ris)!=null? ClientCM.cercaAreaGeografica(ris):"");
                    return (ClientCM.cercaAreaGeografica(ris));
                    }else {

                            String[] s=null;
                            if(ris.contains(" ,") || ris.contains(", ")) {

                                    ris=rimuovi(ris,' ');
                                    s=ris.split(",");

                            }else if(ris.contains(" ")) {

                                    s= ris.split(" ");

                            }else if(ris.contains(",")) {
                                    s=ris.split(",");
                            }else if(ris.contains("-")) {
                                    s=ris.split("-");
                            }



                           String luogo=ClientCM.cercaAreaGeografica(s[0],s[1]);
                            System.out.println("Il luogo cercato e':\n"+luogo);
                            return(luogo);
            }
        }
    
    /**
     * controlla se la stringa passata come parametro è un numero
     * @param a stringa da controllare
     * @return true se la stringa è un numero, false altrimenti
     */
    private static boolean isNumero(String a) {
        int cont=0;
        a=a.toLowerCase();
        for(int i=0;i<a.length();i++) {
                if((a.charAt(i)<97 || a.charAt(i)>122)||(a.charAt(i)==' ')||(a.charAt(i)==',')||(a.charAt(i)=='-')) {
                        cont++;
                }
        }
        if(cont==a.length()) {
                return true;
        }else {
                return false;
        }
		
    }
    
    /**
     * rimuove tutti i caratteri c dalla stringa s
     * @param s stringa di partenza
     * @param c carattere da rimuovere
     * @return stringa priva di caratteri c
     */
    private static String rimuovi(String s,char c) {  //Metodo per rimuovere un elemento dalla stringa: rimuovi(Stringa di partenza, elemento della stringa da rimuovere)
        String r="";
        for(int i=0;i<s.length();i++) {
                if(s.charAt(i)!=c) {
                        r=r+s.charAt(i);
                }
        }
        return r;
    }
}
