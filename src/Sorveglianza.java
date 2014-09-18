import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.highgui.VideoCapture;
import org.opencv.video.BackgroundSubtractorMOG;


public class Sorveglianza {
    public List<MatOfPoint> contorni;
    public Mat webcam_image;
    public Mat foreground;
    public static int puntiPerRilMovimento;
    public static int max_width;
    public VideoCapture webCam;
    public BackgroundSubtractorMOG bg;
    public int fileID;
    public Boolean screenshotPresenti;
    public Integer pseudoNumber;
    
    public Sorveglianza(){
	    // Carico la libreria nativa di openCV.  
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    //Creo un arrayList di punti che conterrà il contorno di ciò che si muove rispetto allo sfondo.
    	this.contorni = new ArrayList<MatOfPoint>();
    	//Creo le due matrici frame - foreground.
    	this.webcam_image = new Mat();
    	this.foreground = new Mat();
    	puntiPerRilMovimento = 1000;
    	max_width = 1;
    	this.fileID = 0;
	    //Assumo il controllo della webcam creando una VideoCapture con ID 0 (webcam di default)
    	this.webCam = new VideoCapture(0);
	    //Creo un background subtractor.
    	this.bg = new BackgroundSubtractorMOG(500, 5, 0.7, 0);
    	this.screenshotPresenti=false;
    	this.pseudoNumber=0;
    }

    public void salvaScreenshot(JFrame frame) throws IOException{
    	BufferedImage image = (BufferedImage)frame.createImage(frame.getSize().width, frame.getSize().height);
        Graphics g = image.getGraphics();
        frame.paint(g);
        g.dispose();
		ImageIO.write(image, "jpg", new File("MyFrame"+this.pseudoNumber+"-" + this.fileID++ + ".jpg"));
		this.screenshotPresenti=true;
    }
    
    public void cancellaFiles(){
        File cartella = new File(System.getProperty("user.dir"));
        File files[] = cartella.listFiles();
       
        for(int i = 0; i < files.length; i++){
                String fname = files[i].getName();
                int pos = fname.lastIndexOf(".");
                if (pos > 0) {
                        String ext = fname.substring(pos, fname.length());
                        if(ext.equals(".jpg")){
                                files[i].delete();
                        }
                }
        }
    }
    
    

}
