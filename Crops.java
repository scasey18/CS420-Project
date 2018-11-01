package farming;

public class Crops extends Item{

	String cropType; //The name of the type of crop 
	//Wheat, Cotton, etc.
	
	public Crops(String name, float price, int locX, int locY, int length, int width,
			String cropType,int marketValue) {
		super(name,price,locX,locY,length,width,marketValue);
		this.cropType = cropType;
	}
	
	public String toString() {
		return name + " - Crops";
	}
	
	public void updateInfo(String name, float price, int locX, int locY, int length, int width,
			String cropType,int marketValue) {
		this.setName(name);
		this.setPrice(price);
		this.setLocX(locX);
		this.setLocY(locY);
		this.setLength(length);
		this.setWidth(width);
		this.setMarketValue(marketValue);
		this.setCropType(cropType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crops other = (Crops) obj;
		if (cropType == null) {
			if (other.cropType != null)
				return false;
		} else if (!cropType.equals(other.cropType))
			return false;
		return true;
	}

	public String getCropType() {
		return cropType;
	}


	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	
	
}
