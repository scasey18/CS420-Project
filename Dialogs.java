package farming;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Dialogs {

	// These are the dialogs created when using the add button to create new objects
	public static String cropDialog() {
		JTextField name = new JTextField();
		Object[] message = { "Type of Crop:", name, };

		int option = JOptionPane.showConfirmDialog(null, message, "Create Crop", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION && !name.getText().trim().equals("")) {
			return name.getText();
		} else if (option == JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, "Crop was not added because all fields were not filled");
		}

		return null;
	}

	public static String[] equipmentDialog() {
		JTextField name = new JTextField();
		JTextField cName = new JTextField();
		JTextField mNum = new JTextField();
		Object[] message = { "Type of Equipment:", name, "Company Name:", cName, "Model Number:", mNum };

		int option = JOptionPane.showConfirmDialog(null, message, "Create Equipment", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION && !name.getText().trim().equals("") && !cName.getText().trim().equals("")
				&& !mNum.getText().trim().equals("")) {
			String[] answer = { name.getText(), cName.getText(), mNum.getText() };
			return answer;
		}

		else if (option == JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, "Equipment was not added because all fields were not filled");
		} else {
			// do nothing
		}
		return null;
	}

	public static String[] liveStockDialog() {
		JTextField animalType = new JTextField();
		String[] possible = { "Male", "Female" };
		JComboBox<String> gender = new JComboBox<String>(possible);
		Object[] message = { "Type of Animal:", animalType, "Gender:", gender, };

		int option = JOptionPane.showConfirmDialog(null, message, "Create Livestock", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION && !animalType.getText().trim().equals("")) {
			String[] answer = { animalType.getText(), gender.getSelectedItem().toString() };
			return answer;
		} else if (option == JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, "Livestock was not added because all fields were not filled");
		}
		return null;
	}

	public static int suppliesDialog() {
		JTextField count = new JFormattedTextField(commonItem.createFormat());
		Object[] message = { "Item Count:", count };

		int option = JOptionPane.showConfirmDialog(null, message, "Create Supplies", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION && !count.getText().trim().equals("")) {
			return Integer.valueOf(count.getText());
		} else if (option == JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, "Supplies was not added because all fields were not filled");
		}
		return -1;
	}
}
