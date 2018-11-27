package farming;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class MenuBarCreator {

	static JFileChooser fileChooser = new JFileChooser();

	JMenuItem saveItem;
	JMenuItem saveAsItem;
	JMenuItem close;
	JMenuItem openItem;

	public MenuBarCreator() {

	}

	public JMenuBar createMenu() {
		FileFilter a = new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				if (arg0.getName().endsWith(".farm") || arg0.isDirectory()) {
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "farm";
			}

		};
		fileChooser.addChoosableFileFilter(a);
		fileChooser.setFileFilter(a);

		saveAsItem = new JMenuItem(new AbstractAction("Save As") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int value = fileChooser.showSaveDialog(Fscreen.createFscreen());

				if (value == JFileChooser.APPROVE_OPTION) {
					File current = fileChooser.getSelectedFile();
					FileOutputStream file;
					try {
						// Reading the object from a file
						if (current.getName().endsWith(".farm")) {
							file = new FileOutputStream(current);
						} else {
							current = new File(current.toString() + ".farm");
							file = new FileOutputStream(current);

						}
						ObjectOutputStream in = new ObjectOutputStream(file);
						in.writeObject(new Farm(Fscreen.createFscreen()));
						Fscreen.createFscreen().isSaved = true;

						in.close();
						file.close();
						Fscreen.createFscreen().currentFile = current;
						Fscreen.createFscreen()
								.setTitle(current.getName().substring(0, current.getName().length() - 5));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		close = new JMenuItem(new AbstractAction("Close") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (Fscreen.createFscreen().isSaved) {
					System.exit(0);
				} else {
					int n = JOptionPane.showConfirmDialog(null,
							"It looks like you have not saved the current farm, would you like to save?", "Last Chance",
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						saveAsItem.getAction().actionPerformed(arg0);
						System.exit(0);
					} else {
						System.exit(0);
					}
				}
			}
		});

		saveItem = new JMenuItem(new AbstractAction("Save") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Fscreen.createFscreen().currentFile != null) {
					try {
						FileOutputStream file = new FileOutputStream(Fscreen.createFscreen().currentFile);
						ObjectOutputStream in = new ObjectOutputStream(file);
						in.writeObject(new Farm(Fscreen.createFscreen()));

						in.close();
						file.close();
						Fscreen.createFscreen().isSaved = true;
					} catch (Exception e1) {

					}
				} else {
					saveAsItem.getAction().actionPerformed(e);
				}
			}

		});

		openItem = new JMenuItem(new AbstractAction("Open") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int value = fileChooser.showOpenDialog(Fscreen.createFscreen());

				if (value == JFileChooser.APPROVE_OPTION) {
					File current = fileChooser.getSelectedFile();
					try {
						// Reading the object from a file
						FileInputStream file = new FileInputStream(current);
						ObjectInputStream in = new ObjectInputStream(file);
						Farm a = (Farm) in.readObject();
						Fscreen.createFscreen().setFarm(a);
						in.close();
						file.close();
						Fscreen.createFscreen().currentFile = current;
						Fscreen.createFscreen()
								.setTitle(current.getName().substring(0, current.getName().length() - 5));
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (StreamCorruptedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Selected file is not valid for this program", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});

		JMenuBar menu = new JMenuBar();
		
		JMenu fileOption = new JMenu("File");
		fileOption.add(openItem);
		fileOption.add(saveItem);
		fileOption.add(saveAsItem);
		fileOption.addSeparator();
		fileOption.add(close);
		
		menu.add(fileOption);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		return menu;
	}

}
