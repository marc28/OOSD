public class KitchenWare extends Product implements Taxable{

	String material;

	public KitchenWare(int productId, String name, double price, int quantity,String material) {
		super(productId, name, price, quantity);
		this.material = material;
	}
	
	@Override
	public String taxReturn(int id, int quantity) {
		if(material.equals("plastic"))
			return "Product ID: " + ProductId + " has a tax rate of " + (plasticTaxRate* quantity);
		return "Product ID: " + ProductId + " has a tax rate of " + (defaultTaxRate* quantity);
	}

}
