package farming;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.tree.*;

public class Fscreen3 extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Want to have access to these on various methods
	JButton addButton;
	JButton removeButton;
	JButton updateButton;
	
	JComboBox<String> options;

	JTree tree;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("All Items");
	
	static JScrollPane scrollPane;
	
	public static void main(String[] args) {
		
		new Fscreen3(1000,600);
		
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
	
	
	public Fscreen3(int width, int height) {
		
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
		
		//createLabel("Add Item", test);
		
		//createLabel("Price",test);
		
		//createLabel("Total",test);
		
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
		MouseListener ml = new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	if(e.getClickCount() == 2) {
		    		DefaultMutableTreeNode selPath = (DefaultMutableTreeNode)tree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
		    		showInfo(selPath.getUserObject());
		    		
		    	}
		        
		    }
		};
		tree.addMouseListener(ml);
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
            				JTextField name = new JTextField();
            				JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
            				JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
            				Object[] message = {
            						"All of these fields are required for item creation",
            						"Name:" , name,
            						"X location:", locX,
            						"Y location:", locY,
            						"Length:", length,
            						"Width:", width,
            						"Price:", price
            				};

            				int option = JOptionPane.showConfirmDialog(null, message, "Create "+
            						options.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            				if (option == JOptionPane.OK_OPTION) {
            					if (!name.getText().trim().equals("") && 
            							!locX.getText().trim().equals("")&&
            							!locY.getText().trim().equals("")&&
            							!width.getText().trim().equals("")&&
            							!length.getText().trim().equals("") &&
            							!price.getText().trim().equals("")) {
            						switch(String.valueOf(options.getSelectedItem())) {
            	        
            						case "Container": child = new DefaultMutableTreeNode(new ItemContainer(name.getText(),
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
            												x
            												),false);
            							}
            							break;
        	            	
            						case "Drone": child = new DefaultMutableTreeNode(new Drone(name.getText(),
            								Float.valueOf(price.getText()),
            								Integer.valueOf(locX.getText()),
            								Integer.valueOf(locY.getText()),
            								Integer.valueOf(length.getText()),
            								Integer.valueOf(width.getText())
            								),false); 
            								break;
        	            	
            						case "Equipment": 
            							String[] a = equipmentDialog(String.valueOf(options.getSelectedItem()));
            							if (a.length == 3) {
            								child = new DefaultMutableTreeNode(new Equipment(name.getText(),
            										Float.valueOf(price.getText()),
            										Integer.valueOf(locX.getText()),
            										Integer.valueOf(locY.getText()),
            										Integer.valueOf(length.getText()),
            										Integer.valueOf(width.getText()),
            										a[0],a[1],a[2]),false);
            							}
            							else if (a.length == 2) {
            								child = new DefaultMutableTreeNode(new Equipment(name.getText(),
            										Float.valueOf(price.getText()),
            										Integer.valueOf(locX.getText()),
            										Integer.valueOf(locY.getText()),
            										Integer.valueOf(length.getText()),
            										Integer.valueOf(width.getText()),
            										a[0],a[1]),false);
            							}
            							else if (a.length == 1) {
            								child = new DefaultMutableTreeNode(new Equipment(name.getText(),
            										Float.valueOf(price.getText()),
            										Integer.valueOf(locX.getText()),
            										Integer.valueOf(locY.getText()),
            										Integer.valueOf(length.getText()),
            										Integer.valueOf(width.getText()),
            										a[0]), false);
            							}
            							else {
            								//Do nothing
            							}
            							break;
        	            	
            						case "Supplies": child = new DefaultMutableTreeNode(new Supplies(name.getText(),
            								Float.valueOf(price.getText()),
            								Integer.valueOf(locX.getText()),
            								Integer.valueOf(locY.getText()),
            								Integer.valueOf(length.getText()),
            								Integer.valueOf(width.getText()),
            								suppliesDialog(String.valueOf(options.getSelectedItem()))),false); 
            						break;
        	            	
            						case "Livestock": 
            							String[] answer = liveStockDialog(String.valueOf(options.getSelectedItem()));
            							child = new DefaultMutableTreeNode(new LiveStock(name.getText(),
            									Float.valueOf(price.getText()),
            									Integer.valueOf(locX.getText()),
            									Integer.valueOf(locY.getText()),
            									Integer.valueOf(length.getText()),
            									Integer.valueOf(width.getText()),
            									answer[0],answer[1]), false); 
            							break;
        	            	
            						default: 
        	            	
            						}
            					}
				
            					model = insertNode(child, model);
            					tree.scrollPathToVisible(new TreePath(child.getPath()));
            					child = null;
				
            				}
            	}
            	else if(e.getSource() == removeButton) {
            		//if ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent() != null)
            		model.removeNodeFromParent((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent());
            	} 
            	else if(e.getSource() == updateButton) {
            		//Update button has been clicked
            		System.out.println("update");
            	}
        }
		catch(Exception NullPointerException) {
			//Has to select a item before it can remove
			JOptionPane.showMessageDialog(null,"Please select a container to add items");
		}
		
	}
	}
	 // These are the dialogs created when using the add button to create new objects
	public static String cropDialog(String currentObject) {
		JTextField name = new JTextField();
    	Object[] message = {"Type of Crop:", name,};

    	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
    			currentObject, JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION) {
			if (!name.getText().trim().equals("")){
				return name.getText();
			}
			else {
				//do nothing
			}
    	}
		else {
			//do nothing
		}
    	return null;
	}
	
	public static String[] equipmentDialog(String currentObject) {
		JTextField name = new JTextField();
		JTextField cName = new JTextField();
		JTextField mNum = new JTextField();
    	Object[] message = {
    			"Fill out list descendingly. Type of Equipment is the only required field.",
    			"Type of Equipment:", name,
    			"Company Name:", cName,
    			"Model Number:", mNum
    			};

    	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
    			currentObject, JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION) {
			if (!name.getText().trim().equals("") &&
					!cName.getText().trim().equals("")&&
					!mNum.getText().trim().equals("")) {
				String[] answer = {name.getText(),cName.getText(),mNum.getText()};
				return answer;
			}
			else if (!name.getText().trim().equals("") &&
					!cName.getText().trim().equals("")) {
				String[] answer = {name.getText(),cName.getText(),mNum.getText()};
				return answer;
			}
    		
			else if (!name.getText().trim().equals("")){
				String[] answer = {name.getText()};
				return answer;
			}
			else {
				return null;
			}
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
    	if (option == JOptionPane.OK_OPTION) {
			if (!count.getText().trim().equals("")){
				return Integer.valueOf(count.getText());
			}
			else {
				//do nothing
			}
    	}
		else {
			//do nothing
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
    	if (option == JOptionPane.OK_OPTION) {
			if (!animalType.getText().trim().equals("")) {
				String[] answer = {animalType.getText(),gender.getSelectedItem().toString()};
				return answer;
			}
    	}
		else {
			//do nothing
		}
    	return null;
	}
	
	public static void showInfo(Object a) {
		
		switch(a.getClass().toString()) {
		case "class farming.Drone":
			Drone drone = (Drone) a;
			Object[] message6 = {
					"Name: " + drone.getName(),
					"X location: "+ drone.getLocX(),
					"Y location: "+ drone.getLocY(),
					"Length: "+ drone.getLength(),
					"Width: "+ drone.getWidth(),
					"Price: "+ drone.getPrice(),
	    			};
			JOptionPane.showMessageDialog(null, message6);
			break;
		case "class farming.Crops":
			Crops crop = (Crops) a;
			Object[] message5 = {
					"Name: " + crop.getName(),
					"X location: "+ crop.getLocX(),
					"Y location: "+ crop.getLocY(),
					"Length: "+ crop.getLength(),
					"Width: "+ crop.getWidth(),
					"Price: "+ crop.getPrice(),
					"Crop Type: " + crop.getCropType()
	    			};
			JOptionPane.showMessageDialog(null, message5);
			break;
		case "class farming.Equipment":
			Equipment equip = (Equipment) a;
			Object[] message4 = {
					"Name: " + equip.getName(),
					"X location: "+ equip.getLocX(),
					"Y location: "+ equip.getLocY(),
					"Length: "+ equip.getLength(),
					"Width: "+ equip.getWidth(),
					"Price: "+ equip.getPrice(),
					"Type of Equipment: " + equip.getEquipmentType(),
	    			"Company Name: " + equip.getCompany(),
	    			"Model Number: " + equip.getModelNumber()
	    			};
			JOptionPane.showMessageDialog(null, message4);
			break;
		case "class farming.ItemContainer":
			ItemContainer barn = (ItemContainer) a;
			Object[] message2 = {
					"Name: " + barn.getName(),
					"X location: "+ barn.getLocX(),
					"Y location: "+ barn.getLocY(),
					"Length: "+ barn.getLength(),
					"Width: "+ barn.getWidth(),
					"Price: "+ barn.getPrice()
	    			};
			JOptionPane.showMessageDialog(null, message2);
			break;
		case "class farming.LiveStock":
			LiveStock animal = (LiveStock) a;
			Object[] message = {
					"Name: " + animal.getName(),
					"X location: "+ animal.getLocX(),
					"Y location: "+ animal.getLocY(),
					"Length: "+ animal.getLength(),
					"Width: "+ animal.getWidth(),
					"Price: "+ animal.getPrice(),
					"Type of Animal: "+ animal.getAnimalType(),
	    			"Gender: "+ animal.getGender(),
	    			};
			JOptionPane.showMessageDialog(null, message);
			break;
		case "class farming.Supplies":
			Supplies supply = (Supplies) a;
			Object[] message3 = {
					"Name: " + supply.getName(),
					"X location: "+ supply.getLocX(),
					"Y location: "+ supply.getLocY(),
					"Length: "+ supply.getLength(),
					"Width: "+ supply.getWidth(),
					"Price: "+ supply.getPrice(),
					"Count: " + supply.getCount()
	    			};
			JOptionPane.showMessageDialog(null, message3);
			break;
		}
		
	}
	
}
