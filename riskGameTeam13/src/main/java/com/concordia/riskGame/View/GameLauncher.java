package com.concordia.riskGame.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;



/**
 * The functionality of the class is to present the viewer a menu to start the
 * game , exit etc
 *
 * @author Darwin Anirudh -Team 13
 * @author Dheeraj As - Team 13
 */
public class GameLauncher extends JFrame implements ActionListener {

	private JFrame gameFrame;
	private TitledBorder border;
	private JPanel gamePanel;
	private JButton startGameButton;
	private JButton createMapButton;
	private JButton editMapButton;
	private JButton tournamentButton;
	private JButton loadGameButton;
	private JButton exitButton;
	private JLabel titleLabel;
	private PlayerCount playerCount;
	private CreateMapFile createMapFile;
	private MapEditView editObject;

	/**
	 * This method launches the game and displays the start menu
	 */
	public GameLauncher() {
		try {
			initUI();
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
		}

	}

	/**
	 * this method initialize the UI of the game
	 */
	public void initUI() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		gameFrame = new JFrame("Concordia Conquest");
		gameFrame.setVisible(true);
		/*try {
			JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/rsz_aoe_bk.jpg"))));
			gameFrame.setContentPane(label);
		} catch (IOException e) {
			System.out.println("Error Message : " + e.getMessage());
		}*/
		gameFrame.setSize(500, 700);
		gameFrame.setLocation(500,200);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		border = new TitledBorder("Concordia Conquest");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);

		gamePanel = new JPanel();
		gamePanel.setBorder(border);

		titleLabel = new JLabel("Concordia Conquest");
		startGameButton = new JButton("Start Game");
		startGameButton.setFont(new Font("Georgia", Font.PLAIN, 10));
		startGameButton.setBackground(UIManager.getColor("Button.highlight"));
		startGameButton.setForeground(new Color(102, 0, 0));
		createMapButton = new JButton("Create Map");
		createMapButton.setForeground(new Color(102, 0, 0));
		createMapButton.setFont(new Font("Georgia", Font.PLAIN, 10));
		editMapButton = new JButton("Edit map");
		editMapButton.setFont(new Font("Georgia", Font.PLAIN, 10));
		editMapButton.setBackground(UIManager.getColor("Button.highlight"));
		startGameButton.setForeground(new Color(102, 0, 0));
		exitButton = new JButton("Exit");
		exitButton.setForeground(new Color(102, 0, 0));
		exitButton.setFont(new Font("Georgia", Font.PLAIN, 10));
		
		tournamentButton = new JButton("Torunament Mode");
		loadGameButton = new JButton("Load Game");

		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));

		startGameButton.setBounds(200, 90, 140, 30);
		startGameButton.addActionListener(this);

		createMapButton.setBounds(200, 190, 140, 30);
		createMapButton.addActionListener(this);

		editMapButton.setBounds(200, 290, 140, 30);
		editMapButton.addActionListener(this);

		tournamentButton.setBounds(200, 390, 140, 30);
		tournamentButton.addActionListener(this);
		
		loadGameButton.setBounds(200, 490, 140, 30);
		loadGameButton.addActionListener(this);
		
		exitButton.setBounds(200, 590, 140, 30);
		exitButton.addActionListener(this);
		
		

		gameFrame.getContentPane().add(titleLabel);
		gameFrame.getContentPane().add(startGameButton);
		gameFrame.getContentPane().add(createMapButton);
		gameFrame.getContentPane().add(editMapButton);
		gameFrame.getContentPane().add(tournamentButton);
		gameFrame.getContentPane().add(loadGameButton);
		gameFrame.getContentPane().add(exitButton);
		gameFrame.getContentPane().add(gamePanel);


	}

	/**
	 * main method
	 * @param args String array.
	 */
	public static void main(String[] args) {
		GameLauncher ex = new GameLauncher();

	}

	/**
	 * This method takes an event as an input and performs task depending on the event
	 */
	public void actionPerformed(ActionEvent event) {

		String action = event.getActionCommand();
		if (event.getSource() == startGameButton) {
			System.out.println("####  startGameButton is clicked ####");
			gameFrame.setVisible(false);
			playerCount = new PlayerCount();
			playerCount.setVisible(true);

		} else if (event.getSource() == createMapButton) {
			System.out.println("#### createMapButton  is clicked ####");
			gameFrame.setVisible(false);
			createMapFile = new CreateMapFile();
			createMapFile.createMap();


		} else if (event.getSource() == editMapButton) {
			System.out.println("#### editMapMapButton  is clicked ####");
			gameFrame.setVisible(false);
			editObject= new MapEditView();
			editObject.EditMapFileChoose();

		} 
		else if (event.getSource() == tournamentButton) {
			System.out.println("#### tournamentButton  is clicked ####");
		
		}
		
		else if (event.getSource() == loadGameButton) {
			System.out.println("#### loadGameButton  is clicked ####");
		

		}
		else if (event.getSource() == exitButton) {
			System.exit(0);

		}

	}
}