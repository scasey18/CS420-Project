package farming;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Crops extends Item{

	String cropType; //The name of the type of crop 
	//Wheat, Cotton, etc.
	
	public Crops(String name, int price, int locX, int locY, int length, int width,
			String cropType,int marketValue) {
		super(name,price,locX,locY,length,width,marketValue);
		this.cropType = cropType;
	}
	
	public String toString() {
		return name + " - Crops";
	}
	
	public void updateInfo(String name, int price, int locX, int locY, int length, int width,
			String cropType,int marketValue) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
		this.setCropType(cropType);
	}
	
	public void showInfo() {
		
		JTextField name = new JTextField();
		JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField mPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField cropType = new JTextField();
		
		name.setText(this.getName());
		locX.setText(Integer.toString(this.getLocX()));
		locY.setText(Integer.toString(this.getLocY()));
		length.setText(Integer.toString(this.getLength()));
		width.setText(Integer.toString(this.getWidth()));
		price.setText(Integer.toString(this.getPrice()));
		mPrice.setText(Integer.toString(this.getMarketValue()));
		cropType.setText(this.getCropType());
		
		Object[] cropMessage = {
				"All of these fields are required for item creation",
				"Name:" , name,
				"X location:", locX,
				"Y location:", locY,
				"Length:", length,
				"Width:", width,
				"Price:", price,
				"Market Price:", mPrice,
				"Crop Type:", cropType
		};
		
		int result = JOptionPane.showConfirmDialog(null, cropMessage, "Update "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(name.getText(), 
					Integer.valueOf(price.getText()), 
					Integer.valueOf(locX.getText()), 
					Integer.valueOf(locY.getText()), 
					Integer.valueOf(length.getText()), 
					Integer.valueOf(width.getText()), 
					cropType.getText(),
					Integer.valueOf(mPrice.getText()));
			Fscreen.text.append("Updated "+ this.toString() + "\n");
		}
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
	
	
}
