


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		MainWindow window = new MainWindow();
		Sorveglianza s = window.sistema;
		ExecutorService pool = Executors.newCachedThreadPool();
		boolean inizializzato=true;
		
		if( window.sistema.webCam.isOpened())  
		{  
			Thread.sleep(500); //Inizializzazione della web-cam.  
			while( window.frame.isShowing() )  
			{  
				s.webCam.read(s.webcam_image);
				if( !s.webcam_image.empty() )  
				{      
					window.frame.setSize(s.webcam_image.width()+40,s.webcam_image.height()+60);
					if(inizializzato){
						window.iniziliazzaPunti(s.webcam_image.width());
						inizializzato = false;
					}
					//Applico il sottrattore di sfondo
					s.bg.apply(s.webcam_image, s.foreground, 0.1);
					//Trovo i contorni.
					Imgproc.findContours(s.foreground, s.contorni, new Mat() , 0, 1);
					//Vado a registrare se e solo se trovo un movimento SIGNIFICATIVO .. almeno puntiPerRilMovimentopunti nell'arrayList.
					if(s.contorni.size() > Sorveglianza.puntiPerRilMovimento){
						s.salvaScreenshot(window.frame);
					}
					else{
						if(s.screenshotPresenti==true){
							pool.execute(new Registratore(s.fileID, s.pseudoNumber));
							s.screenshotPresenti=false;
							s.fileID=0;
							s.pseudoNumber++;
							
							
						}
						
					}
					//Mostro l'immagine  
					window.cameraPanel.matToBufferedImage(s.webcam_image);
					//Aggiorno il timestamp
					window.timestampLabel.setText(Utilities.aggiornaTimestamp());
					//Faccio il clear dell'array contours altrimenti si sovrappone tra di loro creando problemi sia visivi che di memoria.
					s.contorni.clear();
					window.cameraPanel.repaint();
					
				}  
				else  
				{  
					System.out.println(" --(!) Errore, non riesco a catturare frame dalla webcam");  
					break;  
				}  
			}  
		}
		window.sistema.webCam.release(); //Rilascio la webcam.
		pool.shutdown();
		pool.awaitTermination(20, TimeUnit.SECONDS);
		s.cancellaFiles();
		System.exit(0);
	}
}

// Algoritmo puntiPerilmovimento, numArgomenti, settare argomento apply, eliminare frame
