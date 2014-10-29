import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle extends MyShape {
	Point one, two, three;
	Polygon mypolygon = new Polygon();
	Point[] point = new Point[3];

	public Triangle() {

	}

	public Triangle(int a, int b, int c, int d, int e, int f) {
		super(a, b, c, d, e, f);
	}

	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int x[] = { start.x, middle.x, end.x };
		int y[] = { start.y, middle.y, end.y };
		g.drawPolygon(x, y, 3);
		if (fill == true)
			g.fillPolygon(x, y, 3);
	}

}
