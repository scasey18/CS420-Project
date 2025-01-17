package farming;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Crops extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cropType; // The name of the type of crop
	// Wheat, Cotton, etc.

	public Crops(String name, int price, int locX, int locY, int length, int width, String cropType, int marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.cropType = cropType;
	}

	public Crops(String name, String price, String locX, String locY, String length, String width, String cropType,
			String marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.cropType = cropType;
	}

	public Crops clone() {
		return new Crops(name, price, locX, locY, length, width, cropType, marketValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crops other = (Crops) obj;
		if (cropType == null) {
			if (other.cropType != null)
				return false;
		} else if (!cropType.equals(other.cropType))
			return false;
		return true;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public void showInfo() {
		
		JTextField nameField = new JTextField(this.getName());
		JFormattedTextField locXField = new JFormattedTextField(createFormat());
		JFormattedTextField locYField = new JFormattedTextField(createFormat());
		JFormattedTextField lengthField = new JFormattedTextField(createFormat());
		JFormattedTextField widthField = new JFormattedTextField(createFormat());
		JFormattedTextField priceField = new JFormattedTextField(createFormat());
		JTextField mPriceField = new JFormattedTextField(createFormat());

		JTextField cropType = new JTextField(this.getCropType());

		locXField.setText(String.valueOf(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));
		mPriceField.setText(Integer.toString(this.getMarketValue()));

		Object[] cropMessage = { "All of these fields are required for item creation", "Name:", nameField,
				"X location:", locXField, "Y location:", locYField, "Length:", lengthField, "Width:", widthField,
				"Price:", priceField, "Market Value:", mPriceField, "Crop Type:", cropType };

		int result = JOptionPane.showConfirmDialog(null, cropMessage, "Update " + this.getName(),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), Integer.valueOf(priceField.getText()),
					Integer.valueOf(locXField.getText()), Integer.valueOf(locYField.getText()),
					Integer.valueOf(lengthField.getText()), Integer.valueOf(widthField.getText()), cropType.getText(),
					Integer.valueOf(mPriceField.getText()));
			Fscreen.createFscreen().text.append("Updated " + this.toString() + "\n");
		}
	}

	public String toString() {
		return name + " - Crops";
	}

	public void updateInfo(String name, int price, int locX, int locY, int length, int width, String cropType,
			int marketValue) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
		this.setCropType(cropType);
	}

}
