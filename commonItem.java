package farming;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class commonItem {
	
	String name; //holds name
	int price; //Holds price of the container
	int locX; //intial x
	int locY; //intial y
	int length; //length value
	int width; //width value
	
	JTextField nameField = new JTextField();
	JTextField locXField = new JFormattedTextField(NumberFormat.getNumberInstance());
	JTextField locYField = new JFormattedTextField(NumberFormat.getNumberInstance());
	JTextField lengthField = new JFormattedTextField(NumberFormat.getNumberInstance());
	JTextField widthField = new JFormattedTextField(NumberFormat.getNumberInstance());
	JTextField priceField = new JFormattedTextField(NumberFormat.getNumberInstance());
	
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
