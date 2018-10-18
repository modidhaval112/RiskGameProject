package com.concordia.riskGame.View;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * This class renders the map
 * @author Dhaval
 *
 */
public class MapRender {

	private File selectedFile;
	
	/**
	 * This method is used to choose the map file
	 * @return selectedFile uploaded file
	 */
	public File chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		System.out.println(System.getProperty("user.home"));
		int result = fileChooser.showOpenDialog(null);
		if(result==JFileChooser.APPROVE_OPTION) {
			this.selectedFile=fileChooser.getSelectedFile();
		}
		return selectedFile;
	}

	/**
	 * This methos is used to read the uploade file
	 */
	public void readFile() {
		
	}	
	
}
