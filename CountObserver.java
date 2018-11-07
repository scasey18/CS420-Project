package farming;

import java.util.ArrayList;

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

}
