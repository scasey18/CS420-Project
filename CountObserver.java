package farming;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class CountObserver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;
	ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();

	public void addObserver(DefaultMutableTreeNode node) {
		this.nodes.add(node);
	}

	/**
	 * This runs the "root" node and return the number of items in the tree
	 */
	@SuppressWarnings({ "rawtypes" })
	public int count() {
		int countA = 0;
		Enumeration a = Fscreen.createFscreen().rootNode.postorderEnumeration();
		DefaultMutableTreeNode b;
		// Iterates through every node and if it is not a ItemContainer add 1
		// Meaning it has to be an item so add to the count and move on
		while (a.hasMoreElements()) {
			b = (DefaultMutableTreeNode) a.nextElement();
			// if (b.getUserObject().getClass().toString() == "class farming.ItemContainer")
			// {
			if (b.getAllowsChildren() == false) {
				countA++;
			}
		}
		return countA;
	}

	public void itemObject(DefaultMutableTreeNode node) {
		switch (node.getUserObject().getClass().toString()) {
		case "class farming.Equipment":
			((Equipment) node.getUserObject()).setItemCount(count);
			break;
		case "class farming.Drone":
			((Drone) node.getUserObject()).setItemCount(count);
			break;
		case "class farming.Crops":
			((Crops) node.getUserObject()).setItemCount(count);
			break;
		case "class farming.LiveStock":
			((LiveStock) node.getUserObject()).setItemCount(count);
			break;
		case "class farming.Supplies":
			((Supplies) node.getUserObject()).setItemCount(count);
			break;
		}
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

}
