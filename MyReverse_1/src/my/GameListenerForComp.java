package my;

import java.awt.event.MouseEvent;
public class GameListenerForComp extends GameListenerFor2P{
	GameField field;
	ComputerPlayer computer = new ComputerPlayer();
	int compDescriptor;
	boolean compsMove;
	
	GameListenerForComp (GameField f, int d){
		super(f);
		this.field = f;
		this.compDescriptor = d;	
		setPlayerTurn();
		if (compsMove) computer.makeMove(field);
	}

	private void setPlayerTurn() {		
		if (field.moveCounter%2 == compDescriptor) compsMove = true;
		if (field.moveCounter%2 != compDescriptor) compsMove = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!compsMove) {
			super.mouseClicked(e);
			if (!field.possibleCells.isEmpty())setPlayerTurn();
			else super.generateGameOver();
		}
		if (compsMove) {
			
			Cell c = computer.makeMove(field);
			System.out.println("Comp's move: " + c.i_index + ", " + c.j_index);
			if (c != null) super.tryMakeMove(computer.makeMove(field));		
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
