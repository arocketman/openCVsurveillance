


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Utils.Constants;
import Utils.Utilities;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		MainWindow window = new MainWindow();
		Surveillance surveillance = window.getSistema();
		ExecutorService pool = Executors.newCachedThreadPool();

		webcamLoop(window, surveillance, pool);

		window.getSistema().getWebCam().release();
		pool.shutdown();
		pool.awaitTermination(20, TimeUnit.SECONDS);
		System.exit(0);
	}

	/**
	 * The main webcam loop. Searches for movement, takes screenshots and creates the gif.
	 * @param window
	 * @param surveillance
	 * @param pool
	 * @throws InterruptedException
     * @throws IOException
     */
	private static void webcamLoop(MainWindow window, Surveillance surveillance, ExecutorService pool) throws InterruptedException, IOException {
		boolean initialized=false;
		if( window.getSistema().getWebCam().isOpened())
		{
			//Waiting for the webcam initialization.
			Thread.sleep(500);
			while( window.getFrame().isShowing() )
			{
				surveillance.getWebCam().read(surveillance.getWebcam_image());
				if( !surveillance.getWebcam_image().empty() )
				{
					window.getFrame().setSize(surveillance.getWebcam_image().width()+40, surveillance.getWebcam_image().height()+60);
					if(!initialized){
						window.iniziliazzaPunti(surveillance.getWebcam_image().width());
						initialized = true;
					}
					//Applying the background subtraction
					surveillance.getBg().apply(surveillance.getWebcam_image(), surveillance.getForeground(), 0.1);
					//Finding contours
					Imgproc.findContours(surveillance.getForeground(), surveillance.getContorni(), new Mat() , 0, 1);
					//We are going to record (Save screenshots) only if the contours (the movement) is greater than the threshold.
					if(surveillance.getContorni().size() > surveillance.getPoints_movement_threshold()){
						surveillance.salvaScreenshot(window.getFrame());
					}
					else{
						if(surveillance.screenshotsAvailable()){
							pool.execute(new Registratore(surveillance.getFileID(), surveillance.getPseudoNumber()));
							surveillance.setScreenshotPresenti(false);
							surveillance.setFileID(0);
							surveillance.setPseudoNumber(surveillance.getPseudoNumber() + 1);
						}
					}
					//Showing the image
					window.getCameraPanel().matToBufferedImage(surveillance.getWebcam_image());
					//Updating timestamp and clearing contours array
					window.getTimestampLabel().setText(Utilities.aggiornaTimestamp());
					surveillance.getContorni().clear();
					window.getCameraPanel().repaint();

				}
				else
				{
					System.out.println(Constants.ERROR_WEBCAM_UNAVAILABLE);
					break;
				}
			}
		}
	}
}
