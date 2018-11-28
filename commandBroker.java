package farming;

import java.io.Serializable;
import java.util.Stack;

public class commandBroker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<undoCommand> undoCommands = new Stack<undoCommand>();
	private Stack<redoCommand> redoCommands = new Stack<redoCommand>();

	public void add(undoCommand a) {
		this.undoCommands.push(a);
	}

	public void executeUndo() {
		undoCommand a = this.undoCommands.peek();
		if (a.getPair().getAction() == "Update") {
			this.add(new redoCommand(
					new Pair("Update", a.getPair().getNode1(), a.getPair().getNode1().getUserObject())));
		} else {
			this.add(new redoCommand(a.getPair()));
		}
		a.execute();
		undoCommands.pop();
		Fscreen.createFscreen().itemCount.setText("Number of Items: " + Fscreen.createFscreen().cObserve.count());
		Fscreen.createFscreen().isSaved = false;
	}

	public void add(redoCommand a) {
		this.redoCommands.push(a);
	}

	public void executeRedo() {
		redoCommand a = this.redoCommands.peek();
		a.execute();
		undoCommands.pop();
		Fscreen.createFscreen().itemCount.setText("Number of Items: " + Fscreen.createFscreen().cObserve.count());
		Fscreen.createFscreen().isSaved = false;
	}
}
