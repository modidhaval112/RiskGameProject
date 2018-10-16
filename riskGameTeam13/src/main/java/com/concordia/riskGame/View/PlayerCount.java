package com.concordia.riskGame.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.concordia.riskGame.Controller.MapParseController;
import com.concordia.riskGame.util.ReadConfiguration;

/**
 * This Class performs actions for selecting the number of players from property
 * file and select the .map file for the game.
 * 
 * @author Darwin Anirudh -Team 13
 * @author Dheeraj As - Team 13
 *
 */
public class PlayerCount extends JFrame implements ActionListener

{
	private String[] playerCounterArray = {"3","4","5"};
	private ReadConfiguration readConfigurationObject;
	private int noOfPlayers;
	private JFrame countFrame;
	private JLabel countLabel;
	private JLabel selectMapLabel;
	private JComboBox playerCountCombo;
	private JButton okayButton;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filenameFilter;
	private JFrame playerFrame;
	private String filePath = null;
	private int number = 1;
	private MapParseController mapParseObject;

	/**
	 * This constructor calls the Count method
	 */
	public PlayerCount() {
		try {
			Count();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The function of this method is to allow the player to select the number of
	 * players from a configuration file.
	 */
	public void Count() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		countFrame = new JFrame("Concordia Conquest");
		countFrame.setVisible(true);
		readConfigurationObject = new ReadConfiguration();

		// set Background Image
		try {
			JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("rsz_aoe_bk.jpg"))));
			countFrame.setContentPane(label);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("### Read Config value ####" + readConfigurationObject.getPlayerCount());
		/*playerCounterArray = arrayOfPlayer();*/

		countFrame.getContentPane().setLayout(null);

		countLabel = new JLabel("select the number of players");
		countLabel.setPreferredSize(new Dimension(304, 14));
		countLabel.setMaximumSize(new Dimension(300, 300));
		countLabel.setForeground(new Color(255, 255, 255));
		countLabel.setFont(new Font("Georgia", Font.BOLD, 15));
		countLabel.setBounds(100, 101, 190, 30);

		countFrame.getContentPane().add(countLabel);

		playerCountCombo = new JComboBox(playerCounterArray);
		playerCountCombo.setForeground(new Color(102, 0, 0));
		playerCountCombo.setBounds(300, 107, 42, 20);

		countFrame.getContentPane().add(playerCountCombo);

		okayButton = new JButton("OK");
		okayButton.setForeground(new Color(102, 0, 0));
		okayButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		okayButton.setBounds(195, 201, 82, 30);
		okayButton.addActionListener(this);

		countFrame.getContentPane().add(okayButton);

		selectMapLabel = new JLabel("select map file to start the game");

		countFrame.setSize(500, 500);
		countFrame.setLocation(500, 200);

	}

	/**
	 * This function implements actions when okay button is clicked and presents
	 * user the option to select the map file
	 * @param event User Event
	 */
	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource() == okayButton) {

				System.out.println("#### Okay Button is Clicked ####");
				filenameFilter = new FileNameExtensionFilter(" .map", "map", "map");
				countFrame.setVisible(false);
				fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select the desired map file");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileFilter(filenameFilter);

				int result = fileChooser.showOpenDialog(fileChooser);
				fileChooser.setLocation(500, 200);
				fileChooser.setSize(500, 500);
				fileChooser.setVisible(true);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath().toString());
					filePath = selectedFile.getAbsolutePath().toString();
					mapParseObject = new MapParseController();
					mapParseObject.MapParser(selectedFile.getAbsolutePath().toString(),playerCountCombo.getSelectedItem().toString());

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This function gets the number of players from ReadConfiguration class and
	 * @return playerCounterArray String Array of Players
	 */
	public String[] arrayOfPlayer() {
		int playerSize = Integer.parseInt(readConfigurationObject.getPlayerCount());
		playerCounterArray = new String[playerSize];
		
		for (int i = 0; i < playerSize; i++) {
			number = number + 1;
			playerCounterArray[i] = Integer.toString(number);
		}

		return playerCounterArray;

	}

	/**
	 * This method get the filePath
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * this method sets the filePath
	 * @param filePath path of the file
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * method to get Player Array
	 * @return playerCounterArray player array
	 */
	public String[] getPlayerCounter() {
		return playerCounterArray;
	}

	/**
	 * method to set player Array
	 * @param playerCounter Alayer Array
	 */
	public void setPlayerCounter(String[] playerCounter) {
		this.playerCounterArray = playerCounter;
	}

	/**
	 * method to get ReadConfiguration object
	 * @return readConfigurationObject ReadConfiguration  object
	 */
	public ReadConfiguration getReadConfig() {
		return readConfigurationObject;
	}

	/**
	 * method to set ReadConfiguration object
	 * @param readConfig ReadConfiguration  object
	 */
	public void setReadConfig(ReadConfiguration readConfig) {
		this.readConfigurationObject = readConfig;
	}

	/**
	 * method to get numberOfPlayers
	 * @return noOfPlayers number of players
	 */
	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	/**
	 * method to set number of players
	 * @param noOfPlayers number of Players
	 */
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
}
