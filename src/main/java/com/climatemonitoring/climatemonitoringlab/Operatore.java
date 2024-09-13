package com.climatemonitoring.climatemonitoringlab;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
/**
 * Le istanze della classe <em>Operatore</em> possono eseguire i metodi che consentono la registrazione dei centri di monitoraggio,
 * l'assegnazone dei parametri ad un'area specifica e di modificare i dati relativi al centro di monitoraggio a cui sono assegnate
 * 
 * @author Simone Marino
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 * */
public class Operatore implements Serializable{
    
    private static final long serialVersionUID = 1L;
	/**
        * <p> L'attributo <code>nome</code> serve per indicare il nome proprio dell'operatore.
        */
       private String nome;

       /**
        * <p> L'attributo <code>cognome</code> serve per indicare il cognome dell'operatore.
        */
       private String cognome;

       /**
        * <p> L'attributo <code>codiceFiscale</code> contiene il codice fiscale dell'operatore.
        */
       private String codiceFiscale;

       /**
        * <p> L'attributo <code>indirizzoPosta</code> indica l'indirizzo di posta elettronica dell'operatore.
        */
       private String indirizzoPosta;

       /**
        * <p> L'attributo <code>userID</code> indica lo <code>userID</code> fornito all'operatore al momento della registrazione.
        */
       private String userID;

       /**
        * <p> L'attributo <code>password</code> indica la password scelta dall'operatore al momento della registrazione.
        */
       private String password;

       /**
        * <p> L'attributo <code>cm</code> indica il centro di monitoraggio associato all'operatore.
        */
       private CentroMonitoraggio cm;

       /**
        * <p> L'attributo <code>cms</code> contiene la rappresentazione testuale del centro di monitoraggio (<code>cm.toString()</code>) e viene utilizzato per gestire i casi in cui <code>cm</code> è null.
        */
       private String cms;

       /**
        * <p> L'attributo <code>recuperoPassword</code> contiene la stringa utilizzata per il recupero della password dell'operatore.
        */
       private String recuperoPassword;

        
	/**
	 * Metodo che restituisce il nome dell'operatore
	 * @return nome dell'operatore
	 */
	public String getNome() {return nome;}
	/**
	 * Metodo che restituisce il cognome dell'operatore
	 * @return cognome dell'operatore
	 */
	public String getCognome() {return cognome;}
	/**
	 * Metodo che restituisce il codice fiscale dell'operatore
	 * @return codice fiscale dell'operatore
	 */
	public String getCodiceFiscale() {return codiceFiscale;}
        
    /**
     * Metodo che restituisce la mail dell'operatore
     * @return mail dell'operatore
     */
    public String getMail(){ return indirizzoPosta;}
        
    /**
     * Metodo che restituisce l'id dell'operatore
     * @return id dell'operatore
     */
    public String getId(){ return userID;}
        
    /**
     * Metodo che restituisce la password dell'operatore
     * @return password dell'operatore
     */
    public String getPsw(){ return password;}

    /**
     * Metodo che restituisce la parola di recupero della password dell'operatore
     * @return parola di recupero della password dell'operatore
     */
    public String getRecuperoPsw() {return recuperoPassword;}

    /**
     * Metodo che restituisce il nome del centro di monitoraggio dell'operatore
     * @return nome del centro di  monitoraggio dell'operatore
     */
    public String getNomeCentroMonitoraggio(){ return cm.getNome();}

    /**
     * Costruttore della classe Operatore
     * @param nome Nome
     * @param cognome Cognome
     * @param codiceFiscale Codice fiscale
     * @param indirizzoPosta Indirizzo di posta elettronica
     * @param userID userID
     * @param password Password
     * @param cm Centro di monitoraggio associato
     * @param recuperoPassword Parola di recupero della password
     */
    public Operatore(String nome, String cognome, String codiceFiscale, String indirizzoPosta, String userID, String password, CentroMonitoraggio cm, String recuperoPassword) {
		this.nome=nome;
		this.cognome=cognome;
		this.codiceFiscale=codiceFiscale;
		this.indirizzoPosta=indirizzoPosta;
		this.userID=userID;
		this.password=password;
		this.cm=cm;
		this.recuperoPassword=recuperoPassword;
	}	
	
