
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.awt.*;

public class NameSurferGraph_extension extends GCanvas implements NameSurferConstants, ComponentListener {

	// honestly couldve been done in the same way as the normal one but i liked this
	// one more

	private HashMap<String, GLine[]> linez = new HashMap<String, GLine[]>();
	private HashMap<String, GLabel[]> namez = new HashMap<String, GLabel[]>();
	private HashMap<String, Color> colorz = new HashMap<String, Color>();
	private ArrayList<String> namas = new ArrayList<String>();
	private GLine[] gorki = new GLine[NDECADES - 1];
	private GLabel[] saxelebi = new GLabel[NDECADES];
	private String Graph_name;
	private boolean isod = false;
	private NameSurferDataBase x = NameSurfer_extension.database;
	private int clr = 0;
	private Color graphcol;
	private String isodname;
	private boolean resid = false;
	private boolean failsafe = false;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph_extension() {
		addComponentListener(this);

		// You fill in the rest //
	}

	public void charcho() {// same as last one
		GLine acrs = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine acrs1 = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(acrs);
		add(acrs1);
		for (int i = 0; i < NDECADES; i++) {
			GLine fence = new GLine((getWidth() / NDECADES) * i, 0, (getWidth() / NDECADES) * i, getHeight());
			add(fence);
			GLabel year = new GLabel(Integer.toString(START_DECADE + i * (NDECADES - 1)));
			add(fence);
			add(year, (getWidth() / 11) * i + 5, getHeight() - 3);
		}
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {// same as last one
		linez.clear();
		namez.clear();
		namas.clear();
		removeAll();
		charcho();
		// You fill this in //
	}

	public void remove(String S) {// removes the specified line
		linez.remove(S);
		namez.remove(S);
		namas.remove(S);// removes the chosen name from the line, label, and name arrays
		removeAll();
		charcho();
		for (String Neym : namas) {// draws everything again except the chosen name
			addEntry(x.findEntry(Neym));
			drawlinesbyname(Neym);
			drawnamesbyname(Neym);
		}
		// You fill this in //
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 * 
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		Graph_name = entry.getName();
		if (clr % 5 == 0) {
			graphcol = (Color.BLACK);
		}
		if (clr % 5 == 1) {
			graphcol = (Color.RED);
		}
		if (clr % 5 == 2) {
			graphcol = (Color.BLUE);
		}
		if (clr % 5 == 3) {
			graphcol = (Color.GREEN);
		}
		if (clr % 5 == 4) {
			graphcol = (Color.ORANGE);
		} // does the same
		for (int i = 0; i < NDECADES - 1; i++) {
			int cur = entry.getRank(i);
			int nxt = entry.getRank(i + 1);
			if (cur == 0) {
				cur = 1000;
			}
			if (nxt == 0) {
				nxt = 1000;
			}
			GLine xazi = new GLine((getWidth() / NDECADES) * i,
					GRAPH_MARGIN_SIZE + cur * (getHeight() - GRAPH_MARGIN_SIZE * 2) / MAX_RANK,
					(getWidth() / NDECADES) * (i + 1),
					GRAPH_MARGIN_SIZE + nxt * (getHeight() - GRAPH_MARGIN_SIZE * 2) / MAX_RANK);
			if (resid == false && isod==false) {
				colorz.put(Graph_name, graphcol);
			}
			if (isod == true || resid == true) {
				graphcol = colorz.get(Graph_name);
				failsafe = true;
			} // everythings the same except added support for isolation
			xazi.setColor(graphcol);
			gorki[i] = xazi;// make a line array
		}
		if (isod == false && failsafe == false) {
			clr++;
		}
		linez.put(Graph_name, gorki);// puts array in a hashmap, same with labels below
		for (int i = 0; i < NDECADES; i++) {
			int krank = entry.getRank(i);
			if (krank == 0) {
				krank = MAX_RANK;
				if (i < 10) {

					GLabel name = new GLabel(entry.getName() + " *", (getWidth() / NDECADES) * i + 10,
							GRAPH_MARGIN_SIZE + krank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK - 10);
					name.setColor(graphcol);
					if (name.getY() > getHeight() - GRAPH_MARGIN_SIZE) {
						name = new GLabel(entry.getName() + " *",
								(getWidth() / NDECADES) * i - 10 - 2 * name.getWidth(),
								krank * getHeight() / MAX_RANK - 3 * name.getHeight());
					}
					if (name.getX() + name.getWidth() > getWidth()) {
						name = new GLabel(entry.getName() + " *",
								(getWidth() / NDECADES) * i - 10 - 2 * name.getWidth(), krank * getHeight() / MAX_RANK);
					}
					name.setColor(graphcol);
					saxelebi[i] = name;
				}
				if (i == 10) {
					GLabel name = new GLabel(entry.getName() + " *");
					name = new GLabel(entry.getName() + " *", (getWidth() / NDECADES) * i + 10,
							krank * getHeight() / MAX_RANK - 2 * name.getHeight());
					name.setColor(graphcol);
					saxelebi[i] = name;
				}
				namez.put(entry.getName(), saxelebi);
			} else {
				if (i < 10) {

					GLabel name = new GLabel(entry.getName() + " " + krank, (getWidth() / NDECADES) * i + 10,
							GRAPH_MARGIN_SIZE + krank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK);
					name.setColor(graphcol);
					if (name.getY() > getHeight() - GRAPH_MARGIN_SIZE) {
						name = new GLabel(entry.getName() + " " + krank,
								(getWidth() / NDECADES) * i - 10 - name.getWidth(),
								krank * getHeight() / MAX_RANK - 2 * name.getHeight());
					}
					if (name.getX() + name.getWidth() > getWidth()) {
						name = new GLabel(entry.getName() + " " + krank,
								(getWidth() / NDECADES) * i - 10 - name.getWidth(), krank * getHeight() / MAX_RANK);
					}
					name.setColor(graphcol);
					saxelebi[i] = name;
				}
				if (i == 10) {
					GLabel name = new GLabel(entry.getName() + " " + krank);
					name = new GLabel(entry.getName() + " " + krank, (getWidth() / NDECADES) * i + 10,
							krank * getHeight() / MAX_RANK - 2 * name.getHeight());
					name.setColor(graphcol);
					saxelebi[i] = name;
				}
				namez.put(entry.getName(), saxelebi);
			}
		}

	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 * 
	 */

	public void isolate(String S) {
		if (isod == false && namas.contains(S)) {
			isod = true;
			isodname = S;
			linez.remove(S);
			namez.remove(S);
			removeAll();
			charcho();
			addEntry(x.findEntry(S));
			drawlinesbyname(S);
			drawnamesbyname(S);
			// if entered name has already been entered, we remove everything and add only
			// the entered one
		} else if (isod == true) {
			for (String Neym : namas) {
				if (Neym != S) {
					addEntry(x.findEntry(Neym));
					drawlinesbyname(Neym);
					drawnamesbyname(Neym);
				}
			}
			isod = false;
		} // if isolate is clicked again, we add everything back

	}

	public void update() {// same
		// You fill this in //
		if (Graph_name != null) {
			drawlinesbyname(Graph_name);
			drawnamesbyname(Graph_name);
			namas.add(Graph_name);
		}
	}

	public void drawlinesbyname(String Name) {
		for (int i = 0; i < NDECADES - 1; i++) {
			add(linez.get(Name)[i]);
		} // add lines
	}

	public void drawnamesbyname(String Name) {
		for (int i = 0; i < NDECADES; i++) {
			add(namez.get(Name)[i]);
		} // add labels
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {// same
		resid = true;
		failsafe = true;
		removeAll();
		charcho();
		if (Graph_name != null) {
			namez.clear();
			linez.clear();
			if (isod == false) {// if no name isolated
				for (String Neym : namas) {
					addEntry(x.findEntry(Neym));
					drawlinesbyname(Neym);
					drawnamesbyname(Neym);
				}
			} else {// if isolated only redraws the isodname
				addEntry(x.findEntry(isodname));
				drawlinesbyname(isodname);
				drawnamesbyname(isodname);
			}
		}
		resid = false;
		failsafe = false;
	}

	public void componentShown(ComponentEvent e) {
	}

}
