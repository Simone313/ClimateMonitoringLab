/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;

import climatemonitoring.*;
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
     * <p> l'attributo <code>serialVersionUID</code> viene utilizzato per identificare la versione della classe
     * <p> l'attributo <code>stub</code> variabile del tipo dell'interfaccia implementata dal server utilizzata per richiamare i metodi remoti
     */
    private static final long serialVersionUID = 1L;
    private static ServerInterface stub;

    /**
     * 
     * @param args
     * @throws RemoteException
     * @throws NotBoundException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException{
        Registry registro=LocateRegistry.getRegistry(null);
        stub= (ServerInterface) registro.lookup("ClimateMonitoring");
        
        new FrameProg();
    }
    
    /**
     * 
     * @return restituise la lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @throws RemoteException 
     */
    public synchronized static LinkedList<String> getListaNomiCentromonitoraggio() throws RemoteException{
        Operatore op = null;
        if(FrameAccessoOperatore.accessoAvvenuto()){
            op= ClientCM.getOperatore(FrameAccessoOperatore.nom, FrameAccessoOperatore.con, FrameAccessoOperatore.pass);

        }else{
            op= FrameRegistrazioneOperatore.getOperatore();
        }
        return stub.getListaNomiCentromonitoraggio(op);
    }
    /**
     * 
     * @param nom è il nome dell'operatore
     * @param con è il cognome dell'operatore
     * @param pass è la password dell'operatore
     * @return restituisce l'oggetto operatore
     * @throws RemoteException 
     */
    public synchronized static Operatore getOperatore(String nom, String con, String pass) throws RemoteException{
        
        return stub.getOperatore(nom, con, pass);
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
    public synchronized static void inserisciParametriClimatici(String select, String nomeCentro, int ven, int umi, int pre, int  temp, int precipit, int aG, int mG, String not) throws RemoteException{
        stub.setParametriclimatici(select, nomeCentro, ven, umi, pre, temp, precipit, aG, mG, not);
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
    public synchronized static void updateNomeCoordinateCentroMonitoraggio(Operatore op, String nome, String coordinate, String nomeAreaCercata) throws RemoteException, SQLException{
        stub.updateNomeCoordinateCentroMonitoraggio(op, nome, coordinate, nomeAreaCercata);
    }
    /**
     * 
     * @param cf codice fiscale dell'operatore
     * @return restituisce true se il codice fiscale è già presente nel database, false altrimenti
     * @throws RemoteException 
     */
    public synchronized static boolean operatoriEsistenti(String cf) throws RemoteException{
        return stub.operatoriEsistenti(cf);
    }
    /**
     * 
     * @return resittuisce true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @throws RemoteException 
     */
    public synchronized static boolean centrimonitoraggioEsistenti() throws RemoteException{
        return stub.centrimonitoraggioEsistenti();
    }
    /**
     * 
     * @return restituisce una lista contenente il nome di tutti i centri di monitoraggio creati
     * @throws RemoteException 
     */
    public synchronized static LinkedList<String> getCentriMonitoraggioCreati() throws RemoteException{
        return stub.getCentriMonitoraggioCreati();
    }
    /**
     * 
     * @param n nome dell'operatore da controllare
     * @param c cognome dell'operatore da controllare
     * @param pa password dell'operatore da controllare
     * @return restituisce true se l'autenticazione è andata a buon fine, false altrimenti
     * @throws RemoteException 
     */
    public synchronized static boolean controlloCredenzialiAccesso(String n, String c, String pa)throws RemoteException{
        return stub.controlloCredenzialiAccesso(n, c, pa);
    }
    /**
     * 
     * @param elementoSelezionato array il cui primo elemento è il nome del centro di monitoraggio
     * @return restituisce la lista delle aree del centro
     * @throws SQLException
     * @throws RemoteException 
     */
    public synchronized static LinkedList<Area> toAreaList(String[] elementoSelezionato) throws SQLException, RemoteException {
        return stub.toAreaList(elementoSelezionato);
    }
    /**
     * 
     * @return restituisce una stringa contenente tutti i centri di monitoraggio creati
     * @throws RemoteException 
     */
    static synchronized String visualizzaCentri() throws RemoteException {
        return stub.visualizzaCentri();
    }
    /**
     * 
     * @param op operatore di riferimento
     * @param nuovoNome nuovo nome da assegnare al centro
     * @throws SQLException
     * @throws RemoteException 
     */
    public synchronized static void modificaNomeCentro(Operatore op, String nuovoNome) throws SQLException, RemoteException{
        stub.modificaNomeCentro(op, nuovoNome);
    }
    /**
     * 
     * @param operatore operatore di riferimento
     * @param text nuovo indirizzo da assegnare al centro
     * @throws SQLException
     * @throws RemoteException 
     */
    public static void modificaIndirizzoCentro(Operatore operatore, String text) throws SQLException, RemoteException {
        stub.modificaIndirizzoCentro(operatore, text);
    }
    /**
     * 
     * @param nome nome dell'area cercata
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException
     * @throws SQLException 
     */
    public synchronized static String cercaAreaGeografica(String nome) throws IOException, SQLException {
        return stub.cercaAreaGeografica(nome);
    }
    /**
     * 
     * @param lat latitudine dell'area da cercare
     * @param lon longitudine dell'area da cercare
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException
     * @throws SQLException 
     */
    public synchronized static String cercaAreaGeografica(String lat,String lon) throws IOException, SQLException {
        return stub.cercaAreaGeografica(lat, lon);
    }
    /**
     * 
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
     * 
     * @param op operatore del quale si desidera fare la registrazione
     * @throws SQLException
     * @throws IOException
     */
    public static void registrazione(Operatore op) throws SQLException, IOException, IOException{
        stub.registrazione(op);
    }
    /**
     * 
     * @param cm centro di monitoraggio del quale si desidera fare la registrazione
     * @throws IOException
     * @throws SQLException 
     */
    public static void registraCentroAree(CentroMonitoraggio cm) throws IOException, SQLException {
        stub.registraCentroAree(cm);
    }
    
    /**
     * 
     * @param nomeOperatore nome dell'operatore interessato
     * @param cognomeOperatore cognome dell'operatore interessato
     * @param parolaDiRecupero parola per il recupero della password
     * @return password recuperata
     * @throws RemoteException 
     */
    public static String recuperoPassword(String nomeOperatore, String cognomeOperatore,String parolaDiRecupero) throws RemoteException{
        return stub.recuperoPassword(nomeOperatore, cognomeOperatore, parolaDiRecupero);
    }
    
    /**
     * 
     * @param nome nome del centro che deve essere controllato
     * @return restituisce true se esiste già un centro con questo nome, false altrimenti
     * @throws RemoteException 
     */
    public static boolean centroGiaEsistente(String nome) throws RemoteException {
        return stub.centroGiaEsistente(nome);
    }
    
}
