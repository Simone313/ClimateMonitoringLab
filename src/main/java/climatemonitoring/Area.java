package climatemonitoring;

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
	 * <p> <code>nome</code> &gravee il nome dell'area
	 * <p> <code>vento</code> &gravee il valore da 1 a 5 riferito alla potenza del vento
	 * <p> <code>umidita</code> &gravee il valore da 1 a 5 riferito all'umidita' del luogo
	 * <p> <code>pressione</code> &gravee il valore da 1 a 5 referito alla pressione nell'area
	 * <p> <code>temperatura</code> &gravee il valore da 1 a 5 riferito alla temperatura in gradi suddivisa per fasce
	 * <p> <code>precipitazioni</code> &gravee il valore da 1 a 5 riferito alla quantita' di pioggia in mm suddivisa per fasce
	 * <p> <code>AltGhiacciai</code> &gravee il valore da 1 a 5 riferito all'altezza dei ghiacciai in m suddivisa in fasce
	 * <p> <code>MaGhiacciai</code> &gravee il valore da 1 a 5 riferito alla massa dei ghiacciai in Kg suddivisa in fasce
	 * <p> <code>note</code> &gravee la stringa che riporta le eventuali annotazioni dell'operatore
	 * <p> <code>latitudine</code> e <code>longitudine</code> indicano le coordinate dell'area
	 */
	private String nome;
	private int vento;
	private int umidita;
	private int pressione;
	private int temperatura;
	private int precipitazioni;
	private int AltGhiacciai;
	private int MaGhiacciai;
	private String note;
	private double latitudine;
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
	 * @param nome
	 * @param vento
	 * @param umidita
	 * @param pressione
	 * @param temperatura
	 * @param precipitazioni
	 * @param AltGhiacciai
	 * @param MaGhiacciai
	 * @param note
	 * @param latitudine
	 * @param longitudine
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
	 * @return Stringa contenente tutte le informazioni relative all'area
	 */
	public String toString() {
		return nome+" "+latitudine+" "+longitudine+" vento: "+vento+" umidita: "+umidita+" pressione: "+pressione+" temperatura: "+temperatura+" precipitazioni: "+precipitazioni+" Altezza dei ghiacciai: "+ AltGhiacciai+" Massa dei ghiacciai: "+ MaGhiacciai+" Note: "+note;
	}
	/**
	 * 
	 * @return nome dell'area
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * 
	 * @param n nuovo nome dell'area
	 */
	public void setNome(String n) {
		nome=n;
	}
	/**
	 * 
	 * @param d nuova latitudine dell'area
	 */
	public void setLatitudine(Double d) {
		latitudine=d;
	}
	/**
	 * 
	 * @return latitudine dell'area
	 */
	public double getLatitudine() {
		return latitudine;
	}
	/**
	 * 
	 * @param d nuova longitudine dell'area
	 */
	public void setLongitudine(Double d) {
		longitudine=d;
	}
	/**
	 * 
	 * @return longitudine dell'area
	 */
	public double getLongitudine() {
		return longitudine;
	}
	
	/**
	 * 
	 * @return parametro vento
	 */
	public int getVento() {
		return vento;
	}
	/**
	 * 
	 * @return parametro umiidita'
	 */
	public int getUmidita() {
		return umidita;
	}
	/**
	 * 
	 * @return parametro pressione
	 */
	public int getPressione() {
		return pressione;
	}
	/**
	 * 
	 * @return parametro temperatura
	 */
	public int getTemperatura() {
		return temperatura;
	}
	/**
	 * 
	 * @return parametro precipitazioni
	 */
	public int getPrecipitazioni() {
		return precipitazioni;
	}
	/**
	 * 
	 * @return parametro altezza dei ghiacciai
	 */
	public int getAltezzaG() {
		return AltGhiacciai;
	}
	/**
	 * 
	 * @return parametro massa dei ghiacciai
	 */
	public int getMassaG() {
		return MaGhiacciai;
	}
	/**
	 * 
	 * @param a nuovo parametro vento
	 */
	public void setVento(int a) {
		vento=a;
	}
	/**
	 * 
	 * @param a nuovo parametro umidita'
	 */
	public void setUmidita(int a) {
		umidita=a;
	}
	/**
	 * 
	 * @param a nuovo parametro pressione
	 */
	public void setPressione(int a) {
		pressione=a;
	}
	/**
	 * 
	 * @param a nuovo parametro temperatura
	 */
	public void setTemperatura(int a) {
		temperatura=a;
	}
	/**
	 * 
	 * @param a nuovo parametro precipitazioni
	 */
	public void setPrecipitazioni(int a) {
		precipitazioni=a;
	}
	/**
	 * 
	 * @param a nuovo parametro altezza ghiacciai
	 */
	public void setAltezzaG(int a) {
		 AltGhiacciai=a;
	}
	/**
	 * 
	 * @param a nuovo parametro massa ghiacciai
	 */
	public void setMassaG(int a) {
		MaGhiacciai=a;
	}
	/**
	 * 
	 * @param n nuova nota
	 */
	public void setNote(String n) {
		note=n;
	}
	/**
	 * 
	 * @return nota riferita all'area
	 */
	public String getNote() {
		return note;
	}
	
	
}
