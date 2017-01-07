import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Utils.Constants;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;


public class Surveillance {
    private List<MatOfPoint> contorni;
    private Mat webcam_image;
    private Mat foreground;
    private int points_movement_threshold;
    private static int max_width;
    private VideoCapture webCam;
    private BackgroundSubtractorMOG2 bg;
    private int fileID;
    private Boolean screenshotPresenti;
    private Integer pseudoNumber;
    
    public Surveillance(){
	    // Loading OpenCV library
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Creating an array of contours. Basically we're taking note of what points are moving compared to the background.
    	this.contorni = new ArrayList<>();
    	this.webcam_image = new Mat();
    	this.foreground = new Mat();
    	points_movement_threshold = Constants.INITIAL_MOVEMENT_THRESHOLD;
    	max_width = 1;
    	this.fileID = 0;
	    //Getting webcam control with the ID 0 (default webcam)
    	this.webCam = new VideoCapture(0);
	    //Creo un background subtractor.
    	this.bg = Video.createBackgroundSubtractorMOG2(500,16,true);
    	this.screenshotPresenti=false;
    	this.pseudoNumber=0;
    }

    public void salvaScreenshot(JFrame frame) throws IOException{
    	BufferedImage image = (BufferedImage)frame.createImage(frame.getSize().width, frame.getSize().height);
        Graphics g = image.getGraphics();
        frame.paint(g);
        g.dispose();
		ImageIO.write(image, "jpg", new File(Constants.IMAGE_NAME_PREFIX +this.pseudoNumber+ "-" + this.fileID++ + ".jpg"));
		this.screenshotPresenti=true;
    }

    public int getPoints_movement_threshold() {
        return points_movement_threshold;
    }

    public void setPoints_movement_threshold(int points_movement_threshold) {
        this.points_movement_threshold = points_movement_threshold;
    }

    public static int getMax_width() {
        return max_width;
    }

    public static void setMax_width(int max_width) {
        Surveillance.max_width = max_width;
    }

    public List<MatOfPoint> getContorni() {
        return contorni;
    }


    public Mat getWebcam_image() {
        return webcam_image;
    }

    public Mat getForeground() {
        return foreground;
    }

    public VideoCapture getWebCam() {
        return webCam;
    }

    public BackgroundSubtractorMOG2 getBg() {
        return bg;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public Boolean screenshotsAvailable() {
        return screenshotPresenti;
    }

    public void setScreenshotPresenti(Boolean screenshotPresenti) {
        this.screenshotPresenti = screenshotPresenti;
    }

    public Integer getPseudoNumber() {
        return pseudoNumber;
    }

    public void setPseudoNumber(Integer pseudoNumber) {
        this.pseudoNumber = pseudoNumber;
    }
}
