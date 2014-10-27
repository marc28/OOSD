import java.io.*;
import java.util.Scanner;

/**
 * 
 * PrintWriter WRITES to a text file 
 * Scanner and FileReader READS from a text
 * file ObjectOutputStream / FileOutputStream WRITES objects to a file
 * ObjectInputStream / FileInputStream READS object from a file
 * 
 */
public class App {

	public App() {
		//Writing to a TEXT file
		/*try {
			PrintWriter pw = new PrintWriter("test.txt"); // contents erased															// when open
			pw.append("Hello You");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			PrintWriter pw2 = new PrintWriter(new FileWriter("test.txt", true));
			pw2.append("\nbye");
			pw2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Reading a TEXT file
		try {
			Scanner scan = new Scanner(new FileReader("readme.txt"));
			while(scan.hasNextLine()){
				System.out.println(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public static void main(String[] args) {
		new App();

	}

}
