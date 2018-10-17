package farming;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Fscreen {
	
	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		int frameSize = 600;
		
		JLabel lblAddItem = new JLabel("Add item");
		lblAddItem.setVisible(true);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setVisible(true);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setVisible(true);
		
		panel.add(lblAddItem); 
		panel.add(lblPrice);
		panel.add(lblTotal);
		
		//This will be used to hold the contents of the farm
		ArrayList<ItemContainer> containerFarm = new ArrayList<ItemContainer>();
		ArrayList<Item> itemFarm = new ArrayList<Item>();
		
		String[] buttons = {"Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock"};
		
		final JComboBox<String> cb = new JComboBox<String>(buttons);
		
		cb.setVisible(true);
		panel.add(cb);
		
		JButton btnRemove = new JButton("REMOVE");
		panel.add(btnRemove);
		JButton btnAdd = new JButton("ADD");
		panel.add(btnAdd);
		JButton btnUpdate = new JButton("UPDATE");
		panel.add(btnUpdate);
		
		String[] nameData = new String[100];
		
		final JList jl = new JList(nameData);
		panel.add(jl);
		jl.setPreferredSize(new Dimension(frameSize-30, frameSize-150));
		jl.setVisible(true);
		
		TextField tf = new TextField();
		
		nameData[1] = "Test";
		nameData[2] = "test2";
		
		
		btnAdd.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  nameData[1] = "Test2";
			  nameData[2] = "test4";
		  }
		});
		
		JFrame frame = new JFrame("Farm Project");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(frameSize, frameSize);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width/2)-frameSize/2, (screenSize.height/2) - frameSize/2);
		
		frame.add(panel);
	}

}
