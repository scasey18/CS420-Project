package farming;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.*;

public class Fscreen implements ActionListener{
	
	protected static JList<?> jl;

	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		
		JLabel lblAddItem = new JLabel("Add item");
		lblAddItem.setVisible(true);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setVisible(true);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setVisible(true);
		
		panel.add(lblAddItem); 
		panel.add(lblPrice);
		panel.add(lblTotal);
		
		//This will be used to hold the contents of the farm
		ArrayList<ItemContainer> containerFarm = new ArrayList<ItemContainer>();
		ArrayList<Item> itemFarm = new ArrayList<Item>();
		
		String[] buttons = {"Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock"};
		
		final JComboBox<String> cb = new JComboBox<String>(buttons);
		
		cb.setVisible(true);
		panel.add(cb);
		
		JButton btnRemove = new JButton("REMOVE");
		panel.add(btnRemove);
		JButton btnAdd = new JButton("ADD");
		panel.add(btnAdd);
		btnAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JTextField name = new JTextField();
            	JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
            	JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
            	JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
            	JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
            	JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
            	Object[] message = {
            	    "Name:", name,
            	    "X location:", locX,
            	    "Y location:", locY,
            	    "Length:", length,
            	    "Width:", width,
            	    "Price:", price
            	};

            	int option = JOptionPane.showConfirmDialog(null, message, "Create "+
            			cb.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            	if (option == JOptionPane.OK_OPTION) {
            	    if (!name.getText().trim().equals("") && 
            	    		!locX.getText().trim().equals("")&&
            	    		!locY.getText().trim().equals("")&&
            	    		!width.getText().trim().equals("")&&
            	    		!length.getText().trim().equals("") &&
            	    		!price.getText().trim().equals("")) {
            	    	
            	        switch(String.valueOf(cb.getSelectedItem())) {
            	        case "Container":
            	        	containerFarm.add(new ItemContainer(name.getText(),
            	        			Float.valueOf(price.getText()),
            	        			Integer.valueOf(locX.getText()),
            	        			Integer.valueOf(locY.getText()),
            	        			Integer.valueOf(length.getText()),
            	        			Integer.valueOf(width.getText())
            	        			));
            	        	System.out.println(containerFarm);
            	        	break;
            	        case "Crops":
            	        	String x = cropDialog(String.valueOf(cb.getSelectedItem()));
            	        	if (x == null) {
            	        		//Do Nothing
            	        	}
            	        	else {
            	        		itemFarm.add(new Crops(name.getText(),
            	        			Float.valueOf(price.getText()),
            	        			Integer.valueOf(locX.getText()),
            	        			Integer.valueOf(locY.getText()),
            	        			Integer.valueOf(length.getText()),
            	        			Integer.valueOf(width.getText()),
            	        			x
            	        			));
            	        	}
            	        	break;
            	        case "Drone":
            	        	itemFarm.add(new Drone(name.getText(),
            	        			Float.valueOf(price.getText()),
            	        			Integer.valueOf(locX.getText()),
            	        			Integer.valueOf(locY.getText()),
            	        			Integer.valueOf(length.getText()),
            	        			Integer.valueOf(width.getText())
            	        			));
            	        	break;
            	        case "Equipment":
            	        	String[] a = equipmentDialog(String.valueOf(cb.getSelectedItem()));
            	        	if (a.length == 3) {
            	        		itemFarm.add(new Equipment(name.getText(),
            	        			Float.valueOf(price.getText()),
            	        			Integer.valueOf(locX.getText()),
            	        			Integer.valueOf(locY.getText()),
            	        			Integer.valueOf(length.getText()),
            	        			Integer.valueOf(width.getText()),
            	        			a[0],a[1],a[2]));
            	        	}
            	        	else if (a.length == 2) {
            	        		itemFarm.add(new Equipment(name.getText(),
                	        			Float.valueOf(price.getText()),
                	        			Integer.valueOf(locX.getText()),
                	        			Integer.valueOf(locY.getText()),
                	        			Integer.valueOf(length.getText()),
                	        			Integer.valueOf(width.getText()),
                	        			a[0],a[1]));
            	        	}
            	        	else if (a.length == 1) {
            	        		itemFarm.add(new Equipment(name.getText(),
                	        			Float.valueOf(price.getText()),
                	        			Integer.valueOf(locX.getText()),
                	        			Integer.valueOf(locY.getText()),
                	        			Integer.valueOf(length.getText()),
                	        			Integer.valueOf(width.getText()),
                	        			a[0]));
            	        	}
            	        	else {
            	        		//Do nothing
            	        	}
            	        break;
            	        case "Supplies":
            	        	itemFarm.add(new Supplies(name.getText(),
            	        			Float.valueOf(price.getText()),
            	        			Integer.valueOf(locX.getText()),
            	        			Integer.valueOf(locY.getText()),
            	        			Integer.valueOf(length.getText()),
            	        			Integer.valueOf(width.getText()),
            	        			suppliesDialog(String.valueOf(cb.getSelectedItem()))));
            	        break;
            	        case "Livestock":
            	        	String[] answer = liveStockDialog(String.valueOf(cb.getSelectedItem()));
            	        	itemFarm.add(new LiveStock(name.getText(),
            	        			Float.valueOf(price.getText()),
            	        			Integer.valueOf(locX.getText()),
            	        			Integer.valueOf(locY.getText()),
            	        			Integer.valueOf(length.getText()),
            	        			Integer.valueOf(width.getText()),
            	        			answer[0],answer[1]));
            	        	break;
            	        }
            	        
            	        jl = new JList<Object>(containerFarm.toArray());
            	        
            	    } 
            	    else {
            	    }
            	} else {
            	    //Do nothing if they hit cancel
            	}
            }
        });
		JButton btnUpdate = new JButton("UPDATE");
		panel.add(btnUpdate);
		
		
		TextField tf = new TextField();
		
		JFrame frame = new JFrame("Farm Project");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int frameSize = 500;
		frame.setSize(frameSize, frameSize);
		
		jl = new JList<Object>(containerFarm.toArray());
		jl.setPreferredSize(new Dimension(frameSize-100, frameSize-150));
		jl.setVisible(true);
		panel.add(jl);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width/2)-frameSize/2, (screenSize.height/2) - frameSize/2);
		
		frame.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
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
}
