package farming;

import javax.swing.JOptionPane;

public class ItemContainer extends commonItem{
	
	/**
	 * This is used exclusively for the creation of the rootnode of the jtree
	 * @param name
	 */
	public ItemContainer(String name) {
		this.name = name;
		this.price = 0;
		this.length = 500;
		this.width = 500;
	}
	
	public ItemContainer(String name, int price, int locX, int locY, int length, int width) {
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
	}
	
	public ItemContainer clone() {
		return new ItemContainer(name,price,locX,locY,length,width);
	}
	
	public void showInfo(){
		
		nameField.setText(this.getName());
		locXField.setText(Integer.toString(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));
		
		Object[] Message = {
				"All of these fields are required for item creation",
				"Name:" , nameField,
				"X location:", locXField, "Y location:", locYField,
				"Length:", lengthField, "Width:", widthField,
				"Price:", priceField
		};
		
		int result = JOptionPane.showConfirmDialog(null, Message, "Update "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), 
					Integer.valueOf(locXField.getText()), 
					Integer.valueOf(locYField.getText()), 
					Integer.valueOf(lengthField.getText()), 
					Integer.valueOf(widthField.getText()), 
					Integer.valueOf(priceField.getText()));
			Fscreen.createFscreen().text.append("Updated "+ this.toString() + "\n");
		}
	}
	
	public String toString() {
		return this.name + " - Container";
	}
	
	public void updateInfo(String name, int locX, int locY,int length, int width, int price) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
	}

}
