public class Electronic extends Product implements Taxable {

	public Electronic(int productId, String name, double price, int quantity) {
		super(productId, name, price, quantity);
	}

	@Override
	public String taxReturn(int id, int quantity) {
		if (id == 108)
			return "Product ID: " + ProductId + "has a toal tax rate of" + (tvTaxRate * quantity);
		return "Product ID: " + ProductId + "has a tax rate of"	+ (electronicTaxRate * quantity);
	}

}
