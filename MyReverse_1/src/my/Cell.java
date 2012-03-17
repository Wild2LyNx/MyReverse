package my;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Cell implements Cloneable {
	double relativeX, relativeY, side;
	int i_index, j_index;
	int descriptor = 2;

	boolean free = true;
	Color stoneColor = Color.RED;
	Color whiteColor = new Color(220, 220, 220);

	public Cell(int i, int j, double s) {
		this.side = s;
		this.relativeX = j * s;
		this.relativeY = i * s;
		this.i_index = i;
		this.j_index = j;
	}

	public void paint(double fieldX, double fieldY, Graphics2D g) {
		g.draw(new Rectangle2D.Double(fieldX + relativeX, fieldY + relativeY,
				side, side));
		if (!free) {
			// g.draw(new Ellipse2D.Double(fieldX + relativeX + 3, fieldY
			// + relativeY + 3, side - 6, side - 6));
			g.setColor(stoneColor);
			g.fill(new Ellipse2D.Double(fieldX + relativeX + 4, fieldY
					+ relativeY + 4, side - 7, side - 7));
		}
	}

	public void makeBusy(int descriptor) {
		free = false;
		this.descriptor = descriptor;
		if (descriptor == 0)
			stoneColor = Color.BLACK;
		if (descriptor == 1)
			stoneColor = whiteColor;
	}

	public boolean contains(double x, double y, double gamefieldY) {
		if ((relativeX < x) && (x < relativeX + side)) {
			if (((gamefieldY + relativeY) < y)
					&& (y < (gamefieldY + relativeY + side)))
				return true;
		}
		return false;
	}

	public void setStoneColor(int descriptor) {
		this.descriptor = descriptor;
		if (descriptor == 0){
			stoneColor = Color.BLACK;
//			System.out.println("Black color");
		}
		if (descriptor == 1)
			stoneColor = whiteColor;
	}
	
	public Cell clone() {
		try {
			return (Cell) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
