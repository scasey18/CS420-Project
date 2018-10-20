package farming;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Fscreen extends JFrame{
	
	//Want to have access to these on various methods
	JButton addButton;
	JButton removeButton;
	JButton updateButton;
	
	JComboBox options;
	
	JList listOfItems;
	static DefaultListModel defaultListModel = new DefaultListModel();
	
	static JScrollPane scrollPane;
	
	//Generic create label func
	public static JLabel createLabel(String name, JPanel parentPanel) {
		JLabel label = new JLabel(name);
		label.setVisible(true);
		parentPanel.add(label);
		return label;
	}
	
	//Generic create button func
	public static JButton createButton(String name, JPanel parentPanel) {
		JButton button = new JButton(name);
		button.setVisible(true);
		parentPanel.add(button);
		return button;
	}
	
	//Generic create combo box func
	public static JComboBox<String> createComboBox(String[] listOfOptions, JPanel parentPanel) {
		final JComboBox<String> comboBox = new JComboBox<String>(listOfOptions);
		comboBox.setVisible(true);
		parentPanel.add(comboBox);
		return comboBox;
	}
	
	//Generic create list func
		public static JList createList(String[] list, JPanel parentPanel) {
			
			for(String listElement: list) {
				defaultListModel.addElement(listElement);
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JList jlist = new JList(defaultListModel);
			
			scrollPane = new JScrollPane(jlist, 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			jlist.setVisible(true);
			jlist.setFixedCellWidth(800);
			jlist.setFixedCellHeight(30);
			parentPanel.add(scrollPane);
			return jlist;
		}
	
	
	public Fscreen(int width, int height) {
		
		//set window size from args
		this.setSize(width, height);
		//This takes care of centering, setting to null centers to monitor
		this.setLocationRelativeTo(null);
		//This makes it so program fully closes when exit button is hit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set title of window
		this.setTitle("Group G Farming App");
		
		//Want to add components to panel, then add panel to frame (window)
		JPanel test = new JPanel();
		
		//List of the buttons we want to make
		String[] buttons = {"Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock"};
		
		//Label to add item
		JLabel lblAddItem = createLabel("Add Item", test);
		
		//Label for price
		JLabel lblPrice = createLabel("Price",test);
		
		//label for total cost
		JLabel lblTotal = createLabel("Total",test);
		
		//Combo box for options for user to add
		options = createComboBox(buttons, test);
		
		//Buttons for user to interact with
		addButton = createButton("Add", test);
		removeButton = createButton("Remove", test);
		updateButton = createButton("Update", test);
		
		//Button listener class
		buttonListener buttonListener = new buttonListener();
		//Attach listener to buttons
		addButton.addActionListener(buttonListener);
		removeButton.addActionListener(buttonListener);
		updateButton.addActionListener(buttonListener);
		
		//This is the array of data, that will need to be an array of items eventually
		String[] testList = {"t1","t2"};
		listOfItems = createList(testList, test);
		
		//Add panel to frame
		this.add(test);
		//make window visible, not true by default
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new Fscreen(1280,720);
		
	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//e == event
			//e.getsource returns what obj has been clicked
			if(e.getSource() == addButton) {
				//Add button has been clicked
				int opt = options.getSelectedIndex();
				switch (opt) {
	            case 0:  
	            	defaultListModel.add(0, "Container");
	                break;
	            case 1: 
	            	defaultListModel.add(0, "Crops");
	            	break;
	            case 2: 
	            	defaultListModel.add(0, "Drone");
	            	break;
	            case 3: 
	            	defaultListModel.add(0, "Equipment");
	            	break;
	            case 4: 
	            	defaultListModel.add(0, "Supplies");
	            	break;
	            case 5: 
	            	defaultListModel.add(0, "Livestock");
	            	break;
	            default: 
	            	break;
				}
				
			} else if(e.getSource() == removeButton) {
				//Remove button has been clicked
				int s = listOfItems.getSelectedIndex();
				defaultListModel.removeElementAt(s);
				
				
			} else if(e.getSource() == updateButton) {
				//Update button has been clicked
				System.out.println("update");
				
			}
			
		}
		
	}
	
}


