package screenCapture.gui;
/*
 * @author:Mustafa Ataseven 
 * 
 * 
 */

import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.filechooser.FileNameExtensionFilter;


public class App extends JFrame{
	JButton captureBtn;
	BufferedImage image=null;

	Timer timer;
	public App() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch (Exception ex) {
		
			ex.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		
		setSize(300,100);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		
		captureBtn=new JButton("Capture");
		captureBtn.setPreferredSize(new Dimension(100, 50));
		add(captureBtn);
		
		
		
		
		captureBtn.addActionListener((e)->{
		
			this.setLocation(-500, 0);
			try {
				
				image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setDialogTitle("Dosya konumunu seçin");   
				fileChooser.setAcceptAllFileFilterUsed(false);
				
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "png");
		        fileChooser.addChoosableFileFilter(filter);
		        
				int userSelection = fileChooser.showSaveDialog(this);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    if(fileToSave.getName().endsWith(".png")) {
				    	ImageIO.write(image,"png", fileToSave.getAbsoluteFile());
				    }else {
				    	
				    	ImageIO.write(image,"png",new File(fileToSave.getAbsolutePath()+".png"));
				    }
				   
				   
				}	
				
					
				
				
			} catch (Exception e1) {
			 
				e1.printStackTrace();
			}
			setLocation(100, 100);
			this.setVisible(true);
			
		});
		
		setVisible(true);
	}
}
