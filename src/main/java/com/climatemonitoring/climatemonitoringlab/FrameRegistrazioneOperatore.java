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
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.LinkedList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.*;

/**
 * L'istanza della classe FrameRegistrazioneOperatore permette di inserire 
 * tutte le informazioni necessarie per la registrazione di un nuovo operatore
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class FrameRegistrazioneOperatore extends JFrame{
    /**
    * <p> L'attributo <code>registrazioneAvvenuta</code> è un booleano che risulta true se l'apertura del frame creaCentro è derivata da una registrazione.
    */
   public static boolean  registrazioneAvvenuta= false;
    /**
    * <p> L'attributo <code>isForRegistration</code> è un booleano che risulta true se l'apertura del frame menu è derivata da una registrazione.
    */
   public static boolean  isForRegistration= false;

   /**
    * <p> L'attributo <code>nuovoCentroCreato</code> è un booleano che risulta true se durante la registrazione è stato creato un nuovo centro.
    */
   private boolean nuovoCentroCreato = false;

   /**
    * <p> L'attributo <code>nomejl</code> visualizza la scritta 'Nome:'.
    */
   private JLabel nomejl;

   /**
    * <p> L'attributo <code>cognomejl</code> visualizza la scritta 'Cognome:'.
    */
   private JLabel cognomejl;

   /**
    * <p> L'attributo <code>passwordjl</code> visualizza la scritta 'Password:'.
    */
   private JLabel passwordjl;

   /**
    * <p> L'attributo <code>codicefiscalejl</code> visualizza la scritta 'Codice Fiscale:'.
    */
   private JLabel codicefiscalejl;

   /**
    * <p> L'attributo <code>parolaRecuperoPasswordjl</code> visualizza la scritta 'Parola Recupero Password:'.
    */
   private JLabel parolaRecuperoPasswordjl;

   /**
    * <p> L'attributo <code>emailjl</code> visualizza la scritta 'Email:'.
    */
   private JLabel emailjl;

   /**
    * <p> L'attributo <code>centromonitoraggiojl</code> visualizza la scritta 'Centro di monitoraggio associato:'.
    */
   private JLabel centromonitoraggiojl;

   /**
    * <p> L'attributo <code>nome</code> permette di inserire il nome dell'operatore.
    */
   private JTextField nome;

   /**
    * <p> L'attributo <code>cognome</code> permette di inserire il cognome dell'operatore.
    */
   private JTextField cognome;

   /**
    * <p> L'attributo <code>password</code> permette di inserire la password dell'operatore.
    */
   private JPasswordField password;

   /**
    * <p> L'attributo <code>codicefiscale</code> permette di inserire il codice fiscale dell'operatore.
    */
   private JTextField codicefiscale;

   /**
    * <p> L'attributo <code>parolaRecuperoPassword</code> permette di inserire la parola per il recupero della password dell'operatore.
    */
   private JTextField parolaRecuperoPassword;

   /**
    * <p> L'attributo <code>email</code> permette di inserire l'email dell'operatore.
    */
   private JTextField email;

   /**
    * <p> L'attributo <code>centromonitoraggio</code> permette di selezionare il centro di monitoraggio da associare all'operatore.
    */
   private JComboBox centromonitoraggio;

   /**
    * <p> L'attributo <code>creaNuovoCentro</code> è un JButton che permette di creare un nuovo centro.
    */
   private JButton creaNuovoCentro;

   /**
    * <p> L'attributo <code>invia</code> è un JButton che permette di confermare le informazioni fornite e proseguire con la registrazione.
    */
   private JButton invia;

   /**
    * <p> L'attributo <code>menu</code> viene utilizzato per salvare l'istanza della classe <code>FrameMenu</code> che si va a creare al termine dell'operazione di registrazione.
    */
   private static FrameMenu menu;

   /**
    * <p> L'attributo <code>op</code> è l'oggetto di classe <code>Operatore</code> che viene istanziato al termine della registrazione.
    */
   private static Operatore op;


    /**
     * Costruttore della classe FrameRegistrazioneOpeeratore. Innizializza la schermata
     * per l'inserimento delle informazioni per effettuare la registrazione
     * dell'operatore
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public FrameRegistrazioneOperatore() throws SQLException, RemoteException{
        super("Registrazione");
        LinkedList<String> list= ClientCM.getCentriMonitoraggioCreati();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        
        nomejl= new JLabel("Nome:");
        cognomejl= new JLabel("Cognome:");
        passwordjl= new JLabel("Password:");
        codicefiscalejl= new JLabel("Codice Fiscale:");
        parolaRecuperoPasswordjl= new JLabel("Parola per recupero password:");
        emailjl= new JLabel("Email:");
        centromonitoraggiojl= new JLabel("Centro di monitoraggio associato");
        nome= new JTextField(6);
        cognome= new JTextField();
        password= new JPasswordField();
        codicefiscale= new JTextField();
        parolaRecuperoPassword= new JTextField();
        email= new JTextField();
        centromonitoraggio= new JComboBox(list.toArray());
        creaNuovoCentro= new JButton("Crea nuovo centro");
        invia= new JButton("Invio");
        
        invia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean registrazioneCorretta=true;
                isForRegistration=true;
                
                
                
                //CONTROLLO LUNGHEZZA PASSWORD
                char[] p=password.getPassword();
                String psw= arrayCharToString(p);
                if(psw.length()<8){
                    JOptionPane.showMessageDialog(null, "Password troppo corta, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    password.setText("");
                    registrazioneCorretta=false;
                }
                
                
                
                // CONTROLLO DEL CODICE FISCALE
                boolean controlloCodiceFiscale=false;
                String cf= codicefiscale.getText().toUpperCase();
                if(cf.length()!=16){
                    controlloCodiceFiscale=true;
                } //Controllo sul numero di caratteri
                else {
                        String tmp= cf.substring(0, 6);
                        if(isNumero(tmp)) controlloCodiceFiscale=true; //controlla che i primi 6 caratteri non siano numeri
                        tmp=cf.substring(6,8);
                        if(!isNumero(tmp)) controlloCodiceFiscale=true; //controlla che il carattere 6 e 7 sono numeri
                        tmp=""+cf.charAt(8)+cf.charAt(11)+cf.charAt(15);
                        if(isNumero(tmp)) controlloCodiceFiscale=true; //controlla che il carattere 8, 11, 15 siano lettere
                        tmp=""+cf.charAt(9)+cf.charAt(10)+cf.charAt(12)+cf.charAt(13)+cf.charAt(14);
                        if(!isNumero(tmp)) controlloCodiceFiscale=true; //controlla che il carattere 9, 10, 12, 13, 14 sono numeri
                }
                
                if(controlloCodiceFiscale){
                    JOptionPane.showMessageDialog(null, "Codice fiscale non valido, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    codicefiscale.setText("");
                    registrazioneCorretta=false;
                }
                
                //CONTROLLO INDIRIZZO DI POSTA ELETTRONICA
                boolean controlloEmail=true;
                String ind=email.getText();
                for(int i=0;i<ind.length();i++) {
                        if(ind.charAt(i)=='@') {
                                controlloEmail=false;
                        }
                }
                
                if(controlloEmail){
                    JOptionPane.showMessageDialog(null, "Indirizzo di posta non valido, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    email.setText("");
                    registrazioneCorretta=false;
                }
                String ui=UUID.randomUUID().toString(); 
                
                CentroMonitoraggio cm = null;
                if(registrazioneCorretta){
                    try {
                        if(nuovoCentroCreato){
                            cm= FrameCreaCentro.getCentroMonitoraggioCreato();
                        } else{
                            String[] elementoSelezionato= ((String) centromonitoraggio.getSelectedItem()).split(" ");
                            cm = new CentroMonitoraggio(elementoSelezionato[0], elementoSelezionato[1], ClientCM.toAreaList(elementoSelezionato));

                        }
                        
        
                        /*Connection conn = DriverManager.getConnection(url, user, pass);
                        
                        Statement selStmt = conn.createStatement();
                        String stmt= "SELECT * "
                                + "FROM operatori "
                                + "WHERE \"cf\" = '"+cf+"'";
                        ResultSet reStmt= selStmt.executeQuery(stmt);*/
                        boolean k= ClientCM.operatoriEsistenti(cf);
                        if(k){
                            JOptionPane.showMessageDialog(null, "Operatore già esistente, riprova", "ERRORE", JOptionPane.ERROR_MESSAGE);
                            nome.setText("");
                            cognome.setText("");
                            password.setText("");
                            codicefiscale.setText("");
                            parolaRecuperoPassword.setText("");
                            email.setText("");
                        }else{
                            op= new Operatore(nome.getText(),cognome.getText(),cf,ind,ui,psw,cm,parolaRecuperoPassword.getText());
                            Operatore.registrazione(op);
                            JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo", "AVVISO", JOptionPane.INFORMATION_MESSAGE);
                            menu= new FrameMenu();
                            setVisible(false);
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(FrameRegistrazioneOperatore.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FrameRegistrazioneOperatore.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    
                }
                
                
            }
        });
        
        
        
        creaNuovoCentro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuovoCentroCreato=true;
                registrazioneAvvenuta=true;
                new FrameCreaCentro();
                setVisible(false);
            }
        });
        
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
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
        add(passwordjl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        add(password,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy=4;
        add(codicefiscalejl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        add(codicefiscale,gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        add(parolaRecuperoPasswordjl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=5;
        add(parolaRecuperoPassword,gbc);
        
        gbc.gridx=0;
        gbc.gridy=6;
        add(emailjl,gbc);
        
        gbc.gridx=1;
        gbc.gridy=6;
        add(email,gbc);
        
        gbc.gridx=0;
        gbc.gridy=7;
        add(centromonitoraggiojl,gbc);
        
        
        //Controllo se sono già stati creati dei centri in precedenza, altrimenti 
        //ne facciamo creare uno nuovo 
        /*Connection conn= DriverManager.getConnection(url,user,psw);
        Statement st= conn.createStatement();
        String stmt = "SELECT *"
                + " FROM centromonitoraggio";
        
        ResultSet reSt= st.executeQuery(stmt);*/
        
        boolean k= ClientCM.centrimonitoraggioEsistenti();
        if(k){
            gbc.gridx=1;
            gbc.gridy=7;
            add(centromonitoraggio,gbc);
            
            gbc.gridx=1;
            gbc.gridy=8;
            add(creaNuovoCentro, gbc);
            
            
        }else{
            gbc.gridx=1;
            gbc.gridy=7;
            add(creaNuovoCentro, gbc);
        }
        
        
        
        gbc.gridx=2;
        gbc.gridy=8;
        add(invia,gbc);
        setVisible(true);
        
    }
    
    /**
     * metodo che prende in ingresso  un array di caratteri e restituisce una stringa
     * contenente gli elementi dell'array senza spazi e virgole
     * @param c array di caratteri 
     * @return stringa risultante
     */
    private String arrayCharToString(char[] c){
        String ris= Arrays.toString(c);
        ris= ClientCM.rimuovi(ris, '[');
        ris= ClientCM.rimuovi(ris, ']');
        ris=ClientCM.rimuovi(ris, ',');
        ris=ClientCM.rimuovi(ris, ' ');
        return ris;
    }
    
    /**
     * metodo che restituisce l'istanza della classe FrameMenu creata
     * @return istanza della classe FrameMenu
     */
    public static FrameMenu getframeMenu(){
        return menu;
    }
    
    /**
     * metodo che restituisce l'istanza di Operatore che viene creata 
     * al termine dell'operazione di registrazione
     * @return istanza di Operatore
     */
    public static Operatore getOperatore() {
        return op;
    }
    /**
     * metodo che controlla se la stringa passata come parametro è un numero
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
    
}
