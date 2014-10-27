package ObejctReadingWriting;

import java.io.Serializable;

public class Toy implements Serializable{

	public String name;
	public int size;
	
	public Toy(String name, int size) {
		this.name = name;
		this.size = size;
	}

}
