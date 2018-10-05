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
 * This Class implements the GUI for selecting the number of players, also pick the .map file for the game.
 * It then calls the MapParseController.java to read the .map file
 * @author Darwin Anirudh -Team 13
 * @author Dheeraj As - Team 13
 *
 */
public class PlayerCount extends JFrame implements ActionListener 

{

	private String[] playerCounter;
	private ReadConfiguration readConfig;
	private int noOfPlayers;
	private JFrame countFrame;
	
	private JLabel countLabel;
	private JLabel selectMapLabel;
	private JComboBox playerCountCombo;
	private JButton okayButton;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filenameFilter;
	private String filePath = null;
	
	private JFrame playerFrame;
	private int number = 1;

	private MapParseController mapParse;

	PlayerCount() 
	{   
		try 
		{
			Count();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
     public void Count()
     {
    	    JFrame.setDefaultLookAndFeelDecorated(true);   
    	 
			countFrame = new JFrame("Concordia Conquest");
			countFrame.setVisible(true);
			readConfig = new ReadConfiguration();
			
			//set Background Image
			try 
			{
	            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("rsz_aoe_bk.jpg"))));
	            countFrame.setContentPane(label);
	        } catch (IOException e) 
			{
	            e.printStackTrace();
	        }

			System.out.println("### Read Config value ####" + readConfig.getPlayerCount());
			playerCounter = arrayOfPlayer();;
			countFrame.getContentPane().setLayout(null);
            
			countLabel = new JLabel("Select the No.Of Players");
			countLabel.setPreferredSize(new Dimension(304, 14));
			countLabel.setMaximumSize(new Dimension(300, 300));
			
			countLabel.setForeground(new Color(255, 255, 255));
			countLabel.setFont(new Font("Georgia", Font.BOLD, 15));
			countLabel.setBounds(100, 101, 190, 30);
			countFrame.getContentPane().add(countLabel);

			playerCountCombo = new JComboBox(playerCounter);
			playerCountCombo.setForeground(new Color(102, 0, 0));
			playerCountCombo.setBounds(300, 107, 42, 20);
			countFrame.getContentPane().add(playerCountCombo);

			okayButton = new JButton("OK");
			okayButton.setForeground(new Color(102, 0, 0));
			okayButton.setFont(new Font("Georgia", Font.PLAIN, 11));
			okayButton.setBounds(195, 201, 82, 30);
			countFrame.getContentPane().add(okayButton);
			okayButton.addActionListener(this);

			selectMapLabel = new JLabel("Select .map file to start the game");
            
			
			//set size,location
			countFrame.setSize(500, 500);
			countFrame.setLocation(500,200);
			
		 
	}

	public void actionPerformed(ActionEvent event) 
	{
		try 
		{
			if (event.getSource() == okayButton) 
			{

				System.out.println("#### Okay Button is Clicked ####");
				filenameFilter = new FileNameExtensionFilter(" .map", "map", "map");
                countFrame.setVisible(false);
				fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select the desired map file");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileFilter(filenameFilter);

				int result = fileChooser.showOpenDialog(fileChooser);
				fileChooser.setLocation(500,200);
				fileChooser.setSize(500, 500);
				fileChooser.setVisible(true);
			
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath().toString());
					filePath = selectedFile.getAbsolutePath().toString();
					mapParse = new MapParseController();
					mapParse.MapParser(selectedFile.getAbsolutePath().toString());

				}

			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public String[] arrayOfPlayer() 
	{
		int playerSize = Integer.parseInt(readConfig.getPlayerCount());
		playerCounter = new String[playerSize];

		for (int i = 0; i < playerSize; i++) 
		{
			number = number + 1;
			playerCounter[i] = Integer.toString(number);
		}

		return playerCounter;

	}

	public String getFilePath() 
	{
		return filePath;
	}

	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}

	public String[] getPlayerCounter() 
	{
		return playerCounter;
	}

	public void setPlayerCounter(String[] playerCounter)
	{
		this.playerCounter = playerCounter;
	}

	public ReadConfiguration getReadConfig() 
	{
		return readConfig;
	}

	public void setReadConfig(ReadConfiguration readConfig) 
	{
		this.readConfig = readConfig;
	}

	public int getNoOfPlayers() 
	{
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) 
	{
		this.noOfPlayers = noOfPlayers;
	}

}
