package farming;

import java.io.Serializable;
import java.text.NumberFormat;

public class commonItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name; // holds name
	int price; // Holds price of the container
	int locX; // intial x
	int locY; // intial y
	int length; // length value
	int width; // width value

	public static NumberFormat createFormat() {
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setGroupingUsed(false);
		return format;
	}

	public int getLength() {
		return length;
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getWidth() {
		return width;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
