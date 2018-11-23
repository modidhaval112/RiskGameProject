/**
 * 
 */
package com.concordia.riskGame.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author saich
 *
 */
public class Tournament {
	private HashMap<String, String> playerType = new HashMap<>();
	private int noOfTurns;
	private int noOfGames;
	private List<String> maps; 
	private JFrame frame;
	private JPanel panel ;
	private JLabel player1;
	private JLabel player2;
	private JLabel player3;
	private JLabel player4;
	private JLabel map1;
	private JLabel map2;
	private JLabel map3;
	private JLabel map4;
	private JLabel map5;

	private JComboBox cbPlayer1;
	private JComboBox cbPlayer2;
	private JComboBox cbPlayer3;
	private JComboBox cbPlayer4;

	private JButton fileButton1;
	private JButton fileButton2;
	private JButton fileButton3;
	private JButton fileButton4;
	private JButton fileButton5;

	private JFileChooser fileChooser;
	private FileNameExtensionFilter filenameFilter;

	private String playercount;
	private String mapCount;

	private String playersTypes[] = { "Aggressive", "Benevolent", "Random", "Cheater" };
	private JComboBox playerCountCombo;
	private JComboBox mapCountCombo;
	private JComboBox gamesCount;
	private JComboBox turns;

	private String[] playerCounterArray = {"2", "3", "4", "5", "6" };
	private String[] mapArray = {"1","2","3", "4", "5"};


	private JLabel countLabel;
	private JLabel mapLabel;
	private JLabel gamesLabel;
	private JLabel turnsLabel;

	private JButton okayButton;
	
	private List<String> filesSelected;


	Tournament()
	{
		tournamentView();
	}

	public void tournamentView()
	{
		frame = new JFrame();
		panel = new JPanel();

		frame.setTitle("Touranment Mode");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(800,800);
		panel.setSize(700,700);
		panel.setBackground(Color.cyan);
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);        
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		player1 = new JLabel();
		player2 = new JLabel();
		player3 = new JLabel();
		player4 = new JLabel();

		map1 = new JLabel();
		map2 = new JLabel();
		map3 = new JLabel();
		map4 = new JLabel();
		map5 = new JLabel();


		countLabel = new JLabel("Select the number of Players");
		mapLabel=  new JLabel("Select the number of Maps");
		gamesLabel= new JLabel("No of Games");
		playerCountCombo = new JComboBox(playerCounterArray);
		mapCountCombo= new JComboBox(mapArray);
		okayButton = new JButton("OK");

		player1.setText("Player1");
		player1.setVisible(true);
		player2.setText("Player2");
		player2.setVisible(true);
		player3.setText("Player3");
		player3.setVisible(true);
		player4.setText("Player4");
		player4.setVisible(true);

		map1.setText("Map1");
		map1.setVisible(true);
		map2.setText("Map2");
		map2.setVisible(true);
		map3.setText("Map3");
		map3.setVisible(true);
		map4.setText("Map4");
		map4.setVisible(true);
		map5.setText("Map5");
		map5.setVisible(true);

		cbPlayer1 = new JComboBox(playersTypes);
		cbPlayer2 = new JComboBox(playersTypes);
		cbPlayer3 = new JComboBox(playersTypes);
		cbPlayer4 = new JComboBox(playersTypes);


		fileButton1 =new JButton("Map1");
		fileButton2 =new JButton("Map2");
		fileButton3 =new JButton("Map3");
		fileButton4 =new JButton("Map4");
		fileButton5 =new JButton("Map5");

		gbc.gridx = 0;
		gbc.gridy = 0;

