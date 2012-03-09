package my;

import java.util.ArrayList;

public class ComputerPlayer {
	ArrayList<Cell> probableCells = new ArrayList<Cell>(); //Cells where computer can move
	
	public Cell makeMove(GameField field){
		buildProbableCellsList(field);
		
		return null;
	}

	private void buildProbableCellsList(GameField f) {
		for (Cell c: f.possibleCells){
			if (c.free){
				int priority = f.getScoresForThisMove(c);
				if (priority != 0){
					
				}
			}
		}
	}

}
