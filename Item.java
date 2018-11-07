package farming;

public class Item extends commonItem{
	
	int marketValue; //Item market value
	int itemCount; //total count of items to be held by all items


	public void update(int count) {
		this.setItemCount(count);
	}


	public Item(String name,int price, int locX, int locY, int length, int width, int marketValue){
		this.name = name;
		this.locX = locX;
		this.locY = locY;
		this.length = length;
		this.width = width;
		this.price = price;
		this.marketValue = marketValue;
	}

	public int getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(int marketValue) {
		this.marketValue = marketValue;
	}
	
	public String toString() {
		return name;
	}
	
	public int getItemCount() {
		return itemCount;
	}


	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
}
