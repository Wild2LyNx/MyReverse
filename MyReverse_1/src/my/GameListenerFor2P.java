package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JOptionPane;


public class GameListenerFor2P extends MouseAdapter {

	GameField field;	

	public GameListenerFor2P(GameField f) {
		this.field = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
		double x = e.getX();
		double y = e.getY();
		double checkbound = Math.pow(field.cellCount, 2)/2;
		Cell cell = field.findCell(x, y);
		
		System.out.println("Cell: " + cell.i_index + ", " + cell.j_index);
		
		tryMakeMove(checkbound, cell);
	}

	public void tryMakeMove(double checkbound, Cell cell) {
		if (field.canMove(cell)) {
			if (field.moveCounter != 0) field.autosave();
			field.makeCellBusy(cell.i_index, cell.j_index);			
			field.moveCounter++;
			field.resetRedo();
		}
		field.repaint();
//		setPlayer(field.moveCounter);
		if (field.freeCells.size() < checkbound) {
			if (!field.moveIsPossible()) {
				generateGameOver();
			}
		}
	}

	private void generateGameOver() {
		String message = new String();
		int blackScores = field.blackStones.size();
		int whiteScores = field.whiteStones.size();
		if (blackScores > whiteScores)
			message = "Black won by the score of " + blackScores + " to "
					+ whiteScores;
		if (blackScores < whiteScores)
			message = "White won by the score of " + whiteScores + " to "
					+ blackScores;
		if (blackScores == whiteScores)
			message = "The game is a draw";

		JOptionPane.showMessageDialog(field, message,
				"Game over", JOptionPane.INFORMATION_MESSAGE, null);
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
	public void mouseMoved (MouseEvent e1) {
		
	}

}
