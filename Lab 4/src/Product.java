public abstract class Product {

	int ProductId;
	String name;
	double Price;
	int Quantity;

	public Product(){}
	
	public Product(int productId, String name, double price, int quantity) {
		ProductId = productId;
		this.name = name;
		Price = price;
		Quantity = quantity;
	}

	/*@Override
	public String taxReturn(int id, int quantity) {
		if (id == 108)
			return "Product ID: " + ProductId + "has a toal tax rate of" + (tvTaxRate*quantity);
		return "Product ID: " + ProductId + "has a tax rate of" + (defaultTaxRate* quantity);
	}*/

}
