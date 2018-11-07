package farming;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Drone extends Item{
	
	ArrayList<ItemContainer> flightPath = new ArrayList<ItemContainer>();
	//Contains the areas for the Drone to scan
	
	public Drone(String name,int price, int locX, int locY, int length, int width, int marketPrice) {
		super(name,price,locX,locY,length,width, marketPrice);
	}
	
	public void updateInfo(String name,int price, int locX, int locY, int length, int width, int marketPrice) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketPrice);
	}
	
	public void showInfo() {
		JTextField name = new JTextField();
		JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField mPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		
		name.setText(this.getName());
		locX.setText(Integer.toString(this.getLocX()));
		locY.setText(Integer.toString(this.getLocY()));
		length.setText(Integer.toString(this.getLength()));
		width.setText(Integer.toString(this.getWidth()));
		price.setText(Integer.toString(this.getPrice()));
		mPrice.setText(Integer.toString(this.getMarketValue()));
		
		Object[] droneMessage = {
				"All of these fields are required for item creation",
				"Name:" , name,
				"X location:", locX,
				"Y location:", locY,
				"Length:", length,
				"Width:", width,
				"Price:", price,
				"Market Price: ", mPrice
		};
		
		int result = JOptionPane.showConfirmDialog(null, droneMessage, "Create "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(name.getText(), 
					Integer.valueOf(price.getText()), 
					Integer.valueOf(locX.getText()), 
					Integer.valueOf(locY.getText()), 
					Integer.valueOf(length.getText()), 
					Integer.valueOf(width.getText()), 
					Integer.valueOf(mPrice.getText()));
			Fscreen.text.append("Updated "+ this.toString() + "\n");
		}
	}
	
	public void addFlightPath(ItemContainer a) {
		flightPath.add(a);
	}
	
	public void removeFlightPath(ItemContainer a) {
		flightPath.remove(a);
	}
	
	public String toString() {
		return name + " - Drone";
	}
	
	public void clearFlightPath(){
		flightPath.clear();
	}
	
}
