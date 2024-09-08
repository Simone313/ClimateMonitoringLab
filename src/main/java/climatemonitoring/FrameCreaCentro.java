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
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * L'istanza della classe FrameCreaCentro permette all'utente di inserire tutte
 * le informazioni necessarie per la creazione di un nuovo centro di monitoraggio
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameCreaCentro extends JFrame{
/**
 * 
 * 
 * <p> L'attributo <code>panel</code> contiene tutti gli elementi del frame.
 * <p> L'attributo <code>labelNome</code> visualizza la scritta 'Nome:' per l'inserimento del nome del centro.
 * <p> L'attributo <code>labelIndirizzo</code> visualizza la scritta 'Indirizzo:' per l'inserimento dell'indirizzo del centro.
 * <p> L'attributo <code>labelNomeArea</code> visualizza la scritta 'Nome luogo:' per l'inserimento del nome dell'area.
 * <p> L'attributo <code>labelLatitudine</code> visualizza la scritta 'Latitudine:' per l'inserimento della latitudine dell'area.
 * <p> L'attributo <code>labelLongitudine</code> visualizza la scritta 'Longitudine:' per l'inserimento della longitudine dell'area.
 * <p> L'attributo <code>label</code> visualizza la scritta 'Inserimento aree:' per la sezione di inserimento delle aree.
 * 
 * <p> L'attributo <code>nome</code> è un campo di testo (JTextField) per l'inserimento del nome del centro di monitoraggio.
 * <p> L'attributo <code>indirizzo</code> è un campo di testo per l'inserimento dell'indirizzo del centro di monitoraggio.
 * <p> L'attributo <code>nomeArea</code> è un campo di testo per l'inserimento del nome dell'area geografica.
 * <p> L'attributo <code>latitudine</code> è un campo di testo per l'inserimento della latitudine dell'area geografica.
 * <p> L'attributo <code>longitudine</code> è un campo di testo per l'inserimento della longitudine dell'area geografica.
 * 
 * <p> L'attributo <code>preNomeArea</code> punta all'ultimo campo di testo relativo al nome dell'area inserito
 * (inizialmente preNomeArea e nomeArea puntano allo stesso oggetto).
 * <p> L'attributo <code>preLatitudine</code> punta all'ultimo campo di testo relativo alla latitudine inserita
 * (inizialmente preLatitudine e latitudine puntano allo stesso oggetto).
 * <p> L'attributo <code>preLongitudine</code> punta all'ultimo campo di testo relativo alla longitudine inserita
 * (inizialmente preLongitudine e longitudine puntano allo stesso oggetto).
 * 
 * <p> L'attributo <code>aggiungiArea</code> è un pulsante (JButton) che consente di aggiungere
 * ulteriori campi di testo per l'inserimento di altre aree.
 * <p> L'attributo <code>invio</code> è un pulsante che conferma le informazioni inserite e procede con la creazione del centro.
 * <p> L'attributo <code>indietro</code> è un pulsante che permette di tornare alla schermata precedente.
 * 
 * <p> L'attributo <code>count</code> è un contatore intero che tiene traccia del numero di aree aggiuntive inserite.
 * <p> L'attributo <code>listaAree</code> è una lista (LinkedList) che contiene tutte le aree inserite.
 */
private JPanel panel;
private JLabel labelNome;
private JLabel labelIndirizzo;
private JLabel labelNomeArea;
private JLabel labelLatitudine;
private JLabel labelLongitudine;
private JLabel label;

private JTextField nome;
private JTextField indirizzo;
private JTextField nomeArea;
private JTextField latitudine;
private JTextField longitudine;
private JTextField preNomeArea;
private JTextField preLatitudine;
private JTextField preLongitudine;

private JButton aggiungiArea;
private JButton invio;
private JButton indietro;

private int count = 0;
private LinkedList<Area> listaAree;
/**
 *  memorizza l'istanza della classe CentroMonitoraggio creata alla fine dell'operazione.
 */
private static CentroMonitoraggio cm;

    
    /**
    * Costruttore della classe FrameCreaCentro. Inizializza la finestra per la
    * creazione di un nuovo centro di monitoraggiopermettendo l'inserimento del nome, 
    * dell'indirizzo, e delle aree associate al centro.
    */
    public FrameCreaCentro(){
        
        super("Creazione di un nuovo centro di monitoraggio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        labelNome= new JLabel("Nome:");
        labelIndirizzo= new JLabel("Inidirizzo:");
        labelNomeArea= new JLabel("Nome luogo:");
        labelLatitudine= new JLabel("Latitudine:");
        labelLongitudine= new JLabel("Longitudine:");
        label= new JLabel("Inserimento aree");
        nome= new JTextField(10);
        indirizzo= new JTextField(10);
        nomeArea= new JTextField(10);
        latitudine= new JTextField(10);
        longitudine= new JTextField(10);
        aggiungiArea= new JButton("+");
        invio= new JButton("Invio");
        indietro=new JButton("Indietro");
        listaAree= new LinkedList<Area>();
        
        panel= new JPanel();
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(panel)); 
        gbc.gridx=0;
        gbc.gridy=0;
        panel.add(labelNome,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        panel.add(nome,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(labelIndirizzo,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        panel.add(indirizzo,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(label,gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        panel.add(labelNomeArea,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        panel.add(nomeArea,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        panel.add(labelLatitudine,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        panel.add(latitudine,gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        panel.add(labelLongitudine,gbc);
        
        gbc.gridx=1;
        gbc.gridy=5;
        panel.add(longitudine,gbc);
        
        gbc.gridx=2;
        gbc.gridy=2;
        panel.add(aggiungiArea,gbc);
        
        gbc.gridx=3;
        gbc.gridy=2;
        panel.add(invio,gbc);
        
        gbc.gridx=2;
        gbc.gridy=3;
        panel.add(indietro,gbc);
        
        preNomeArea=nomeArea;
        preLatitudine=latitudine;
        preLongitudine=longitudine;
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(ClientCM.centroGiaEsistente(nome.getText())){
                        JOptionPane.showMessageDialog(null, "ERRORE: Il nome del centro è già utilizzato, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(!nome.getText().equals("") && !indirizzo.getText().equals("")){
                    
                            if(!preNomeArea.getText().equals("") && !preLatitudine.getText().equals("") && !preLongitudine.getText().equals("")){
                                Area a= new Area(preNomeArea.getText(), Double.parseDouble(preLatitudine.getText()), Double.parseDouble(preLongitudine.getText()));
                                listaAree.add(a);

                                try {
                                    cm= new CentroMonitoraggio(nome.getText(),indirizzo.getText(),listaAree);
                                    Operatore.registraCentroAree(cm);

                                } catch (IOException ex) {
                                    Logger.getLogger(FrameCreaCentro.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(FrameCreaCentro.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                JOptionPane.showMessageDialog(null, "Creazione avvenuta con successo", "CREAZIONE COMPLETATA", JOptionPane.INFORMATION_MESSAGE);
                                ind();
                            }else{
                                JOptionPane.showMessageDialog(null, "ERRORE: Inserire i dati dell'area corrente prima di effettuare nuove aggiunte", "AGGIUNTA NEGATA", JOptionPane.ERROR_MESSAGE);

                            }
                    
                        }else{
                            JOptionPane.showMessageDialog(null, "ERRORE: Inserire nome e indirizzo prima di proseguire", "IMPOSSIBILE PROSEGUIRE", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FrameCreaCentro.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });
        
        aggiungiArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //prima di effettuare l'aggiunta controllo se i dati dell'area precedente sono stati inseriti
                
                if(!preNomeArea.getText().equals("") && !preLatitudine.getText().equals("") && !preLongitudine.getText().equals("")){
                    JLabel ln= new JLabel("Nome luogo:");
                    JLabel llat=new JLabel("Latitudine:");
                    JLabel llon= new JLabel("Longitudine:");

                    JTextField n= new JTextField(10);
                    JTextField lat= new JTextField(10);
                    JTextField lon= new JTextField(10);
                    
                    Area a= new Area(preNomeArea.getText(), Double.parseDouble(preLatitudine.getText()), Double.parseDouble(preLongitudine.getText()));
                    listaAree.add(a);
                    preNomeArea=n;
                    preLatitudine=lat;
                    preLongitudine=lon;
                    

                    gbc.gridx=0;
                    gbc.gridy=6+(3*count);
                    panel.add(ln,gbc);

                    gbc.gridx=1;
                    gbc.gridy=6+(3*count);
                    panel.add(n,gbc);

                    gbc.gridx=0;
                    gbc.gridy=7+(3*count);
                    panel.add(llat,gbc);

                    gbc.gridx=1;
                    gbc.gridy=7+(3*count);
                    panel.add(lat,gbc);

                    gbc.gridx=0;
                    gbc.gridy=8+(3*count);
                    panel.add(llon,gbc);

                    gbc.gridx=1;
                    gbc.gridy=8+(3*count);
                    panel.add(lon,gbc);

                    panel.revalidate();
                    panel.repaint();

                    count++;
                }else{
                    JOptionPane.showMessageDialog(null, "ERRORE: Inserire i dati dell'area corrente prima di effettuare nuove aggiunte", "AGGIUNTA NEGATA", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ind();
                
            }
        });
        
        setVisible(true);
    }
    /**
    * Determina se la schermata precedente era il menu principale o la schermata di registrazione,
    * e naviga alla schermata corrispondente.
    */
    private void ind(){
        if(FrameRegistrazioneOperatore.isForRegistration){
            tornaAllaRegistrazione();
        }else{
            tornaAlMenu();
        }
    }
    /**
     * metodo che ritorna alla schermata di menu
     */
    private void tornaAlMenu(){
        FrameMenu menu;
        if(FrameAccessoOperatore.accessoAvvenuto()){
            menu=FrameAccessoOperatore.getframeMenu();
        }else{
            menu=FrameRegistrazioneOperatore.getframeMenu();
        }

        menu.setVisible(true);
        setVisible(false);
    }
    /**
     * metodo che ritorna alla schermata di registrazione
     */
    private void tornaAllaRegistrazione(){
        FrameRegistrazioneOperatore fr=FrameOperatore.getFrameRegistrazione();
        
        fr.setVisible(true);
        setVisible(false);
    }
    
    /**
     * metodo che restituisce il centro di monitoraggio creato 
     * @return il centro di monitoraggio creato
     */
    public static CentroMonitoraggio getCentroMonitoraggioCreato(){
        return cm;
    }
}
