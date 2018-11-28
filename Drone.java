package farming;

import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Drone extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ItemContainer> flightPath = new ArrayList<ItemContainer>();
	// Contains the areas for the Drone to scan

	public Drone(String name, int price, int locX, int locY, int length, int width, int marketPrice) {
		super(name, price, locX, locY, length, width, marketPrice);
	}

	public Drone(String name, String price, String locX, String locY, String length, String width, String marketPrice) {
		super(name, price, locX, locY, length, width, marketPrice);
	}

	public Drone clone() {
		return new Drone(name, price, locX, locY, length, width, marketValue);
	}

	public void addFlightPath(ItemContainer a) {
		flightPath.add(a);
	}

	public void clearFlightPath() {
		flightPath.clear();
	}

	public void removeFlightPath(ItemContainer a) {
		flightPath.remove(a);
	}

	public void showInfo() {
		
		JTextField nameField = new JTextField(this.getName());
		JFormattedTextField locXField = new JFormattedTextField(createFormat());
		JFormattedTextField locYField = new JFormattedTextField(createFormat());
		JFormattedTextField lengthField = new JFormattedTextField(createFormat());
		JFormattedTextField widthField = new JFormattedTextField(createFormat());
		JFormattedTextField priceField = new JFormattedTextField(createFormat());
		JTextField mPriceField = new JFormattedTextField(createFormat());

		nameField.setText(this.getName());
		locXField.setText(Integer.toString(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));
		mPriceField.setText(Integer.toString(this.getMarketValue()));

		Object[] droneMessage = { "All of these fields are required for item creation", "Name:", nameField,
				"X location:", locXField, "Y location:", locYField, "Length:", lengthField, "Width:", widthField,
				"Price:", priceField, "Market Value:", mPriceField, };

		int result = JOptionPane.showConfirmDialog(null, droneMessage, "Create " + this.getName(),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), Integer.valueOf(priceField.getText()),
					Integer.valueOf(locXField.getText()), Integer.valueOf(locYField.getText()),
					Integer.valueOf(lengthField.getText()), Integer.valueOf(widthField.getText()),
					Integer.valueOf(mPriceField.getText()));
			Fscreen.createFscreen().text.append("Updated " + this.toString() + "\n");
		}
	}

	public String toString() {
		return name + " - Drone";
	}

	public void updateInfo(String name, int price, int locX, int locY, int length, int width, int marketPrice) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketPrice);
	}

}
