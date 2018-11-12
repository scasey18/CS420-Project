package farming;

public class redoCommand {
	
	private Pair redo;

	public redoCommand(Pair redo) {
		this.redo = redo;
	}
	
	public void execute() {
		// TODO Auto-generated method stub
		switch(redo.getAction()) {
		case "Remove":
			Fscreen.createFscreen().model.removeNodeFromParent(redo.getNode1());
			break;
		case "Add":
			Fscreen.createFscreen().model.insertNodeInto(redo.getNode1(), redo.getNode2(), 0);
			Fscreen.createFscreen().cObserve.addObserver(redo.getNode1());
			break;
		case "Update":
			redo.getNode1().setUserObject(redo.getObj());
			break;
		}
		//Redraws the tree in order to see the readded node
		Fscreen.createFscreen().tree.repaint();
		Fscreen.createFscreen().model.reload();
		Fscreen.createFscreen().text.append(this.toString() + "\n");
	}
	
	public String toString() {
		return "Redo (" +redo.getAction() + " " + redo.getNode1() + ")";
	}
	
	public Pair getPair() {
		return redo;
	}
}
