package farming;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fscreen {
	
	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		
		JLabel lbl = new JLabel("Add item");
		lbl.setVisible(true);

		
		JLabel lble = new JLabel("Price");
		lble.setVisible(true);
		
		JLabel labl = new JLabel("Total");
		labl.setVisible(true);
		
		panel.add(labl); 
		panel.add(lbl);
		panel.add(lble);

		
		String[] buttons = {"Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock"};
		
		final JComboBox<String> cb = new JComboBox<String>(buttons);
		
		cb.setVisible(true);
		panel.add(cb);
		
        JButton removeButton = new JButton("REMOVE");
		panel.add(removeButton);
		JButton addButton = new JButton("ADD");
		panel.add(addButton);
		JButton updateButton = new JButton("UPDATE");
		panel.add(updateButton);
		
		JFrame frame = new JFrame("Farm Project");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLocation(300, 130);
		
		frame.add(panel);
	}

}
