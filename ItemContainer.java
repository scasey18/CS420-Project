package farming;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ItemContainer extends commonItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is used exclusively for the creation of the rootnode of the jtree
	 * 
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

	public ItemContainer(String name, String price, String locX, String locY, String length, String width) {
		this.name = name;
		this.locX = Integer.valueOf(locX);
		this.locY = Integer.valueOf(locY);
		this.length = Integer.valueOf(length);
		this.width = Integer.valueOf(width);
		this.price = Integer.valueOf(price);
	}

	public ItemContainer clone() {
		return new ItemContainer(name, price, locX, locY, length, width);
	}

	public void showInfo() {
		
		JTextField nameField = new JTextField(this.getName());
		JFormattedTextField locXField = new JFormattedTextField(createFormat());
		JFormattedTextField locYField = new JFormattedTextField(createFormat());
		JFormattedTextField lengthField = new JFormattedTextField(createFormat());
		JFormattedTextField widthField = new JFormattedTextField(createFormat());
		JFormattedTextField priceField = new JFormattedTextField(createFormat());

		locXField.setText(Integer.toString(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));

		Object[] Message = { "All of these fields are required for item creation", "Name:", nameField, "X location:",
				locXField, "Y location:", locYField, "Length:", lengthField, "Width:", widthField, "Price:",
				priceField };

		int result = JOptionPane.showConfirmDialog(null, Message, "Update " + this.getName(),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), Integer.valueOf(locXField.getText()),
					Integer.valueOf(locYField.getText()), Integer.valueOf(lengthField.getText()),
					Integer.valueOf(widthField.getText()), Integer.valueOf(priceField.getText()));
			Fscreen.createFscreen().text.append("Updated " + this.toString() + "\n");
		}
	}

	public String toString() {
		return this.name + " - Container";
	}

	public void updateInfo(String name, int locX, int locY, int length, int width, int price) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
	}

}
