package marc.com;

public class Driver {

	public Driver() {
		Shape s1 = new Square(5);
		Circle sCirc = new Circle(10,10);
		Shape sTrian = new Triangle(5, 5, 5, 20);
		Shape sSquare = new Square(6, 50);
		
		print(s1);
		print(sCirc);
		print(sTrian);
		print(sSquare);
	}

	void print(Shape s){
		System.out.println("Area: " + s.calArea() + " Opacity: "+ s.getOpacity());
	}
	
	public static void main(String[] args) {
		new Driver();

	}

}
