/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;


import java.awt.BorderLayout;
import java.awt.Toolkit;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
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
     *
     * @throws RemoteException
     */
    public ServerCM() throws RemoteException {}
    /**
	 * <p> l'attributo <code>url</code> contiene una serie di informazioni per la connessione con il database (protocollo, indirizzo del server, numero di porta, ecc...)
	 * <p> l'attributo <code>user</code> contiene il nome dell'utente del database
	 * <p> l'attributo <code>password</code> contiene la password per accedere al database
         * <p> l'attributo <code>conn</code> viene utilizzato per aprire una connessione con il database
	 */
    private static  String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static String user = "postgres";
    private static String password = "climatemonitoring";
    private static Connection conn;

    /**
     *
     * @param args
     * @throws SQLException
     * @throws RemoteException
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
        JLabel label = new JLabel("Server avviato correttamente!", SwingConstants.CENTER);
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    /**
     * 
     * @param op Operatore di riferimento
     * @return restituise la lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @throws RemoteException 
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
     * 
     * @param nom è il nome dell'operatore
     * @param con è il cognome dell'operatore
     * @param pass è la password dell'operatore
     * @return restituisce l'oggetto operatore
     * @throws RemoteException 
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
     * 
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
     * @throws RemoteException 
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
     * 
     * @param n nome del centro di monitoraggio
     * @return restituisce il nome del centro di monitoraggio associato al nome
     * @throws SQLException 
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
     * 
     * @param op l'oggetto operatore di riferimento
     * @param nome nuovo nome dell'area
     * @param coordinate coordinate dell'area
     * @param nomeAreaCercata nome precedente dell'area
     * @throws RemoteException
     * @throws SQLException 
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
     * 
     * @param cf codice fiscale dell'operatore
     * @return restituisce true se il codice fiscale è già presente nel database, false altrimenti
     * @throws RemoteException 
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
     * 
     * @return resittuisce true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @throws RemoteException 
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
     * 
     * @return restituisce una lista contenente il nome di tutti i centri di monitoraggio creati
     * @throws RemoteException 
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
     * 
     * @param n nome dell'operatore da controllare
     * @param c cognome dell'operatore da controllare
     * @param pa password dell'operatore da controllare
     * @return restituisce true se l'autenticazione è andata a buon fine, false altrimenti
     * @throws RemoteException 
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
     * 
     * @param s array il cui primo elemento è il nome del centro di monitoraggio
     * @return restituisce la lista delle aree del centro
     * @throws SQLException
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
     * 
     * @param s array il cui primo elemento è il nome del centro di monitoraggio
     * @return restituisce la lista delle aree del centro
     * @throws SQLException
     * @throws RemoteException 
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
     * 
     * @return restituisce una stringa contenente tutti i centri di monitoraggio creati
     * @throws RemoteException 
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
     * 
     * @param op operatore di riferimento
     * @param nuovoNome nuovo nome da assegnare al centro
     * @throws SQLException
     * @throws RemoteException 
     */
    @Override
    public void modificaNomeCentro(Operatore op, String nuovoNome) throws SQLException, RemoteException{
        Statement selStmt = conn.createStatement();
        String nomeCorrente=op.getCentroMonitoraggio().getNome();
        String stmt = "UPDATE centromonitoraggio "
        + "SET \"NomeCentro\" = '" + nuovoNome + "' "
        + "WHERE \"NomeCentro\" = '" + nomeCorrente + "'";

        selStmt.executeUpdate(stmt);
    }
    /**
     * 
     * @param op operatore di riferimento
     * @param nuovoIndirizzo nuovo indirizzo da assegnare al centro
     * @throws SQLException
     * @throws RemoteException 
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
     * 
     * @param nome nome dell'area cercata
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException
     * @throws SQLException 
     * @throws java.rmi.RemoteException 
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
     * 
     * @param lat latitudine dell'area da cercare
     * @param lon longitudine dell'area da cercare
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException
     * @throws SQLException 
     * @throws java.rmi.RemoteException 
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
     * 
     * @param op operatore del quale si desidera fare la registrazione
     * @throws IOException
     * @throws SQLException
     * @throws RemoteException 
     */
    @Override
    public void registrazione(Operatore op) throws IOException, SQLException, RemoteException {
        Statement st= conn.createStatement();
        String stmt="INSERT INTO operatori VALUES('"+op.getNome()+"'"+","+"'"+op.getCognome()+"'"+","+"'"+op.getCodiceFiscale()+"'"+","+"'"+op.getMail()+"'"+","+"'"+op.getId()+"'"+","+"'"+op.getPsw()+"'"+","+"'"+op.getNomeCentroMonitoraggio()+"','"+op.getRecuperoPsw()+"')";

        st.executeUpdate(stmt);
    }
    /**
     * 
     * @param cm centro di monitoraggio del quale si desidera fare la registrazione
     * @throws IOException
     * @throws SQLException
     * @throws RemoteException 
     */
    @Override
    public void registraCentroAree(CentroMonitoraggio cm) throws IOException, SQLException, RemoteException {
        for(int i=0;i<cm.getAree().size();i++){
            Statement st= conn.createStatement();
            String stmt="INSERT INTO centromonitoraggio VALUES('"+cm.getNome()+"','"+cm.getIndirizzo().replace(' ','_')+"','"+cm.getAree().get(i).getNome()+"','"+cm.getAree().get(i).getLatitudine()+","+cm.getAree().get(i).getLongitudine()+"')";
            st.executeUpdate(stmt);
        }
    }
    
    
    /**
     * 
     * @param nomeOperatore nome dell'operatore interessato
     * @param cognomeOperatore cognome dell'operatore interessato
     * @param parolaDiRecupero parola per il recupero della password
     * @return password recuperata
     * @throws RemoteException 
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
     * 
     * @param nome nome del centro che deve essere controllato
     * @return restituisce true se esiste già un centro con questo nome, false altrimenti
     * @throws RemoteException 
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
