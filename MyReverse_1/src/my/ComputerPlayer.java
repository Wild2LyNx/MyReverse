package my;

import java.util.Map;
import java.util.TreeMap;

public class ComputerPlayer {

	Map	<Integer, Cell> probablyCells = new TreeMap<Integer, Cell>();
	int key = 0;
	
	public Cell makeMove(GameField field) {
		buildProbableCellsList(field);
		Cell moveCell = probablyCells.get(key);		
		return moveCell;
	}		

	private void buildProbableCellsList(GameField f) {
		probablyCells.clear();
		key = 0;
		for (Cell c : f.possibleCells) {
			if ((c != null)&&(f.canMove(c))) {
				int scores = f.getScoresForThisMove(c);
				if (scores != 0) {
					if ((c.i_index == 0) | (c.j_index == 0) | (c.i_index == 7)
							| (c.j_index == 7))
						scores += 100;
					probablyCells.put(scores, c);
					if (key < scores) key = scores;
					scores = 0;
				}
			}
		}
	}

}
