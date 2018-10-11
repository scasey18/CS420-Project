package farming;

import java.util.ArrayList;

public class LiveStock extends Item{
	
	String animalType; //Pig,Dog,Cow are all examples
	ArrayList<String> info = new ArrayList<String>(); 
	//Idea is to hold all the warnings or notes for each animal
	//Such as vaccination dates or health warnings
	String gender; //Male or Female (M or F)
	
	public LiveStock(String name, String animalType, String gender) {
		super(name);
		this.animalType = animalType;
		this.gender = gender;
	}
	
	/**
	 * This method adds a piece of string info to an animal
	 * @param a, the info to be added
	 */
	public void addInfo(String a) {
		info.add(a);
	}
	
	/**
	 * Removes the requested info
	 * @param a , info to be deleted
	 */
	public void removeInfo(String a) {
		info.remove(a);
	}
	
	/**
	 * This will remove all info from a livestock's info
	 */
	public void clearInfo() {
		info.clear();
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
