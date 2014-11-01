import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 * All classes implement Serializable for the purpose of 
 * Saving and Loading
 * 
 */

public class Line extends MyShape implements Serializable {


	/**
	 * The Serializable class Line needs to declare a static final
	 * serialVersionUID field of type long
	 */
	private static final long serialVersionUID = 1L;
	
	//Line requires Two points, start and end.
	Point start,end;
	
	/**
	 * Default Constructor
	 */
	public Line(){}
	
	
	/**
	 * Constructor which takes 4 ints to instantiate the 2 Points 
	 * of the Line.
	 */
	public Line(int a, int b, int c, int d) {
		super(a,b,c,d);
		this.start = new Point(a,b);
		this.end = new Point(c,d);
	}

	/**
	 * the boolean fill for a line will always be true,
	 * it doesn't effect the line either way as its always a solid line.
	 * 
	 * The line is straight forward in that the Graphics2Ds method, drawlLine()
	 * is used.It takes the starting point and ending points location.
	 * 
	 * setColor() is used to set the colour of that specific lines instance.
	 */
	public void draw(Graphics2D g, boolean x) {
		x = true;
		g.setColor(color);
		g.drawLine(start.x, start.y, end.x, end.y);
	}



}
