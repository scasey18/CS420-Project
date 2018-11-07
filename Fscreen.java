package farming;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class Fscreen extends JFrame {

	private static final long serialVersionUID = 1L;
	// Want to have access to these on various methods
	/**
	 * All buttons on the screen
	 */
	JButton addButton;
	JButton removeButton;
	JButton visualButton;
	JButton UndoButton;
	JButton RedoButton;
	JButton updateButton;
	/**
	 * All the labels on the screen
	 */
	JLabel itemCount;
	JLabel currPrice;
	JLabel workLoad;

	JComboBox<String> options;
	
	PriceVisitor prices = new PriceVisitor();
	MarketVisitor marketPrices = new MarketVisitor();
	CountObserver cObserve = new CountObserver();

	/**
	 * JTree implentation
	 */
	JTree tree;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ItemContainer("root"));

	static JScrollPane scrollPane;
	static JTextArea text;

	// Generic create label func
	public static JLabel createLabel(String name, JPanel parentPanel) {
		JLabel label = new JLabel(name);
		label.setVisible(true);
		parentPanel.add(label);
		return label;
	}

	// Generic create button func
	public static JButton createButton(String name, JPanel parentPanel) {
		JButton button = new JButton(name);
		button.setVisible(true);
		parentPanel.add(button);
		return button;
	}

	// Generic create combo box func
	public static JComboBox<String> createComboBox(String[] listOfOptions, JPanel parentPanel) {
		final JComboBox<String> comboBox = new JComboBox<String>(listOfOptions);
		comboBox.setVisible(true);
		parentPanel.add(comboBox);
		return comboBox;
	}

	// This function will update the model when adding a node to the current
	// Container
	private DefaultTreeModel insertNode(DefaultMutableTreeNode node, DefaultTreeModel model) {
		model.insertNodeInto(node, (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent(),
				((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getChildCount());
		text.append("Added " + node.getUserObject() + "\n");
		itemCount.setText("Number of Items: " + cObserve.count(rootNode));
		return model;
	}
	
	private static Fscreen instance;

	public static synchronized Fscreen createFscreen() {
		if (instance == null) {
			instance = new Fscreen();
		}
		return instance;
	}

	private Fscreen() {
		int width = 800;
		int height = 600;
		// set window size from args
		this.setSize(width, height);
		// This takes care of centering, setting to null centers to monitor
		this.setLocationRelativeTo(null);
		// This makes it so program fully closes when exit button is hit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set title of window
		this.setTitle("Group G Farming App");

		// Want to add components to panel, then add panel to frame (window)
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
		// List of the buttons we want to make
		String[] buttons = { "Container", "Crops", "Drone", "Equipment", "Supplies", "Livestock" };

		// Combo box for options for user to add
		options = createComboBox(buttons, buttonPanel);

		// Buttons for user to interact with
		addButton = createButton("Add", buttonPanel);
		removeButton = createButton("Remove", buttonPanel);
		visualButton = createButton("Visualize", buttonPanel);

		// Button listener class
		buttonListener buttonListener = new buttonListener();
		// Attach listener to buttons
		addButton.addActionListener(buttonListener);
		removeButton.addActionListener(buttonListener);
		visualButton.addActionListener(buttonListener);

		tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setVisible(true);

		JScrollPane scroll = new JScrollPane(tree);
		
		scroll.setPreferredSize(new Dimension(600,400));

		JSplitPane a = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		a.setLeftComponent(scroll);

		text = new JTextArea();
		text.setText("Created farm \n");
		text.setEditable(false);
		JScrollPane textArea = new JScrollPane(text);
		JPanel textPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel2 = new JPanel();
		UndoButton = createButton("Undo", buttonPanel2);
		RedoButton = createButton("Redo", buttonPanel2);
		updateButton = createButton("Update", buttonPanel2);
		UndoButton.addActionListener(buttonListener);
		RedoButton.addActionListener(buttonListener);
		updateButton.addActionListener(buttonListener);
		textPanel.add(textArea);
		textPanel.add(buttonPanel2, BorderLayout.PAGE_END);
		a.setRightComponent(textPanel);

		mainPanel.add(a, BorderLayout.CENTER);
		a.setDividerLocation(scroll.getPreferredSize().width * 4 / 5);

		MouseListener ml = new MouseAdapter() {
			@SuppressWarnings("static-access")
			public void mousePressed(MouseEvent e) {
				try {
					// This will show and update Info for the items in the tree
					//Right click to show info
					if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
						DefaultMutableTreeNode selPath = (DefaultMutableTreeNode) tree
								.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
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
					}
					// The will display the market value and price for any item currently selected
					// in the tree
					// By updating the JLabel text
					else if (e.getClickCount() == 1
							&& tree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent() != null) {
						DefaultMutableTreeNode selPath = (DefaultMutableTreeNode) tree
								.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
						currPrice.setText("Current Price: " + prices.visit(selPath));
						workLoad.setText("Current Market Value: " + marketPrices.visit(selPath));
					}
				} catch (NullPointerException h) {
					// Do nothing as this is the UI catching a misclick or something
				}

			}
		};
		tree.addMouseListener(ml);

		// The panel for all the bottom information
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		currPrice = createLabel("Current Price: ", infoPanel);
		workLoad = createLabel("Current Market Value: ", infoPanel);
		itemCount = createLabel("Number of Items: 0", infoPanel);
		mainPanel.add(infoPanel, BorderLayout.PAGE_END);
		// The UI looks strange being all flush to the side so I added a Border to make
		// it look better
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(mainPanel);
		// make window visible, not true by default
		this.setVisible(true);
	}


	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			DefaultMutableTreeNode child = null;

			// e == event
			// e.getsource returns what obj has been clicked
			try {
				if (e.getSource() == addButton &&
				// This code is to see if the node can accept children (false for all except
				// Containers)
						((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getAllowsChildren()) {
					// Add button has been clicked
					// Below are all args for creating new item
					JTextField name = new JTextField();
					JTextField locX = new JFormattedTextField(NumberFormat.getNumberInstance());
					JTextField locY = new JFormattedTextField(NumberFormat.getNumberInstance());
					JTextField length = new JFormattedTextField(NumberFormat.getNumberInstance());
					JTextField width = new JFormattedTextField(NumberFormat.getNumberInstance());
					JTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
					JTextField Mprice = new JFormattedTextField(NumberFormat.getNumberInstance());

					Object[] message = { "All of these fields are required for item creation", "Name:", name,
							"X location:", locX, "Y location:", locY, "Length:", length, "Width:", width, "Price:",
							price, "Market Price:", Mprice };
					Object[] message2 = { "All of these fields are required for item creation", "Name:", name,
							"X location:", locX, "Y location:", locY, "Length:", length, "Width:", width, "Price:",
							price, };
					// This pulls up two different fields because Containers do not have a market
					// value
					Object[] message3 = {};
					if (options.getSelectedItem() == "Container") {
						message3 = message2;
					} else {
						message3 = message;
					}

					int option = JOptionPane.showConfirmDialog(null, message3, "Create " + options.getSelectedItem(),
							JOptionPane.OK_CANCEL_OPTION);

					// This could definitely be condensed if i was a better coder
					if ((option == JOptionPane.OK_OPTION && !name.getText().trim().equals("")
							&& !locX.getText().trim().equals("") && !locY.getText().trim().equals("")
							&& !width.getText().trim().equals("") && !length.getText().trim().equals("")
							&& !price.getText().trim().equals("") && !Mprice.getText().trim().equals(""))
							|| (option == JOptionPane.OK_OPTION && options.getSelectedItem() == "Container"
									&& !name.getText().trim().equals("") && !locX.getText().trim().equals("")
									&& !locY.getText().trim().equals("") && !width.getText().trim().equals("")
									&& !length.getText().trim().equals("") && !price.getText().trim().equals(""))) {
						switch (String.valueOf(options.getSelectedItem())) {

						case "Container":
							child = new DefaultMutableTreeNode(
									new ItemContainer(name.getText(), Integer.valueOf(price.getText()),
											Integer.valueOf(locX.getText()), Integer.valueOf(locY.getText()),
											Integer.valueOf(length.getText()), Integer.valueOf(width.getText())),
									true);
							break;

						case "Crops":
							String x = Dialogs.cropDialog(String.valueOf(options.getSelectedItem()));
							if (x == null) {
								// Do Nothing
							} else {
								child = new DefaultMutableTreeNode(new Crops(name.getText(),
										Integer.valueOf(price.getText()), Integer.valueOf(locX.getText()),
										Integer.valueOf(locY.getText()), Integer.valueOf(length.getText()),
										Integer.valueOf(width.getText()), x, Integer.valueOf(Mprice.getText())), false);
							}
							break;

						case "Drone":
							child = new DefaultMutableTreeNode(new Drone(name.getText(), Integer.valueOf(price.getText()),
									Integer.valueOf(locX.getText()), Integer.valueOf(locY.getText()),
									Integer.valueOf(length.getText()), Integer.valueOf(width.getText()),
									Integer.valueOf(Mprice.getText())), false);
							break;

						case "Equipment":
							String[] a = Dialogs.equipmentDialog(String.valueOf(options.getSelectedItem()));
							if (a == null) {
								// nothing was input correctly
							} else {
								child = new DefaultMutableTreeNode(
										new Equipment(name.getText(), Integer.valueOf(price.getText()),
												Integer.valueOf(locX.getText()), Integer.valueOf(locY.getText()),
												Integer.valueOf(length.getText()), Integer.valueOf(width.getText()),
												Integer.valueOf(Mprice.getText()), a[0], a[1], a[2]),
										false);
							}
							break;

						case "Supplies":
							int a1 = Dialogs.suppliesDialog(String.valueOf(options.getSelectedItem()));
							if (a1 >= 0) {
								child = new DefaultMutableTreeNode(new Supplies(name.getText(),
										Integer.valueOf(price.getText()), Integer.valueOf(locX.getText()),
										Integer.valueOf(locY.getText()), Integer.valueOf(length.getText()),
										Integer.valueOf(width.getText()), a1, Integer.valueOf(Mprice.getText())),
										false);
							} else {

							}
							break;

						case "Livestock":
							String[] answer = Dialogs.liveStockDialog(String.valueOf(options.getSelectedItem()));
							if (answer != null) {
								child = new DefaultMutableTreeNode(
										new LiveStock(name.getText(), Integer.valueOf(price.getText()),
												Integer.valueOf(locX.getText()), Integer.valueOf(locY.getText()),
												Integer.valueOf(length.getText()), Integer.valueOf(width.getText()),
												answer[0], answer[1], Integer.valueOf(Mprice.getText())),
										false);
							}
							break;
						}

						if (child != null) {
							//Only items have a count variable
							if (child.getUserObject().getClass().toString() != "class farming.ItemContainer") {
								cObserve.addObserver(child);
							}
							model = insertNode(child, model);
							tree.scrollPathToVisible(new TreePath(child.getPath()));
							child = null;
						}

					} else if (option == JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "Object was not added because all fields were not filled");
					}
				} else if (e.getSource() == removeButton) {
					DefaultMutableTreeNode sel = (DefaultMutableTreeNode) tree.getSelectionPath()
							.getLastPathComponent();
					cObserve.removeObserver(sel);
					model.removeNodeFromParent(sel);
					text.append("Removed " + sel.getUserObject() + "\n");
					itemCount.setText("Number of Items: " + cObserve.count(rootNode));
				} else if (e.getSource() == visualButton) {
					@SuppressWarnings("unused")
					Visualize a = new Visualize(model);
					JOptionPane.showMessageDialog(null, "Vizualized Farm has been generated");
				} else if (e.getSource() == UndoButton) {
					JOptionPane.showMessageDialog(null, "Undo works");
				} else if (e.getSource() == RedoButton) {
					JOptionPane.showMessageDialog(null, "Redo Works");
				} else if (e.getSource() == updateButton) {
					cObserve.setCount(cObserve.count(rootNode));
				}
			} catch (Exception NullPointerException) {
				// Has to select a item before it can remove
				if (e.getSource() == addButton) {
					JOptionPane.showMessageDialog(null, "Please select a container to add items");
				} else if (e.getSource() == removeButton) {
					JOptionPane.showMessageDialog(null, "Please select an item to remove");
				}
			}
		}
	}
}