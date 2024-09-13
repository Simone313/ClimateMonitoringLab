package com.climatemonitoring.climatemonitoringlab;

import java.io.Serializable;

/**
 * 
 * La classe Area viene utilizzata per creare degli oggetti di tipo area all'interno del programma
 * 
 *
 * 
 * 
 * @author Simone Marino 
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 */ 
public class Area implements Serializable{
    private static final long serialVersionUID = 1L;
	/**
	 * <p> l'attributo <code>nome</code> indica il nome dell'area
         */
         private String nome;
        /**
	 * <p> l'attributo <code>vento</code> indica il valore da 1 a 5 riferito alla potenza del vento
	 */
         private int vento;
        /**
         * <p> l'attributo <code>umidita</code> indica il valore da 1 a 5 riferito all'umidita' del luogo
	 */
         private int umidita;
         /**
         * <p> l'attributo <code>pressione</code> indica il valore da 1 a 5 referito alla pressione nell'area
	 */
         private int pressione;
         /**
         * <p> l'attributo <code>temperatura</code> indica il valore da 1 a 5 riferito alla temperatura in gradi suddivisa per fasce
	 */
         private int temperatura;
         /**
         * <p> l'attributo <code>precipitazioni</code> indica il valore da 1 a 5 riferito alla quantita' di pioggia in mm suddivisa per fasce
	 */
         private int precipitazioni;
         /**
         * <p> l'attributo <code>AltGhiacciai</code> indica il valore da 1 a 5 riferito all'altezza dei ghiacciai in m suddivisa in fasce
	 */
         private int AltGhiacciai;
         /**
         * <p> l'attributo <code>MaGhiacciai</code> indica il valore da 1 a 5 riferito alla massa dei ghiacciai in Kg suddivisa in fasce
	 */
         private int MaGhiacciai;
         /**
         * <p> l'attributo <code>note</code> indica la stringa che riporta le eventuali annotazioni dell'operatore
	 */
         private String note;
         /**
         * <p> l'attributo <code>latitudine</code> indica la latitudine dell'area
         */
         private double latitudine;
         /**
         * <p> l'attributo <code>longitudine</code> indica la longitudine dell'area
	 */
	private double longitudine;
	
	
	
	
	
	
	
	
	
	
	/**
	 * Crea un'area senza assegnare alcun parametro
	 * @param nome nome dell'area
	 * @param latitudine latitudine dell'area
	 * @param longitudine longitudine dell'area
	 */
	public Area(String nome, double latitudine, double longitudine) {
		this.nome=nome;
		this.latitudine=latitudine;
		this.longitudine=longitudine;
	}
	/**
	 * Crea un'area assegnandogli dei parametri
	 * @param nome nome dell'area
	 * @param vento parametro vento
	 * @param umidita parametro umidità
	 * @param pressione parametro pressione
	 * @param temperatura parametro temperatura
	 * @param precipitazioni parametro precipitazioni
	 * @param AltGhiacciai parametro altezza ghiacciai
	 * @param MaGhiacciai parametro massa ghiacciai
	 * @param note note 
	 * @param latitudine latitudine
	 * @param longitudine longitudine
	 */
	public Area(String nome, int vento,int umidita,int pressione,int temperatura,int precipitazioni,int AltGhiacciai,int MaGhiacciai, String note, double latitudine, double longitudine) {
		this.nome=nome;
		this.vento=vento;
		this.umidita=umidita;
		this.pressione=pressione;
		this.temperatura=temperatura;
		this.precipitazioni=precipitazioni;
		this.AltGhiacciai=AltGhiacciai;
		this.MaGhiacciai=MaGhiacciai;
		this.note=note;
		this.latitudine=latitudine;
		this.longitudine=longitudine;
		
	}
	
