import Utils.Constants;
import Utils.Utilities;

public class Registratore implements Runnable {

    private int numArgomenti;
	private int pseudoNumber;
	
	Registratore(int n, int p){
		this.numArgomenti=n;
		this.pseudoNumber=p;
	}
	
	public void run(){
        String [] argomenti = new String[this.numArgomenti+1];
        for(int i=0;i<this.numArgomenti;i++)
            argomenti[i]=Constants.IMAGE_NAME_PREFIX+this.pseudoNumber+"-"+i+".jpg";

		if(numArgomenti > Constants.MINIMUM_JPEGS_FOR_GIF){
            //Creating an array of Strings with the name of the images. The last one is the created gif itself.
			argomenti[numArgomenti]= Constants.GIF_NAME_PREFIX + Utilities.aggiornaTimestamp()+".gif";
			try {
				GifSequenceWriter.main(argomenti);
				System.out.println(Constants.GIF_SAVED_MESSAGE + numArgomenti);
			} catch (Exception e) {
				e.printStackTrace();
            }
		}


        Utilities.deleteJPGFiles(argomenti);
	}
}
