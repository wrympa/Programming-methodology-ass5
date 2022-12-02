
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import acmx.export.java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String[] a;
	private String Name="";
	private int[] ranks = new int[NDECADES];

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers giving
	 * the rank of that name for each decade.
	 * 
	 */
	public NameSurferEntry(String line) {//gets line
		if (line != null) {
			a = line.split(" ");//makes a string array
		}
		Name = a[0];//name is the first item
		for(int i=1; i<NDECADES+1; i++)
		{
			ranks[i-1] = Integer.parseInt(a[i]);//makes array from remaining items
		}
		// You fill this in //
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 * 
	 * @throws IOException
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return Name;//returns name
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The decade
	 * value is an integer indicating how many decades have passed since the first
	 * year in the database, which is given by the constant START_DECADE. If a name
	 * does not appear in a decade, the rank value is 0.
	 * 
	 * @throws IOException
	 */
	public int getRank(int decade){
		// You need to turn this stub into a real implementation //
		return ranks[decade];//returns name based on decade
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		return Name + Arrays.toString(ranks);
	}
}
