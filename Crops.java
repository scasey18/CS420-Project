package farming;

public class Crops extends Item{

	String cropType; //The name of the type of crop 
	//Wheat, Cotton, etc.
	

	public Crops(String name, int locX, int locY, int length, int width,
			String cropType) {
		super(name,locX,locY,length,width);
		this.cropType = cropType;
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
