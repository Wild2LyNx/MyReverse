package my;

import java.util.Map;
import java.util.TreeMap;


public class ComputerPlayer implements Player {

	Map	<Integer, Cell> probablyCells = new TreeMap<Integer, Cell>();
	int key = 0;
	
	public void makeMove(GameField field) {		
		buildProbableCellsList(field);
		Cell moveCell = probablyCells.get(key);	
		System.out.println("Comp's move: " + moveCell.i_index + ", "
				+ moveCell.j_index);
		field.tryMakeMove(moveCell);
//		if (field.movedSuccess)field.setPlayer();
	}		

	private void buildProbableCellsList(GameField f) {
		probablyCells.clear();
		key = 0;
		for (Cell c : f.possibleCells) {
			if ((c != null)&&(f.canMove(c))) {
				int scores = f.getScoresForThisMove(c);
				if (scores != 0) {
					if ((c.i_index == 0) | (c.j_index == 0) | (c.i_index == f.cellCount - 1)
							| (c.j_index == f.cellCount - 1))
						scores += 100;
					probablyCells.put(scores, c);
					if (key < scores) key = scores;
					scores = 0;
				}
			}
		}
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

}
