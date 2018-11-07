package farming;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class PriceVisitor implements Visitor{
	
	public int visit(Crops crop) {
		return crop.getPrice();
	}
	public int visit(Drone drone) {
		return drone.getPrice();
	}
	public int visit(Equipment equip) {
		return equip.getPrice();
	}
	public int visit(ItemContainer container) {
		return container.getPrice();
	}
	public int visit(LiveStock stock) {
		return stock.getPrice();
	}
	public int visit(Supplies supply) {
		return supply.getPrice();
	}
	
	public int visit(DefaultMutableTreeNode node) {
		if (node.getAllowsChildren() == false) {
			return ObjectPrice(node);
		}
		else {
			int price = ObjectPrice(node);
			Enumeration<?> child = node.children();
			while(child.hasMoreElements()) {
				price += ObjectPrice((DefaultMutableTreeNode)child.nextElement());
			}
			return price;
		}
	}
	
	public int ObjectPrice(DefaultMutableTreeNode object) {
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
