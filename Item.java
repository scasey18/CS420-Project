package farming;

public class Item {
	
	//TODO: This is a class for other objects so this class can have properties removed as soon as other object variables are decided
	
	String name; //Name of the item
	float price; //Price of the item (float is necessary)
	int locX; //x-coordinate
	int locY; //y-coordinate
	int length; //size across the x axis
	int width; //size across the y axis
	
	public Item(String name, int locX, int locY, int length, int width){
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = 500; //Default price value
	}
	
	public Item(String name,float price, int locX, int locY, int length, int width){
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (length != other.length)
			return false;
		if (locX != other.locX)
			return false;
		if (locY != other.locY)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (width != other.width)
			return false;
		return true;
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
	
	
	
}
