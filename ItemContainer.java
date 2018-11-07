package farming;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ItemContainer extends commonItem{
	
	public ItemContainer(String name, int price, int locX, int locY, int length, int width) {
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
	}
	
	/**
	 * This is used exclusively for the creation of the rootnode of the jtree
	 * @param name
	 */
	public ItemContainer(String name) {
		this.name = name;
		this.price = 0;
	}
	
	public void updateInfo(String name, int locX, int locY,int length, int width, int price) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
	}
	
	public void showInfo(){
		JTextField name = new JTextField();
		JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
		
		name.setText(this.getName());
		locX.setText(Integer.toString(this.getLocX()));
		locY.setText(Integer.toString(this.getLocY()));
		length.setText(Integer.toString(this.getLength()));
		width.setText(Integer.toString(this.getWidth()));
		price.setText(Integer.toString(this.getPrice()));
		
		Object[] Message = {
				"All of these fields are required for item creation",
				"Name:" , name,
				"X location:", locX, "Y location:", locY,
				"Length:", length, "Width:", width,
				"Price:", price
		};
		
		int result = JOptionPane.showConfirmDialog(null, Message, "Update "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(name.getText(), 
					Integer.valueOf(locX.getText()), 
					Integer.valueOf(locY.getText()), 
					Integer.valueOf(length.getText()), 
					Integer.valueOf(width.getText()), 
					Integer.valueOf(price.getText()));
			Fscreen.text.append("Updated "+ this.toString() + "\n");
		}
	}
	
	public String toString() {
		return this.name + " - Container";
	}

}
