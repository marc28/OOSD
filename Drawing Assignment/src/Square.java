import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

/**
 * All classes implement Serializable for the purpose of 
 * Saving and Loading
 * 
 */

public class Square extends MyShape implements Serializable{

	/**
	 * The Serializable class Square needs to declare a static final
	 * serialVersionUID field of type long
	 */
	private static final long serialVersionUID = 1L;
	
	//The square class requires Two points
	Point start,end;
	
	/**
	 * Default Constructor
	 */
	public Square(){}
	
	/**
	 * Constructor which takes 4 ints to instantiate the 2 Points 
	 * of the Square.
	 */
	public Square(int a, int b, int c, int d) {
		super(a,b,c,d);
		this.start = new Point(a,b);
		this.end = new Point(c,d);
	}

	/**
	 * The draw method here is over written to facilitate the drawing of a
	 * Square. It is required because Square is a subclass of MyShape so it must 
	 * instantiate this abstract method in MyShape.
	 * 
	 * The width and height of the Square is set using the distance from the start 
	 * and end points.
	 * 
	 */
	public void draw(Graphics2D g, boolean fill)
	{
		g.setColor(color);
		int width = (int) Math.round(start.distance(end));
		AffineTransform saveAT = g.getTransform(); //required to rotate the square
		//The angle is calculated by the formula below : 
		//http://stackoverflow.com/questions/19617924/understanding-graphics-2d-and-rotate-drawrect-methods
		double angle = Math.toRadians(0) + (Math.atan2((this.end.y - this.start.y),(this.end.x - this.start.x)));
		g.rotate(angle, start.x, start.y); //rotate the square, using starting point and the angle
		if(fill == true)
		g.fillRect(start.x, start.y, width, width);
		g.drawRect(start.x, start.y, width, width);
		g.setTransform(saveAT); 

	}


}
