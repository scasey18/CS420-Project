package farming;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class Item extends commonItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int marketValue; // Item market value
	int itemCount; // total count of items to be held by all items

	JTextField mPriceField = new JFormattedTextField(createFormat());

	public Item(String name, int price, int locX, int locY, int length, int width, int marketValue) {
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
		this.marketValue = marketValue;
	}

	public Item(String name, String price, String locX, String locY, String length, String width, String marketValue) {
		this.name = name;
		this.locX = Integer.valueOf(locX);
		this.locY = Integer.valueOf(locY);
		this.length = Integer.valueOf(length);
		this.width = Integer.valueOf(width);
		this.price = Integer.valueOf(price);
		this.marketValue = Integer.valueOf(marketValue);
	}

	public int getItemCount() {
		return itemCount;
	}

	public int getMarketValue() {
		return marketValue;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setMarketValue(int marketValue) {
		this.marketValue = marketValue;
	}

	public String toString() {
		return name;
	}

	public void update(int count) {
		this.setItemCount(count);
	}

}
