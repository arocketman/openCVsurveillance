package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;


public class Utilities {

	/**
	 * Returns current time in the specified format.
	 * @return
     */
    public static String aggiornaTimestamp(){
	      Calendar cal = Calendar.getInstance();
	      cal.getTime();
	      SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy HH.mm.ss");
	      return sdf.format(cal.getTime());	  
 }

	/**
	 * Deletes the created ".jpg" files after the gif is created.
	 * @param argomenti
	 */
    public static void deleteJPGFiles(String[] argomenti){
		HashSet<String> set = new HashSet<>(Arrays.asList(argomenti));

        File folder = new File(System.getProperty("user.dir"));
        File files[] = folder.listFiles();

		for (File file : files) {
			String fname = file.getName();
			int pos = fname.lastIndexOf(".");
			if (pos > 0) {
				if (set.contains(fname)) {
					file.delete();
					set.remove(fname);
				}
			}
		}
    }
}
