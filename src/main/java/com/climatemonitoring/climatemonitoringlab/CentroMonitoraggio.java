package com.climatemonitoring.climatemonitoringlab;

import java.io.Serializable;
import java.util.LinkedList;
/**
 * La classe CentroMonitoraggio viene utilizzata per creare i centri di monitoraggio con un nome, un indirizzo e delle aree associate
 * 
 * /**
 *
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */
public class CentroMonitoraggio implements Serializable{
    private static final long serialVersionUID = 1L;
	/**
	 * <p> l'attributo <code>nome</code> indica il nome del centro di monitoraggio
	 */
    
	private String nome;
        /**
         * <p> l'attributo <code>Indirizzo</code> indica l'indirizzo di dove si trova il centro di monitoraggio
         */
	private String Indirizzo;
        /**
         * <p> l'attributo <code>listaAree</code> è una lista che contiene tutte le aree associate al centro di monitoraggio
         */
	private LinkedList<Area> listaAree;
	
    /**
     * Costruttore della classe CentroMonitoraggio
     * @param nome nome del centro
     * @param Indirizzo indirizzo del centro
     * @param listaAree lista delle aree relativee al centro
     */
    public CentroMonitoraggio(String nome, String Indirizzo,LinkedList<Area> listaAree) {
		this.nome=nome;
		this.Indirizzo=Indirizzo;
		this.listaAree=listaAree;
	}
	/**
	 * metodo che restituisce il nome del centro
	 * @return nome del centro
	 */
	public String getNome() {return nome;}
	/**
	 * metodo che restituisce l'indirizzo del centro
	 * @return indirizzo del centro
	 */
	public String getIndirizzo() {return Indirizzo;}
	/**
	 * metodo che imposta il nome del centro
	 * @param nom nuovo nome del centro
	 */
	public void setNome(String nom) {nome=nom;}
	/**
	 * metodo che imposta l'indirizzo del centro
	 * @param ind nuovo indirizzo del centro
	 */
	public void setIndirizzo(String ind) {Indirizzo=ind;}
	/**
	 * metodo che imposta il nome di un area
	 * @param nome nuovo nome da assegnare all'area
	 * @param index indice dell'area alla quale assegnare il nome
	 */
	public void setAree(String nome, int index) {listaAree.get(index).setNome(nome);}

    /**
     * metodo che restituisce la lista della aree
     * @return lista della aree
     */
    public LinkedList<Area> getAree(){return listaAree;}
	/**
         * metodo toString della classe
	 * @return stringa contenente tutte le informazioni del centro
	 */
	public String toString() {return nome+" "+Indirizzo+" "+ClientCM.rimuovi(ClientCM.rimuovi(listaAree.toString(),'['),']') ;}
	
}