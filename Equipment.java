package farming;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Equipment extends Item{
	
	String equipmentType; //Tractor, Feeder, etc.
	ArrayList<String> info = new ArrayList<String>(); 
	//hold info about this piece of equipment
	String company; //John Deere, etc.
	String modelNumber; //MX993, A113, etc. (Sometimes model numbers have letters)
	
	public Equipment(String name, int price, int locX, int locY, int length, int width,int marketValue,
			String equipmentType, String company, String modelNumber) {
		super(name,price,locX,locY,length,width,marketValue);
		this.equipmentType = equipmentType;
		this.company = company;
		this.modelNumber = modelNumber;
	}
	
	public Equipment(String name,int price,int locX, int locY, int length, int width,
			int marketValue, String equipmentType, String company) {
		super(name,price,locX,locY,length,width,marketValue);
		this.equipmentType = equipmentType;
		this.company = company;
		this.modelNumber = "";
	}
	
	public Equipment(String name, int price,int locX, int locY, int length, int width,
			int marketValue, String equipmentType) {
		super(name,price,locX,locY,length,width,marketValue);
		this.equipmentType = equipmentType;
		this.company ="";
		this.modelNumber = "";
	}
	
	public String toString() {
		return name + " - Equipment";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (equipmentType == null) {
			if (other.equipmentType != null)
				return false;
		} else if (!equipmentType.equals(other.equipmentType))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (modelNumber == null) {
			if (other.modelNumber != null)
				return false;
		} else if (!modelNumber.equals(other.modelNumber))
			return false;
		return true;
	}
	
	public void updateInfo(String name, int price, int locX, int locY, int length, int width,int marketValue,
			String equipmentType, String company, String modelNumber) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
		this.setEquipmentType(equipmentType);
		this.setCompany(company);
		this.setModelNumber(modelNumber);
	}
	
	public void showInfo() {
		JTextField name = new JTextField();
		JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField mPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField equipmentType = new JTextField();
		JTextField company = new JTextField();
		JTextField modelNumber = new JTextField();
		
		name.setText(this.getName());
		locX.setText(Integer.toString(this.getLocX()));
		locY.setText(Integer.toString(this.getLocY()));
		length.setText(Integer.toString(this.getLength()));
		width.setText(Integer.toString(this.getWidth()));
		price.setText(Integer.toString(this.getPrice()));
		mPrice.setText(Integer.toString(this.getMarketValue()));
		equipmentType.setText(this.getEquipmentType());
		company.setText(this.getCompany());
		modelNumber.setText(this.getModelNumber());
		
		Object[] equipMessage = {
				"All of these fields are required for item creation",
				"Name:" , name,
				"X location:", locX,
				"Y location:", locY,
				"Length:", length,
				"Width:", width,
				"Price:", price,
				"Market Price:", mPrice,
				"Type of Equipment: ", equipmentType,
    			"Company Name: ", company,
    			"Model Number: ",modelNumber
		};
		
		int result = JOptionPane.showConfirmDialog(null, equipMessage, "Update "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(name.getText(), 
					Integer.valueOf(price.getText()),
					Integer.valueOf(locX.getText()),
					Integer.valueOf(locY.getText()), 
					Integer.valueOf(length.getText()), 
					Integer.valueOf(width.getText()), 
					Integer.valueOf(mPrice.getText()),
					equipmentType.getText(), 
					company.getText(), 
					modelNumber.getText());
			Fscreen.text.append("Updated "+ this.toString() + "\n");
		}
	}

	/**
	 * This method adds a piece of string info to a piece of equipment
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
	 * This will remove all info from a equipment's info
	 */
	public void clearInfo() {
		info.clear();
	}
	
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
}
