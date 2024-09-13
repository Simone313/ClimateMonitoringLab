/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;


import static com.climatemonitoring.climatemonitoringlab.FrameRegistrazioneOperatore.registrazioneAvvenuta;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *
 * La classe ServerCM serve per accedere direttamente al database e fornisce 
 * una serie di servizi a tutti i client
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class ServerCM extends UnicastRemoteObject implements ServerInterface{
    
    /**
     * Costruttore della classe ServerCM
     * @throws RemoteException RemoteException
     */
    public ServerCM() throws RemoteException {}
    /**
    * <p> L'attributo <code>url</code> contiene una serie di informazioni per la connessione con il database, inclusi il protocollo, l'indirizzo del server, e il numero di porta.
    */
   private static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";

   /**
    * <p> L'attributo <code>user</code> contiene il nome dell'utente utilizzato per accedere al database.
    */
   private static String user = "postgres";

   /**
    * <p> L'attributo <code>password</code> contiene la password necessaria per accedere al database.
    */
   private static String password = "climatemonitoring";

   /**
    * <p> L'attributo <code>conn</code> viene utilizzato per gestire e aprire una connessione con il database.
    */
   private static Connection conn;


    /**
     * metodo main del server
     * @param args arguments
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public static void main(String[] args) throws SQLException, RemoteException{
        conn = DriverManager.getConnection(url, user, password);
        
        
        
        ServerCM stub= new ServerCM();
        Registry registro=LocateRegistry.createRegistry(1099);
        registro.rebind("ClimateMonitoring", stub);
        
        JFrame frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - frame.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        JLabel labeluser = new JLabel("Inserisci username: ");
        JLabel labelpsw = new JLabel("Inserisci password: ");
        JLabel labelhost = new JLabel("Inserisci host (es. 127.0.0.1): ");
        JTextField usertxt= new JTextField(10);
        JTextField pswtxt= new JTextField(10);
        JTextField hosttxt= new JTextField(10);
        JButton invio= new JButton("Invio");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx=0;
        gbc.gridy=0;
        frame.add(labeluser,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        frame.add(labelpsw,gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        frame.add(labelhost,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        frame.add(usertxt,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        frame.add(pswtxt,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        frame.add(hosttxt,gbc);
        
        gbc.gridx=2;
        gbc.gridy=4;
        frame.add(invio,gbc);
        
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user=usertxt.getText();
                password= pswtxt.getText();
                url="jdbc:postgresql://"+hosttxt.getText()+":5432/postgres";
            }
        });
        
        frame.setVisible(true);
    }
    /**
     * metodo che restituise la lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @param op Operatore di riferimento
     * @return lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @throws RemoteException RemoteException
     */
    @Override
    public LinkedList<String> getListaNomiCentromonitoraggio(Operatore op) throws RemoteException {
        try {
            LinkedList<String> nomi= new LinkedList<String>();
            Statement selStmt = conn.createStatement();
            String nomeCentro=op.getCentroMonitoraggio().getNome();
            String stmt = "SELECT * FROM centromonitoraggio WHERE \"NomeCentro\" = '"+nomeCentro+"'";
            
            ResultSet reS= selStmt.executeQuery(stmt);
            while(reS.next()){
                nomi.add(reS.getString("luogo"));
            }
            
            return nomi;
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * metodo che restituisce l'oggetto operatore dato il nome, cognome e password
     * @param nom è il nome dell'operatore
     * @param con è il cognome dell'operatore
     * @param pass è la password dell'operatore
     * @return oggetto operatore
     * @throws RemoteException RemoteException
     */
    @Override
    public Operatore getOperatore(String nom, String con, String pass) throws RemoteException {
        Operatore o = null;
        try {
            
            Statement selStmt = conn.createStatement();
            String stmt= "SELECT * "
            + "FROM operatori "
            + "WHERE \"nome\" = '"+nom+"' AND \"cognome\" = '"+con+"' AND \"password\" = '"+pass+"'";
           
            
            ResultSet reStmt = selStmt.executeQuery(stmt);
            
            while(reStmt.next()){
                o= new Operatore(reStmt.getString("nome"), reStmt.getString("cognome"), reStmt.getString("cf"), reStmt.getString("email"), reStmt.getString("id"), reStmt.getString("password"), getCentroMonitoraggio(reStmt.getString("centromonitoraggio")), reStmt.getString("recuperopassword"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    /**
     * metodo che imposta i parametri climatici passati come parametri
     * @param select è il luogo di riferimento
     * @param nomeCentro è il nome del centro di monitoraggio al quale appartiene il luogo
     * @param ven è il parametro del vento
     * @param umi è il parametro dell'umidità
     * @param pre è il parametro della pressione
     * @param temp è il parametro della temperatura
     * @param precipit è il parametro delle precipitazioni
     * @param aG è il parametro dell'altezza dei ghiacciai
     * @param mG è il parametro della massa dei ghiacciai
     * @param not sono le note inserite dall'operatore
     * @throws RemoteException RemoteException
     */
    @Override
    public void setParametriclimatici(String select, String nomeCentro, int ven, int umi, int pre, int  temp, int precipit, int aG, int mG, String not) throws RemoteException {
        
        try {
            LocalDate ora= LocalDate.now();
            String data= (Date.valueOf(ora).toString());
            boolean luogoGiaPresente=false;
            Statement selStmt = conn.createStatement();
            String stmt="SELECT * FROM parametriclimatici";
            ResultSet reSet= selStmt.executeQuery(stmt);
            while(reSet.next()){
                if(reSet.getString("Luogo").equals(select)){
                    luogoGiaPresente=true;
                }
            }
            
            if(luogoGiaPresente){
                stmt="UPDATE parametriclimatici SET \"Centro\" = '"+nomeCentro+"', \"Vento\" = '"+ven+"', \"Umidità\" = '"+umi+"', \"Pressione\" = '"+pre+"', \"Temperatura\" = '"+temp+"', \"Precipitazioni\" = '"+precipit+"', \"Altezza Ghiacciai\" = '"+aG+"', \"Massa Ghiacciai\" = '"+mG+"', \"Note\" = '"+not+"' WHERE \"Luogo\" = '"+select+"'";
            }else{
                stmt = "INSERT INTO parametriclimatici VALUES ('"+nomeCentro+"','"+select+"','"+data+"','"+ven+"','"+umi+"','"+pre+"','"+temp+"','"+precipit+"','"+aG+"','"+mG+"','"+not+"')";
            }
            
            
            selStmt.executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metodo che restituisce il nome del centro di monitoraggio associato al nome
     * @param n nome del centro di monitoraggio
     * @return nome del centro di monitoraggio associato al nome
     * @throws SQLException SQLException
     */
    private static CentroMonitoraggio getCentroMonitoraggio(String n) throws SQLException{
        CentroMonitoraggio cm = null;
        Statement selStmt= conn.createStatement();
        String stmt="SELECT * "
                + "FROM centromonitoraggio "
                + "WHERE \"NomeCentro\" = '"+n+"'";
        ResultSet reS= selStmt.executeQuery(stmt);
        LinkedList<Area> aree= new LinkedList<Area>();
        if(reS.isBeforeFirst()){
            String indirizzo = null;
            while(reS.next()){
                indirizzo= reS.getString("indirizzo");
                Double lat=Double.parseDouble(reS.getString("coordinate").split(",")[0]);
                Double lon=Double.parseDouble(reS.getString("coordinate").split(",")[1]);

                aree.add(new Area(reS.getString("luogo"),lat,lon));
            
            }
            cm = new CentroMonitoraggio(n,indirizzo, aree);
        }
        
        
        
        return cm;
    }
    /**
     * metodo che aggiorna il nome e le coordinate di un luogo
     * @param op l'oggetto operatore di riferimento
     * @param nome nuovo nome dell'area
     * @param coordinate coordinate dell'area
     * @param nomeAreaCercata nome precedente dell'area
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    @Override
    public void updateNomeCoordinateCentroMonitoraggio(Operatore op, String nome, String coordinate, String nomeAreaCercata) throws RemoteException, SQLException {
        Statement selStmt = conn.createStatement();
        String nomeCorrente=op.getCentroMonitoraggio().getNome();
        String stmt = "UPDATE centromonitoraggio "
                + "SET \"luogo\" = '" + nome + "', \"coordinate\" = '"+ coordinate+"' "
                + "WHERE \"luogo\" = '" + nomeAreaCercata + "'";

        selStmt.executeUpdate(stmt);
    }
    /**
     * metodo che restituisce true se il codice fiscale è già presente nel database, false altrimenti
     * @param cf codice fiscale dell'operatore
     * @return true se il codice fiscale è già presente nel database, false altrimenti
     * @throws RemoteException RemoteException
     */
    @Override
    public boolean operatoriEsistenti(String cf) throws RemoteException {
        boolean risposta=false;
        try {
            Statement selStmt = conn.createStatement();
            String stmt= "SELECT * "
                    + "FROM operatori "
                    + "WHERE \"cf\" = '"+cf+"'";
            ResultSet reStmt= selStmt.executeQuery(stmt);
            risposta= reStmt.isBeforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return risposta;
    }
    /**
     * metodo che resittuisce true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @return true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @throws RemoteException RemoteException
     */
    @Override
    public boolean centrimonitoraggioEsistenti() throws RemoteException {
        boolean risposta=false;
        try {
            Statement selStmt = conn.createStatement();
            String stmt= "SELECT *"
                + " FROM centromonitoraggio";
            ResultSet reStmt= selStmt.executeQuery(stmt);
            risposta= reStmt.isBeforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return risposta;
    
    }
    
    /**
     * metodo che restituisce una lista contenente il nome di tutti i centri di monitoraggio creati
     * @return lista contenente il nome di tutti i centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    @Override
    public LinkedList<String> getCentriMonitoraggioCreati()throws RemoteException{
        try {
            LinkedList<String> CentroMonitoraggio_dati= new LinkedList<String>();
            Statement selStmt = conn.createStatement();
            String stmt= "SELECT *"
                    + "FROM centromonitoraggio";
            ResultSet reStmt= selStmt.executeQuery(stmt);
            while(reStmt.next()) {
                CentroMonitoraggio_dati.add(reStmt.getString("NomeCentro")+" "+reStmt.getString("indirizzo")+" "+reStmt.getString("luogo")+" "+reStmt.getString("coordinate"));
            }
            return CentroMonitoraggio_dati;
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * metodo che restituisce true se l'autenticazione è andata a buon fine, false altrimenti
     * @param n nome dell'operatore da controllare
     * @param c cognome dell'operatore da controllare
     * @param pa password dell'operatore da controllare
     * @return true se l'autenticazione è andata a buon fine, false altrimenti
     * @throws RemoteException RemoteException
     */
    @Override
    public boolean controlloCredenzialiAccesso(String n, String c, String pa)throws RemoteException{
        try {
            LinkedList<String> OperatoriRegistrati_dati= new LinkedList<String>();
            Statement selStmt = conn.createStatement();
            String stmt= "SELECT *"
                    + "FROM operatori";
            ResultSet reStmt= selStmt.executeQuery(stmt);
            while(reStmt.next()) {
                OperatoriRegistrati_dati.add(reStmt.getString("nome")+" "+reStmt.getString("cognome")+" "+reStmt.getString("cf")+" "+reStmt.getString("email")+" "+reStmt.getString("id")+" "+reStmt.getString("password")+" "+reStmt.getString("centromonitoraggio")+" "+reStmt.getString("recuperoPassword"));
            }
            
            boolean bool=false;
            for(int i=0;i<OperatoriRegistrati_dati.size();i++) {
                String[] rigaSplit= OperatoriRegistrati_dati.get(i).split(" ");
                if(n.equalsIgnoreCase(rigaSplit[0]) && c.equalsIgnoreCase(rigaSplit[1])  && pa.equals(rigaSplit[5])) {
                    bool=true;

                }
            }
            return bool;
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * metodo che restituisce la lista delle aree del centro
     * @param s array il cui primo elemento è il nome del centro di monitoraggio
     * @return lista delle aree del centro
     * @throws SQLException SQLException
     */
    public static LinkedList<Area> toAreaListStatic(String[] s) throws SQLException{
            LinkedList<Area> risposta = new LinkedList<Area>();
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement selStmt = conn.createStatement();
            String stmt = "SELECT * " +
              "FROM centromonitoraggio " +
              "WHERE \"NomeCentro\" = '" + s[0] + "'";

            ResultSet reStmt= selStmt.executeQuery(stmt);
            while(reStmt.next()){
                Double lat=Double.parseDouble(reStmt.getString("coordinate").split(",")[0]);
                Double lon=Double.parseDouble(reStmt.getString("coordinate").split(",")[1]);
                        
                risposta.add(new Area(reStmt.getString("luogo"),lat,lon));
            }
            return risposta;
        }
    /**
     * metodo che restituisce la lista delle aree del centro
     * @param s array il cui primo elemento è il nome del centro di monitoraggio
     * @return lista delle aree del centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    @Override
    public LinkedList<Area> toAreaList(String[] s) throws SQLException, RemoteException {
        LinkedList<Area> risposta = new LinkedList<Area>();
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement selStmt = conn.createStatement();
        String stmt = "SELECT * " +
          "FROM centromonitoraggio " +
          "WHERE \"NomeCentro\" = '" + s[0] + "'";

        ResultSet reStmt= selStmt.executeQuery(stmt);
        while(reStmt.next()){
            Double lat=Double.parseDouble(reStmt.getString("coordinate").split(",")[0]);
            Double lon=Double.parseDouble(reStmt.getString("coordinate").split(",")[1]);

            risposta.add(new Area(reStmt.getString("luogo"),lat,lon));
        }
        return risposta;    
    }
    /**
     * metodo che restituisce una stringa contenente tutti i centri di monitoraggio creati
     * @return stringa contenente tutti i centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    @Override
    public String visualizzaCentri() throws RemoteException{
        try {
            LinkedList<String> CentroMonitoraggio_dati= new LinkedList<String>();
            Statement selStmt = conn.createStatement();
            String stmt= "SELECT *"
                    + "FROM centromonitoraggio";
            ResultSet reStmt= selStmt.executeQuery(stmt);
            while(reStmt.next()) {
                CentroMonitoraggio_dati.add(reStmt.getString("NomeCentro")+" "+reStmt.getString("indirizzo")+" "+reStmt.getString("luogo")+" "+reStmt.getString("coordinate"));
            }
            String ret="";
            for(int i=0;i<CentroMonitoraggio_dati.size();i++) {
                ret=ret+CentroMonitoraggio_dati.get(i).toString()+"\n";
                
            }
            return ret;
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * metodo che va a modificare il nome del centro di monitoraggio
     * @param op operatore di riferimento
     * @param nuovoNome nuovo nome da assegnare al centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    @Override
    public void modificaNomeCentro(Operatore op, String nuovoNome) throws SQLException, RemoteException{
        Statement selStmt = conn.createStatement();
        String nomeCorrente=op.getCentroMonitoraggio().getNome();
        String stmt = "UPDATE centromonitoraggio "
        + "SET \"NomeCentro\" = '" + nuovoNome + "' "
        + "WHERE \"NomeCentro\" = '" + nomeCorrente + "'";

        selStmt.executeUpdate(stmt);
        
        selStmt = conn.createStatement();
        String cfOperatore=op.getCodiceFiscale();
        stmt = "UPDATE operatori "
        + "SET \"centromonitoraggio\" = '" + nuovoNome + "' "
        + "WHERE \"cf\" = '" + cfOperatore + "'";

        selStmt.executeUpdate(stmt);
    }
    /**
     * metodo che va a modificare l'indirizzo del centro di monitoraggio
     * @param op operatore di riferimento
     * @param nuovoIndirizzo nuovo indirizzo da assegnare al centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    @Override
    public void modificaIndirizzoCentro(Operatore op, String nuovoIndirizzo) throws SQLException, RemoteException{
        
        Statement selStmt = conn.createStatement();
        String nomeCorrente=op.getCentroMonitoraggio().getNome();
        String stmt = "UPDATE centromonitoraggio "
        + "SET \"indirizzo\" = '" + nuovoIndirizzo + "' "
        + "WHERE \"NomeCentro\" = '" + nomeCorrente + "'";

        selStmt.executeUpdate(stmt);
    }
    /**
     * metodo che restituisce le informazioni relative all'area cercata per nome
     * @param nome nome dell'area cercata
     * @return informazioni relative all'area cercata
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws java.rmi.RemoteException RemoteException
     */
    @Override
    public String cercaAreaGeografica(String nome) throws IOException, SQLException, RemoteException {
        Statement st= conn.createStatement();
        String stmt = "SELECT *"
                + " FROM parametriclimatici "
                + " WHERE \"Luogo\" = '" + nome + "'";


        ResultSet r= st.executeQuery(stmt);
        boolean luoghiTrovati=false;
        if(r.isBeforeFirst()){ // controlliamo se il result set coontiene delle righe 
            stmt= "SELECT *"
                + "FROM centromonitoraggio join parametriclimatici on \"luogo\"=\"Luogo\""
                + "WHERE \"luogo\"='"+nome+"'";
            r=st.executeQuery(stmt);
            
            String risp="";
            while(r.next()){
                luoghiTrovati=true;
                String luogo=r.getString("luogo");
                String coordinate=r.getString("coordinate");
                risp=risp+luogo+" "+coordinate+"\n   Vento: "+r.getInt("Vento")+"\n   Umidità: "+r.getInt("Umidità")+"\n   Pressione: "+ r.getInt("Pressione")+"\n   Temperatura:"+r.getInt("Temperatura")+"\n   Precipitazioni: "+r.getInt("Precipitazioni")+"\n   Altezza dei Ghiacciai: "+r.getInt("Altezza Ghiacciai")+"\n   Massa dei Ghiacciai: "+r.getInt("Massa Ghiacciai")+"\n   Note: "+r.getString("Note")+"\n";
                st= conn.createStatement();
                stmt="SELECT * "
                        + "FROM geonames "
                        + "WHERE \"nome\" = '"+luogo+"' AND \"coordinate\" != '"+coordinate+"'";
                ResultSet r2= st.executeQuery(stmt);

                while(r2.next()){
                    risp=risp+r2.getString("nome")+" "+r2.getString("coordinate");
                }
            }

            return risp;
        }else{
            stmt= "SELECT *"
                + "FROM geonames"
                + " WHERE \"nome\" = '" + nome + "'";
            r=st.executeQuery(stmt);

            String risp="";
            while(r.next()){
                luoghiTrovati=true;
                risp=risp+r.getString("nome")+" "+r.getString("coordinate")+"\n";
            }
            
            if(!luoghiTrovati){
                stmt= "SELECT *"
                + "FROM geonames"
                + " WHERE \"paese\" = '" + nome + "'";
                r=st.executeQuery(stmt);

                risp="";
                while(r.next()){
                    risp=risp+r.getString("nome")+" "+r.getString("coordinate")+"\n";
                }
            }
            
            return risp;
        }
    }
    /**
     * metodo che restituisce le informazioni relative all'area cercata per coordinate
     * @param lat latitudine dell'area da cercare
     * @param lon longitudine dell'area da cercare
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws java.rmi.RemoteException RemoteException
     */
    @Override
    public String cercaAreaGeografica(String lat, String lon) throws IOException, SQLException, RemoteException {
        Statement st= conn.createStatement();
            
        String stmt= "SELECT *"
                + "FROM geonames join parametriclimatici on \"nome\"=\"Luogo\""
                + "WHERE \"coordinate\" = '"+(lat+","+lon)+"'";
        ResultSet r=st.executeQuery(stmt);
        boolean k=false;
        String risp="";
        while(r.next()){
            k=true;
            risp=risp+r.getString("nome")+" "+r.getString("coordinate")+"\n   Vento: "+r.getInt("Vento")+"\n   Umidità: "+r.getInt("Umidità")+"\n   Pressione: "+ r.getInt("Pressione")+"\n   Temperatura:"+r.getInt("Temperatura")+"\n   Precipitazioni: "+r.getInt("Precipitazioni")+"\n   Altezza dei Ghiacciai: "+r.getInt("Altezza Ghiacciai")+"\n   Massa dei Ghiacciai: "+r.getInt("Massa Ghiacciai")+"\n   Note: "+r.getString("Note")+"\n";
        }
        
        if(!k){
            stmt= "SELECT *"
                + "FROM geonames "
                + "WHERE \"coordinate\" = '"+(lat+","+lon)+"'";
            r=st.executeQuery(stmt);
            while(r.next()){
                risp=risp+r.getString("nome")+" "+r.getString("coordinate");
            }
        }
        return risp;
    }
    /**
     * metodo che inserisce nella tabella operatori un nuovo operatore
     * @param op operatore del quale si desidera fare la registrazione
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    @Override
    public void registrazione(Operatore op) throws IOException, SQLException, RemoteException {
        Statement st= conn.createStatement();
        String stmt="INSERT INTO operatori VALUES('"+op.getNome()+"'"+","+"'"+op.getCognome()+"'"+","+"'"+op.getCodiceFiscale()+"'"+","+"'"+op.getMail()+"'"+","+"'"+op.getId()+"'"+","+"'"+op.getPsw()+"'"+","+"'"+op.getNomeCentroMonitoraggio()+"','"+op.getRecuperoPsw()+"')";

        st.executeUpdate(stmt);
    }
    /**
     * metodo che inserisce nella teballa dei centri di monitoraggio un nuovo centro
     * @param cm centro di monitoraggio del quale si desidera fare la registrazione
     * @param op Operatore che effettua la registrazione del centro
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    @Override
    public void registraCentroAree(CentroMonitoraggio cm, Operatore op) throws IOException, SQLException, RemoteException {
        for(int i=0;i<cm.getAree().size();i++){
            Statement st= conn.createStatement();
            String stmt="INSERT INTO centromonitoraggio VALUES('"+cm.getNome()+"','"+cm.getIndirizzo().replace(' ','_')+"','"+cm.getAree().get(i).getNome()+"','"+cm.getAree().get(i).getLatitudine()+","+cm.getAree().get(i).getLongitudine()+"')";
            st.executeUpdate(stmt);
        }
        
        Statement selStmt = conn.createStatement();
        String cfOperatore=op.getCodiceFiscale();
        String stmt = "UPDATE operatori "
        + "SET \"centromonitoraggio\" = '" + cm.getNome() + "' "
        + "WHERE \"cf\" = '" + cfOperatore + "'";

        selStmt.executeUpdate(stmt);
    }
    
    
    /**
     * metodo che restituisce la password dell'operatore se la parola di recupero corrisponde
     * @param nomeOperatore nome dell'operatore interessato
     * @param cognomeOperatore cognome dell'operatore interessato
     * @param parolaDiRecupero parola per il recupero della password
     * @return password recuperata
     * @throws RemoteException RemoteException
     */
    @Override
    public String recuperoPassword(String nomeOperatore, String cognomeOperatore,String parolaDiRecupero) throws RemoteException {
        String psw=null;
        try {
            Statement st= conn.createStatement();
            
            String stmt= "SELECT *"
                    + "FROM operatori "
                    + "WHERE \"nome\" = '"+nomeOperatore+"' AND \"cognome\" = '"+cognomeOperatore+"' AND \"recuperopassword\" = '"+parolaDiRecupero+"'";
            ResultSet r=st.executeQuery(stmt);
            while(r.next()){
                psw=r.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return psw;
    }
    
    /**
     * metodo che restituisce true se esiste già un centro con questo nome, false altrimenti
     * @param nome nome del centro che deve essere controllato
     * @return truee se esiste già un centro con questo nome, false altrimenti
     * @throws RemoteException RemoteException
     */
    @Override
    public boolean centroGiaEsistente(String nome) throws RemoteException {
        
        try {
            Statement st= conn.createStatement();
            
            String stmt= "SELECT *"
                    + "FROM centromonitoraggio "
                    + "WHERE \"NomeCentro\" = '"+nome+"'";
            ResultSet r=st.executeQuery(stmt);
            return r.isBeforeFirst();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return false;
    }
    
    
    
}
