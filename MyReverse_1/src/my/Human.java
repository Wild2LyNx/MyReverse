package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Human extends MouseAdapter implements Player {
	int state = States.WAITING;
	GameField field;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (state == States.MOVING) {
			double x = e.getX();
			double y = e.getY();
			Cell cell = findCellByXY(x, y, field.allCells);

			if (cell != null) {
				System.out.println("Cell: " + cell.i_index + ", "
						+ cell.j_index);
				field.tryMakeMove(cell);
			}

			if (field.movedSuccess) {
				state = States.WAITING;
				this.field = null;
			}
		}
	}

	private Cell findCellByXY(double x, double y, Cell allCells[][]) {
		for (Cell[] cv : allCells) {
			for (Cell cg : cv) {
				if (cg.contains(x, y))
					return cg;
			}
		}
		return null;
	}

	@Override
	public void makeMove(GameField gameField) {
		state = States.MOVING;
		this.field = gameField;
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public void stateChanged(GameField gameField, Cell cell) {
		// TODO Auto-generated method stub

	}

	@Override
	public void passAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processGameOver() {
		// TODO Auto-generated method stub
		
	}
}
