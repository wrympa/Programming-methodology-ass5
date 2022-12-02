
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class NameSurfer_extension extends Program implements NameSurferConstants {

	private JLabel Name = new JLabel("Name");//creates interactors
	private JTextField field = new JTextField(20);
	private JButton graph = new JButton("graph");
	private JButton clear = new JButton("clear");
	private JButton remove = new JButton("remove");
	private JButton isolate = new JButton("isolate");
	public static NameSurferDataBase database = new NameSurferDataBase(NAMES_DATA_FILE);//makes the database
	private NameSurferGraph_extension gr = new NameSurferGraph_extension();//makes canvas/graph

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		// You fill this in, along with any helper methods //
		add(Name, SOUTH);//adds the label, 4 buttons and text field
		add(field, SOUTH);
		add(graph, SOUTH);
		add(clear, SOUTH);
		add(remove, SOUTH);
		add(isolate, EAST);
		add(gr);//adds canvas
		gr.charcho();//draws the template lines and years
		addActionListeners();
	}

///* Method: actionPerformed(e) */
///**
// * This class is responsible for detecting when the buttons are
// * clicked, so you will have to define a method to respond to
// * button actions.
// */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getSource() == graph) {//if source is the graph button, makes the appropriate graph for the name
			println(database.findEntry(field.getText()).toString());//for debug
			gr.addEntry(database.findEntry(field.getText()));//adds entry in graph class
			gr.update();//makes the appropriate graph
			field.setText(null);
		}
		if (e.getSource() == clear) {//calls the clear function
				gr.clear();
		}
		if (e.getSource() == remove) {//calls the remove function
			gr.remove(field.getText());
			field.setText(null);
		}
		if (e.getSource() == isolate) {//calls the isolate function
			gr.isolate(field.getText());
			field.setText(null);
		}
	}
}