		panel.add(countLabel, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		panel.add(playerCountCombo, gbc);

		gbc.gridx = 8;
		gbc.gridy = 0;

		panel.add(mapLabel, gbc);
		gbc.gridx = 12;
		gbc.gridy = 0;

		panel.add(mapCountCombo, gbc);
		frame.add(panel);

		playerCountCombo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				playercount = (String) playerCountCombo.getSelectedItem();

				if (playercount.equals("2")||playercount.equals("3") || playercount.equals("4") ) {

					gbc.gridx = 0;
					gbc.gridy = 4;

					panel.add(player1, gbc);
					gbc.gridx = 4;
					gbc.gridy = 4;

					panel.add(cbPlayer1, gbc);

					gbc.gridx = 0;
					gbc.gridy = 6;
					panel.add(player2, gbc);
					gbc.gridx = 4;
					gbc.gridy = 6;
					panel.add(cbPlayer2, gbc);

					panel.remove(cbPlayer3);
					panel.remove(player3);
					panel.remove(cbPlayer4);
					panel.remove(player4);


				}
				if (playercount.equals("3")|| playercount.equals("4") ) {

					gbc.gridx = 0;
					gbc.gridy = 8;
					panel.add(player3, gbc);
					gbc.gridx = 4;
					gbc.gridy = 8;
					panel.add(cbPlayer3, gbc);

					panel.remove(cbPlayer4);
					panel.remove(player4);
				}
				if (playercount.equals("4") ) {
					gbc.gridx = 0;
					gbc.gridy = 10;
					panel.add(player4, gbc);
					gbc.gridx = 4;
					gbc.gridy = 10;
					panel.add(cbPlayer4, gbc);

				}


			}
		});


		mapCountCombo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				mapCount = (String) mapCountCombo.getSelectedItem();

				if (mapCount.equals("2")||mapCount.equals("3") || mapCount.equals("4") ||mapCount.equals("5") ) {

					gbc.gridx = 8;
					gbc.gridy = 4;
					panel.add(map1, gbc);
					gbc.gridx = 12;
					gbc.gridy = 4;
					panel.add(fileButton1, gbc);

					gbc.gridx = 8;
					gbc.gridy = 6;
					panel.add(map2, gbc);
					gbc.gridx = 12;
					gbc.gridy = 6;
					panel.add(fileButton2, gbc);
					panel.remove(fileButton3);
					panel.remove(map3);
					panel.remove(fileButton4);
					panel.remove(map4);
					panel.remove(fileButton5);
					panel.remove(map5);


				}
				if (mapCount.equals("3") ||mapCount.equals("4")||mapCount.equals("5")) {

					gbc.gridx = 8;
					gbc.gridy = 8;
					panel.add(map3, gbc);
					gbc.gridx = 12;
					gbc.gridy = 8;
					panel.add(fileButton3, gbc);
					panel.remove(fileButton4);
					panel.remove(map4);
					panel.remove(fileButton5);
					panel.remove(map5);
				}
				if (mapCount.equals("4") ||mapCount.equals("5")) {
					gbc.gridx = 8;
					gbc.gridy = 10;
					panel.add(map4, gbc);
					gbc.gridx = 12;
					gbc.gridy = 10;
					panel.add(fileButton4, gbc);
					panel.remove(fileButton5);
					panel.remove(map5);

				}

				if (mapCount.equals("5")) {
					gbc.gridx = 8;
					gbc.gridy = 12;

					panel.add(map5, gbc);
					gbc.gridx = 12;
					gbc.gridy = 12;

					panel.add(fileButton5, gbc);

				}
				gbc.gridx = 3;
				gbc.gridy = 16;
				panel.add(okayButton, gbc);
				frame.validate();
				frame.repaint();
				frame.repaint();

			}
		});



	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fileButton1|| e.getSource() == fileButton2||e.getSource() == fileButton3||e.getSource() == fileButton4||e.getSource() == fileButton5)
		{
			fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Map1");
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			filenameFilter = new FileNameExtensionFilter(".map Files", "map", "map");
			fileChooser.setFileFilter(filenameFilter);
			File file = fileChooser.getSelectedFile();
			String fileName = file.getName();
			filesSelected.add(file.getAbsolutePath());
		}
	}  
}
