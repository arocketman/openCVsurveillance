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
	public JFrame frame;
	public CameraPanel cameraPanel;
	public JLabel timestampLabel;
	public Sorveglianza sistema;
	public static JSlider js;

	
	public MainWindow(){
		sistema = new Sorveglianza();
		frame = new JFrame("Video Sorveglianza v 0.1 - Capuano , De Prisco, Esposito");  
		cameraPanel = new CameraPanel(); 
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		//Imposto proprieta' della JFRAME (Dimensioni,sfondo,aggiungo il panel e la rendo visibile).
		frame.setSize(400,400);
		frame.add(cameraPanel,BorderLayout.CENTER);      
		frame.setVisible(true);
		//Creo una label
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
		Sorveglianza.puntiPerRilMovimento=width;
		Sorveglianza.max_width=width;
		js.setValue(Sorveglianza.max_width);
		js.setSize(100, 100);
		js.setVisible(true);
        Hashtable labelTable = new Hashtable();
        int percentuale = (Sorveglianza.puntiPerRilMovimento*100)/Sorveglianza.max_width;
        labelTable.put( Sorveglianza.max_width/2 , new JLabel(100+"%") );
        MainWindow.js.setLabelTable( labelTable );
        MainWindow.js.setPaintLabels(true);
		Listener st = new Listener();
        js.addChangeListener(st);
		cameraPanel.add(js);
		
	}
	
}


class Listener implements ChangeListener{


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void stateChanged(ChangeEvent e) {
		
		JSlider source = (JSlider)e.getSource();
        Sorveglianza.puntiPerRilMovimento=source.getValue();
        Hashtable labelTable = new Hashtable();
        int percentuale = (Sorveglianza.puntiPerRilMovimento*100)/Sorveglianza.max_width;
        labelTable.put( Sorveglianza.max_width/2 , new JLabel(String.valueOf(percentuale)+"%") );
        MainWindow.js.setLabelTable( labelTable );
        MainWindow.js.setPaintLabels(true);
		
	}
	
}
