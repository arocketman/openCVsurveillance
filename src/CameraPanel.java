import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

public class CameraPanel extends JPanel{  
     private static final long serialVersionUID = 1L;  
     private BufferedImage image;  
     // Costruttore eredita da JPanel  
     public CameraPanel(){  
          super();  
     }  
 
      //Converte un elemento di tipo Mat (Opencv) in un elemento di tipo BufferedImage che puo' essere visualizzato sulla JFrame.
     public boolean matToBufferedImage(Mat matrix) {  
         //Creo una MatOfByte, assegno l'estensione e faccio il passaggio da Mat a MatOfByte tramite imencode.
          MatOfByte mb=new MatOfByte();  
          Highgui.imencode(".jpg", matrix, mb);  
          //Vado a trasformare la mia MatOfByte in un byte[] che e' possibile utilizzare con ByteArrayInputStream per effettuare la read da ImageIO.
          try {  
               this.image = ImageIO.read(new ByteArrayInputStream(mb.toArray()));  
          } catch (IOException e) {  
               e.printStackTrace();  
               return false; // Errore
          }  
       return true; // OK  
     }  
     
     public void paintComponent(Graphics g){  
          super.paintComponent(g);  
          if (this.image==null) return;  
          g.drawImage(this.image,10,10,this.image.getWidth(),this.image.getHeight(), null);
     }
   
}  

//CAMERAPANEL,