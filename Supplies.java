package farming;

public class Supplies extends Item{
	
	int count; //This will hold the current amount of the the supply
	
	public Supplies(String name, float price, int locX, int locY, int length, int width,
			int count) {
		super(name, price, locX, locY, length, width);
		this.count = count;
	}
	
	public String toString() {
		return name + " - Supplies";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplies other = (Supplies) obj;
		if (count != other.count)
			return false;
		return true;
	}

}
