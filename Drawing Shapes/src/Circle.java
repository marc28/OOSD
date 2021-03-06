import java.awt.Point;
import java.awt.geom.Ellipse2D;


public class Circle extends Shapes{

	Point x, y;
	
	public Circle(){
		
	}
	
	public Circle(int x1, int x2,int y1, int y2) {
		x.x = x1;
		x.y = x2;
		y.x = y1;
		y.y = y2;
	}
	
	
	Circle drawCircle(int x, int y, int x2, int y2){
		Point a = new Point(x, y);
		Point b = new Point(x2, y2);
		int radius = (int) a.distance(b);
		Point use = new Point(a.x -radius, a.y - radius);
		return new Circle(use.x, use.y ,radius*2, radius*2);
	}

}
