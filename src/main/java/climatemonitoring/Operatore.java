package climatemonitoring;
//import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Date;
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
	 * <p> l'attributo <code>nome</code> serve per indicare il nome proprio dell'operatore
	 * <p> l'attributo <code>cognome</code> serve per indicare il cognome dell'operatore
	 * <p> l'attributo <code>codiceFiscale</code> contiene il codice fiscale dell'operatore
	 * <p> l'attributo <code>indirizzoPosta</code> indica l'indirizzo di posta elettronica dell'operatore
	 * <p> l'attributo <code>userID</code> indica lo userID fornito all'operatore al momento della registrazione
	 * <p> l'attributo <code>password</code> indica la password scelta dall'operatore al momento della registrazione
	 * <p> l'attributo <code>cm</code> indica il centro di monitoraggio associato all'operatore
	 * <p> l'attributo <code>cms</code> contiene cm.toString() e serve per effettuare l'operazione op.toString() quando cm vale null
	 * <p> l'attributo <code>recuperoPassword</code> contiene la stringa che verrà utilizzata per effettuare il recupero della password dell'operatore
	 */
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String indirizzoPosta;
	private String userID;
	private String password;
	private CentroMonitoraggio cm;
	private String cms; //Stringa che contiene cm.toString(); da utilizzare per evitare che il secondo costruttore dia errore
	
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
     * @param nome
     * @param cognome
     * @param codiceFiscale
     * @param indirizzoPosta
     * @param userID
     * @param password
     * @param cm
     * @param recuperoPassword
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
	 * @param op indica l'oggetto operatore che deve essere registrato e inserito all'interno del file
	 * @throws IOException se non viene trovato il file chiamato "OperatoriRegistrati.dati.txt"
         * @throws java.sql.SQLException
	 * */
	public static void registrazione(Operatore op) throws IOException, SQLException {
            /*Connection conn= DriverManager.getConnection(url,user,psw);
            
            Statement st= conn.createStatement();
            String stmt="INSERT INTO operatori VALUES('"+op.getNome()+"'"+","+"'"+op.getCognome()+"'"+","+"'"+op.getCodiceFiscale()+"'"+","+"'"+op.getMail()+"'"+","+"'"+op.getId()+"'"+","+"'"+op.getPsw()+"'"+","+"'"+op.getNomeCentroMonitoraggio()+"'"+")";
            
            st.executeUpdate(stmt);*/
            
            ClientCM.registrazione(op);
            
	}
	
	
	/**
	 * @param cm indica il centro di monitoraggio che deve essere registrato e inserito nel file
	 * @throws IOException se non viene trovato il file chiamato "CentroMonitoraggio.dati.txt"
         * @throws java.sql.SQLException
	 * */
	public static void registraCentroAree(CentroMonitoraggio cm) throws IOException, SQLException {
		//if(this.log==false) throw new OperatoreNonLoggatoException("Operatore non loggato");
		/*Connection conn= DriverManager.getConnection(url,user,psw);
                for(int i=0;i<cm.getAree().size();i++){
                    Statement st= conn.createStatement();
                    String stmt="INSERT INTO centromonitoraggio VALUES('"+cm.getNome()+"','"+cm.getIndirizzo().replace(' ','_')+"','"+cm.getAree().get(i).getNome()+"','"+cm.getAree().get(i).getLatitudine()+","+cm.getAree().get(i).getLongitudine()+"')";
                    st.executeUpdate(stmt);
                }*/
                
		ClientCM.registraCentroAree(cm);
		
	}
	
	
	/**
	 *
	 * @return Stringa che raacchiude tutte le informazioni relative all'operatore
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
        * @param area
        * @param umidita
	* @param vento, umidita, pressione, temperatura, precipitazioni, AlGhiacciai, MaGhiacciai e note sono parametri associti all'area
        * @param note
        * @param pressione
        * @param temperatura
        * @param AlGhiacciai
        * @param precipitazioni
        * @param MaGhiacciai
        * @throws IOException se non viene trovato il file chiamato "ParametriClimatici.dati.txt"
        * @throws java.sql.SQLException
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
	 * 
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
        * @param i Area alla quale devono essere assegnati i parametri 
        * @param ven vento
        * @param um umidità
        * @param pres pressione
        * @param temp temperatura
        * @param prec precipitazioni
        * @param alt altezza dei ghiacciai
        * @param mas massa dei ghiacciai
        * @param no  note
	* @return Area con i paramteri assegnati
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
