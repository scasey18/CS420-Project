package farming;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class MarketVisitor implements Visitor{
	
	public int visit(ItemContainer container) {
		return 0;
	}
	public int visit(Crops crop) {
		return crop.getMarketValue();
	}
	public int visit(Drone drone) {
		return drone.getMarketValue();
	}
	public int visit(Equipment equip) {
		return equip.getMarketValue();
	}
	public int visit(LiveStock stock) {
		return stock.getMarketValue();
	}
	public int visit(Supplies supply) {
		return supply.getMarketValue();
	}
	
	public int visit(DefaultMutableTreeNode node) {
		if (node.getAllowsChildren() == false) {
			return ObjectMPrice(node);
		}
		else {
			int price = ObjectMPrice(node);
			Enumeration<?> child = node.children();
			while(child.hasMoreElements()) {
				price += ObjectMPrice((DefaultMutableTreeNode)child.nextElement());
			}
			return price;
		}
	}
	
	public int ObjectMPrice(DefaultMutableTreeNode object) {
		switch (object.getUserObject().getClass().toString()) {
		case "class farming.ItemContainer":
			return visit((ItemContainer) object.getUserObject());
		case "class farming.Equipment":
			return visit((Equipment) object.getUserObject());
		case "class farming.Drone":
			return visit((Drone) object.getUserObject());
		case "class farming.Crops":
			return visit((Crops) object.getUserObject());
		case "class farming.LiveStock":
			return visit((LiveStock) object.getUserObject());
		case "class farming.Supplies":
			return visit((Supplies) object.getUserObject());
		}
		return 0;
	}
	
}
