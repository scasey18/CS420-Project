package farming;

import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;

public class Pair implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private DefaultMutableTreeNode node1;
	private DefaultMutableTreeNode node2;
	private Object obj;

	public Pair(String action, DefaultMutableTreeNode node1, DefaultMutableTreeNode node2) {
		this.action = action;
		this.node1 = node1;
		this.node2 = node2;
	}

	public Pair(String action, DefaultMutableTreeNode node1, Object obj) {
		this.action = action;
		this.node1 = node1;
		this.obj = obj;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public DefaultMutableTreeNode getNode1() {
		return node1;
	}

	public void setNode1(DefaultMutableTreeNode node1) {
		this.node1 = node1;
	}

	public DefaultMutableTreeNode getNode2() {
		return node2;
	}

	public void setNode2(DefaultMutableTreeNode node2) {
		this.node2 = node2;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public Pair clone() {
		if (this.getObj() != null) {
			return new Pair(this.getAction(),this.getNode1(),this.getObj());
		}
		return new Pair(this.getAction(),this.getNode1(),this.getNode2());
	}

}
