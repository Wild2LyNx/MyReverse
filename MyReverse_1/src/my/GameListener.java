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
//		field.findCell(e.getX(), e.getY());
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
