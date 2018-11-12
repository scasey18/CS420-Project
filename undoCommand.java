package farming;

public class undoCommand implements commandPattern{
	
	private Pair undo;

	public undoCommand(Pair undo) {
		this.undo = undo;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		switch(undo.getAction()) {
		case "Add":
			Fscreen.createFscreen().model.removeNodeFromParent(undo.getNode1());
			break;
		case "Remove":
			Fscreen.createFscreen().model.insertNodeInto(undo.getNode1(), undo.getNode2(), 0);
			Fscreen.createFscreen().cObserve.addObserver(undo.getNode1());
			break;
		case "Update":
			undo.getNode1().setUserObject(undo.getObj());
			break;
		}
		//Redraws the tree in order to see the readded node
		Fscreen.createFscreen().tree.repaint();
		Fscreen.createFscreen().model.reload();
		Fscreen.createFscreen().text.append(this.toString() + "\n");
	}
	
	public String toString() {
		return "Undo (" +undo.getAction() + " " + undo.getNode1() + ")";
	}
	
	public Pair getPair() {
		return undo;
	}

}
