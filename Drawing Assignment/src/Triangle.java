import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

public class Triangle extends MyShape implements Serializable {


	Point one, two, three;
	Polygon mypolygon = new Polygon();
	Point[] point = new Point[3];

	public Triangle(){}
	public Triangle(int a, int b, int c, int d, int e, int f) {
		super(a, b, c, d, e, f);
		this.one = new Point(a,b);
		this.two = new Point(c,d);
		this.three = new Point(e,f);
	}

	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int x[] = { one.x, two.x, three.x };
		int y[] = { one.y, two.y, three.y };
		g.drawPolygon(x, y, 3);
		if (fill == true)
			g.fillPolygon(x, y, 3);
	}

}
