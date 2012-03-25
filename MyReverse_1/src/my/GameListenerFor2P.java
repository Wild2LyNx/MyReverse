package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameListenerFor2P extends MouseAdapter {

	GameField field;

	public GameListenerFor2P(GameField f) {
		this.field = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		Cell cell = field.findCellByXY(x, y);

		if (cell != null)
			System.out.println("Cell: " + cell.i_index + ", " + cell.j_index);

		field.tryMakeMove(cell);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e1) {

	}
}
