package farming;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LiveStock extends Item{
	
	String animalType; //Pig,Dog,Cow are all examples
	ArrayList<String> info = new ArrayList<String>(); 
	//Idea is to hold all the warnings or notes for each animal
	//Such as vaccination dates or health warnings
	String gender; //Male or Female (M or F)
	
	public LiveStock(String name, int price, int locX, int locY, int length, int width
			,String animalType, String gender, int marketValue) {
		super(name,price,locX,locY,length,width, marketValue);
		this.animalType = animalType;
		this.gender = gender;
	}
	
	public String toString() {
		return name + " - Livestock";
	}
	
	public void updateInfo(String name, int price, int locX, int locY, int length, int width
			,String animalType, String gender, int marketValue) {
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
	
	public void showInfo() {
		
		JTextField name = new JTextField();
		JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField mPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField animalType = new JTextField();
		JTextField animalGender = new JTextField();
		name.setText(this.getName());
		locX.setText(Integer.toString(this.getLocX()));
		locY.setText(Integer.toString(this.getLocY()));
		length.setText(Integer.toString(this.getLength()));
		width.setText(Integer.toString(this.getWidth()));
		price.setText(Float.toString(this.getPrice()));
		mPrice.setText(Integer.toString(this.getMarketValue()));
		animalType.setText(this.getAnimalType());
		animalGender.setText(this.getGender());
		
		Object[] animalMessage = {
				"All of these fields are required for item creation",
				"Name:" , name,
				"X location:", locX,
				"Y location:", locY,
				"Length:", length,
				"Width:", width,
				"Price:", price,
				"Market Value:", mPrice,
				"Animal Type:", animalType,
				"Gender:", animalGender
		};
		
		int result = JOptionPane.showConfirmDialog(null, animalMessage, "Update "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(name.getText(), 
					Integer.valueOf(price.getText()), 
					Integer.valueOf(locX.getText()), 
					Integer.valueOf(locY.getText()), 
					Integer.valueOf(length.getText()), 
					Integer.valueOf(width.getText()), 
					animalType.getText(), 
					animalGender.getText(),
					Integer.valueOf(mPrice.getText()));
			Fscreen.text.append("Updated "+ this.toString() + "\n");
		}
	}
	
	/**
	 * This method adds a piece of string info to an animal
	 * @param a, the info to be added
	 */
	public void addInfo(String a) {
		info.add(a);
	}
	
	/**
	 * Removes the requested info
	 * @param a , info to be deleted
	 */
	public void removeInfo(String a) {
		info.remove(a);
	}
	
	/**
	 * This will remove all info from a livestock's info
	 */
	public void clearInfo() {
		info.clear();
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

}
