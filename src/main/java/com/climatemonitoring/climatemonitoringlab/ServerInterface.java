/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.climatemonitoring.climatemonitoringlab;

/**
 *
 * @author simo0
 */
import climatemonitoring.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author simo0
 */
public interface ServerInterface extends Remote{

    /**
     *
     * @param op
     * @return
     * @throws RemoteException
     */
    public LinkedList<String> getListaNomiCentromonitoraggio(Operatore op) throws RemoteException;

    /**
     *
     * @param nom
     * @param con
     * @param pass
     * @return
     * @throws RemoteException
     */
    public Operatore getOperatore(String nom, String con, String pass) throws RemoteException;

    /**
     *
     * @param select
     * @param nomeCentro
     * @param ven
     * @param umi
     * @param pre
     * @param temp
     * @param precipit
     * @param aG
     * @param mG
     * @param not
     * @throws RemoteException
     */
    public void setParametriclimatici(String select, String nomeCentro, int ven, int umi, int pre, int  temp, int precipit, int aG, int mG, String not) throws RemoteException;

    /**
     *
     * @param op
     * @param nome
     * @param coordinate
     * @param nomeAreaCercata
     * @throws RemoteException
     * @throws SQLException
     */
    public void updateNomeCoordinateCentroMonitoraggio(Operatore op, String nome, String coordinate, String nomeAreaCercata) throws RemoteException, SQLException;

    /**
     *
     * @param cf
     * @return
     * @throws RemoteException
     */
    public boolean operatoriEsistenti(String cf) throws RemoteException;

    /**
     *
     * @return
     * @throws RemoteException
     */
    public boolean centrimonitoraggioEsistenti() throws RemoteException;

    /**
     *
     * @return
     * @throws RemoteException
     */
    public LinkedList<String> getCentriMonitoraggioCreati() throws RemoteException;

    /**
     *
     * @param n
     * @param c
     * @param pa
     * @return
     * @throws RemoteException
     */
    public boolean controlloCredenzialiAccesso(String n, String c, String pa) throws RemoteException;

    /**
     *
     * @param s
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public LinkedList<Area> toAreaList(String[] s) throws SQLException, RemoteException;

    /**
     *
     * @return
     * @throws RemoteException
     */
    public String visualizzaCentri() throws RemoteException;

    /**
     *
     * @param op
     * @param nuovoNome
     * @throws SQLException
     * @throws RemoteException
     */
    public void modificaNomeCentro(Operatore op, String nuovoNome) throws SQLException, RemoteException;

    /**
     *
     * @param op
     * @param nuovoIndirizzo
     * @throws SQLException
     * @throws RemoteException
     */
    public void modificaIndirizzoCentro(Operatore op, String nuovoIndirizzo) throws SQLException, RemoteException;

    /**
     *
     * @param nome
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws RemoteException
     */
    public String cercaAreaGeografica(String nome) throws IOException, SQLException, RemoteException;

    /**
     *
     * @param lat
     * @param lon
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws RemoteException
     */
    public String cercaAreaGeografica(String lat,String lon) throws IOException, SQLException, RemoteException;

    /**
     *
     * @param op
     * @throws IOException
     * @throws SQLException
     * @throws RemoteException
     */
    public void registrazione(Operatore op) throws IOException, SQLException, RemoteException;

    /**
     *
     * @param cm
     * @throws IOException
     * @throws SQLException
     * @throws RemoteException
     */
    public void registraCentroAree(CentroMonitoraggio cm) throws IOException, SQLException, RemoteException;

    /**
     *
     * @param nomeOperatore
     * @param cognomeOperatore
     * @param parolaDiRecupero
     * @return
     * @throws RemoteException
     */
    public String recuperoPassword(String nomeOperatore, String cognomeOperatore,String parolaDiRecupero) throws RemoteException;

    /**
     *
     * @param nome
     * @return
     * @throws RemoteException
     */
    public boolean centroGiaEsistente(String nome) throws RemoteException;
}
