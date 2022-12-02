
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private JLabel Name = new JLabel("Name");//creates interactors
	private JTextField field = new JTextField(20);
	private NameSurferGraph gr = new NameSurferGraph();//makes canvas/graph
	private JButton graph = new JButton("graph");
	private JButton clear = new JButton("clear");
	private NameSurferDataBase database = new NameSurferDataBase(NAMES_DATA_FILE);//makes the database
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		// You fill this in, along with any helper methods //
		add(Name, SOUTH);//adds the label, 2 buttons and text field
		add(field, SOUTH);
		add(graph, SOUTH);
		add(clear, SOUTH);
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
			println(database.findEntry(field.getText()).toString());//just for debug
			gr.addEntry(database.findEntry(field.getText()));//calls the add entry function in namesurfergraph
			gr.update();//actually draws the graph
			field.setText(null);//clears the textbox
		}
		if (e.getSource() == clear) {
			gr.clear();//calls the clear graph function
		}
	}
}
