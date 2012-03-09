package my;

import java.awt.event.MouseEvent;
public class GameListenerForComp extends GameListenerFor2P{
	GameField field;
	ComputerPlayer computer;
	int compDescriptor;
	boolean compsMove;
	double checkbound;
	
	GameListenerForComp (GameField f, int d){
		super(f);
		this.compDescriptor = d;	
		checkbound = Math.pow(field.cellCount, 2)/2;
		setPlayerTurn();
		if (compsMove) computer.makeMove(field);
	}

	private void setPlayerTurn() {
		if (field.moveCounter%2 == compDescriptor) compsMove = true;
		compsMove = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!compsMove) {
			super.mouseClicked(e);
			setPlayerTurn();
		}
		if (compsMove) super.tryMakeMove(checkbound, computer.makeMove(field));		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
