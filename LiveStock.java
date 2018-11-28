package farming;

import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LiveStock extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String animalType; // Pig,Dog,Cow are all examples
	ArrayList<String> info = new ArrayList<String>();
	// Idea is to hold all the warnings or notes for each animal
	// Such as vaccination dates or health warnings
	String gender; // Male or Female (M or F)

	public LiveStock(String name, int price, int locX, int locY, int length, int width, String animalType,
			String gender, int marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.animalType = animalType;
		this.gender = gender;
	}

	public LiveStock(String name, String price, String locX, String locY, String length, String width,
			String animalType, String gender, String marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.animalType = animalType;
		this.gender = gender;
	}

	public LiveStock clone() {
		return new LiveStock(name, price, locX, locY, length, width, animalType, gender, marketValue);
	}

	/**
	 * This method adds a piece of string info to an animal
	 * 
	 * @param a,
	 *            the info to be added
	 */
	public void addInfo(String a) {
		info.add(a);
	}

	/**
	 * This will remove all info from a livestock's info
	 */
	public void clearInfo() {
		info.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LiveStock other = (LiveStock) obj;
		if (animalType == null) {
			if (other.animalType != null)
				return false;
		} else if (!animalType.equals(other.animalType))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

	public String getAnimalType() {
		return animalType;
	}

	public String getGender() {
		return gender;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	/**
	 * Removes the requested info
	 * 
	 * @param a
	 *            , info to be deleted
	 */
	public void removeInfo(String a) {
		info.remove(a);
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

	@SuppressWarnings("static-access")
	public void showInfo() {
		
		JTextField nameField = new JTextField(this.getName());
		JFormattedTextField locXField = new JFormattedTextField(createFormat());
		JFormattedTextField locYField = new JFormattedTextField(createFormat());
		JFormattedTextField lengthField = new JFormattedTextField(createFormat());
		JFormattedTextField widthField = new JFormattedTextField(createFormat());
		JFormattedTextField priceField = new JFormattedTextField(createFormat());
		JTextField mPriceField = new JFormattedTextField(createFormat());
		
		JTextField animalType = new JTextField(this.getAnimalType());
		JTextField animalGender = new JTextField(this.getGender());

		locXField.setText(Integer.toString(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));
		mPriceField.setText(Integer.toString(this.getMarketValue()));

		Object[] animalMessage = { "All of these fields are required for item creation", "Name:", nameField,
				"X location:", locXField, "Y location:", locYField, "Length:", lengthField, "Width:", widthField,
				"Price:", priceField, "Market Value:", mPriceField, "Animal Type:", animalType, "Gender:",
				animalGender };

		int result = JOptionPane.showConfirmDialog(null, animalMessage, "Update " + this.getName(),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), Integer.valueOf(priceField.getText()),
					Integer.valueOf(locXField.getText()), Integer.valueOf(locYField.getText()),
					Integer.valueOf(lengthField.getText()), Integer.valueOf(widthField.getText()), animalType.getText(),
					animalGender.getText(), Integer.valueOf(mPriceField.getText()));
			Fscreen.createFscreen().text.append("Updated " + this.toString() + "\n");
		}
	}

	public String toString() {
		return name + " - Livestock";
	}

	public void updateInfo(String name, int price, int locX, int locY, int length, int width, String animalType,
			String gender, int marketValue) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setAnimalType(animalType);
		this.setGender(gender);
		this.setMarketValue(marketValue);
	}

}
