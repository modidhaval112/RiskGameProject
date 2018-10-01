package riskGameView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Window.Type;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollBar;

public class GamePlayWindow 
{

	private JFrame frmConcordiaConquest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GamePlayWindow window = new GamePlayWindow();
					window.frmConcordiaConquest.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GamePlayWindow() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConcordiaConquest = new JFrame();
		frmConcordiaConquest.setTitle("Concordia Conquest - Play");
		frmConcordiaConquest.setBounds(100, 100, 450, 300);
		frmConcordiaConquest.setSize(1000, 800);
		frmConcordiaConquest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{237, 319, 312, 116, 0};
		gridBagLayout.rowHeights = new int[]{14, 23, 23, 23, 23, 85, 14, 507, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmConcordiaConquest.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblContinents = new JLabel("Continents");
		GridBagConstraints gbc_lblContinents = new GridBagConstraints();
		gbc_lblContinents.anchor = GridBagConstraints.NORTH;
		gbc_lblContinents.insets = new Insets(0, 0, 5, 5);
		gbc_lblContinents.gridx = 0;
		gbc_lblContinents.gridy = 0;
		frmConcordiaConquest.getContentPane().add(lblContinents, gbc_lblContinents);
		
		JLabel lblCountries = new JLabel("Countries");
		GridBagConstraints gbc_lblCountries = new GridBagConstraints();
		gbc_lblCountries.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountries.gridx = 1;
		gbc_lblCountries.gridy = 0;
		frmConcordiaConquest.getContentPane().add(lblCountries, gbc_lblCountries);
		
		JLabel lblAdjecentCountries = new JLabel("Neighbouring Countries");
		GridBagConstraints gbc_lblAdjecentCountries = new GridBagConstraints();
		gbc_lblAdjecentCountries.anchor = GridBagConstraints.NORTH;
		gbc_lblAdjecentCountries.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdjecentCountries.gridx = 2;
		gbc_lblAdjecentCountries.gridy = 0;
		frmConcordiaConquest.getContentPane().add(lblAdjecentCountries, gbc_lblAdjecentCountries);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.gridheight = 5;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		frmConcordiaConquest.getContentPane().add(scrollPane_3, gbc_scrollPane_3);
		
		JTextArea textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		
		JScrollBar scrollBar_3 = new JScrollBar();
		scrollPane_3.setRowHeaderView(scrollBar_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_4.gridheight = 5;
		gbc_scrollPane_4.gridx = 1;
		gbc_scrollPane_4.gridy = 1;
		frmConcordiaConquest.getContentPane().add(scrollPane_4, gbc_scrollPane_4);
		
		JTextArea textArea_6 = new JTextArea();
		scrollPane_4.setViewportView(textArea_6);
		
		JScrollBar scrollBar_4 = new JScrollBar();
		scrollPane_4.setRowHeaderView(scrollBar_4);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridheight = 5;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 1;
		frmConcordiaConquest.getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		
		JTextArea textArea_5 = new JTextArea();
		scrollPane_2.setViewportView(textArea_5);
		
		JScrollBar scrollBar_2 = new JScrollBar();
		scrollPane_2.setRowHeaderView(scrollBar_2);
		
		JButton btnReinforce = new JButton("Reinforce");
		GridBagConstraints gbc_btnReinforce = new GridBagConstraints();
		gbc_btnReinforce.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReinforce.anchor = GridBagConstraints.NORTH;
		gbc_btnReinforce.insets = new Insets(0, 0, 5, 0);
		gbc_btnReinforce.gridx = 3;
		gbc_btnReinforce.gridy = 1;
		frmConcordiaConquest.getContentPane().add(btnReinforce, gbc_btnReinforce);
		
		JButton btnAttack = new JButton("Attack");
		GridBagConstraints gbc_btnAttack = new GridBagConstraints();
		gbc_btnAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAttack.anchor = GridBagConstraints.NORTH;
		gbc_btnAttack.insets = new Insets(0, 0, 5, 0);
		gbc_btnAttack.gridx = 3;
		gbc_btnAttack.gridy = 2;
		frmConcordiaConquest.getContentPane().add(btnAttack, gbc_btnAttack);
		
		JButton btnFortify = new JButton("Fortify");
		GridBagConstraints gbc_btnFortify = new GridBagConstraints();
		gbc_btnFortify.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFortify.anchor = GridBagConstraints.NORTH;
		gbc_btnFortify.insets = new Insets(0, 0, 5, 0);
		gbc_btnFortify.gridx = 3;
		gbc_btnFortify.gridy = 3;
		frmConcordiaConquest.getContentPane().add(btnFortify, gbc_btnFortify);
		
		JButton btnEndTurn = new JButton("End Turn");
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEndTurn.anchor = GridBagConstraints.NORTH;
		gbc_btnEndTurn.insets = new Insets(0, 0, 5, 0);
		gbc_btnEndTurn.gridx = 3;
		gbc_btnEndTurn.gridy = 4;
		frmConcordiaConquest.getContentPane().add(btnEndTurn, gbc_btnEndTurn);
		
		JButton btnMenu = new JButton("Menu");
		GridBagConstraints gbc_btnMenu = new GridBagConstraints();
		gbc_btnMenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMenu.anchor = GridBagConstraints.NORTH;
		gbc_btnMenu.insets = new Insets(0, 0, 5, 0);
		gbc_btnMenu.gridx = 3;
		gbc_btnMenu.gridy = 5;
		frmConcordiaConquest.getContentPane().add(btnMenu, gbc_btnMenu);
		
		JLabel lblSummary = new JLabel("Summary");
		GridBagConstraints gbc_lblSummary = new GridBagConstraints();
		gbc_lblSummary.anchor = GridBagConstraints.WEST;
		gbc_lblSummary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSummary.gridx = 1;
		gbc_lblSummary.gridy = 6;
		frmConcordiaConquest.getContentPane().add(lblSummary, gbc_lblSummary);
		
		JLabel lblCurrentAction = new JLabel("Current Action");
		GridBagConstraints gbc_lblCurrentAction = new GridBagConstraints();
		gbc_lblCurrentAction.anchor = GridBagConstraints.NORTH;
		gbc_lblCurrentAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentAction.gridx = 2;
		gbc_lblCurrentAction.gridy = 6;
		frmConcordiaConquest.getContentPane().add(lblCurrentAction, gbc_lblCurrentAction);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		frmConcordiaConquest.getContentPane().add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 7;
		frmConcordiaConquest.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_1.setViewportView(textArea_2);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane_1.setRowHeaderView(scrollBar_1);
	}
}
