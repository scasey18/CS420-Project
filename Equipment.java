package farming;

import java.util.ArrayList;

public class Equipment extends Item{
	
	String equipmentType; //Tractor, Feeder, etc.
	ArrayList<String> info = new ArrayList<String>(); 
	//hold info about this piece of equipment
	String company; //John Deere, etc.
	String modelNumber; //MX993, A113, etc. (Sometimes model numbers have letters)
	
	public Equipment(String name, float price, int locX, int locY, int length, int width,
			String equipmentType, String company, String modelNumber) {
		super(name,price,locX,locY,length,width);
		this.equipmentType = equipmentType;
		this.company = company;
		this.modelNumber = modelNumber;
	}
	
	public Equipment(String name, int locX, int locY, int length, int width,
			String equipmentType, String company, String modelNumber) {
		super(name,locX,locY,length,width);
		this.equipmentType = equipmentType;
		this.company = company;
		this.modelNumber = modelNumber;
	}
	
	public Equipment(String name, int locX, int locY, int length, int width,
			String equipmentType, String company) {
		super(name,locX,locY,length,width);
		this.equipmentType = equipmentType;
		this.company = company;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (equipmentType == null) {
			if (other.equipmentType != null)
				return false;
		} else if (!equipmentType.equals(other.equipmentType))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (modelNumber == null) {
			if (other.modelNumber != null)
				return false;
		} else if (!modelNumber.equals(other.modelNumber))
			return false;
		return true;
	}

	/**
	 * This method adds a piece of string info to a piece of equipment
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
	 * This will remove all info from a equipment's info
	 */
	public void clearInfo() {
		info.clear();
	}
	
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
}
