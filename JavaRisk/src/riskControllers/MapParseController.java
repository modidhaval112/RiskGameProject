package riskControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import riskGameView.PlayerCount;

/**
 * This Class has the implementation of reading the .map file and displaying the countries and continents on the console.
 * @author Dheeraj As
 *
 */
public class MapParseController 
{

	private File file;
	private String currentLine;
	private String filePath;

	public void MapParser(String filePath) 
	{
		try 
		{

			File f = new File(filePath);

			BufferedReader bufferReaderForFile = new BufferedReader(new FileReader(f));
			String st, co, cu;

			HashMap<String, String> cont = new HashMap<>();
			HashMap<String, String> cou = new HashMap<>();

			while ((st = bufferReaderForFile.readLine()) != null) 
			{
				
				int i = 0;
				if (st.startsWith("[")) {

					String id = st.substring(st.indexOf("[") + 1, st.indexOf("]"));

					if (id.equalsIgnoreCase("Continents")) {

						while ((co = bufferReaderForFile.readLine()) != null && !co.startsWith("[")) {
							if (!co.isEmpty()) {
								System.out.println("Inside " + co);
								String[] data = co.split("=");
								System.out.println("0 :" + data[0]);
								System.out.println("1 :" + data[1]);
								cont.put(data[0], data[1]);

							}
							bufferReaderForFile.mark(0);
						}

						bufferReaderForFile.reset();

					}

					if (id.equalsIgnoreCase("Territories")) 
					{

						while ((cu = bufferReaderForFile.readLine()) != null && !cu.startsWith("[")) 
						{
							if (!cu.isEmpty())
								System.out.println(" cu " + cu);
							String[] data = cu.split(",", 2);

							if (!data[0].isEmpty())

							{

								int index = 0;
								for (int ii = 0; ii < 4; ii++)
									index = cu.indexOf(",", index + 1);

								System.out.println(cu.substring(0, index));
								System.out.println(cu.substring(index + 1));

								String fp = cu.substring(0, index);
								String sp = cu.substring(index + 1);

								String[] c = fp.split(",");

								System.out.println("fp :" + c[0]);
								System.out.println("sp :" + sp);

								cou.put(c[0], sp);

							}
						}

					}

				}

			}

			System.out.println("Display contients and their values");
			Iterator it = cont.entrySet().iterator();

			while (it.hasNext()) 
			{
				Map.Entry pair = (Map.Entry) it.next();
				System.out.println("Key is " + pair.getKey() + " value is   " + pair.getValue());
			}

			System.out.println("Display countries and their neighbouring countries");
			Iterator it2 = cou.entrySet().iterator();

			while (it2.hasNext()) 
			{
				Map.Entry pair = (Map.Entry) it2.next();
				System.out.println("Key is " + pair.getKey() + " value is   " + pair.getValue());
			}

		} 
		
		catch (Exception e)
		{

		}

	}

	public void ValidateMap(String filePath) 
	{
		file = new File(filePath);

		try {
			BufferedReader bufferReaderForFile = new BufferedReader(new FileReader(file));

			while ((currentLine = bufferReaderForFile.readLine()) != null) {

				if (currentLine.contains("[Map]")) {

				} else {

				}
			}
		} 
		
		catch (IOException e) 
		{

			e.printStackTrace();
		}
	}

}
