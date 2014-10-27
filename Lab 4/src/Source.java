import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Source {

	Scanner scan = new Scanner(System.in);
	ArrayList<Product> products = new ArrayList<>();
	
	public Source() {
		fillArrayList();
		writeToFile();
		showDetailsFromTextfile();
		while (true) {
			dsiplayOptions();
			int input = scan.nextInt();

			switch (input) {
			case 1:
				System.out.println("Enter a product id");
				int a = scan.nextInt();
				System.out.println("Enter a quantity");
				int b = scan.nextInt();
				buyAProuct(a, b);
				break;
			case 2:
				System.out.println("Enter the product id");
				int x = scan.nextInt();
				if (checkIfInStock(x))
					System.out.println("Yes, stock is available");
				else
					System.out.println("No stock is available");
				break;
			case 3:
				System.out.println("Enter an id");
				int id = scan.nextInt();
				displayAProduct(id);
				break;
			case 4:
				System.out.println("Enter an ID");
				int id2 = scan.nextInt();
				System.out.println("Enter quantity");
				int quan2 = scan.nextInt();
				calculateTaxReturn(id2, quan2);
				break;
			case 5:
				return;
			default:
				System.out.println("Please enter values between 1 - 5");
			}

		}

	}

	private void calculateTaxReturn(int id2, int quan2) {
		for(Product p : products){
			if(p.ProductId == id2){
				if(p instanceof Taxable)
				{
					System.out.println((((Taxable) p).taxReturn(id2, id2)));
				}else{
					System.out.println("This product is not Taxable");
				}
			}
		}
		
	}

	private void displayAProduct(int id) {
		for (Product p : products) {
			if (p.ProductId == id) {
				System.out.println("Product ID: " + p.ProductId);
				System.out.println("Product Name: " + p.name);
				System.out.println("Product Price: " + p.Price);
				System.out.println("Remaining Stock: " + p.Quantity);
				return;
			}
		}

		System.out.println("Sorry, not in stock");
	}

	private boolean checkIfInStock(int x) {
		for (Product p : products) {
			if (p.ProductId == x) {
				if (p.Quantity >= 0)
					return true;
			}
		}
		return false;

	}

	void showDetailsFromTextfile(){
		String file = "";
		try {
			Scanner scan = new Scanner(new FileReader("products.txt"));
			while(scan.hasNextLine()){
				file += scan.nextLine();

			}
			System.out.println(file);
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void fillArrayList() {
		products.add(new Furniture(101, "chair", 25.99, 10));
		products.add(new Furniture(102, "table", 140.99, 10));
		products.add(new Dresser(103, "dresser", 22.99, 10, 10, 20, 15));
		products.add(new KitchenWare(104, "forks", 22.99, 10, "plastic")); 														
		products.add(new KitchenWare(105, "knives", 120.99, 10, "metal"));
		products.add(new KitchenWare(106, "plates", 120.99, 10, "metal"));
		products.add(new Electronic(107, "keyboards", 8.20, 30)); 
		products.add(new Television(108, "tv", 8.20, 30, 11.9));
		products.add(new Electronic(109, "mobile phone", 255.66, 30));
		products.add(new Mp3Player(110, "mp3", 8.20, 30, 65));
		
	}
	/**
	 * Method to Write files to the binary file 
	 */
	private void writeToFile(){
		try {
			ObjectOutputStream  fileOut = new ObjectOutputStream(new FileOutputStream("products.dat"));
			for(int i = 0 ;i<products.size();i++){
				fileOut.writeObject(products.indexOf(i));
			}
			fileOut.close();
			System.out.println("File Updated");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buyAProuct(int id, int quantity) {
		for (Product p : products) {
			if (p.ProductId == id) {
				p.Quantity -= quantity;
				System.out.println("Thank you for your ordering " + p.name);
				writeToFile();
				return;
			}
		}
		System.out.println("Sorry, we are out of stock");

	}

	void dsiplayOptions() {
		System.out.println("Please Enter 1 to buy a Product");
		System.out.println("Please Enter 2 to check if Product is in stock");
		System.out.println("Please Enter 3 to Display Product");
		System.out.println("Please Enter 4 Calculate Tax Return");
		System.out.println("Please Enter 5 Exit");
	}

	public static void main(String[] args) {
		new Source();

	}

}
