package marc.com;

public class Rectangle extends Shape{

	protected double length, width;
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
		this.opacity = 100;
	}
	
	public Rectangle(double length, double width, double opacity) {
		this.length = length;
		this.width = width;
		this.opacity = opacity;
	}

	@Override
	public double calArea() {
		return length * width;
	}

}
