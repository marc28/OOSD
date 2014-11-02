import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 * All classes implement Serializable for the purpose of 
 * Saving and Loading
 * 
 */

public class Circle extends MyShape implements Serializable {

	/**
	 * The Serializable class Circle needs to declare a static final
	 * serialVersionUID field of type long
	 */
	private static final long serialVersionUID = 1L;

	// Two points required for a Circle
	Point start, end;

	/**
	 * Default Constructor
	 */
	public Circle() {}

	/**
	 * Constructor which takes 4 ints to instantiate the 2 Points.
	 */
	public Circle(int x, int y, int x2, int y2) {
		super(x, y, x2, y2);
		this.start = new Point(x, y);
		this.end = new Point(x2, y2);
	}

	/**
	 * The draw method here is over written to facilitate the drawing of a
	 * Circle.It is required because Circle is a subclass of MyShape so it must 
	 * instantiate this abstract method in MyShape.
	 * 
	 * The radius of the circle is calculated using the distance between the
	 * first point and the second point. fillOval takes 4 parameters, the
	 * starting x and y point and the width and height. The width and height
	 * should both be double the size of the radius.
	 * 
	 */
	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int radius = (int) Math.round(start.distance(end));
		if (fill == true)
			g.fillOval(this.start.x - radius, this.start.y - radius, radius * 2, radius * 2);
		g.drawOval(this.start.x - radius, this.start.y - radius, radius * 2, radius * 2);

	}

}
