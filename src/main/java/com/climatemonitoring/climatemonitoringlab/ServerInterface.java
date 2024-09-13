/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;


import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Interfaccia che comprende i metodi che devono essere implementati dal server
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public interface ServerInterface extends Remote{

    /**
     * metodo che restituise la lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @param op Operatore di riferimento
     * @return lista dei nomi del centro di monitoraggio associato all'operatore che si è registrato o ha fatto l'accesso
     * @throws RemoteException RemoteException
     */
    public LinkedList<String> getListaNomiCentromonitoraggio(Operatore op) throws RemoteException;

    /**
     * metodo che restituisce l'oggetto operatore dato il nome, cognome e password
     * @param nom è il nome dell'operatore
     * @param con è il cognome dell'operatore
     * @param pass è la password dell'operatore
     * @return oggetto operatore
     * @throws RemoteException RemoteException
     */
    public Operatore getOperatore(String nom, String con, String pass) throws RemoteException;

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
    public void setParametriclimatici(String select, String nomeCentro, int ven, int umi, int pre, int  temp, int precipit, int aG, int mG, String not) throws RemoteException;

    /**
     * metodo che aggiorna il nome e le coordinate di un luogo
     * @param op l'oggetto operatore di riferimento
     * @param nome nuovo nome dell'area
     * @param coordinate coordinate dell'area
     * @param nomeAreaCercata nome precedente dell'area
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    public void updateNomeCoordinateCentroMonitoraggio(Operatore op, String nome, String coordinate, String nomeAreaCercata) throws RemoteException, SQLException;

    /**
     * metodo che restituisce true se il codice fiscale è già presente nel database, false altrimenti
     * @param cf codice fiscale dell'operatore
     * @return true se il codice fiscale è già presente nel database, false altrimenti
     * @throws RemoteException RemoteException
     */
    public boolean operatoriEsistenti(String cf) throws RemoteException;

    /**
     * metodo che resittuisce true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @return true se sono già stati creati dei centri di monitoraggio, false altrimenti
     * @throws RemoteException RemoteException
     */
    public boolean centrimonitoraggioEsistenti() throws RemoteException;

    /**
     * metodo che restituisce una lista contenente il nome di tutti i centri di monitoraggio creati
     * @return lista contenente il nome di tutti i centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    public LinkedList<String> getCentriMonitoraggioCreati() throws RemoteException;

    /**
     * metodo che restituisce true se l'autenticazione è andata a buon fine, false altrimenti
     * @param n nome dell'operatore da controllare
     * @param c cognome dell'operatore da controllare
     * @param pa password dell'operatore da controllare
     * @return true se l'autenticazione è andata a buon fine, false altrimenti
     * @throws RemoteException RemoteException
     */
    public boolean controlloCredenzialiAccesso(String n, String c, String pa) throws RemoteException;

    /**
     * metodo che restituisce la lista delle aree del centro
     * @param s array il cui primo elemento è il nome del centro di monitoraggio
     * @return lista delle aree del centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public LinkedList<Area> toAreaList(String[] s) throws SQLException, RemoteException;

    /**
     * metodo che restituisce una stringa contenente tutti i centri di monitoraggio creati
     * @return stringa contenente tutti i centri di monitoraggio creati
     * @throws RemoteException RemoteException
     */
    public String visualizzaCentri() throws RemoteException;

    /**
     * metodo che va a modificare il nome del centro di monitoraggio
     * @param op operatore di riferimento
     * @param nuovoNome nuovo nome da assegnare al centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public void modificaNomeCentro(Operatore op, String nuovoNome) throws SQLException, RemoteException;

    /**
     * metodo che va a modificare l'indirizzo del centro di monitoraggio
     * @param op operatore di riferimento
     * @param nuovoIndirizzo nuovo indirizzo da assegnare al centro
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public void modificaIndirizzoCentro(Operatore op, String nuovoIndirizzo) throws SQLException, RemoteException;

    /**
     * metodo che restituisce le informazioni relative all'area cercata per nome
     * @param nome nome dell'area cercata
     * @return informazioni relative all'area cercata
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws java.rmi.RemoteException RemoteException
     */
    public String cercaAreaGeografica(String nome) throws IOException, SQLException, RemoteException;

    /**
     * metodo che restituisce le informazioni relative all'area cercata per coordinate
     * @param lat latitudine dell'area da cercare
     * @param lon longitudine dell'area da cercare
     * @return restituisce le informazioni relative all'area cercata
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws java.rmi.RemoteException RemoteException
     */
    public String cercaAreaGeografica(String lat,String lon) throws IOException, SQLException, RemoteException;

    /**
     * metodo che inserisce nella tabella operatori un nuovo operatore
     * @param op operatore del quale si desidera fare la registrazione
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public void registrazione(Operatore op) throws IOException, SQLException, RemoteException;

    /**
     * metodo che inserisce nella teballa dei centri di monitoraggio un nuovo centro
     * @param cm centro di monitoraggio del quale si desidera fare la registrazione
     * @param op Operatore che effettua la registrazione del centro
     * @throws IOException IOException
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public void registraCentroAree(CentroMonitoraggio cm, Operatore op) throws IOException, SQLException, RemoteException;

    /**
     * metodo che restituisce la password dell'operatore se la parola di recupero corrisponde
     * @param nomeOperatore nome dell'operatore interessato
     * @param cognomeOperatore cognome dell'operatore interessato
     * @param parolaDiRecupero parola per il recupero della password
     * @return password recuperata
     * @throws RemoteException RemoteException
     */
    public String recuperoPassword(String nomeOperatore, String cognomeOperatore,String parolaDiRecupero) throws RemoteException;

    /**
     * metodo che restituisce true se esiste già un centro con questo nome, false altrimenti
     * @param nome nome del centro che deve essere controllato
     * @return truee se esiste già un centro con questo nome, false altrimenti
     * @throws RemoteException RemoteException
     */
    public boolean centroGiaEsistente(String nome) throws RemoteException;
}
