package farming;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

public class DarkMode {
	
	static Boolean darkTheme = false;

	public static void applyDark() {
		UIManager.put("Panel.background", new Color(42, 42, 42));

		// JLabel edits
		UIManager.put("Label.foreground", Color.white);

		// Button edits
		UIManager.put("Button.background", new Color(101, 156, 181));
		UIManager.put("Button.foreground", Color.white);

		// TextArea edits
		UIManager.put("TextArea.background", new Color(74, 74, 74));
		UIManager.put("TextArea.foreground", Color.white);
		// Also for JTree
		UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());

		// The JTree edits
		UIManager.put("Tree.background", new Color(74, 74, 74));
		UIManager.put("Tree.textBackground", new Color(74, 74, 74));
		UIManager.put("Tree.textForeground", Color.white);

		// Option Panes
		UIManager.put("OptionPane.background", new Color(42, 42, 42));
		UIManager.put("OptionPane.messageForeground", Color.white);

		// SplitPane
		UIManager.put("SplitPane.border", new Color(42, 42, 42));
		UIManager.put("SplitPaneDivider.draggingColor", new Color(101, 156, 181));
		UIManager.put("SplitPane.dividerSize", 5);

		// JMenuBar
		UIManager.put("MenuBar.background", new Color(74, 74, 74));
		UIManager.put("MenuBar.border", new Color(74, 74, 74));
		UIManager.put("MenuBar.foreground", Color.white);

		// JMenu
		UIManager.put("menuText", new Color(101, 156, 181));
	}
	
	public static void applyLight() {
		
		UIManager.put("Panel.background", Color.white);

		// JLabel edits
		UIManager.put("Label.foreground", Color.black);

		// Button edits
		UIManager.put("Button.background", Color.white);
		UIManager.put("Button.foreground", Color.black);

		// TextArea edits
		UIManager.put("TextArea.background", Color.gray);
		UIManager.put("TextArea.foreground", Color.black);
		// Also for JTree
		UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());

		// The JTree edits
		UIManager.put("Tree.background", Color.white);
		UIManager.put("Tree.textBackground", Color.white);
		UIManager.put("Tree.textForeground", Color.black);

		// Option Panes
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("OptionPane.messageForeground", Color.gray);

		// SplitPane
		UIManager.put("SplitPane.border", Color.white);
		UIManager.put("SplitPaneDivider.draggingColor", Color.black);
		UIManager.put("SplitPane.dividerSize", 5);

		// JMenuBar
		UIManager.put("MenuBar.background", Color.white);
		UIManager.put("MenuBar.border", Color.white);
		UIManager.put("MenuBar.foreground", Color.black);

		// JMenu
		UIManager.put("menuText", Color.BLACK);
	}
	
	public static boolean getDarkTheme() {
		return darkTheme;
	}
	
	public static void setDarkTheme(Boolean input) {
		darkTheme = input;
	}

}
