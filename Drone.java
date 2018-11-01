package farming;

import java.util.ArrayList;

public class Drone extends Item{
	
	ArrayList<ItemContainer> flightPath = new ArrayList<ItemContainer>();
	//Contains the areas for the Drone to scan
	
	public Drone(String name,float price, int locX, int locY, int length, int width, int marketPrice) {
		super(name,price,locX,locY,length,width, marketPrice);
	}
	
	public void updateInfo(String name,float price, int locX, int locY, int length, int width, int marketPrice) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
	}
	
	public void addFlightPath(ItemContainer a) {
		flightPath.add(a);
	}
	
	public void removeFlightPath(ItemContainer a) {
		flightPath.remove(a);
	}
	
	public String toString() {
		return name + " - Drone";
	}
	
	public void clearFlightPath(){
		flightPath.clear();
	}
	
}
