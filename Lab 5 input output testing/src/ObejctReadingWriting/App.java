package ObejctReadingWriting;

/*
 * file ObjectOutputStream / FileOutputStream WRITES objects to a file
 * ObjectInputStream / FileInputStream READS object from a file
 */
import java.io.*;
import java.util.Scanner;

public class App {

	Toy [] toyshop = new Toy[4];
	Toy [] toyshop2 = new Toy[4];
	public App() {
		
		toyshop[0] = new Toy("a",1);
		toyshop[1] = new Toy("b",2);
		toyshop[2] = new Toy("c",3);
		toyshop[3] = new Toy("d",4);
		
		//Saving the toys to the file
		Scanner scan = new Scanner(System.in);
		writingObjects();
		readObjects();
	}
	
	//Method to write objects to a file
	void writingObjects(){
		try {
			ObjectOutputStream  fileOut = new ObjectOutputStream(new FileOutputStream("objectWrite.txt"));
			
			for(int i=0;i<toyshop.length;i++){
				fileOut.writeObject(toyshop[i]);
			}
			fileOut.close();
			System.out.println("Success");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Method to Read objects from a file
	void readObjects(){
		
		Toy aToy;
		int i = 1;
		int numofToys = 0;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("objectWrite.txt"));
			aToy = (Toy)in.readObject();
			toyshop2[0] = aToy;
			
			while(aToy != null){
				aToy = (Toy)in.readObject();
				toyshop2[i] = aToy;
				i++;
			}
			
			
			System.out.println("Read ok");
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			if(i > 1)
				numofToys = i;
			else{
				System.out.println("IOError");
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new App();

	}

}
