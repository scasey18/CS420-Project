package farming;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Equipment extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String equipmentType; // Tractor, Feeder, etc.
	ArrayList<String> info = new ArrayList<String>();
	// hold info about this piece of equipment
	String company; // John Deere, etc.
	String modelNumber; // MX993, A113, etc. (Sometimes model numbers have letters)

	public Equipment(String name, int price, int locX, int locY, int length, int width, int marketValue,
			String equipmentType, String company, String modelNumber) {
		super(name, price, locX, locY, length, width, marketValue);
		this.equipmentType = equipmentType;
		this.company = company;
		this.modelNumber = modelNumber;
	}

	public Equipment(String name, String price, String locX, String locY, String length, String width,
			String marketValue, String equipmentType, String company, String modelNumber) {
		super(name, price, locX, locY, length, width, marketValue);
		this.equipmentType = equipmentType;
		this.company = company;
		this.modelNumber = modelNumber;
	}

	public Equipment clone() {
		return new Equipment(name, price, locX, locY, length, width, marketValue, equipmentType, company, modelNumber);
	}

	/**
	 * This method adds a piece of string info to a piece of equipment
	 * 
	 * @param a,
	 *            the info to be added
	 */
	public void addInfo(String a) {
		info.add(a);
	}

	/**
	 * This will remove all info from a equipment's info
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

	public String getCompany() {
		return company;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public String getModelNumber() {
		return modelNumber;
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

	public void setCompany(String company) {
		this.company = company;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	@SuppressWarnings("static-access")
	public void showInfo() {

		JTextField equipmentType = new JTextField();
		JTextField company = new JTextField();
		JTextField modelNumber = new JTextField();

		nameField.setText(this.getName());
		locXField.setText(Integer.toString(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));
		mPriceField.setText(Integer.toString(this.getMarketValue()));
		equipmentType.setText(this.getEquipmentType());
		company.setText(this.getCompany());
		modelNumber.setText(this.getModelNumber());

		Object[] equipMessage = { "All of these fields are required for item creation", "Name:", nameField,
				"X location:", locXField, "Y location:", locYField, "Length:", lengthField, "Width:", widthField,
				"Price:", priceField, "Market Value:", mPriceField, "Type of Equipment: ", equipmentType,
				"Company Name: ", company, "Model Number: ", modelNumber };

		int result = JOptionPane.showConfirmDialog(null, equipMessage, "Update " + this.getName(),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), Integer.valueOf(priceField.getText()),
					Integer.valueOf(locXField.getText()), Integer.valueOf(locYField.getText()),
					Integer.valueOf(lengthField.getText()), Integer.valueOf(widthField.getText()),
					Integer.valueOf(mPriceField.getText()), equipmentType.getText(), company.getText(),
					modelNumber.getText());
			Fscreen.createFscreen().text.append("Updated " + this.toString() + "\n");
		}
	}

	public String toString() {
		return name + " - Equipment";
	}

	public void updateInfo(String name, int price, int locX, int locY, int length, int width, int marketValue,
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

}
