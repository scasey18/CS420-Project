package farming;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.*;

import java.util.Enumeration;

public class Fscreen extends JFrame{
	
	//Want to have access to these on various methods
	JButton addButton;
	JButton removeButton;
	JButton updateButton;
	
	JComboBox options;

	JTree tree;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("All Items");
	
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

		tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(tree);
		Dimension d = scroll.getPreferredSize();
		d.width = 800;
		d.height = 400;
		scroll.setPreferredSize(d);
		
		test.add(scroll);
		
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
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
            DefaultMutableTreeNode n;
            DefaultMutableTreeNode child;
           
			//e == event
			//e.getsource returns what obj has been clicked
			if(e.getSource() == addButton) {
				//Add button has been clicked
				int opt = options.getSelectedIndex();
				switch (opt) {
	            case 0:  
	            	//defaultListModel.add(0, "Container");
	            	n = new DefaultMutableTreeNode("test");
	                child = new DefaultMutableTreeNode(n);
	            	model.insertNodeInto(child, root, root.getChildCount());
	                tree.scrollPathToVisible(new TreePath(child.getPath()));
	                break;
	            case 1: 
	            	//addItem("Test2", rootNode);
	            	n = new DefaultMutableTreeNode("test2");
	                child = new DefaultMutableTreeNode(n);
	            	model.insertNodeInto(child, root, root.getChildCount());
	                tree.scrollPathToVisible(new TreePath(child.getPath()));
	            	break;
	            case 2: 
	            	//addItem("Test3", rootNode);
	            	n = new DefaultMutableTreeNode("test3");
	                child = new DefaultMutableTreeNode(n);
	            	model.insertNodeInto(child, root, root.getChildCount());
	                tree.scrollPathToVisible(new TreePath(child.getPath()));
	            	break;
	            case 3: 
	            	//addItem("Test4", rootNode);
	            	n = new DefaultMutableTreeNode("test4");
	                child = new DefaultMutableTreeNode(n);
	            	model.insertNodeInto(child, root, root.getChildCount());
	                tree.scrollPathToVisible(new TreePath(child.getPath()));
	            	break;
	            case 4: 
	            	//addItem("Test5", rootNode);
	            	n = new DefaultMutableTreeNode("test5");
	                child = new DefaultMutableTreeNode(n);
	            	model.insertNodeInto(child, root, root.getChildCount());
	                tree.scrollPathToVisible(new TreePath(child.getPath()));
	            	break;
	            case 5: 
	            	//addItem("Test6", rootNode);
	            	n = new DefaultMutableTreeNode("test6");
	                child = new DefaultMutableTreeNode(n);
	            	model.insertNodeInto(child, root, root.getChildCount());
	                tree.scrollPathToVisible(new TreePath(child.getPath()));
	            	break;
	            default: 
	            	break;
				}
				
			} else if(e.getSource() == removeButton) {
				//Remove button has been clicked
				
				
			} else if(e.getSource() == updateButton) {
				//Update button has been clicked
				System.out.println("update");
				
			}
			
		}
		
	}
	
	private DefaultMutableTreeNode addItem(String nodeName) {
		
		DefaultMutableTreeNode n = new DefaultMutableTreeNode(nodeName);
		
		return n;
	}
	
}


