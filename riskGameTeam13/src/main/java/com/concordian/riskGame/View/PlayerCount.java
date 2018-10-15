package com.concordian.riskGame.View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.concordia.riskGame.util.ReadConfiguration;
import com.concordian.riskGame.Controller.MapParseController;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JScrollPane;

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
	 * returns String array.
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getPlayerCounter() {
		return playerCounterArray;
	}

	public void setPlayerCounter(String[] playerCounter) {
		this.playerCounterArray = playerCounter;
	}

	public ReadConfiguration getReadConfig() {
		return readConfigurationObject;
	}

	public void setReadConfig(ReadConfiguration readConfig) {
		this.readConfigurationObject = readConfig;
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

}
