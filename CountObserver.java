package farming;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;

public class CountObserver {
	
	private int count;
	ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
	
	public void addObserver(DefaultMutableTreeNode node) {
        this.nodes.add(node);
    }
 
    public void removeObserver(DefaultMutableTreeNode node) {
        this.nodes.remove(node);
    }
 
    public void setCount(int count) {
        this.count = count;
        for (DefaultMutableTreeNode node : nodes) {
            itemObject(node);
        }
        
    }
    
    public void itemObject(DefaultMutableTreeNode node) {
    	switch(node.getUserObject().getClass().toString()) {
		case "class farming.Equipment":
			((Equipment)node.getUserObject()).setItemCount(count);
			break;
		case "class farming.Drone":
			((Drone)node.getUserObject()).setItemCount(count);
			break;
		case "class farming.Crops":
			((Crops)node.getUserObject()).setItemCount(count);
			break;
		case "class farming.LiveStock":
			((LiveStock)node.getUserObject()).setItemCount(count);
			break;
		case "class farming.Supplies":
			((Supplies)node.getUserObject()).setItemCount(count);
			break;
    	}
    }
    
    /**
	 * This runs the "root" node and return the number of items in the tree
	 */
	@SuppressWarnings({ "rawtypes" })
	public int count(DefaultMutableTreeNode rootNode) {
		int countA = 0;
		Enumeration a = rootNode.children();
		DefaultMutableTreeNode b;
		// Iterates through every node and if it is not a ItemContainer add 1
		// Meaning it has to be an item so add to the count and move on
		while (a.hasMoreElements()) {
			b = (DefaultMutableTreeNode) a.nextElement();
			if (b.getUserObject().getClass().toString() == "class farming.ItemContainer") {
				
			}
			else {countA++;}
		}
		return countA;
	}

}
