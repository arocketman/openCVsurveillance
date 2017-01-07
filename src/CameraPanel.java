import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class CameraPanel extends JPanel{

     private static final long serialVersionUID = 1L;  
     private BufferedImage image;

     public CameraPanel(){  
          super();  
     }  

     /**
      * Converts a Mat to a BufferedImage so that it can be shown on the JFrame.
      * @param matrix the OpenCV Mat
      * @return true if the conversion was successful. False otherwise.
     */
     public boolean matToBufferedImage(Mat matrix) {
          //Mat -> MatOfByte so that we can use the toArray() method.
          MatOfByte mb=new MatOfByte();
          Imgcodecs.imencode(".jpg",matrix,mb);
          try {  
               this.image = ImageIO.read(new ByteArrayInputStream(mb.toArray()));  
          } catch (IOException e) {  
               e.printStackTrace();  
               return false;
          }  
       return true;
     }

    /**
     * Draws the BufferedImage to the JFrame.
     * @param g
     */
    public void paintComponent(Graphics g){
          super.paintComponent(g);  
          if (this.image==null) return;  
          g.drawImage(this.image,10,10,this.image.getWidth(),this.image.getHeight(), null);
     }
   
}  
