
public class Product {

	private String name;
	private double price;
	private double individualDiscount;
	private static double gloablDiscount = 0;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	//=========
	public double calcFinalPrice(){
		if(individualDiscount > gloablDiscount){
			return price -= individualDiscount;
		}else{
			return price -= gloablDiscount;
		}
	}

	public void setIndividDiscount(double individDiscount) {
		individualDiscount=individDiscount;
	}

	public static void setGloablDiscount(double disc) {
		gloablDiscount = disc;
	
	}

	//======
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	

	
	

}
