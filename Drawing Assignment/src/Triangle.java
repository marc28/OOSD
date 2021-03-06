import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

/**
 * All classes implement Serializable for the purpose of 
 * Saving and Loading
 * 
 */
public class Triangle extends MyShape implements Serializable {

	/**
	 * The Serializable class Triangle needs to declare a static final
	 * serialVersionUID field of type long
	 */
	private static final long serialVersionUID = 1L;
	
	
	Point one, two, three; //Three points for a Triangle
	Polygon mypolygon = new Polygon(); //To draw a triangle I needed a polygon object
	Point[] point = new Point[3]; //An array to hold the 3 points

	/**
	 * Default Constructor
	 */
	public Triangle(){}
	
	/**
	 * Constructor that takes 6 ints to make three points. 
	 */
	public Triangle(int a, int b, int c, int d, int e, int f) {
		super(a, b, c, d, e, f);
		this.one = new Point(a,b);
		this.two = new Point(c,d);
		this.three = new Point(e,f);
	}

	/**
	 * The drawPolygon() method takes two arrays, all the x coordinates and y
	 * coordinates. It also requires a 3rd parameter, the total number of points 
	 * which I have hard-coded as 3 given its a triangle.
	 */
	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int x[] = { one.x, two.x, three.x };
		int y[] = { one.y, two.y, three.y };
		g.drawPolygon(x, y, 3);
		if (fill == true)
			g.fillPolygon(x, y, 3);
	}

}
