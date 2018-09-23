package riskGameView;

import java.io.File;

import javax.swing.JFileChooser;

public class MapRender {

	private File selectedFile;
	
	
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
	
}
