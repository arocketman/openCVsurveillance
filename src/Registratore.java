

public class Registratore implements Runnable {
	
	public int numArgomenti;
	public int pseudoNumber;
	
	public Registratore(int n, int p){
		this.numArgomenti=n;
		this.pseudoNumber=p;
	}
	
	public void run(){
		if(numArgomenti>3){
			String [] argomenti = new String[this.numArgomenti+1];
			for(int i=0;i<this.numArgomenti;i++)
			argomenti[i]="MyFrame"+this.pseudoNumber+"-"+i+".jpg";
			argomenti[numArgomenti]="Video - "+Utilities.aggiornaTimestamp()+".gif";
			try {
				GifSequenceWriter.main(argomenti);
				System.out.println("GIF salvata "+numArgomenti);
			} catch (Exception e) {
				e.printStackTrace();
			  }

		}
	}
}
