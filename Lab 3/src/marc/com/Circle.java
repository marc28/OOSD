package marc.com;

public class Circle extends Shape{

	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
		
	}
	
	public Circle(double radius, double opacity) {
		this.radius = radius;
		this.opacity = opacity;
	}

	@Override
	public double calArea() {
		return  radius * radius * Math.PI;
	}

}
