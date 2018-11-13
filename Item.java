package farming;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class Item extends commonItem{
	
	int marketValue; //Item market value
	int itemCount; //total count of items to be held by all items
	
	JTextField mPriceField = new JFormattedTextField(NumberFormat.getNumberInstance());


	public Item(String name,int price, int locX, int locY, int length, int width, int marketValue){
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
		this.marketValue = marketValue;
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
