package com.concordia.riskGame.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.concordia.riskGame.model.Continent.Continent;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Map.MapOperations;
import com.concordia.riskGame.model.Map.MapParseController;
/**
 * @author saich
 *
 */
public class MapEditView extends java.awt.Frame {

	private JLabel headingLabel = new JLabel();
	private JLabel labecountries = new JLabel();
	private JButton removeContinent = new JButton();
	private JButton removeCountry = new JButton();
	private JButton addCountry = new JButton();
	private JButton addContinent = new JButton();
	private JButton renameContinent = new JButton();
	private JButton renameCountry = new JButton();
	private JButton addAdjacentCountry = new JButton();
	private JButton removeAdjacentCountry = new JButton();
	private JButton saveMap = new JButton();
	private JButton exitMap = new JButton();
	private JTextArea log = new JTextArea(30,30);
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filenameFilter;
	private String filePath = null;	
	private HashMap<Country, List<Country>> countryAndNeighbors;
	private HashMap<String, List<String>> countryAndNeighborsMap = new  HashMap<String, List<String>> () ;
	private HashMap<Continent, List<Country>> continentAndItsCountries;
	private HashMap<String, List<String>> continentsAndCountriesMap = new  HashMap<String, List<String>> () ;
	private List<String> Continents = new ArrayList<String>();
	private DefaultListModel<String> countries = new DefaultListModel<>(); 
	private DefaultListModel<String> countryNeighbours = new DefaultListModel<>();  
	private DefaultListModel<String> continents = new DefaultListModel<>(); 
	private DefaultListModel<String> continentCountries = new DefaultListModel<>(); 
	private String labels[]=new String [30];
	private MapParseController mapParseObject;
	private JFrame frame = new JFrame();
	private JPanel   panel = new JPanel();

	private JTextField AddText = new JTextField("Enter Country or Continent to add or rename", 20);
	public MapEditView() {
	}

