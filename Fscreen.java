package farming;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.tree.*;

public class Fscreen extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Want to have access to these on various methods
	JButton addButton;
	JButton removeButton;
	JButton visualButton;
	
	JComboBox<String> options;

	JTree tree;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new DefaultMutableTreeNode(new ItemContainer("root")));
	
	static JScrollPane scrollPane;
	
	public static void main(String[] args) {
		
		new Fscreen(1000,600);
		
	}
	
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
	
	 // This function will update the model when adding a node to the current Container
	private DefaultTreeModel insertNode(DefaultMutableTreeNode node, DefaultTreeModel model) {
		model.insertNodeInto(node, (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent(),
	    	((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getChildCount());
			
		return model;
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
		JPanel mainPanel = new JPanel();
		
		//List of the buttons we want to make
		String[] buttons = {"Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock"};
		
		//Combo box for options for user to add
		options = createComboBox(buttons, mainPanel);
		
		//Buttons for user to interact with
		addButton = createButton("Add", mainPanel);
		removeButton = createButton("Remove", mainPanel);
		visualButton = createButton("Visualize", mainPanel);
		
		//Button listener class
		buttonListener buttonListener = new buttonListener();
		//Attach listener to buttons
		addButton.addActionListener(buttonListener);
		removeButton.addActionListener(buttonListener);
		visualButton.addActionListener(buttonListener);
		
		

		tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(tree);
		Dimension d = scroll.getPreferredSize();
		d.width = 800;
		d.height = 400;
		scroll.setPreferredSize(d);
		
		mainPanel.add(scroll);
		
		MouseListener ml = new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	if(e.getClickCount() == 2) {
		    		DefaultMutableTreeNode selPath = (DefaultMutableTreeNode)tree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
		    		String b = selPath.getUserObject().getClass().toString();
		    		switch (b) {
		    		case "class farming.ItemContainer":
		    			((ItemContainer)selPath.getUserObject()).showInfo(); break;
		    		case "class farming.Equipment":
		    			((Equipment)selPath.getUserObject()).showInfo(); break;
		    		case "class farming.Drone":
		    			((Drone)selPath.getUserObject()).showInfo(); break;
		    		case "class farming.Crops":
		    			((Crops)selPath.getUserObject()).showInfo(); break;
		    		case "class farming.LiveStock":
		    			((LiveStock)selPath.getUserObject()).showInfo(); break;
		    		case "class farming.Supplies":
		    			((Supplies)selPath.getUserObject()).showInfo(); break;
		    		}
		    	}
		        
		    }
		};
		tree.addMouseListener(ml);
		//Add panel to frame
		this.add(mainPanel);
		//make window visible, not true by default
		this.setVisible(true);
		
	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode child = null;
           
			//e == event
			//e.getsource returns what obj has been clicked
            try {
            	if(e.getSource() == addButton && 
            			//This code is to see if the node can accept children (false for all except Containers)
            			((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getAllowsChildren()
            			) {
            				//Add button has been clicked
            				//Below are all args for creating new item
            				JTextField name = new JTextField();
            				JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField Mprice = new JFormattedTextField(NumberFormat.getNumberInstance());
           
            				Object[] message = {
            						"All of these fields are required for item creation",
            						"Name:" , name,
            						"X location:", locX,"Y location:", locY,
            						"Length:", length,"Width:", width,
            						"Price:", price, "Market Price:", Mprice
            				};
            				Object[] message2 = {
            						"All of these fields are required for item creation",
            						"Name:" , name,
            						"X location:", locX, "Y location:", locY,
            						"Length:", length, "Width:", width,
            						"Price:", price,
            				};
            				Object[] message3 = {};
            				if (options.getSelectedItem() == "Container") {
            					message3 = message2;
            				}
            				else {
            					message3 = message;
            				}
            				
            				int option = JOptionPane.showConfirmDialog(null, message3, "Create "+
            						options.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            				
            				if ((option == JOptionPane.OK_OPTION && !name.getText().trim().equals("") && 
        							!locX.getText().trim().equals("")&&
        							!locY.getText().trim().equals("")&&
        							!width.getText().trim().equals("")&&
        							!length.getText().trim().equals("") &&
        							!price.getText().trim().equals("") && 
        							!Mprice.getText().trim().equals("")) || 
            						(option == JOptionPane.OK_OPTION && 
        							options.getSelectedItem() == "Container" &&
        							!name.getText().trim().equals("") && 
        							!locX.getText().trim().equals("")&&
        							!locY.getText().trim().equals("")&&
        							!width.getText().trim().equals("")&&
        							!length.getText().trim().equals("") &&
        							!price.getText().trim().equals("")))
            				{
            						switch(String.valueOf(options.getSelectedItem())) {
            	        
            						case "Container": child = new DefaultMutableTreeNode(
            								new ItemContainer(name.getText(),
            								Float.valueOf(price.getText()),
            								Integer.valueOf(locX.getText()),
            								Integer.valueOf(locY.getText()),
        	        						Integer.valueOf(length.getText()),
        	        						Integer.valueOf(width.getText())),true); 
            						break;
        	                
            						case "Crops": 
            							String x = cropDialog(String.valueOf(options.getSelectedItem()));
            							if (x == null) {
            								//Do Nothing
            							}
            							else {
            								child = new DefaultMutableTreeNode(new Crops
            										(name.getText(),
            												Float.valueOf(price.getText()),
            												Integer.valueOf(locX.getText()),
            												Integer.valueOf(locY.getText()),
            												Integer.valueOf(length.getText()),
            												Integer.valueOf(width.getText()),
            												x,
            												Integer.valueOf(Mprice.getText())
            												),false);
            							}
            							break;
        	            	
            						case "Drone": 
            							child = new DefaultMutableTreeNode(new Drone(name.getText(),
            								Float.valueOf(price.getText()),
            								Integer.valueOf(locX.getText()),
            								Integer.valueOf(locY.getText()),
            								Integer.valueOf(length.getText()),
            								Integer.valueOf(width.getText()),
            								Integer.valueOf(Mprice.getText())
            								),false); 
            								break;
        	            	
            						case "Equipment": 
            							String[] a = equipmentDialog(String.valueOf(options.getSelectedItem()));
            							if (a == null) {
            								//nothing was input correctly
            							}
            							else {
            								child = new DefaultMutableTreeNode(new Equipment(name.getText(),
            										Float.valueOf(price.getText()),
            										Integer.valueOf(locX.getText()),
            										Integer.valueOf(locY.getText()),
            										Integer.valueOf(length.getText()),
            										Integer.valueOf(width.getText()),
            										Integer.valueOf(Mprice.getText()),
            										a[0],a[1],a[2]),false);
            							}
            							break;
        	            	
            						case "Supplies": 
            							int a1 = suppliesDialog(String.valueOf(options.getSelectedItem()));
            							if (a1 >= 0) {
            								child = new DefaultMutableTreeNode(new Supplies(name.getText(),
                    								Float.valueOf(price.getText()),
                    								Integer.valueOf(locX.getText()),
                    								Integer.valueOf(locY.getText()),
                    								Integer.valueOf(length.getText()),
                    								Integer.valueOf(width.getText()),
                    								a1, 
                    								Integer.valueOf(Mprice.getText())),false); 
            							}
            							else {
            								
            							}
            						break;
        	            	
            						case "Livestock": 
            							String[] answer = liveStockDialog(String.valueOf(options.getSelectedItem()));
            							if (answer != null) {
            								child = new DefaultMutableTreeNode(new LiveStock(name.getText(),
                									Float.valueOf(price.getText()),
                									Integer.valueOf(locX.getText()),
                									Integer.valueOf(locY.getText()),
                									Integer.valueOf(length.getText()),
                									Integer.valueOf(width.getText()),
                									answer[0],answer[1], 
                									Integer.valueOf(Mprice.getText())), false); 
            							}
            							break;
        	            	
            						default: 
            							break;
        	            	
            						}
            						
            						if (child != null) {
                						model = insertNode(child, model);
                						tree.scrollPathToVisible(new TreePath(child.getPath()));
                    					child = null;
                					}
                					
            					}
            				else if (option == JOptionPane.OK_OPTION) {
            					JOptionPane.showMessageDialog(null,"Object was not added because all fields were not filled");
            				}
            					
            				}
            	else if(e.getSource() == removeButton) {
            		model.removeNodeFromParent((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent());
            	}
            	else if(e.getSource() == visualButton) {
            		new Visualize(model);
            		JOptionPane.showMessageDialog(null,"Vizualized Farm has been generated");
            	}
            }
            catch(Exception NullPointerException) {
            	//Has to select a item before it can remove
            	if(e.getSource() == addButton) {
            		JOptionPane.showMessageDialog(null,"Please select a container to add items");
            	}
            	else if (e.getSource() == removeButton) {
            		JOptionPane.showMessageDialog(null,"Please select an item to remove");
            	}
            	else {
            		
            	}
		}
		
	}
	}
	 // These are the dialogs created when using the add button to create new objects
	public static String cropDialog(String currentObject) {
		JTextField name = new JTextField();
    	Object[] message = {"Type of Crop:", name,};

    	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
    			currentObject, JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION && !name.getText().trim().equals("")){
				return name.getText();
    	}
    	else if (option == JOptionPane.OK_OPTION) {
    		JOptionPane.showMessageDialog(null,currentObject + " was not added because all fields were not filled");
    	}
		
    	return null;
	}
	
	public static String[] equipmentDialog(String currentObject) {
		JTextField name = new JTextField();
		JTextField cName = new JTextField();
		JTextField mNum = new JTextField();
    	Object[] message = {
    			"Type of Equipment:", name,
    			"Company Name:", cName,
    			"Model Number:", mNum
    			};

    	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
    			currentObject, JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION && 
    			!name.getText().trim().equals("") &&
					!cName.getText().trim().equals("")&&
					!mNum.getText().trim().equals("")) {
    		String[] answer = {name.getText(),cName.getText(),mNum.getText()};
			return answer;
    	}
    	
    	else if (option == JOptionPane.OK_OPTION) {
    		JOptionPane.showMessageDialog(null,currentObject + " was not added because all fields were not filled");
    	}
		else {
			//do nothing
		}
    	return null;
	}
	
	public static int suppliesDialog(String currentObject) {
		JTextField count = new JFormattedTextField(NumberFormat.getNumberInstance());
    	Object[] message = {"Item Count:", count};

    	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
    			currentObject, JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION && !count.getText().trim().equals("")) {
			return Integer.valueOf(count.getText());
		}
    	else if (option == JOptionPane.OK_OPTION) {
    		JOptionPane.showMessageDialog(null,currentObject + " was not added because all fields were not filled");
    	}
    	return -1;
	}

	public static String[] liveStockDialog(String currentObject) {
		JTextField animalType = new JTextField();
		String[] possible = {"Male", "Female"};
		JComboBox<String> gender = new JComboBox<String>(possible);
    	Object[] message = {
    			"Type of Animal:", animalType,
    			"Gender:", gender,
    			};

    	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
    			currentObject, JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION &&!animalType.getText().trim().equals("")) {
				String[] answer = {animalType.getText(),gender.getSelectedItem().toString()};
				return answer;
    	}
    	else if (option == JOptionPane.OK_OPTION) {
    		JOptionPane.showMessageDialog(null,currentObject + " was not added because all fields were not filled");
    	}
    	return null;
	}
}
