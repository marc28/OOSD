
public class Dresser extends Furniture{

	Dimension dimension;
	public Dresser(int productId, String name, double price, int quantity, int a, int b, int c) {
		super(productId, name, price, quantity);
		dimension = new Dimension(a, b, c);
	}

}
