package farming;

import javax.swing.tree.DefaultMutableTreeNode;

public interface Visitor {

	int visit(Crops crop);

	int visit(DefaultMutableTreeNode node);

	int visit(Drone drone);

	int visit(Equipment equip);

	int visit(ItemContainer container);

	int visit(LiveStock stock);

	int visit(Supplies supply);

}
