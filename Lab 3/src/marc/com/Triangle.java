package marc.com;

public class Triangle extends Shape{

	double side1, side2, side3;
	public Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		this.opacity = 100;
	}
	
	public Triangle(double side1, double side2, double side3, double opacity) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		this.opacity = opacity;
	}

	@Override
	public double calArea() {
		double p = (side1 + side2 + side3)/2;
		return Math.sqrt(p*((p-side1)*(p-side2)*(p-side3)));
	}

}
