package farming;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
		
		//This will be used to hold the contents of the farm
		ArrayList<ItemContainer> containerFarm = new ArrayList<ItemContainer>();
		ArrayList<Item> itemFarm = new ArrayList<Item>();
		
		String[] buttons = {"Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock"};
		
		final JComboBox<String> cb = new JComboBox<String>(buttons);
		
		cb.setVisible(true);
		panel.add(cb);
		
		JButton butn = new JButton("REMOVE");
		panel.add(butn);
		JButton bttn = new JButton("ADD");
		panel.add(bttn);
		JButton btn = new JButton("UPDATE");
		panel.add(btn);
		
		JFrame frame = new JFrame("Farm Project");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int frameSize = 500;
		frame.setSize(frameSize, frameSize);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width/2)-frameSize/2, (screenSize.height/2) - frameSize/2);
		
		frame.add(panel);
	}

}
