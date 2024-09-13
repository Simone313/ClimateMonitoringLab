/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * CliantCM è la classe che utilizza i metodi messi a disposizione del server per
 * l'accesso al database. Contiene anche il metodo main dal quale si avvia il 
 * programma per i client
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class ClientCM implements Serializable{
    /**
     * Costruttore della classe ClientCM 
     */
    public ClientCM(){}
    /**
     * <p> l'attributo <code>serialVersionUID</code> viene utilizzato per identificare la versione della classe
     * <p> l'attributo <code>stub</code> variabile del tipo dell'interfaccia implementata dal server utilizzata per richiamare i metodi remoti
     */
    private static final long serialVersionUID = 1L;
    private static ServerInterface stub;

    /**
     * metodo main dei client
     * @param args arguments
     * @throws RemoteException RemoteException
     * @throws NotBoundException NotBoundException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException{
        Registry registro=LocateRegistry.getRegistry(null);
        stub= (ServerInterface) registro.lookup("ClimateMonitoring");
        
        new FrameProg();
    }
    
    /**
     * metodo che restituise la lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @return restituise la lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @throws RemoteException RemoteException
     */
    public synchronized static LinkedList<String> getListaNomiCentromonitoraggio() throws RemoteException, SQLException{
        Operatore op = null;
        if(FrameAccessoOperatore.accessoAvvenuto()){
            //op= ClientCM.getOperatore(FrameAccessoOperatore.nom, FrameAccessoOperatore.con, FrameAccessoOperatore.pass);
            op= FrameAccessoOperatore.getOperatore();
        }else{
            op= FrameRegistrazioneOperatore.getOperatore();
        }
        return stub.getListaNomiCentromonitoraggio(op);
    }
    /**
     * metodo che restituisce l'oggetto operatore dato il nome, cognome e password
     * @param nom è il nome dell'operatore
     * @param con è il cognome dell'operatore
     * @param pass è la password dell'operatore
     * @return restituisce l'oggetto operatore
     * @throws RemoteException RemoteException
     */
    public synchronized static Operatore getOperatore(String nom, String con, String pass) throws RemoteException{
        
        return stub.getOperatore(nom, con, pass);
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
    public synchronized static void inserisciParametriClimatici(String select, String nomeCentro, int ven, int umi, int pre, int  temp, int precipit, int aG, int mG, String not) throws RemoteException{
        stub.setParametriclimatici(select, nomeCentro, ven, umi, pre, temp, precipit, aG, mG, not);
    }
    /**
     * metodo che aggiorna il nome e le coordinate di un luogo
     * @param op l'oggetto operatore di riferimento
     * @param nome nuovo nome dell'area
     * @param coordinate coordinate dell'area
     * @param nomeAreaCercata nome precedente dell'area
     * @throws RemoteException RemoteException
     * @throws SQLException  SQLException
     */
    public synchronized static void updateNomeCoordinateCentroMonitoraggio(Operatore op, String nome, String coordinate, String nomeAreaCercata) throws RemoteException, SQLException{
        stub.updateNomeCoordinateCentroMonitoraggio(op, nome, coordinate, nomeAreaCercata);
    }
    /**
     * metodo che restituisce true se il codice fiscale è già presente nel database, false altrimenti
     * @param cf codice fiscale dell'operatore
     * @return restituisce true se il codice fiscale è già presente nel database, false altrimenti
     * @throws RemoteException RemoteException
     */
    public synchronized static boolean operatoriEsistenti(String cf) throws RemoteException{
        return stub.operatoriEsistenti(cf);
    }
    /**
     * metodo che resittuisce true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @return resittuisce true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @throws RemoteException RemoteException
     */
    public synchronized static boolean centrimonitoraggioEsistenti() throws RemoteException{
        return stub.centrimonitoraggioEsistenti();
    }
    /**
     * metodo che restituisce una lista contenente il nome di tutti i centri di monitoraggio creati
     * @return restituisce una lista contenente il nome di tutti i centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    public synchronized static LinkedList<String> getCentriMonitoraggioCreati() throws RemoteException{
        return stub.getCentriMonitoraggioCreati();
    }
    /**
     * metodo che restituisce true se l'autenticazione è andata a buon fine, false altrimenti
     * @param n nome dell'operatore da controllare
     * @param c cognome dell'operatore da controllare
     * @param pa password dell'operatore da controllare
     * @return restituisce true se l'autenticazione è andata a buon fine, false altrimenti
     * @throws RemoteException RemoteException
     */
    public synchronized static boolean controlloCredenzialiAccesso(String n, String c, String pa)throws RemoteException{
        return stub.controlloCredenzialiAccesso(n, c, pa);
    }
    /**
     * metodo che restituisce la lista delle aree del centro
     * @param elementoSelezionato array il cui primo elemento è il nome del centro di monitoraggio
     * @return restituisce la lista delle aree del centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public synchronized static LinkedList<Area> toAreaList(String[] elementoSelezionato) throws SQLException, RemoteException {
        return stub.toAreaList(elementoSelezionato);
    }
    /**
     * metodo che restituisce una stringa contenente tutti i centri di monitoraggio creati
     * @return restituisce una stringa contenente tutti i centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    static synchronized String visualizzaCentri() throws RemoteException {
        return stub.visualizzaCentri();
    }
    /**
     * metodo che va a modificare il nome del centro di monitoraggio
     * @param op operatore di riferimento
     * @param nuovoNome nuovo nome da assegnare al centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public synchronized static void modificaNomeCentro(Operatore op, String nuovoNome) throws SQLException, RemoteException{
        stub.modificaNomeCentro(op, nuovoNome);
    }
    /**
     * metodo che va a modificare l'indirizzo del centro di monitoraggio
     * @param operatore operatore di riferimento
     * @param text nuovo indirizzo da assegnare al centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public static void modificaIndirizzoCentro(Operatore operatore, String text) throws SQLException, RemoteException {
        stub.modificaIndirizzoCentro(operatore, text);
    }
    /**
     * metodo che restituisce le informazioni relative all'area cercata per nome
     * @param nome nome dell'area cercata
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException IOException
     * @throws SQLException SQLException
     */
    public synchronized static String cercaAreaGeografica(String nome) throws IOException, SQLException {
        return stub.cercaAreaGeografica(nome);
    }
    /**
     * metodo che restituisce le informazioni relative all'area cercata per coordinate
     * @param lat latitudine dell'area da cercare
     * @param lon longitudine dell'area da cercare
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException IOException
     * @throws SQLException SQLException
     */
    public synchronized static String cercaAreaGeografica(String lat,String lon) throws IOException, SQLException {
        return stub.cercaAreaGeografica(lat, lon);
    }
    /**
     * metodo che rmiuove un carattere da una stringa
     * @param s stringa di partenza
     * @param c carattere da rimuovere
     * @return restituisce la stringa s senza il carattere c
     */
    static String rimuovi(String s, char c) {
		String r="";
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)!=c) {
				r=r+s.charAt(i);
			}
		}
		return r;
	}
    /**
     * metodo che inserisce nella tabella operatori un nuovo operatore
     * @param op operatore del quale si desidera fare la registrazione
     * @throws SQLException SQLException
     * @throws IOException IOException
     */
    public static void registrazione(Operatore op) throws SQLException, IOException, IOException{
        stub.registrazione(op);
    }
    /**
     * metodo che inserisce nella teballa dei centri di monitoraggio un nuovo centro
     * @param cm centro di monitoraggio del quale si desidera fare la registrazione
     * @param op Operatore che effettua la registrazione
     * @throws IOException IOException
     * @throws SQLException SQLException
     */
    public static void registraCentroAree(CentroMonitoraggio cm, Operatore op) throws IOException, SQLException {
        stub.registraCentroAree(cm,op);
    }
    
    /**
     * metodo che restituisce la password dell'operatore se la parola di recupero corrisponde
     * @param nomeOperatore nome dell'operatore interessato
     * @param cognomeOperatore cognome dell'operatore interessato
     * @param parolaDiRecupero parola per il recupero della password
     * @return password recuperata
     * @throws RemoteException RemoteException
     */
    public static String recuperoPassword(String nomeOperatore, String cognomeOperatore,String parolaDiRecupero) throws RemoteException{
        return stub.recuperoPassword(nomeOperatore, cognomeOperatore, parolaDiRecupero);
    }
    
    /**
     * metodo che restituisce true se esiste già un centro con questo nome, false altrimenti
     * @param nome nome del centro che deve essere controllato
     * @return restituisce true se esiste già un centro con questo nome, false altrimenti
     * @throws RemoteException RemoteException
     */
    public static boolean centroGiaEsistente(String nome) throws RemoteException {
        return stub.centroGiaEsistente(nome);
    }
    
}
