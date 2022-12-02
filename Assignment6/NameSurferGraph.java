
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
	
public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
	ArrayList<NameSurferEntry> namas = new ArrayList<NameSurferEntry>();// list of entered(and not removed) names
	HashMap<String, Color> colorz = new HashMap<String, Color>();
	private String Graph_name;// entered name
	private int[] Graph_rank = new int[11];// ranks of current name
	NameSurferEntry Graph_entry;
	int clr = 0;// used for choosing colour
	Color graphcol;
	private boolean resid = false;//makes sure that colors dont change when resized, same happens in extend(failsafe boolean also serves the same purpose)

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);

		// You fill in the rest //
	}

	public void charcho() {
		GLine acrs = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine acrs1 = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(acrs);
		add(acrs1);// draws two bottom and top horizontal lines
		for (int i = 0; i < NDECADES; i++) {
			GLine fence = new GLine((getWidth() / NDECADES) * i, 0, (getWidth() / NDECADES) * i, getHeight());
			add(fence);
			GLabel year = new GLabel(Integer.toString(START_DECADE + i * (NDECADES - 1)));
			add(fence);
			add(year, (getWidth() / 11) * i + 5, getHeight() - 3);
		} // adds number of years and vertical lines, same in the extend version
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {// removes everyhting exept the starting lines. same in extension
		namas.clear();
		removeAll();
		charcho();
		// You fill this in //
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 * 
	 */
	public void addEntry(NameSurferEntry entry) {// makes local name and array of ranks
		// You fill this in //
		Graph_name = entry.getName();
		for (int i = 0; i < NDECADES; i++) {
			Graph_rank[i] = entry.getRank(i);
		}
		Graph_entry = entry;
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 * 
	 */
	public void update() {
		// You fill this in //
		if(resid==false)
		{
		if (Graph_name != null) {// draws lines and names and adds name to array of added names
			drawlinesbyname(Graph_name);
			drawnamesbyname(Graph_name);
			namas.add(Graph_entry);
		}
		}
		else
		{
			removeAll();
			charcho();
			if (Graph_name != null) {
				for (NameSurferEntry entry : namas) {// draws graph again for all names
					addEntry(entry);
					drawlinesbyname(Graph_name);
					drawnamesbyname(Graph_name);
				}
			}
		}
	}

	public void drawlinesbyname(String Name) {
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
		} // sets color
		for (int i = 0; i < NDECADES - 1; i++) {
			int cur = Graph_rank[i];
			int nxt = Graph_rank[i + 1];
			if (cur == 0) {
				cur = 1000;// if rank = 0, we draw it at the bottom
			}
			if (nxt == 0) {
				nxt = 1000;
			}
			GLine xazi = new GLine((getWidth() / NDECADES) * i,
					GRAPH_MARGIN_SIZE + cur * (getHeight() - GRAPH_MARGIN_SIZE * 2) / MAX_RANK,
					(getWidth() / NDECADES) * (i + 1),
					GRAPH_MARGIN_SIZE + nxt * (getHeight() - GRAPH_MARGIN_SIZE * 2) / MAX_RANK);
			if(resid==true)
			{
				graphcol=colorz.get(Name);
			}
			if(resid==false) {
				colorz.put(Name, graphcol);
			}
			xazi.setColor(graphcol);
			add(xazi);
		} // draws 10 lines
	}

	public void drawnamesbyname(String Name) {// adds names
		for (int i = 0; i < NDECADES; i++) {
			int krank = Graph_rank[i];
			if (krank == 0) {
				krank = MAX_RANK;
				if (i < 10) {

					GLabel name = new GLabel(Graph_name + " *", (getWidth() / NDECADES) * i + 10,
							GRAPH_MARGIN_SIZE + krank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK - 10);
					name.setColor(graphcol);
					if (name.getY() > getHeight() - GRAPH_MARGIN_SIZE) {// corects for position
						name = new GLabel(Graph_name + " *", (getWidth() / NDECADES) * i - 10 - 2 * name.getWidth(),
								krank * getHeight() / MAX_RANK - 3 * name.getHeight());
					}
					if (name.getX() + name.getWidth() > getWidth()) {
						name = new GLabel(Graph_name + " *", (getWidth() / NDECADES) * i - 10 - 2 * name.getWidth(),
								krank * getHeight() / MAX_RANK);
					}
					add(name);
				}
				if (i == 10) {
					GLabel name = new GLabel(Graph_name + " *");
					name = new GLabel(Graph_name + " *", (getWidth() / NDECADES) * i + 10,
							krank * getHeight() / MAX_RANK - 2 * name.getHeight());
					name.setColor(graphcol);
					add(name);
				}
			} else {
				if (i < 10) {

					GLabel name = new GLabel(Graph_name + " " + krank, (getWidth() / NDECADES) * i + 10,
							GRAPH_MARGIN_SIZE + krank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK);
					if (name.getY() > getHeight() - GRAPH_MARGIN_SIZE) {
						name = new GLabel(Graph_name + " " + krank, (getWidth() / NDECADES) * i - 10 - name.getWidth(),
								krank * getHeight() / MAX_RANK - 2 * name.getHeight());
					}
					if (name.getX() + name.getWidth() > getWidth()) {
						name = new GLabel(Graph_name + " " + krank, (getWidth() / NDECADES) * i - 10 - name.getWidth(),
								krank * getHeight() / MAX_RANK);
					}
					name.setColor(graphcol);
					add(name);
				}
				if (i == 10) {
					GLabel name = new GLabel(Graph_name + " " + krank);
					name = new GLabel(Graph_name + " " + krank, (getWidth() / NDECADES) * i + 10,
							krank * getHeight() / MAX_RANK + name.getHeight());
					name.setColor(graphcol);
					add(name);
				}
			}
		}
		if (resid == false) {
			clr++;// increases clr so that next color is different
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		resid = true;
		update();
		resid = false;
	}

	public void componentShown(ComponentEvent e) {
	}

}
