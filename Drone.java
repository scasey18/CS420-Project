package farming;

import java.util.ArrayList;

public class Drone extends Item{
	
	ArrayList<ItemContainer> flightPath = new ArrayList<ItemContainer>();
	//Contains the areas for the Drone to scan
	
	public Drone(String name, int locX, int locY, int length, int width) {
		super(name,locX,locY,length,width);
	}
	
	public void addFlightPath(ItemContainer a) {
		flightPath.add(a);
	}
	
	public void removeFlightPath(ItemContainer a) {
		flightPath.remove(a);
	}
	
	public void addCustomFlightPath(int locX, int locY, int length, int width) {
		flightPath.add(new ItemContainer(locX, locY, length, width));
	}
	
	public void clearFlightPath(){
		flightPath.clear();
	}
	
}
