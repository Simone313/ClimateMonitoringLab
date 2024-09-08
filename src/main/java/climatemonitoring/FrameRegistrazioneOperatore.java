/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

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


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 *
 * @author simo0
 */
public class FrameRegistrazioneOperatore extends JFrame{
    /**
     * <p> l'attributo <code>isForRegistration</code> è un booleano che risulta true se l'apertura del frame menu è derivata da una registrazione 
     * <p> l'attributo <code>nuovoCentroCreato</code> è un booleano che risulta true se durante la registrazione è stato creato un nuovo centro
     * <p> l'attributo <code>nomejl</code> visualizza la scritta 'Nome:'
     * <p> l'attributo <code>cognomejl</code> visualizza la scritta 'Cognome:'
     * <p> l'attributo <code>passwordjl</code> visualizza la scritta 'Password:'
     * <p> l'attributo <code>codicefiscalejl</code> visualizza la scritta 'Codice Fiscale:'
     * <p> l'attributo <code>parolarecuperoPasswordjl</code> visualizza la scritta 'Parola Recupero Password:'
     * <p> l'attributo <code>emailjl</code> visualizza la scritta 'Email:'
     * <p> l'attributo <code>centromonitoraggiojl</code> visualizza la scritta 'Centro di monitoraggio associato:'
     * <p> l'attributo <code>nome</code> permette di inserire il nome dell'operatore
     * <p> l'attributo <code>cognome</code> permette di inserire il cognome dell'operatore
     * <p> l'attributo <code>password</code> permette di inserire la password dell'operatore
     * <p> l'attributo <code>codicefiscale</code> permette di inserire il codice fiscale dell'operatore
     * <p> l'attributo <code>parolarecuperoPassword</code> permette di inserire la parola per il recupero della password dell'operatore
     * <p> l'attributo <code>email</code> permette di inserire l'email dell'operatore
     * <p> l'attributo <code>centromonitoraggio</code> permette di inserire il centro di monitoraggio da associare all'operatore
     * <p> l'attributo <code>creaNuovoCentro</code> è un button che permette di creare un nuovo centro
     * <p> l'attributo <code>invia</code> è un button che permette di confermare le informazioni fornite e proseguire con la registrazione
     * <p> l'attributo <code>menu</code> viene tuilizzato per salvare l'istanza della classe FrameMenu che si va a creare al termine dell'operazione di registrazione
     * <p> l'attributo <code>op</code> è l'oggetto di classe Operatore che viene istanziato al termina della registrazione
     */
    public static boolean isForRegistration=false;

    private boolean nuovoCentroCreato=false;
    private JLabel nomejl;
    private JLabel cognomejl;
    private JLabel passwordjl;
    private JLabel codicefiscalejl;
    private JLabel parolaRecuperoPasswordjl;
    private JLabel emailjl;
    private JLabel centromonitoraggiojl;
    private JTextField nome;
    private JTextField cognome;
    private JPasswordField password;
    private JTextField codicefiscale;
    private JTextField parolaRecuperoPassword;
    private JTextField email;
    private JComboBox centromonitoraggio;
    private JButton creaNuovoCentro;
    private JButton invia;
    private static FrameMenu menu;
    
    private static Operatore op;

    /**
     *
     * @throws SQLException
     * @throws RemoteException
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
                        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
                        String user = "postgres";
                        String pass = "simone03";
        
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
                isForRegistration=true;
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
    
    
    private String arrayCharToString(char[] c){
        String ris= Arrays.toString(c);
        ris= ClientCM.rimuovi(ris, '[');
        ris= ClientCM.rimuovi(ris, ']');
        ris=ClientCM.rimuovi(ris, ',');
        ris=ClientCM.rimuovi(ris, ' ');
        return ris;
    }
    
    /**
     *
     * @return
     */
    public static FrameMenu getframeMenu(){
        return menu;
    }
    
    /**
     *
     * @return
     */
    public static Operatore getOperatore() {
        return op;
    }
    
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
