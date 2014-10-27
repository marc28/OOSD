package marc.com;

public abstract class Shape {
	protected double opacity;
	
	public abstract double calArea();
	
	public double getOpacity() {
		return opacity;
	}
	
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
	
	
}
