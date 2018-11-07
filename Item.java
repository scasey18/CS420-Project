package farming;

public class Item{
	
	String name; //Name of the item
	int price; //Price of the item
	int locX; //x-coordinate
	int locY; //y-coordinate
	int length; //size across the x axis
	int width; //size across the y axis
	int marketValue; //Item market value
	
	int itemCount;


	public void update(int count) {
		this.setItemCount(count);
	}


	public Item(String name,int price, int locX, int locY, int length, int width, int marketValue){
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
		this.marketValue = marketValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(int marketValue) {
		this.marketValue = marketValue;
	}
	
	public String toString() {
		return name;
	}
	
	public int getItemCount() {
		return itemCount;
	}


	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
}
