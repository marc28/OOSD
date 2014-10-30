import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Line extends MyShape implements Serializable {
	Point start,end;
	public Line(){
		
	}
	public Line(int a, int b, int c, int d) {
		super(a,b,c,d);
		this.start = new Point(a,b);
		this.end = new Point(c,d);
	}

	
	
	public void draw(Graphics2D g, boolean x) {
		x = true;
		g.setColor(color);
		g.drawLine(start.x, start.y, end.x, end.y);
	}



}
