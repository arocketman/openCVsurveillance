import Utils.Utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainWindow {
	private JFrame frame;
	private CameraPanel cameraPanel;
	private JLabel timestampLabel;
	private Surveillance sistema;
	private static JSlider js;

    private final static String WINDOW_TITLE = "Video Surveillance v 0.2";

    /**
     * Constructor for the MainWindow. Builds the JFrame.
     */
	public MainWindow(){
		sistema = new Surveillance();
		frame = new JFrame(WINDOW_TITLE);
		cameraPanel = new CameraPanel(); 
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(400,400);
		frame.add(cameraPanel,BorderLayout.CENTER);      
		frame.setVisible(true);

        //Setting up the timestamp label
		timestampLabel = new JLabel(Utilities.aggiornaTimestamp());
		timestampLabel.setForeground(Color.black);
		timestampLabel.setOpaque(true);
		timestampLabel.setBackground(Color.white);
		timestampLabel.setFont(new Font("Verdana", 1, 16));
		cameraPanel.add(timestampLabel);
		
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public void iniziliazzaPunti(int width){
		
		js = new JSlider(JSlider.HORIZONTAL,0, width, width/2);
		sistema.setPoints_movement_threshold(width);
		Surveillance.setMax_width(width);
		js.setValue(Surveillance.getMax_width());
		js.setSize(100, 100);
		js.setVisible(true);
        Hashtable labelTable = new Hashtable();
        int percentuale = (sistema.getPoints_movement_threshold() *100)/ Surveillance.getMax_width();
        labelTable.put( Surveillance.getMax_width() /2 , new JLabel(100+"%") );
        MainWindow.js.setLabelTable( labelTable );
        MainWindow.js.setPaintLabels(true);
		Listener st = new Listener();
        js.addChangeListener(st);
		cameraPanel.add(js);
		
	}

	public JFrame getFrame() {
		return frame;
	}


	public CameraPanel getCameraPanel() {
		return cameraPanel;
	}

	public JLabel getTimestampLabel() {
		return timestampLabel;
	}

	public Surveillance getSistema() {
		return sistema;
	}

	class Listener implements ChangeListener{


		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void stateChanged(ChangeEvent e) {

			JSlider source = (JSlider)e.getSource();
			sistema.setPoints_movement_threshold(source.getValue());
			Hashtable labelTable = new Hashtable();
			int percentuale = (sistema.getPoints_movement_threshold() *100)/ Surveillance.getMax_width();
			labelTable.put( Surveillance.getMax_width() /2 , new JLabel(String.valueOf(percentuale)+"%") );
			MainWindow.js.setLabelTable( labelTable );
			MainWindow.js.setPaintLabels(true);

		}

	}
	
}



