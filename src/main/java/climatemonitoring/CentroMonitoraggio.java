package climatemonitoring;

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
	 * <p> l'attributo <code>Indirizzo</code> indica l'indirizzo di dove si trova il centro di monitoraggio
	 * <p> l'attributo <code>listaAree</code> &grave una lista che contiene tutte le aree associate al centro di monitoraggio
	 */
    
	private String nome;
	private String Indirizzo;
	private LinkedList<Area> listaAree;
	
    /**
     *
     * @param nome
     * @param Indirizzo
     * @param listaAree
     */
    public CentroMonitoraggio(String nome, String Indirizzo,LinkedList<Area> listaAree) {
		this.nome=nome;
		this.Indirizzo=Indirizzo;
		this.listaAree=listaAree;
	}
	/**
	 * 
	 * @return nome del centro
	 */
	public String getNome() {return nome;}
	/**
	 * 
	 * @return indirizzo del centro
	 */
	public String getIndirizzo() {return Indirizzo;}
	/**
	 * 
	 * @param nom nuovo nome del centro
	 */
	public void setNome(String nom) {nome=nom;}
	/**
	 * 
	 * @param ind nuovo indirizzo del centro
	 */
	public void setIndirizzo(String ind) {Indirizzo=ind;}
	/**
	 * 
	 * @param nome nuovo nome da assegnare all'area
	 * @param index indice dell'area alla quale assegnare il nome
	 */
	public void setAree(String nome, int index) {listaAree.get(index).setNome(nome);}

    /**
     *
     * @return
     */
    public LinkedList<Area> getAree(){return listaAree;}
	/**
	 * @return stringa contenente tutte le informazioni del centro
	 */
	public String toString() {return nome+" "+Indirizzo+" "+ClientCM.rimuovi(ClientCM.rimuovi(listaAree.toString(),'['),']') ;}
	
}