	/**
         * metodo toString della classe
	 * @return Stringa contenente tutte le informazioni relative all'area
	 */
	public String toString() {
		return nome+" "+latitudine+" "+longitudine+" vento: "+vento+" umidita: "+umidita+" pressione: "+pressione+" temperatura: "+temperatura+" precipitazioni: "+precipitazioni+" Altezza dei ghiacciai: "+ AltGhiacciai+" Massa dei ghiacciai: "+ MaGhiacciai+" Note: "+note;
	}
	/**
	 * metodo che restituisce il nome dell'area
	 * @return nome dell'area
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * metodo che imposta il nome dell'area
	 * @param n nuovo nome dell'area
	 */
	public void setNome(String n) {
		nome=n;
	}
	/**
	 * metodo che imposta la latitudine dell'area
	 * @param d nuova latitudine dell'area
	 */
	public void setLatitudine(Double d) {
		latitudine=d;
	}
	/**
	 * metodo che restituisce la latitudine
	 * @return latitudine dell'area
	 */
	public double getLatitudine() {
		return latitudine;
	}
	/**
	 * metodo che imposta la longitudine dell'area
	 * @param d nuova longitudine dell'area
	 */
	public void setLongitudine(Double d) {
		longitudine=d;
	}
	/**
	 * metodo che restituisce la longitudine
	 * @return longitudine dell'area
	 */
	public double getLongitudine() {
		return longitudine;
	}
	
	/**
	 *  metodo che restituisce il parametro vento
	 * @return parametro vento
	 */
	public int getVento() {
		return vento;
	}
	/**
	 * metodo che restituisce il parametro umidità
	 * @return parametro umiidita'
	 */
	public int getUmidita() {
		return umidita;
	}
	/**
	 * metodo che restituisce il parametro pressione
	 * @return parametro pressione
	 */
	public int getPressione() {
		return pressione;
	}
	/**
	 * metodo che restituisce il parametro temperatura
	 * @return parametro temperatura
	 */
	public int getTemperatura() {
		return temperatura;
	}
	/**
	 * metodo che restituisce il parametro precipitazioni
	 * @return parametro precipitazioni
	 */
	public int getPrecipitazioni() {
		return precipitazioni;
	}
	/**
	 * metodo che restituisce il parametro altezza ghiacciai
	 * @return parametro altezza dei ghiacciai
	 */
	public int getAltezzaG() {
		return AltGhiacciai;
	}
	/**
	 * metodo che restituisce il parametro massa ghiacciai
	 * @return parametro massa dei ghiacciai
	 */
	public int getMassaG() {
		return MaGhiacciai;
	}
	/**
	 * metodo che imposta il parametro vento
	 * @param a nuovo parametro vento
	 */
	public void setVento(int a) {
		vento=a;
	}
	/**
	 * metodo che imposta il parametro umidità
	 * @param a nuovo parametro umidita'
	 */
	public void setUmidita(int a) {
		umidita=a;
	}
	/**
	 * metodo che imposta il parametro pressione
	 * @param a nuovo parametro pressione
	 */
	public void setPressione(int a) {
		pressione=a;
	}
	/**
	 * metodo che imposta il parametro temperatura
	 * @param a nuovo parametro temperatura
	 */
	public void setTemperatura(int a) {
		temperatura=a;
	}
	/**
	 * metodo che imposta il parametro precipitazioni
	 * @param a nuovo parametro precipitazioni
	 */
	public void setPrecipitazioni(int a) {
		precipitazioni=a;
	}
	/**
	 * metodo che imposta il parametro altezza ghiacciai
	 * @param a nuovo parametro altezza ghiacciai
	 */
	public void setAltezzaG(int a) {
		 AltGhiacciai=a;
	}
	/**
	 * metodo che imposta il parametro massa ghiacciai
	 * @param a nuovo parametro massa ghiacciai
	 */
	public void setMassaG(int a) {
		MaGhiacciai=a;
	}
	/**
	 * metodo che imposta le note
	 * @param n nuova nota
	 */
	public void setNote(String n) {
		note=n;
	}
	/**
	 * metodo che restituisce le note
	 * @return nota riferita all'area
	 */
	public String getNote() {
		return note;
	}
	
	
}
