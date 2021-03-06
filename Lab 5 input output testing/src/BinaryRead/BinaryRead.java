package BinaryRead;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ObejctReadingWriting.Toy;

public class BinaryRead {
	String fileName ="binaryTest.bin";
	String binaryFileName = "file.dat";
	Toy toy1 = new Toy("Marc",7);
	Toy toy2 = new Toy("Trish",6);
	ArrayList<Toy> toys = new ArrayList<>();
	ArrayList<Toy> toysIn = new ArrayList<Toy>();
	
	
	public BinaryRead(){
		toys.add(toy1);
		toys.add(toy2);
		

		writeBinaryObjects();
		readBinaryObjects();
	}

	
	
	void writeBinary(){
		try {
			ObjectOutputStream  writeOut = new ObjectOutputStream(new FileOutputStream(fileName));
			writeOut.writeInt(1984);
			writeOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Writing Complete");
		
		
	}

	int readBinary(){
		int x = 0;
		try {
			ObjectInputStream readIn = new ObjectInputStream(new FileInputStream(fileName));
			x = readIn.readInt();
			readIn.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return x;
	}
	
	/**
	 * 
	 */
	void writeBinaryObjects(){
		try {
			ObjectOutputStream  writeOut = new ObjectOutputStream(new FileOutputStream(binaryFileName));
			writeOut.writeObject(toy1);
			writeOut.writeObject(toy2);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Binary Written");
	}
	
	/**
	 * 
	 */
	void readBinaryObjects(){
		int i = 1;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(binaryFileName));
			Toy newToy = (Toy) in.readObject();
			toysIn.add(newToy);
			
			while(newToy != null){
				newToy = (Toy) in.readObject();
				toysIn.add(i,newToy);
				System.out.println(toysIn.get(i).name);
				i++;
			
			}
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			if(i > 1)
				;
			else{
				System.out.println("IOError");
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No Class Found");
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new BinaryRead();

	}
	

}
