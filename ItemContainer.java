package farming;

import java.util.ArrayList;

public class ItemContainer {
	
	ArrayList<Item> self = new ArrayList<Item>();
	float total = 0;
	String name; //holds name
	float price; //Holds price of the container
	int locX; //intial x
	int locY; //intial y
	int length; //length value
	int width; //width value
	float containerPrice; //holds total price for everything in the container
	
	public ItemContainer(String name, int locX, int locY, int length, int width) {
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = 500; //Default if price is not given
	}
	
	public ItemContainer(String name, Float price, int locX, int locY, int length, int width) {
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
	}
	
	/**
	 * This is used for custom scanning areas defined by the farmer.
	 * @param locX
	 * @param locY
	 * @param length
	 * @param width
	 */
	public ItemContainer(int locX,int locY, int length, int width) {
		this.name = "Custom";
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = 0;
	}

	public ArrayList<Item> getSelf() {
		return self;
	}
	
	/**
	 * This will calculate the total price of all items in this container
	 */
	public void totalPrice() {
		total = 0;
		for (Item a : self) {
			total += a.getPrice();
		}
		containerPrice = total;
	}
	
	/**
	 * This adds a item to the container but does not if the same item already exists
	 * @param a, item you wish to add to the container
	 */
	public void addItem(Item a) {
		if (!self.contains(a)) {
			self.add(a);
			totalPrice();
		}
	}
	
	public void removeItem(Item a) {
		if (self.contains(a)) {
			self.remove(a);
			totalPrice();
		}
	}
	
	/**
	 * This method will remove all items from this container
	 */
	public void removeAllItems() {
		self.clear();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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
	
	public String toString() {
		return this.name + " - Container";
	}

}
