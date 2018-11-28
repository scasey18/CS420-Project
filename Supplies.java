package farming;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Supplies extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int count; // This will hold the current amount of the the supply

	public Supplies(String name, int price, int locX, int locY, int length, int width, int count, int marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.count = count;
	}

	public Supplies(String name, String price, String locX, String locY, String length, String width, int count,
			String marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.count = Integer.valueOf(count);
	}

	public Supplies clone() {
		return new Supplies(name, price, locX, locY, length, width, count, marketValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplies other = (Supplies) obj;
		if (count != other.count)
			return false;
		return true;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

		JFormattedTextField count = new JFormattedTextField(createFormat());

		locXField.setText(Integer.toString(this.getLocX()));
		locYField.setText(Integer.toString(this.getLocY()));
		lengthField.setText(Integer.toString(this.getLength()));
		widthField.setText(Integer.toString(this.getWidth()));
		priceField.setText(Integer.toString(this.getPrice()));
		mPriceField.setText(Integer.toString(this.getMarketValue()));
		count.setText(Integer.toString(this.getCount()));

		Object[] supplyMessage = { "All of these fields are required for item creation", "Name:", nameField,
				"X location:", locXField, "Y location:", locYField, "Length:", lengthField, "Width:", widthField,
				"Price:", priceField, "Market Value:", mPriceField, "Count", count };

		int result = JOptionPane.showConfirmDialog(null, supplyMessage, "Create " + this.getName(),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(nameField.getText(), Integer.valueOf(priceField.getText()),
					Integer.valueOf(locXField.getText()), Integer.valueOf(locYField.getText()),
					Integer.valueOf(lengthField.getText()), Integer.valueOf(widthField.getText()),
					Integer.valueOf(count.getText()), Integer.valueOf(mPriceField.getText()));
			Fscreen.createFscreen().text.append("Updated " + this.toString() + "\n");
		}
	}

	public String toString() {
		return name + " - Supplies";
	}

	public void updateInfo(String name, int price, int locX, int locY, int length, int width, int count,
			int marketValue) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
		this.setCount(count);
	}

}
