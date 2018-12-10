package farming;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ListenerCreator {

	public static MouseListener createMouse() {

		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					// This will show and update Info for the items in the tree
					// Right click to show info
					if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3) {
						DefaultMutableTreeNode selPath = (DefaultMutableTreeNode) Fscreen.createFscreen().tree
								.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
						Object backup = Fscreen.createBackup(selPath.getUserObject());
						switch (selPath.getUserObject().getClass().toString()) {
						case "class farming.ItemContainer":
							((ItemContainer) selPath.getUserObject()).showInfo();
							break;
						case "class farming.Equipment":
							((Equipment) selPath.getUserObject()).showInfo();
							break;
						case "class farming.Drone":
							((Drone) selPath.getUserObject()).showInfo();
							break;
						case "class farming.Crops":
							((Crops) selPath.getUserObject()).showInfo();
							break;
						case "class farming.LiveStock":
							((LiveStock) selPath.getUserObject()).showInfo();
							break;
						case "class farming.Supplies":
							((Supplies) selPath.getUserObject()).showInfo();
							break;
						}
						if (selPath.getUserObject() != backup && backup != null) {

							Fscreen.createFscreen().broker.add(new undoCommand(new Pair("Update", selPath, backup)));
							Fscreen.createFscreen().isSaved = false;
						}
						Fscreen.createFscreen().tree.repaint();
					}
					// The will display the market value and price for any item currently selected
					// in the tree
					// By updating the JLabel text
					else if (e.getClickCount() == 1 && Fscreen.createFscreen().tree
							.getPathForLocation(e.getX(), e.getY()).getLastPathComponent() != null) {

						DefaultMutableTreeNode selPath = (DefaultMutableTreeNode) Fscreen.createFscreen().tree
								.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
						Fscreen.currPrice.setText("Current Price: " + Fscreen.prices.visit(selPath));
						Fscreen.workLoad.setText("Current Market Value: " + Fscreen.marketPrices.visit(selPath));
					}
				} catch (NullPointerException h) {
					// Do nothing as this is the UI catching a misclick or something similar
				}

			}
		};
		return ml;
	}

	public static WindowListener createWindow() {
		return new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
				Fscreen.createFscreen().menuBar.close.getAction().actionPerformed(new ActionEvent(e, 0, null));
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

		};
	}

	public static ActionListener createButtonListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Fscreen.createFscreen().model = (DefaultTreeModel) Fscreen.createFscreen().tree.getModel();
				DefaultMutableTreeNode child = null;

				// e == event
				// e.getsource returns what obj has been clicked
				try {
					if (e.getSource() == Fscreen.createFscreen().addButton &&
					// This code is to see if the node can accept children (false for all except
					// Containers)
					((DefaultMutableTreeNode) Fscreen.createFscreen().tree.getSelectionPath().getLastPathComponent())
							.getAllowsChildren()) {
						// Add button has been clicked
						// Below are all args for creating new item
						JTextField name = new JTextField();
						JTextField locX = new JFormattedTextField(commonItem.createFormat());
						JTextField locY = new JFormattedTextField(commonItem.createFormat());
						JTextField length = new JFormattedTextField(commonItem.createFormat());
						JTextField width = new JFormattedTextField(commonItem.createFormat());
						JTextField price = new JFormattedTextField(commonItem.createFormat());
						JTextField Mprice = new JFormattedTextField(commonItem.createFormat());

						Object[] message = { "All of these fields are required for item creation", "Name:", name,
								"X location:", locX, "Y location:", locY, "Length:", length, "Width:", width, "Price:",
								price, "Market Price:", Mprice };
						Object[] message2 = { "All of these fields are required for item creation", "Name:", name,
								"X location:", locX, "Y location:", locY, "Length:", length, "Width:", width, "Price:",
								price, };
						// This pulls up two different fields because Containers do not have a market
						// value
						Object[] message3 = {};
						if (Fscreen.createFscreen().options.getSelectedItem() == "Container") {
							message3 = message2;
						} else {
							message3 = message;
						}

						int option = JOptionPane.showConfirmDialog(null, message3,
								"Create " + Fscreen.createFscreen().options.getSelectedItem(),
								JOptionPane.OK_CANCEL_OPTION);

						// This could definitely be condensed if i was a better coder
						if ((option == JOptionPane.OK_OPTION && !name.getText().trim().equals("")
								&& !locX.getText().trim().equals("") && !locY.getText().trim().equals("")
								&& !width.getText().trim().equals("") && !length.getText().trim().equals("")
								&& !price.getText().trim().equals("") && !Mprice.getText().trim().equals(""))
								|| (option == JOptionPane.OK_OPTION
										&& Fscreen.createFscreen().options.getSelectedItem() == "Container"
										&& !name.getText().trim().equals("") && !locX.getText().trim().equals("")
										&& !locY.getText().trim().equals("") && !width.getText().trim().equals("")
										&& !length.getText().trim().equals("") && !price.getText().trim().equals(""))) {

							switch (String.valueOf(Fscreen.createFscreen().options.getSelectedItem())) {

							case "Container":
								child = new DefaultMutableTreeNode(new ItemContainer(name.getText(), price.getText(),
										locX.getText(), locY.getText(), length.getText(), width.getText()), true);
								break;

							case "Crops":
								String x = Dialogs.cropDialog();
								if (x != null) {
									child = new DefaultMutableTreeNode(
											new Crops(name.getText(), price.getText(), locX.getText(), locY.getText(),
													length.getText(), width.getText(), x, Mprice.getText()),
											false);
								}
								break;

							case "Drone":
								child = new DefaultMutableTreeNode(
										new Drone(name.getText(), price.getText(), locX.getText(), locY.getText(),
												length.getText(), width.getText(), Mprice.getText()),
										false);
								break;

							case "Equipment":
								String[] a = Dialogs.equipmentDialog();
								if (a != null) {
									child = new DefaultMutableTreeNode(new Equipment(name.getText(), price.getText(),
											locX.getText(), locY.getText(), length.getText(), width.getText(),
											Mprice.getText(), a[0], a[1], a[2]), false);
								}
								break;

							case "Supplies":
								int a1 = Dialogs.suppliesDialog();
								if (a1 >= 0) {
									child = new DefaultMutableTreeNode(new Supplies(name.getText(), price.getText(),
											locX.getText(), locY.getText(), length.getText(), width.getText(), a1,
											Mprice.getText()), false);
								}
								break;

							case "Livestock":
								String[] answer = Dialogs.liveStockDialog();
								if (answer != null) {
									child = new DefaultMutableTreeNode(new LiveStock(name.getText(), price.getText(),
											locX.getText(), locY.getText(), length.getText(), width.getText(),
											answer[0], answer[1], Mprice.getText()), false);
								}
								break;
							}

							if (child != null) {
								// Only items have a count variable
								if (child.getUserObject().getClass() != ItemContainer.class) {
									Fscreen.createFscreen().cObserve.addObserver(child);
								}
								Fscreen.createFscreen().model = Fscreen.insertNode(child,
										Fscreen.createFscreen().model);
								Fscreen.createFscreen().broker.add(new undoCommand(
										new Pair("Add", child, (DefaultMutableTreeNode) child.getParent())));
								Fscreen.createFscreen().tree.scrollPathToVisible(new TreePath(child.getPath()));
								
								
								Fscreen.createFscreen().isSaved = false;

								child = null;
							}

						} else if (option == JOptionPane.OK_OPTION) {
							JOptionPane.showMessageDialog(null,
									"Object was not added because all fields were not filled");
						}
					} else if (e.getSource() == Fscreen.createFscreen().removeButton) {
						DefaultMutableTreeNode sel = (DefaultMutableTreeNode) Fscreen.createFscreen().tree
								.getSelectionPath().getLastPathComponent();
						Fscreen.createFscreen().cObserve.removeObserver(sel);
						Fscreen.createFscreen().broker.add(
								new undoCommand(new Pair("Remove", sel, (DefaultMutableTreeNode) sel.getParent())));
						Fscreen.createFscreen().model.removeNodeFromParent(sel);
						Fscreen.createFscreen().text.append("Removed " + sel.getUserObject() + "\n");
						Fscreen.createFscreen().itemCount
								.setText("Number of Items: " + Fscreen.createFscreen().cObserve.count());

						Fscreen.createFscreen().isSaved = false;

					} else if (e.getSource() == Fscreen.createFscreen().visualButton) {
						@SuppressWarnings("unused")
						Visualize a = new Visualize(Fscreen.createFscreen().model);
						JOptionPane.showMessageDialog(null, "Vizualized Farm has been generated");
					} else if (e.getSource() == Fscreen.createFscreen().UndoButton) {
						Fscreen.createFscreen().broker.executeUndo();
						
					} else if (e.getSource() == Fscreen.createFscreen().RedoButton) {
						Fscreen.createFscreen().broker.executeRedo();
						
					} else if (e.getSource() == Fscreen.createFscreen().updateButton) {
						Fscreen.createFscreen().cObserve.setCount(Fscreen.createFscreen().cObserve.count());
					}
					else if(e.getSource() == Fscreen.createFscreen().devButton) {
						Fscreen.createFscreen().advertise(null);
					}
				} catch (Exception NullPointerException) {
					// Has to select a item before it can remove
					if (e.getSource() == Fscreen.createFscreen().addButton) {
						JOptionPane.showMessageDialog(null, "Please select a container to add items");
					} else if (e.getSource() == Fscreen.createFscreen().removeButton) {
						JOptionPane.showMessageDialog(null, "Please select an item to remove");
					}
				}
			}

		};
	}

}
