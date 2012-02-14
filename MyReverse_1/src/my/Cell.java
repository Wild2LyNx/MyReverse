package my;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Cell {
	double relativeX, relativeY, side;
	boolean free = true;
	Color stoneColor = Color.RED;

	public Cell(int i, int j, double s) {
		this.side = s;
		this.relativeX = i * s;
		this.relativeY = j * s;
	}

	public void paint(double fieldX, double fieldY, Graphics2D g) {
		g.draw(new Rectangle2D.Double(fieldX + relativeX, fieldY + relativeY,
				side, side));
		if (!free) {			
			g.draw(new Ellipse2D.Double(fieldX + relativeX + 3, fieldY
					+ relativeY + 3, side - 6, side - 6));
			g.setColor(stoneColor);
			g.fill(new Ellipse2D.Double(fieldX + relativeX + 4, fieldY
					+ relativeY + 4, side - 7, side - 7));
		}
	}

}
