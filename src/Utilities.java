import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Utilities {
	/**Funzione che restituisce la stringa nel formato GIORNO-MESE-ANNO ORA-MINUTI-SECONDI. Statica in quanto utilita'.*/
    public static String aggiornaTimestamp(){
	      Calendar cal = Calendar.getInstance();
	      cal.getTime();
	      SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy HH.mm.ss");
	      return sdf.format(cal.getTime());	  
 }
}

//CAMERAPANEL 