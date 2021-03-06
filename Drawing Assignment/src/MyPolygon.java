import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 * All classes implement Serializable for the purpose of 
 * Saving and Loading
 * 
 */

public class MyPolygon extends MyShape implements Serializable{

	/**
	 * The Serializable class Polygon needs to declare a static final
	 * serialVersionUID field of type long
	 */
	private static final long serialVersionUID = 1L;
	
	//6 points required for a 5 sided polygon
	Point one,two, three, four, five,six;

	/**
	 * Default Constructor
	 */
	public MyPolygon(){}
	
	/**
	 * Constructor which takes 6 points 
	 */
	public MyPolygon(Point a, Point b, Point c, Point d, Point e, Point f) {
		super(a,b,c,d,e,f);
		one = new Point(a);
		two = new Point(b);
		three = new Point(c);
		four = new Point(d);
		five = new Point(e);
		six = new Point(f);
	}
	

	/**
	 * The drawPolygon() method takes two arrays, all the x coordinates and y
	 * coordinates. It also requires a 3rd parameter, the total number of points 
	 * which I have hard-coded as 6 given its a Polygon.
	 * 
	 * Please note that that last and first points are the same as to complete 
	 * the shape.
	 */
	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int x []= {one.x, two.x, three.x,four.x,five.x,one.x};
		int y []= {one.y, two.y, three.y,four.y,five.y,one.y};
		g.drawPolygon(x, y, 6);
		if(fill == true) //a solid fill or just outline
			g.fillPolygon(x,y,6);
		
	}

}