	public void MapDefinition(HashMap<Country, List<Country>> countryAndNeighbors,HashMap<Continent, List<Country>> continentAndItsCountries,String filePath) {
		this.countryAndNeighbors=countryAndNeighbors;
		this.continentAndItsCountries=continentAndItsCountries;
		this.filePath=filePath;
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

		addAdjacentCountry.setText("AddAdjacentCountry");
		addAdjacentCountry.setName("addAdjacentCountryButton");
		addAdjacentCountry.setVisible(true);

		removeAdjacentCountry.setText("RemoveAdjacentCountry");
		removeAdjacentCountry.setName("removeAdjacentCountryButton");
		removeAdjacentCountry.setVisible(true);
		
		saveMap.setText("SaveMap");
		saveMap.setName("saveMap");
		saveMap.setVisible(true);

		exitMap.setText("ExitMap");
		exitMap.setName("exitMap");
		exitMap.setVisible(true);

		JScrollPane scroll = new JScrollPane(log);


		frame.setTitle("Edit Map");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(800,800);
		panel.setSize(700,700);
		panel.setBackground(Color.cyan);
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);        
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(labecountries,gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		panel.add(addCountry,gbc);

		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		panel.add(addContinent,gbc);


		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		panel.add(renameCountry,gbc);

		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		panel.add(renameContinent,gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		panel.add(removeCountry,gbc);

		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		panel.add(removeContinent,gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 4;
		panel.add(addAdjacentCountry,gbc);
		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.gridwidth = 4;
		panel.add(removeAdjacentCountry,gbc);
		
		gbc.gridx = 8;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 2;
		panel.add(saveMap,gbc);
         
		gbc.gridx = 8;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 2;
		panel.add(exitMap,gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 12;
		panel.add(AddText,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 12;
		gbc.weighty=1;


		panel.add(scroll,gbc);



		for (Map.Entry<Country, List<Country>> entry : countryAndNeighbors.entrySet())
		{

			List<Country>  countryNeighbours= entry.getValue();
			List<String> neighboursList = new ArrayList<String>();

			for (int i=0 ;i<countryNeighbours.size();i++) {


				neighboursList.add(countryNeighbours.get(i).getCountryName().toString());


			}
			countries.addElement(entry.getKey().getCountryName().toString());

			countryAndNeighborsMap.put(entry.getKey().getCountryName().toString(), neighboursList);


		}

		for (Map.Entry<Continent, List<Country>> entry : continentAndItsCountries.entrySet())
		{


			List<Country>  continentCountries= entry.getValue();
			List<String> CountriesList = new ArrayList<String>();

			for (int i=0 ;i<continentCountries.size();i++) {


				CountriesList.add(continentCountries.get(i).getCountryName().toString());


			}
			continents.addElement(entry.getKey().getContinentName().toString());

			continentsAndCountriesMap.put(entry.getKey().getContinentName().toString(), CountriesList);


		}


		JList<String> countriesJList = new JList<>(countries);  
		countriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		countriesJList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(countriesJList);
		listScroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Countries List",TitledBorder.CENTER, TitledBorder.TOP));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weighty=2;
		gbc.weightx=0.5;
		panel.add(listScroller,gbc);

		JList<String> jCN = new JList<>(countryNeighbours);  
		jCN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jCN.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller2 = new JScrollPane(jCN);
		listScroller2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Neighbours",TitledBorder.CENTER, TitledBorder.TOP));

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weighty=2;
		gbc.weightx=0.5;
		panel.add(listScroller2,gbc);

		JList<String> continentsJList = new JList<>(continents);  
		continentsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		continentsJList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane ContinentslistScroller = new JScrollPane(continentsJList);
		ContinentslistScroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Countnents List",TitledBorder.CENTER, TitledBorder.TOP));
		
		gbc.gridx = 6;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weightx=0.5;
		gbc.weighty=2;
		panel.add(ContinentslistScroller,gbc);

		JList<String> countinentCountriesJList = new JList<>(continentCountries);  
		countinentCountriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		countinentCountriesJList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane countinentCountrieslistScroller = new JScrollPane(countinentCountriesJList);
		countinentCountrieslistScroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ContinentCountries",TitledBorder.CENTER, TitledBorder.TOP));
		gbc.gridx = 9;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weightx=0.5;
		gbc.weighty=2;
		panel.add(countinentCountrieslistScroller,gbc);


		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				System.out.println("First index: " + listSelectionEvent.getFirstIndex());
				System.out.println(", Last index: " + listSelectionEvent.getLastIndex());
				System.out.println(listSelectionEvent.getSource().toString());
				boolean adjust = listSelectionEvent.getValueIsAdjusting();
				System.out.println(", Adjusting? " + adjust);
				if (!adjust) {
					JList list = (JList) listSelectionEvent.getSource();
					int selections[] = list.getSelectedIndices();
					Object selectionValues[] = list.getSelectedValues();
					for (int i = 0, n = selections.length; i < n; i++) {
						if (i == 0) {
							System.out.println(" Selections: ");
						}
						System.out.println(selections[i] + "/" + selectionValues[i] + " ");
						Country country=new Country(selectionValues[i].toString());

						System.out.println(country);

						List<String>countryNeighboursList=countryAndNeighborsMap.get(selectionValues[i].toString());
						System.out.println(countryNeighboursList);
						countryNeighbours.removeAllElements();
						if(countryNeighboursList!=null)
						{
						for(int k=0; k<countryNeighboursList.size(); k++)
						{
							countryNeighbours.addElement(countryNeighboursList.get(k));

						}
						}
					}
					frame.getContentPane().validate();
					frame.getContentPane().repaint();

				}
			}
		};



		ListSelectionListener continentsListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				System.out.println("First index: " + listSelectionEvent.getFirstIndex());
				System.out.println(", Last index: " + listSelectionEvent.getLastIndex());
				System.out.println(listSelectionEvent.getSource().toString());
				boolean adjust = listSelectionEvent.getValueIsAdjusting();
				System.out.println(", Adjusting? " + adjust);
				if (!adjust) {
					JList list = (JList) listSelectionEvent.getSource();
					int selections[] = list.getSelectedIndices();
					Object selectionValues[] = list.getSelectedValues();
					for (int i = 0, n = selections.length; i < n; i++) {
						if (i == 0) {
							System.out.println(" Selections: ");
						}
						System.out.println(selections[i] + "/" + selectionValues[i] + " ");


						List<String>countinentCountriesList=continentsAndCountriesMap.get(selectionValues[i].toString());
						System.out.println(countinentCountriesList);
						continentCountries.removeAllElements();
						if(countinentCountriesList!=null)
						{
						for(int k=0; k<countinentCountriesList.size(); k++)
						{
							continentCountries.addElement(countinentCountriesList.get(k));

						}
					}
					}
					frame.getContentPane().validate();
					frame.getContentPane().repaint();

				}
			}
		};


		countriesJList.addListSelectionListener(listSelectionListener);
		continentsJList.addListSelectionListener(continentsListener);

		removeContinent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s = (String) continentsJList.getSelectedValue();
				System.out.println("Continent Selected: " + s);
				setLog("Continent Removed: " + s);
				removeContinent(s);
				frame.validate();
				frame.repaint();
				panel.repaint();

			}
		});

		removeCountry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s = (String) countriesJList.getSelectedValue();
				System.out.println("Country Selected: " + s);
				setLog("Country Removed: " + s);
				removeCountry(s);

			}
		});

		addCountry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s =  continentsJList.getSelectedValue();


				if(continentsJList.isSelectionEmpty())
				{
					setLog("Select Continent to add Country");

				}
				else if(!s.equals("null"))
				{
					setLog("Country Input is : " + s);
					addCountry(AddText.getText(),continentsJList.getSelectedValue().toString());
				}

			}
		});

		addContinent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s =  continentsJList.getSelectedValue();

				if(AddText.getText().isEmpty())
				{
					setLog("Enter Continent in Textbox");

				}
				else
				{
					continentsAndCountriesMap.put(AddText.getText(),null);
					continents.addElement(AddText.getText());
					frame.validate();
					frame.repaint();
					panel.repaint();
				}

			}
		});

		renameContinent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s =  continentsJList.getSelectedValue();

				if(AddText.getText().isEmpty())
				{
					setLog("Enter Continent in Textbox");

				}
				else
				{
					setLog("Renaming Continent");
					renameContinent(s, AddText.getText());
					
				}

			}
		});
		
		renameCountry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s =  countriesJList.getSelectedValue();

				if(AddText.getText().isEmpty())
				{
					setLog("Enter Country in Textbox");

				}
				else
				{
					setLog("Renaming County");
					renameCountry(s, AddText.getText());
					
				}

			}
		});
		
		addAdjacentCountry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		
			{
				  String flag="F";
				  
				  for (Map.Entry<String, List<String>> entry : countryAndNeighborsMap.entrySet())
					{

	                 if(entry.getKey().equals(AddText.getText()))
	                 {
	                	 flag="T";

					}
					}
				String s =  countriesJList.getSelectedValue();

				if(AddText.getText().isEmpty())
				{
					setLog("Enter Adjacent Country in Textbox");

				}
				else
				{
					if(flag.equals("T"))
					{
					setLog("Adding Adjacent Country");
					addAdjacentCountry(s,AddText.getText());
					}
					
					else
					{
						setLog("Enter Country in the list");

					}
						
					
				}

			}
		});
		
		removeAdjacentCountry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
		
				String country =  countriesJList.getSelectedValue();
				String adjacentCountry=   jCN.getSelectedValue();
				
				
				if(AddText.getText().isEmpty())
				{
					setLog("Enter Adjacent Country in Textbox");

				}
				else
				{
					
					
					setLog("Remvoing Adjacent Country");
					removeAdjacentCountry(country,adjacentCountry);
					
					
					
					
					
				}

			}
		});
		
		
		saveMap.addActionListener(new ActionListener()
		
		{
			
			public void actionPerformed(ActionEvent e)
			{
				 HashMap<Continent, List<Country>> continentsWithItsCountries= new HashMap<>();
				 HashMap<Country, List<Country>> countriesWithItsNeighbours = new HashMap<>();

			for (Map.Entry<String, List<String>> entry : continentsAndCountriesMap.entrySet())
			{

				 List<Country> countries = new ArrayList<>() ;

				
				Continent continent = new Continent(entry.getKey());
				List<String>  continentCountries= entry.getValue();
				if(continentCountries!=null)
				{

				for (int i=0 ;i<continentCountries.size();i++) {


					
					 Country country = new Country(continentCountries.get(i).toString(), 0, 0, entry.getKey());

						countries.add(country);

					}

				}
				continentsWithItsCountries.put(continent, countries);

				}
			
			for (Map.Entry<String, List<String>> entry : countryAndNeighborsMap.entrySet())
			{

				List<Country> neighbourCountries = new ArrayList<>();
 
				Country country = new Country(entry.getKey(),getContinet(entry.getKey().toString()));

				List<String>  countryCountries= entry.getValue();
				List<String> CountriesList = new ArrayList<String>();


				if(countryCountries!=null)
				{
				for (int i=0 ;i<countryCountries.size();i++) {

					
					
						 Country countries = new Country(countryCountries.get(i).toString(), 0, 0, getContinet(countryCountries.get(i).toString()));
						 neighbourCountries.add(countries);
					

				}
				}
				countriesWithItsNeighbours.put(country,neighbourCountries);


			}
			
			
			exitMap.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					frame.dispose();


				}
			});
			
			MapContents mapContents = new MapContents();
			System.out.println();

			mapContents.setContinentAndItsCountries(continentsWithItsCountries);
	        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
			MapOperations mapOperations = new MapOperations();
			System.out.println("In operations");
			String fileName=filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length());
			try {
				mapOperations.writeMapFile(mapContents,fileName.substring(0, fileName.lastIndexOf(".")),filePath.substring(0,filePath.lastIndexOf("\\")));
			} catch (FileNotFoundException e1) {
				System.out.println("Error Message  : " + e1.getMessage());
			}

			
			}
		});
		


	}
	
	public String getContinet(String country)
	
	{
		String Country = null;
		for (Map.Entry<String, List<String>> entry : continentsAndCountriesMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			List<String> CountriesList = new ArrayList<String>();
			
			if(continentCountries!=null)
			{

			for (int i=0 ;i<continentCountries.size();i++) {

				if(continentCountries.get(i).equals(country))
						{
					Country=entry.getKey();
						}
				

			}
			}


		}
		return Country;
		
		
	}

	public void removeContinent(String Continent)
	{


		List<String> continentRemoveCountries =new ArrayList<String>();
		continentRemoveCountries= continentsAndCountriesMap.get(Continent);


        if(continentRemoveCountries!=null)
        {
		for (int i=0 ;i<continentRemoveCountries.size();i++) {

			removeCountry(continentRemoveCountries.get(i));
		}
        }

		System.out.println("In removing Continent");
		continentsAndCountriesMap.remove(Continent);
		continents.removeElement(Continent);
		
	}







	public void removeCountry(String Country)
	{
		System.out.println("In removing Continent");
		countryAndNeighborsMap.remove(Country);
		countries.removeElement(Country);

		for (Map.Entry<String, List<String>> entry : continentsAndCountriesMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			List<String> CountriesList = new ArrayList<String>();
			
			if(continentCountries!=null)
			{

			for (int i=0 ;i<continentCountries.size();i++) {


				if(!continentCountries.get(i).toString().equals(Country))
				{

					CountriesList.add(continentCountries.get(i).toString());
				}

			}
			}
			continentsAndCountriesMap.put(entry.getKey().toString(), CountriesList);


		}


		for (Map.Entry<String, List<String>> entry : countryAndNeighborsMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			List<String> CountriesList = new ArrayList<String>();



			for (int i=0 ;i<continentCountries.size();i++) {

				if(!continentCountries.get(i).equals(Country))
				{

					CountriesList.add(continentCountries.get(i).toString());
				}

			}
			countryAndNeighborsMap.put(entry.getKey().toString(), CountriesList);


		}


		frame.validate();
		frame.repaint();
		panel.repaint();

	}

	public void setLog(String logger)
	{
		String currentText = log.getText();
		String newLog = new Date() + " " +  logger;
		String appendLog =newLog + "\n" + currentText;
		log.setText(appendLog);

	}

	public void addCountry(String Country,String Continent)
	{
		System.out.println("In Adding Country");
		countries.addElement(Country);

		for (Map.Entry<String, List<String>> entry : continentsAndCountriesMap.entrySet())
		{

			List<String>  continentCountries= new ArrayList<String>();
			System.out.println(entry.getKey().toString());




			if(Continent.equals(entry.getKey().toString()) &!(entry.getValue()== null))

			{
				continentCountries= entry.getValue();

				continentCountries.add(Country);
				System.out.println("continent countries are"+continentCountries);
				continentsAndCountriesMap.put(entry.getKey().toString(), continentCountries);
			}


			if(entry.getValue()== null & Continent.equals(entry.getKey().toString()))
			{
				continentCountries.add(Country);
				System.out.println("continent countries loop are"+continentCountries);
				continentsAndCountriesMap.put(entry.getKey().toString(), continentCountries);
			}



		}

		countryAndNeighborsMap.put(Country,null);





		frame.validate();
		frame.repaint();
		panel.repaint();

	}

	public void renameContinent(String continent, String renameContinent)

	{
		String removeFlag="N";
		List<String>  continentCountriesList = null;
		for (Map.Entry<String, List<String>> entry : continentsAndCountriesMap.entrySet())
		{


			if(entry.getKey().equals(continent))


			{

				continentCountriesList= entry.getValue();


				removeFlag="Y";


			}
		}


		if(removeFlag.equals("Y"))
		{


			continentsAndCountriesMap.remove(continent);
			continents.removeElement(continent);
			continentsAndCountriesMap.put(renameContinent, continentCountriesList);
			continents.addElement(renameContinent);
		}

		frame.validate();
		frame.repaint();
		panel.repaint();

	} 
	
	
	public void renameCountry(String Country,String renamedCountry)
	{
		System.out.println("In renaming Country");
		

		for (Map.Entry<String, List<String>> entry : continentsAndCountriesMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			List<String> CountriesList = new ArrayList<String>();

			for (int i=0 ;i<continentCountries.size();i++) {


				if(!continentCountries.get(i).toString().equals(Country))
				{

					CountriesList.add(continentCountries.get(i).toString());
				}
				
				if(continentCountries.get(i).toString().equals(Country))
				{
					CountriesList.add(renamedCountry);
					
				}
				

			}
			continentsAndCountriesMap.put(entry.getKey().toString(), CountriesList);


		}

		List<String> CountriesRenameList = new ArrayList<String>();

		for (Map.Entry<String, List<String>> entry : countryAndNeighborsMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			List<String> CountriesList = new ArrayList<String>();
			
			if(entry.getKey().equals(Country))
			{
				System.out.println("before put");
				CountriesRenameList= continentCountries;
				//countryAndNeighborsMap.put(renamedCountry,continentCountries);
				System.out.println("after put");
			}


			for (int i=0 ;i<continentCountries.size();i++) {

				if(!continentCountries.get(i).equals(Country))
				{

					CountriesList.add(continentCountries.get(i).toString());
					System.out.println("after put1");

				}
				
				if(continentCountries.get(i).equals(Country))
				{

					CountriesList.add(renamedCountry);
					System.out.println("after put2");

				}

			}
			System.out.println("after put3");

		countryAndNeighborsMap.put(entry.getKey().toString(), CountriesList);
			System.out.println("after put4");



		}

		countries.removeElement(Country);

		countries.addElement(renamedCountry);
		countryAndNeighborsMap.remove(Country);
		countryAndNeighborsMap.put(renamedCountry,CountriesRenameList);
		

		
		frame.validate();
		frame.repaint();
		panel.repaint();

	}
	public void addAdjacentCountry(String country, String adjacentCountry)
	{
		
		for (Map.Entry<String, List<String>> entry : countryAndNeighborsMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			
			if(entry.getKey().equals(country))
			{
				continentCountries.add(adjacentCountry);
				countryAndNeighborsMap.put(country,continentCountries);
			}
			
		}

         
			
	}
	
	public void removeAdjacentCountry(String country, String adjacentCountry)
	{
		
		for (Map.Entry<String, List<String>> entry : countryAndNeighborsMap.entrySet())
		{


			List<String>  continentCountries= entry.getValue();
			
			if(entry.getKey().equals(country) & continentCountries!=null)
			{
				continentCountries.remove(adjacentCountry);
				countryAndNeighborsMap.put(country,continentCountries);
			}
			
		}

         
			
	}


	public void EditMapFileChoose() {	
		try {

			{
				System.out.println("#### In Choosing the file ####");
				filenameFilter = new FileNameExtensionFilter(" .map", "map", "map");
				//countFrame.setVisible(true);
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
					mapParseObject.editMapParsermapParser(selectedFile.getAbsolutePath().toString());

				}
			}
		}


		catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
		}
	}


}



