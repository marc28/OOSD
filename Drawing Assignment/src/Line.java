import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends MyShape {

	public Line(){
		super();
	}
	public Line(int a, int b, int c, int d) {
		super(a,b,c,d);
	}

	
	@Override
	public void draw(Graphics2D g, boolean x) {
		g.setColor(color);
		g.drawLine(start.x, start.y, end.x, end.y);
	}



}
