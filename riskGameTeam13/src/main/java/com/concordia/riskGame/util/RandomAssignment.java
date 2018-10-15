package com.concordia.riskGame.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.entity.Player;

public class RandomAssignment {

	
	public Player randonAssignmentMethod(int noOfPlayers, List<Country> countryList) throws IOException {
		
/*		System.out.println("Please enter No Of Players : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer noOfPlayers = playerList.size();
		
		System.out.println("Please enter No Of Continents : ");
		br = new BufferedReader(new InputStreamReader(System.in));
		Integer noOfContinents = Integer.parseInt(br.readLine());
		
		System.out.println("Please enter No Of Countries : ");
		br = new BufferedReader(new InputStreamReader(System.in));
		Integer noOfCountries = Integer.parseInt(br.readLine());*/
		
		//int noOfPlayers = playerList.size();
		int noOfCountries = countryList.size();
		
		System.out.println("Players : " + noOfPlayers + " Countries : " + noOfCountries);
		
		List<Player> playersList = new ArrayList<Player>();
		for (int i = 0; i < noOfPlayers; i++) {
			Player playerObject = new Player("p"+(i+1));
			playersList.add(playerObject);
		}
		
/*		for (int i = 0; i < noOfPlayers; i++) {
			System.out.println("Player List : " + playersList.get(i).getName());
		}
*/
/*		List<Continent> continentsList = new ArrayList<Continent>();
		for (int i = 0; i < noOfContinents; i++) {
			Continent continentObject = new Continent("c"+(i+1));
			continentsList.add(continentObject);
		}*/
		
		/*for (int i = 0; i < noOfContinents; i++) {
			System.out.println("Continents List : " + continentsList.get(i).getContinentName());
		}*/
		
		//List<Country> countryList = new ArrayList<Country>();
		/*for (int i = 0; i < noOfCountries; i++) {
			Country countryObject = new Country("n"+(i+1));
			countryList.add(countryObject);
		}*/
		
		/*for (int i = 0; i < noOfCountries; i++) {
			System.out.println("Country List : " + countryList.get(i).getCountryName());
		}*/
		
		RandomAssignment inputObject = new RandomAssignment();
		int[] dividedValuesList = inputObject.divider(noOfCountries, noOfPlayers);
		
		Map<Continent, List<Country>> continentAssign = new HashMap<>();
		List<Country> newCountryList = new ArrayList<Country>(countryList);

/*		int k = 0;
		for (int i = 0; i < noOfContinents; i++) {
			List<Country> countryList1 = new ArrayList<>();
			for (int j = 0; j < dividedValuesList[i]; j++) {
				int rando = (int) ((Math.random() * newCountryList.size()));
				countryList1.add(newCountryList.get(rando));
				newCountryList.remove(rando);
			}
			continentsList.get(i).setCountries(countryList1);
			continentAssign.put(continentsList.get(i), countryList1);
		}*/
		System.out.println("\n");
		for ( Continent key : continentAssign.keySet() ) {
		    System.out.println("Continent Name : " +  key.getContinentName() );
		    for (int i = 0; i < key.getCountries().size(); i++) {
				System.out.println("     Assigned Countries : " + continentAssign.get(key).get(i).getCountryName());
			}
		    System.out.println("");
		}
		
		
		
		dividedValuesList = inputObject.divider(noOfCountries, noOfPlayers);
		Map<Player, List<Country>> playerAssign = new HashMap<>();
		newCountryList = new ArrayList<Country>(countryList);
		Player player = new Player();
		
		int k = 0;
		for (int i = 0; i < noOfPlayers; i++) {
			List<Country> countryList1 = new ArrayList<>();
			for (int j = 0; j < dividedValuesList[i]; j++) {		
				int rando = (int) ((Math.random() * newCountryList.size()));
				countryList1.add(newCountryList.get(rando));
				newCountryList.remove(rando);
			}
			playersList.get(i).setAssignedCountries(countryList1);
			playerAssign.put(playersList.get(i), countryList1);
		}

		player.setPlayerAssign(playerAssign);
		
		/*System.out.println("\n");
		for ( Player key : playerAssign.keySet() ) {
		    System.out.println("Player Name : " +  key.getName() );
		    for (int i = 0; i < key.getAssignedCountries().size(); i++) {
				System.out.println("     Assigned Of Countries : " + playerAssign.get(key).get(i).getCountryName());
			}
		    System.out.println("");
		}*/
		
		return player;
		
	}
	
	int[] divider(int number, int parts)
    {
		int[] randoms = new int[parts];
	    Arrays.fill(randoms, 1); // At least one
	    int remainder = number - parts;
	    Random random = new Random();
	    for (int i = 0; i < parts - 1 && remainder > 0; ++i) {
	        int diff = random.nextInt(remainder);
	        randoms[i] += diff;
	        remainder -= diff;
	   }
	   randoms[parts - 1] += remainder;
	   Arrays.sort(randoms);

	   // Reverse (for getting a descending array):
	   for (int i = 0, j = parts - 1; i < j; ++i, --j) {
	       int temp = randoms[i];
	       randoms[i] = randoms[j];
	       randoms[j] = temp;
	   }
        return randoms;
    }
}
