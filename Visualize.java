package farming;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Visualize {
	
	static JPanel view;
	private Graphics2D g2;
	
	public Visualize(DefaultTreeModel model) {
		BufferedImage off_Image = new BufferedImage(findLargestX((DefaultMutableTreeNode)model.getRoot())+10, 
				findLargestY((DefaultMutableTreeNode)model.getRoot())+10,
				                    BufferedImage.TYPE_INT_ARGB);

		g2 = off_Image.createGraphics();
        traverseTree((DefaultMutableTreeNode)model.getRoot());
        JFrame view = new JFrame("Visualized Farm");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        view.setLocation(dim.width/2-view.getSize().width/2, dim.height/2-view.getSize().height/2);
        view.add(new JLabel(new ImageIcon(off_Image)));
        view.pack();
        
        view.setVisible(true);
	}
	
	public void paint(int x, int y, int z, int a) {
		g2.setColor(Color.BLACK);
		g2.drawRect(x,y,z,a);
	}
	
	public void traverseTree(DefaultMutableTreeNode node) {
		@SuppressWarnings("rawtypes")
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
	
	public int findLargestX(DefaultMutableTreeNode node) {
		@SuppressWarnings("rawtypes")
		Enumeration en = node.postorderEnumeration();
		int x = 0;
		while(en.hasMoreElements()) {
			node = (DefaultMutableTreeNode) en.nextElement();
			switch(node.getUserObject().getClass().toString()) {
			case "class farming.ItemContainer":
				ItemContainer a = (ItemContainer)node.getUserObject();
				if (a.getLocX()+ a.getLength() > x) {
					x = a.getLocX()+ a.getLength();
				}
				break;
			case "class farming.LiveStock":
				LiveStock a1 = (LiveStock)node.getUserObject();
				if (a1.getLocX()+ a1.getLength() > x) {
					x = a1.getLocX()+ a1.getLength();
				}
				break;
			case "class farming.Drone":
				Drone a2 = (Drone)node.getUserObject();
				if (a2.getLocX()+ a2.getLength() > x) {
					x = a2.getLocX()+ a2.getLength();
				}
				break;
			case "class farming.Supplies":
				Supplies a3 = (Supplies)node.getUserObject();
				if (a3.getLocX()+ a3.getLength() > x) {
					x = a3.getLocX()+ a3.getLength();
				}
				break;
			case "class farming.Crops":
				Crops a4 = (Crops)node.getUserObject();
				if (a4.getLocX()+ a4.getLength() > x) {
					x = a4.getLocX()+ a4.getLength();
				}
				break;
			case "class farming.Equipment":
				Equipment a5 = (Equipment)node.getUserObject();
				if (a5.getLocX()+ a5.getLength() > x) {
					x = a5.getLocX()+ a5.getLength();
				}
				break;
			}
		}
		return x;
	}
	
	public int findLargestY(DefaultMutableTreeNode node) {
		@SuppressWarnings("rawtypes")
		Enumeration en = node.postorderEnumeration();
		int x = 0;
		while(en.hasMoreElements()) {
			node = (DefaultMutableTreeNode) en.nextElement();
			switch(node.getUserObject().getClass().toString()) {
			case "class farming.ItemContainer":
				ItemContainer a = (ItemContainer)node.getUserObject();
				if (a.getLocY()+a.getWidth() > x) {
					x = a.getLocY()+a.getWidth();
				}
				break;
			case "class farming.LiveStock":
				LiveStock a1 = (LiveStock)node.getUserObject();
				if (a1.getLocY()+a1.getWidth() > x) {
					x = a1.getLocY()+a1.getWidth();
				}
				break;
			case "class farming.Drone":
				Drone a2 = (Drone)node.getUserObject();
				if (a2.getLocY()+a2.getWidth() > x) {
					x = a2.getLocY()+a2.getWidth();
				}
				break;
			case "class farming.Supplies":
				Supplies a3 = (Supplies)node.getUserObject();
				if (a3.getLocY()+a3.getWidth() > x) {
					x = a3.getLocY()+a3.getWidth();
				}
				break;
			case "class farming.Crops":
				Crops a4 = (Crops)node.getUserObject();
				if (a4.getLocY()+a4.getWidth() > x) {
					x = a4.getLocY()+a4.getWidth();
				}
				break;
			case "class farming.Equipment":
				Equipment a5 = (Equipment)node.getUserObject();
				if (a5.getLocY()+a5.getWidth() > x) {
					x = a5.getLocY()+a5.getWidth();
				}
				break;
			}
		}
		return x;
	}
}
