package my;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class GameField extends JComponent {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	int cellCount = 8;
	double gameFieldSide, gameFieldX, gamefieldY, cellSide;	
	
//	ParameterizedArray<Cell> blackStones, whiteStones, freeCells, possibleCells;	
	Cell allCells [][] = new Cell [cellCount][cellCount];
	ArrayList<Cell> blackStones = new ArrayList<Cell>();
	ArrayList<Cell> whiteStones = new ArrayList<Cell>();
	ArrayList<Cell> freeCells = new ArrayList<Cell>();
	ArrayList<Cell> possibleCells = new ArrayList<Cell>();	
	
	int moveCounter = 0;

	public GameField(JFrame f) {
		this.frame = f;
		initDimensions();
		initCells();
	}

	private void initCells() {
		for (int i = 0; i < cellCount; i++) {
			for (int j = 0; j < cellCount; j++){				
				allCells [i][j] = new Cell(i, j, cellSide);
				freeCells.add(allCells [i][j]);
//				allCells.put(i, j, new Cell(i, j, cellSide));
			}			
		}
	}

	private void initDimensions() {
		double minDimension = Math.min(frame.getHeight(), frame.getWidth());
		gameFieldSide = minDimension - 70;

		gameFieldX = (frame.getWidth() - gameFieldSide) / 2;
		gamefieldY = (frame.getHeight() - gameFieldSide) / 2 - 10;

		cellSide = gameFieldSide / cellCount;
	}

	public void paintComponent(Graphics g) {
		// System.out.println("width: " + frame.getWidth() + "\n height: " +
		// frame.getHeight() + "\n side:" + gameFieldSide + "\n x: " +
		// gameFieldX);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.draw(new Rectangle2D.Double(0, gamefieldY, gameFieldSide,
				gameFieldSide));
		g2.setColor(Color.YELLOW);
		g2.fill(new Rectangle2D.Double(1, gamefieldY + 1, gameFieldSide - 1,
				gameFieldSide - 1));
		
		possibleCells.add(allCells[cellCount/2 - 1][cellCount/2 - 1]);
		makeCellBusy (cellCount/2 - 1, cellCount/2 - 1, 1);
		makeCellBusy (cellCount/2, cellCount/2, 1);
		makeCellBusy (cellCount/2, cellCount/2 - 1, 0);
		makeCellBusy (cellCount/2 - 1, cellCount/2, 0);		
		
		for (Cell[] cv: allCells) {
			for (Cell cg: cv){
				g2.setColor(Color.BLACK);
				cg.paint(0, gamefieldY, g2);
			}
		}
	}

	private void makeCellBusy(int i, int j, int descriptor ) {		
		allCells[i][j].makeBusy(descriptor);
		freeCells.remove(allCells[i][j]);
		possibleCells.remove(allCells[i][j]);
		if (descriptor == 0) {			
			blackStones.add(allCells[i][j]);
			checkAdjacentCells(i,j);
		}
		if (descriptor == 1){
			whiteStones.add(allCells[i][j]);
			
		}
	}

	private void checkAdjacentCells(int i, int j) {
		ArrayList<Cell> aroundCells = getAround(i,j);
		for (Cell c: aroundCells){
			if (c.free){
				if(!possibleCells.contains(c)){
				possibleCells.add(c);
				}
			}
			if (c.descriptor != moveCounter%2){
				int d = getDirection(c, i, j);
				
			}
		}
	}

	private int getDirection(Cell c, int i, int j) {		
		if (c.i_index < i){ //top side! 
			if (c.j_index < j) return 1; //top left corner;
			if (c.j_index == j) return 2; //up line;
			return 3; //top right corner;
		}
		
		if (c.i_index == i){ //left or right line!
			if (c.j_index < j) return 4; //left line;
			if (c.j_index > j) return 5; //right line;
		}
		
		if (c.i_index > i){ //down side! This 'if' not necessary, I know... 
			if (c.j_index < j) return 6; //down left corner;
			if (c.j_index == j) return 7; //down line;
			return 8;//down right corner;
		}
		return 0;
	}

	public ArrayList<Cell> getAround(int i, int j){
		ArrayList<Cell> aroundCells = new ArrayList<Cell>();
		for (int x = i-1; x < i+2; x++){
			for (int y = j-1; y < j+2; y++){				
					aroundCells.add(allCells [x][y]);
				}
			}
		aroundCells.remove(allCells[i][j]);		
		return aroundCells;		
	}

}
