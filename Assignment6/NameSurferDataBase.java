import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import acmx.export.java.io.FileReader;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the requested
	 * file does not exist or if an error occurs as the file is being read.
	 */
	private HashMap<String, NameSurferEntry> base = new HashMap<String, NameSurferEntry>();
	private BufferedReader rd = null;

	public NameSurferDataBase(String filename){
		// You fill this in //
		BufferedReader rd = null;
		String info = "";
		try {
			rd = new BufferedReader(new FileReader(filename));
			while (info != null) {//while there is a line
				info = rd.readLine();
				if (info != null) {
					String[] vals = info.split(" ");//reads line and splits it
					String key = vals[0];//name is the key
					NameSurferEntry dox = new NameSurferEntry(info);//makes name surfer entry withthe whole line(name + 11 nums)
					base.put(key, dox);//puts info in hashmap
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 * 
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		if(base.containsKey(name))
		{
		return base.get(name);//returns entry
		}
		else
		{
			return null;
		}
		}
}
