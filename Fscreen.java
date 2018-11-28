package farming;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class Fscreen extends JFrame {

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
	static JLabel currPrice;
	static JLabel workLoad;

	File currentFile;
	boolean isSaved = true;
	MenuBarCreator menuBar = new MenuBarCreator();

	JMenuBar menubar;

	JComboBox<String> options;

	static PriceVisitor prices = new PriceVisitor();
	static MarketVisitor marketPrices = new MarketVisitor();

	CountObserver cObserve = new CountObserver();

	commandBroker broker = new commandBroker();

	private static final long serialVersionUID = 1L;
	static JScrollPane scrollPane;
	JTextArea text;
	private static Fscreen instance;
	static JScrollPane scroll;
	static JScrollPane textArea;
	/**
	 * JTree implentation
	 */
	JTree tree = new JTree();
	DefaultTreeModel model;

	UIManager ui = new UIManager();

	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ItemContainer("root"));

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

	public static synchronized Fscreen createFscreen() {
		if (instance == null) {
			instance = new Fscreen();
		}
		return instance;
	}

	// Generic create label func
	public static JLabel createLabel(String name, JPanel parentPanel) {
		JLabel label = new JLabel(name);
		label.setVisible(true);
		parentPanel.add(label);
		return label;
	}

	private Fscreen() {
		int width = 800;
		int height = 600;
		// set window size from args
		this.setSize(width, height);
		// This takes care of centering, setting to null centers to monitor
		this.setLocationRelativeTo(null);
		// This makes it so program fully closes when exit button is hit
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(ListenerCreator.createWindow());

		// Set title of window
		this.setTitle("Group G Farming App");
		
		//DarkMode.applyDark();
		
		// Want to add components to panel, then add panel to frame (window)
		JPanel mainPanel = new JPanel(new BorderLayout());

		menubar = menuBar.createMenu();

		this.setJMenuBar(menubar);

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
		ActionListener buttonListener = ListenerCreator.createButtonListener();
		// Attach listener to buttons
		addButton.addActionListener(buttonListener);
		removeButton.addActionListener(buttonListener);
		visualButton.addActionListener(buttonListener);

		tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setVisible(true);

		scroll = new JScrollPane(tree);

		scroll.setPreferredSize(new Dimension(600, 400));

		JSplitPane a = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		a.setLeftComponent(scroll);

		text = new JTextArea();
		text.setText("Created farm \n");
		text.setEditable(false);
		textArea = new JScrollPane(text);
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

		tree.addMouseListener(ListenerCreator.createMouse());

		// The panel for all the bottom information
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
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

	// This function will update the model when adding a node to the current
	// Container
	public static DefaultTreeModel insertNode(DefaultMutableTreeNode node, DefaultTreeModel model) {
		model.insertNodeInto(node,
				(DefaultMutableTreeNode) Fscreen.createFscreen().tree.getSelectionPath().getLastPathComponent(),
				((DefaultMutableTreeNode) Fscreen.createFscreen().tree.getSelectionPath().getLastPathComponent())
						.getChildCount());
		Fscreen.createFscreen().text.append("Added " + node.getUserObject() + "\n");
		Fscreen.createFscreen().itemCount.setText("Number of Items: " + Fscreen.createFscreen().cObserve.count());
		return model;
	}

	public static Object createBackup(Object a) {
		switch (a.getClass().toString()) {
		case "class farming.ItemContainer":
			return ((ItemContainer) a).clone();
		case "class farming.Equipment":
			return ((Equipment) a).clone();
		case "class farming.Drone":
			return ((Drone) a).clone();
		case "class farming.Crops":
			return ((Crops) a).clone();
		case "class farming.LiveStock":
			return ((LiveStock) a).clone();
		case "class farming.Supplies":
			return ((Supplies) a).clone();
		}
		return null;
	}

	public void setFarm(Farm a) {
		Fscreen.createFscreen().broker = a.getBroker();
		
		Fscreen.createFscreen().tree.setModel(a.getTree());
		Fscreen.createFscreen().text.setText(a.getText().getText());
		Enumeration<?> a1 = ((DefaultMutableTreeNode)tree.getModel().getRoot()).postorderEnumeration();
		DefaultMutableTreeNode node;
		while(a1.hasMoreElements()) {
			node = (DefaultMutableTreeNode) a1.nextElement();
			if (node.getUserObject().getClass() != ItemContainer.class) {
				this.cObserve.addObserver(node);
			}
		}
		Fscreen.createFscreen().itemCount.setText("Number of Items: " + Fscreen.createFscreen().cObserve.count());
		this.getContentPane().repaint();
	}
}