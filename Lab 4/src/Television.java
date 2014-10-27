
public class Television extends Electronic {

	double displaySize;
	public Television(int productId, String name, double price, int quantity, double displaySize) {
		super(productId, name, price, quantity);
		this.displaySize = displaySize;
	}

}
