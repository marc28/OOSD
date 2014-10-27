
public class Mp3Player extends Electronic{

	int memorySize;
	public Mp3Player(int productId, String name, double price, int quantity, int memorySize) {
		super(productId, name, price, quantity);
		this.memorySize = memorySize;
	}

}
