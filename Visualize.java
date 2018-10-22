package farming;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Visualize {
	
	static JFrame farmView;
	
	public Visualize(DefaultTreeModel model) {
		farmView = new JFrame();
		farmView.setSize(500, 500);

		farmView.setLocationRelativeTo(null);
		farmView.setVisible(true);
		farmView.setVisible(true);
        traverseTree((DefaultMutableTreeNode)model.getRoot());
        
	}
	
	public void paint(int x, int y, int z, int a) {
		Graphics2D g2 = (Graphics2D) farmView.getGraphics();
		g2.setColor(Color.BLACK);
		g2.drawRect(x,y,z,a);
	}
	
	public void traverseTree(DefaultMutableTreeNode node) {
		Enumeration en = node.postorderEnumeration();
		
		while(en.hasMoreElements()) {
			node = (DefaultMutableTreeNode) en.nextElement();
			switch(node.getUserObject().getClass().toString()) {
			case "class farming.ItemContainer":
				ItemContainer a = (ItemContainer)node.getUserObject();
				paint(a.getLocX(),a.getLocY(),a.getLength(),a.getWidth());
				break;
			case "class farming.LiveStock":
				LiveStock a1 = (LiveStock)node.getUserObject();
				paint(a1.getLocX(),a1.getLocY(),a1.getLength(),a1.getWidth());
				break;
			case "class farming.Drone":
				Drone a2 = (Drone)node.getUserObject();
				paint(a2.getLocX(),a2.getLocY(),a2.getLength(),a2.getWidth());
				break;
			case "class farming.Supplies":
				Supplies a3 = (Supplies)node.getUserObject();
				paint(a3.getLocX(),a3.getLocY(),a3.getLength(),a3.getWidth());
				break;
			case "class farming.Crops":
				Crops a4 = (Crops)node.getUserObject();
				paint(a4.getLocX(),a4.getLocY(),a4.getLength(),a4.getWidth());
				break;
			case "class farming.Equipment":
				Equipment a5 = (Equipment)node.getUserObject();
				paint(a5.getLocX(),a5.getLocY(),a5.getLength(),a5.getWidth());
				break;
			}
		}
	}
}
