package farming;

import java.io.Serializable;

import javax.swing.JTextArea;
import javax.swing.tree.TreeModel;

/**
 * This class is to save the file of the farm only
 * 
 * @author scase
 *
 */
public class Farm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private commandBroker broker;
	private CountObserver observer;
	private TreeModel tree;
	private JTextArea text;

	public Farm(Fscreen instance) {
		broker = instance.broker;
		observer = instance.cObserve;
		tree = instance.tree.getModel();
		text = instance.text;
	}

	public JTextArea getText() {
		return text;
	}

	public void setText(JTextArea text) {
		this.text = text;
	}

	public commandBroker getBroker() {
		return broker;
	}

	public void setBroker(commandBroker broker) {
		this.broker = broker;
	}

	public CountObserver getObserver() {
		return observer;
	}

	public void setObserver(CountObserver observer) {
		this.observer = observer;
	}

	public TreeModel getTree() {
		return tree;
	}

	public void setTree(TreeModel tree) {
		this.tree = tree;
	}

}
