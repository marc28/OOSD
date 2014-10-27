
public class ProductDriver {

	public ProductDriver() {
		Product p1 = new Product("a", 100);
		System.out.println(p1.getPrice());
		//p1.calcFinalPrice(10, 8);
		//p1.setIndividDiscountOnProduct(5);
		Product p2 = new Product("b", 20);
		Product.setGloablDiscount(10.0);
		p1.setIndividDiscount(50);
		System.out.println(p1.calcFinalPrice() + " " + p2.calcFinalPrice());
	}

	public static void main(String[] args) {
		new ProductDriver();

	}

}
