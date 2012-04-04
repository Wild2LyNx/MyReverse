package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Human extends MouseAdapter implements Player{
	private static final int WAITING = 0;
	private static final int MOVING = 1;
	int state = WAITING; 
	GameField field;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (state == MOVING){
		double x = e.getX();
		double y = e.getY();		
		Cell cell = findCellByXY(x, y, field.allCells);

		if (cell != null)
			System.out.println("Cell: " + cell.i_index + ", " + cell.j_index);
		field.tryMakeMove(cell);
		
		if (field.movedSuccess) {
			state = WAITING;			
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
		state = MOVING;
		this.field = gameField;
	}
	@Override
	public int getState() {
		return state;
	}
}