	/**
         * metodo che registra un operatore e lo inserisce all'interno del database
	 * @param op indica l'oggetto operatore che deve essere registrato
	 * @throws IOException IOException
         * @throws java.sql.SQLException SQLException
	 * */
	public static void registrazione(Operatore op) throws IOException, SQLException {
            /*Connection conn= DriverManager.getConnection(url,user,psw);
            
            Statement st= conn.createStatement();
            String stmt="INSERT INTO operatori VALUES('"+op.getNome()+"'"+","+"'"+op.getCognome()+"'"+","+"'"+op.getCodiceFiscale()+"'"+","+"'"+op.getMail()+"'"+","+"'"+op.getId()+"'"+","+"'"+op.getPsw()+"'"+","+"'"+op.getNomeCentroMonitoraggio()+"'"+")";
            
            st.executeUpdate(stmt);*/
            
            ClientCM.registrazione(op);
            
	}
	
	
	/**
         * metodo che registra un centro di monitoraggio e lo inserisce nel database
	 * @param cm indica il centro di monitoraggio che deve essere registrato
         * @param op Operatore che effettua la registrazione
	 * @throws IOException IOException
         * @throws java.sql.SQLException SQLException
	 * */
	public static void registraCentroAree(CentroMonitoraggio cm, Operatore op) throws IOException, SQLException {
		//if(this.log==false) throw new OperatoreNonLoggatoException("Operatore non loggato");
		/*Connection conn= DriverManager.getConnection(url,user,psw);
                for(int i=0;i<cm.getAree().size();i++){
                    Statement st= conn.createStatement();
                    String stmt="INSERT INTO centromonitoraggio VALUES('"+cm.getNome()+"','"+cm.getIndirizzo().replace(' ','_')+"','"+cm.getAree().get(i).getNome()+"','"+cm.getAree().get(i).getLatitudine()+","+cm.getAree().get(i).getLongitudine()+"')";
                    st.executeUpdate(stmt);
                }*/
                
		ClientCM.registraCentroAree(cm, op);
		
	}
	
	
	/**
	 * metodo toString della classe operatore
	 * @return Stringa che racchiude tutte le informazioni relative all'operatore
	 * 
	 * */
	public String toString() {
		if(cm!=null) {
			return nome+" "+cognome+" "+codiceFiscale+" "+indirizzoPosta+" "+userID+" "+password+" "+cm.getNome()+" "+recuperoPassword;
		}else {
			return nome+" "+cognome+" "+codiceFiscale+" "+indirizzoPosta+" "+userID+" "+password+" "+cms+" "+recuperoPassword;
		}
		
	}
	
	/**
         * metodo per l'inserimento dei parametri climatici di un'area
        * @param area nome dell'area
        * @param umidita umidità
	* @param vento vento
        * @param note note dell'operatore
        * @param pressione pressione
        * @param temperatura temperatura
        * @param AlGhiacciai altezza dei ghiacciai
        * @param precipitazioni precipitazioni
        * @param MaGhiacciai massa dei ghiacciai
        * @throws IOException IOException
        * @throws java.sql.SQLException SQLException
	 * */
	
	public void inserisciParametriClimatici(String area,int vento, int umidita, int pressione, int temperatura, int precipitazioni, int AlGhiacciai, int MaGhiacciai, String note) throws IOException, SQLException {
		/*if((vento<0 || vento>5) || (umidita<0 || umidita>5) || (pressione<0 || pressione>5) || (temperatura<0 || temperatura>5) || (precipitazioni<0 || precipitazioni>5) || ( AlGhiacciai<0 ||  AlGhiacciai>5) || ( MaGhiacciai<0 ||  MaGhiacciai>5)) throw new ParametroException("Dati non validi"); 
		Connection conn= DriverManager.getConnection(url,user,psw);
                Statement st= conn.createStatement();
                String stmt= "INSERT INTO parametriclimatici VALUES("+this.getCentroMonitoraggio().getNome()+","+area+","+new Date()+","+vento+","+umidita+","+pressione+","+temperatura+","+precipitazioni+","+AlGhiacciai+","+MaGhiacciai+","+note+")";
                */
                ClientCM.inserisciParametriClimatici(area, this.getNomeCentroMonitoraggio(), vento, umidita, pressione, temperatura, precipitazioni, AlGhiacciai, MaGhiacciai, note);
	}
	/**
	 * metodo che restituisce il centro di monitoraggio associato all'operatore
	 * @return centro di monitoraggio dell'operatore
	 */
	public CentroMonitoraggio getCentroMonitoraggio() {
		return cm;
	}
	/**
	 * Modifica il nome del centro di monitoraggio dell'operatore
	 * @param n Nuovo nome del centro
	 */
	public void modificaNomeCentro(String n) {
		cm.setNome(n);
	}
	/**
	 * Modifica l'indirizzo del centro di monitoraggio dell'operatore
	 * @param i Nuovo indirizzo
	 */
	public void modificaIndirizzoCentro(String i) {
		cm.setIndirizzo(i);
	}
	
	/**
	 * Modifica un area del centro di monitoraggio dell'operatore 
	 * @param nomeArea indica l'area che deve essere modificata
	 * @param nuovaArea nuovo nome che deve essere assegnato all'area
	 * @param lat latitudine dell'area
	 * @param lon longitudine dell'area
	 */
	public void modificaAreeCentro(String nomeArea, String nuovaArea, double lat, double lon) {
		for(int i=0;i<cm.getAree().size();i++) {
			if(nomeArea.equalsIgnoreCase(cm.getAree().get(i).getNome())) {
				cm.getAree().get(i).setNome(nuovaArea);
				cm.getAree().get(i).setLatitudine(lat);
				cm.getAree().get(i).setLongitudine(lon);
			}
		}
	}
	
	/**
         * metodo che restituisce l'area con i parametri assegnati
        * @param i Area alla quale devono essere assegnati i parametri 
        * @param ven vento
        * @param um umidità
        * @param pres pressione
        * @param temp temperatura
        * @param prec precipitazioni
        * @param alt altezza dei ghiacciai
        * @param mas massa dei ghiacciai
        * @param no  note
	* @return Area area con i paramteri assegnati
	* */
	
	public Area inserisciParametri(Area i,int ven, int um, int pres, int temp, int prec, int alt, int mas,String no) {
		i.setVento(ven);
		i.setUmidita(um);
		i.setPressione(pres);
		i.setTemperatura(temp);
		i.setPrecipitazioni(prec);
		i.setAltezzaG(alt);
		i.setMassaG(mas);
		i.setNote(no);
		return(i);
	}


	
}			
