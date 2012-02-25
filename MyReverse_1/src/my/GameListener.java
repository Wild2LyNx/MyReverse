package my;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameListener implements MouseListener {
	
	GameField field;
	
	public GameListener (GameField f) {
		this.field = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
//		System.out.println("x: " + e.getX() + " y: " + e.getY());
		Cell cell = field.findCell(x, y);
		System.out.println("Cell: " + cell.i_index + ", " + cell.j_index);
		if (field.canMove(cell)) {
			field.makeCellBusy(cell.i_index, cell.j_index);
		}
		field.repaint();
		field.moveCounter++;
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

}
