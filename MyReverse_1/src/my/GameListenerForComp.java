package my;


import java.awt.event.MouseEvent;


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
			if (!field.possibleCells.isEmpty())
				setPlayerTurn();
			else
				super.generateGameOver();
		}
		if (compsMove) {
			c = computer.makeMove(field);

			if (c != null) {
				System.out.println("Comp's move: " + c.i_index + ", "
						+ c.j_index);
				super.tryMakeMove(computer.makeMove(field));
			}
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
		if (compsMove) {
			super.tryMakeMove(computer.makeMove(field));
			setPlayerTurn();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
