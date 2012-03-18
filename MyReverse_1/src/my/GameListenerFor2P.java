package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class GameListenerFor2P extends MouseAdapter {

	GameField field;
	double checkbound;

	public GameListenerFor2P(GameField f) {
		this.field = f;
		checkbound = Math.pow(field.cellCount, 2) / 2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		Cell cell = field.findCell(x, y);

		if (cell != null)
			System.out.println("Cell: " + cell.i_index + ", " + cell.j_index);

		tryMakeMove(cell);
	}

	public void tryMakeMove(Cell cell) {
		if (field.canMove(cell)) {
			if (field.moveCounter != 0)
				field.autosave();
			field.makeCellBusy(cell.i_index, cell.j_index);
			field.moveCounter++;
			field.resetRedo();
		}		
		if (field.possibleCells.isEmpty()|field.blackStones.isEmpty()|field.whiteStones.isEmpty()) generateGameOver();
		else if (!field.moveIsPossible()){
			if (!field.moveIsPossibleForNext())generateGameOver();
			else generatePass();	
		}
		field.repaint();
	}

	public void generateGameOver() {
		field.gameOver = true;
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

		JOptionPane.showMessageDialog(field, message, "Game over",
				JOptionPane.INFORMATION_MESSAGE, null);
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

	public void generatePass() {
		String message = new String(
				"Unfortunaetly, You have no possible moves, so You have to pass Your move :(");
		JOptionPane.showMessageDialog(field, message, "I'm sorry",
				JOptionPane.INFORMATION_MESSAGE, null);
		if (field.moveCounter != 0)
			field.autosave();
		field.moveCounter++;
		field.resetRedo();
	}

}
