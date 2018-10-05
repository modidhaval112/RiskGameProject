package riskGameView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;

/**
 * The functionality of the class is to present the viewer a menu to start the
 * game , exit etc
 *
 * @author Team 13 Fall 2018
 */

public class GameLauncher extends JFrame implements ActionListener {

	private JFrame gameFrame;
	private TitledBorder border;
	private JPanel gamePanel;
	private JButton startGameButton;
	private JButton createMapButton;
	private JButton editMapButton;
	private JButton exitButton;
	private JLabel titleLabel;
	private PlayerCount playerCount;
	private CreateMapFile createMapFile;
	

	public GameLauncher() {
		try {
			initUI();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initUI() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		gameFrame = new JFrame("Concordia Conquest");
		try {
            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("rsz_aoe_bk.jpg"))));
            gameFrame.setContentPane(label);
        } catch (IOException e) {
            e.printStackTrace();
        }
		gameFrame.setSize(500, 500);
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

		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
	
		
		startGameButton.setBounds(200, 90, 100, 30);
		startGameButton.addActionListener(this);

		createMapButton.setBounds(200, 190, 100, 30);
		createMapButton.addActionListener(this);

		editMapButton.setBounds(200, 290, 100, 30);
		editMapButton.addActionListener(this);
		
		exitButton.setBounds(200, 390, 100, 30);
		exitButton.addActionListener(this);
		
	
		gameFrame.getContentPane().add(titleLabel);
		gameFrame.getContentPane().add(startGameButton);
		gameFrame.getContentPane().add(createMapButton);
		gameFrame.getContentPane().add(editMapButton);
		gameFrame.getContentPane().add(exitButton);
		

		gameFrame.getContentPane().add(gamePanel);
		gameFrame.setVisible(true);


	}

	public static void main(String[] args) {
		GameLauncher ex = new GameLauncher();

	}

	public void actionPerformed(ActionEvent event) {

		String action = event.getActionCommand();
		if (event.getSource() == startGameButton) {
			System.out.println("####  startGameButton is clicked ####");
			/*PlayerCounter pc = new PlayerCounter();
			
			pc.setVisible(true);*/
			gameFrame.setVisible(false);
			playerCount = new PlayerCount();
			playerCount.setVisible(true);
			
		} else if (event.getSource() == createMapButton) {
			System.out.println("#### createMapButton  is clicked ####");
			gameFrame.setVisible(false);
			createMapFile = new CreateMapFile();
			
			
		} else if (event.getSource() == editMapButton) {
			System.out.println("#### editMapMapButton  is clicked ####");
			gameFrame.setVisible(false);
			PlayerCount p = new PlayerCount();
			
		} else if (event.getSource() == exitButton) {
			System.exit(0);
			
		}

	}

	
	
	                                                                           
	


}