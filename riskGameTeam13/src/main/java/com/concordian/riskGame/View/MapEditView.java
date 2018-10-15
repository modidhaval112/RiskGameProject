package com.concordian.riskGame.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordian.riskGame.Model.MapContents;

/**
 * @author saich
 *
 */
public class MapEditView extends java.awt.Frame {

	private JLabel headingLabel = new JLabel();
	private JLabel label1 = new JLabel();
	private JButton removeContinent = new JButton();
	private JButton removeCountry = new JButton();
	private JButton addCountry = new JButton();
	private JButton addContinent = new JButton();
	private JButton renameContinent = new JButton();
	private JButton renameCountry = new JButton();
	private List<Country> CountriesList;
	private MapContents mapContentsObj;




	private JFrame frame = new JFrame();
	private JPanel   panel = new JPanel();
	
	private JTextField AddText = new JTextField("Enter Country or Continent to add or rename", 20);
	public MapEditView() {
		MapDefinition();
	}

	private void MapDefinition() {
		headingLabel.setText(" Please Choose your operation to rename,remove or add Continent or Country from Map  ");
		headingLabel.setVisible(true);

		removeContinent.setText("RemoveContinent");
		removeContinent.setName("removeContinentButton");
		removeContinent.setVisible(true);

		removeCountry.setText("RemoveCountry");
		removeCountry.setName("removeCountryButton");
		removeCountry.setVisible(true);


		addCountry.setText("AddCountry");
		addCountry.setName("addCountryButton");
		addCountry.setVisible(true);

		addContinent.setText("AddContinent");
		addContinent.setName("addContinentButton");
		addContinent.setVisible(true);


		renameContinent.setText("RenameContinent");
		renameContinent.setName("renameContinentButton");
		renameContinent.setVisible(true);

		renameCountry.setText("RenameCountry");
		renameCountry.setName("renameCountryButton");
		renameCountry.setVisible(true);






		frame.setTitle("Edit Map");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(400,400);
		panel.setBackground(Color.cyan);
		GridBagLayout layout = new GridBagLayout();


		panel.setLayout(layout);        
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(label1,gbc);


		gbc.gridx = 2;
		gbc.gridy = 4;
		panel.add(addCountry,gbc);


		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(addContinent,gbc);


		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(renameCountry,gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		panel.add(renameContinent,gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;

		panel.add(removeCountry,gbc);

		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 2;

		panel.add(removeContinent,gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 4;

		panel.add(AddText,gbc);

		DefaultListModel<String> l1 = new DefaultListModel<>();  
		l1.addElement("Item1");  
		l1.addElement("Item2");  
		l1.addElement("Item3");  
		l1.addElement("Item4");  
		l1.addElement("Item4");  
		l1.addElement("Item4");  
		l1.addElement("Item4");  
		l1.addElement("Item4");  
		l1.addElement("Item4");  
		l1.addElement("Item4");  

		JList<String> list = new JList<>(l1);  
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		//list.setBounds(100,100, 75,75);  
		listScroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Countries List",TitledBorder.CENTER, TitledBorder.TOP));



		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;

		panel.add(listScroller,gbc);


		mapContentsObj = new MapContents();
		//CountriesList=mapContentsObj.getCountries();

		DefaultListModel<String> l2 = new DefaultListModel<>();  
		l2.addElement("Item1");  
		l2.addElement("Item2");  
		l2.addElement("Item3");  
		l2.addElement("Item4");  
		JList<Country> list2 = new JList<>();  
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller2 = new JScrollPane(list2);
		//list.setBounds(100,100, 75,75);  
		listScroller2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Countinents List",TitledBorder.CENTER, TitledBorder.TOP));


		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;

		panel.add(listScroller2,gbc);

		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}
}



