package farming;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Supplies extends Item{
	
	int count; //This will hold the current amount of the the supply
	
	public Supplies(String name, int price, int locX, int locY, int length, int width,
			int count, int marketValue) {
		super(name, price, locX, locY, length, width, marketValue);
		this.count = count;
	}
	
	public void updateInfo(String name, int price, int locX, int locY, int length, int width,
			int count, int marketValue) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
		this.setCount(count);
	}
	
	public void showInfo() {
		
		JTextField name = new JTextField();
		JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField mPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		JTextField count = new JFormattedTextField(NumberFormat.getNumberInstance());
		
		name.setText(this.getName());
		locX.setText(Integer.toString(this.getLocX()));
		locY.setText(Integer.toString(this.getLocY()));
		length.setText(Integer.toString(this.getLength()));
		width.setText(Integer.toString(this.getWidth()));
		price.setText(Integer.toString(this.getPrice()));
		mPrice.setText(Integer.toString(this.getMarketValue()));
		count.setText(Integer.toString(this.getCount()) );
		
		Object[] supplyMessage = {
				"All of these fields are required for item creation",
				"Name:" , name,
				"X location:", locX,
				"Y location:", locY,
				"Length:", length,
				"Width:", width,
				"Price:", price,
				"Market Value:", mPrice,
				"Count", count
		};
		
		int result = JOptionPane.showConfirmDialog(null, supplyMessage, "Create "+
				this.getName(), JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			this.updateInfo(name.getText(), 
					Integer.valueOf(price.getText()), 
					Integer.valueOf(locX.getText()), 
					Integer.valueOf(locY.getText()), 
					Integer.valueOf(length.getText()), 
					Integer.valueOf(width.getText()), 
					Integer.valueOf(count.getText()) ,
					Integer.valueOf(mPrice.getText()));
			Fscreen.text.append("Updated "+ this.toString() + "\n");
		}
	}
	
	public String toString() {
		return name + " - Supplies";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

}
