package my;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class GameListenerForComp extends GameListenerFor2P {
	GameField field;
	ComputerPlayer computer = new ComputerPlayer();
	Cell c;
	int compDescriptor, descriptor;
	boolean compsMove;

	GameListenerForComp(GameField f, int d) {
		super(f);
		this.field = f;
		this.compDescriptor = d;
		setPlayerTurn();
		if (compsMove)
			computer.makeMove(field);
	}

	private void setPlayerTurn() {
		descriptor = field.moveCounter % 2;
		if (descriptor == compDescriptor)
			compsMove = true;
		if (descriptor != compDescriptor)
			compsMove = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!compsMove) {
			super.mouseClicked(e);
//			if (field.possibleCells.isEmpty()) super.generateGameOver();
//			else if (!field.moveIsPossible()) super.generatePass();		
			setPlayerTurn();
		}
		if (compsMove) {
			if (!field.moveIsPossible()) {		
				if (!field.moveIsPossibleForNext())generateGameOver();
				else generatePassForComp();
				setPlayerTurn();
				return;
			}
			c = computer.makeMove(field);

			if (c != null) {
				System.out.println("Comp's move: " + c.i_index + ", "
						+ c.j_index);
				super.tryMakeMove(c);
				c = null;
			}
//			if (field.possibleCells.isEmpty()) super.generateGameOver();
//			else if (!field.moveIsPossible()) super.generatePass();		
			setPlayerTurn();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setPlayerTurn();
		if (field.possibleCells.isEmpty()) super.generateGameOver();
		else if (!field.moveIsPossible()) {
			if (!compsMove) super.generatePass();
			else {
				generatePassForComp();
				setPlayerTurn();
			}
		}
		if (compsMove) {
			c = computer.makeMove(field);
			if (c != null)
				super.tryMakeMove(c);
//			if (field.possibleCells.isEmpty()) super.generateGameOver();
//			else if (!field.moveIsPossible()) super.generatePass();		
			setPlayerTurn();
			c = null;
		}
	}

	private void generatePassForComp() {
		String message = new String(
				"Unfortunaetly, computer have no possible moves, so it have to pass it's move.");
		JOptionPane.showMessageDialog(field, message, "I'm sorry",
				JOptionPane.INFORMATION_MESSAGE, null);
		if (field.moveCounter != 0)
			field.autosave();
		field.moveCounter++;
		field.resetRedo();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
