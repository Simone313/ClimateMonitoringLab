package climatemonitoring;
/**
 * E' un eccezione che si verifica quando uno dei parametri inseriti non rispetta l'intervallo prestabilito.
 * @author Simone Marino
 * @author Andri Tosku
 * @author Mattia Statti
 * @author Defo Tagne Gabin Alban
 * */
public class ParametroException extends RuntimeException{

    /**
     *
     * @param msg
     */
    public ParametroException(String msg) {
		super(msg);
	}
}
