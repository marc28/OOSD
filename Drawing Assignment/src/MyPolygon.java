import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;


public class MyPolygon extends MyShape implements Serializable{

	Point one,two, three, four, five,six;

	public MyPolygon(){}
	public MyPolygon(Point a, Point b, Point c, Point d, Point e, Point f) {
		super(a,b,c,d,e,f);
		one = new Point(a);
		two = new Point(b);
		three = new Point(c);
		four = new Point(d);
		five = new Point(e);
		six = new Point(f);
	}
	//start, end, middle, polyOne, polyTwo, polyThree

	@Override
	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int x []= {one.x, two.x, three.x,four.x,five.x,one.x};
		int y []= {one.y, two.y, three.y,four.y,five.y,one.y};
		g.drawPolygon(x, y, 6);
		if(fill == true)
			g.fillPolygon(x,y,6);
		
	}

}
