public class CalculatorTimesFive {

	private int[][] ray = new int[25][25];

	public CalculatorTimesFive() {
		fillArray();
		printArray();
	}

	// Fill the array
	public void fillArray() {
		// int num1 = 0, num2 = 0;
		for (int i = 0; i < ray.length; i++) {
			for (int j = 0; j < ray.length; j++) {
				ray[i][j] = (i + 1) * (j + 1);
			}
		}
	}

	public void printArray() {
		boolean yes = true;
		for (int i = 0; i < ray.length; i++) {
			for (int j = 0; j < ray.length; j++) {
				if (i == 0) {
					System.out.print(ray[i][j]);
					if(j<9)
					System.out.print("_ _ _ _");
					else
						System.out.print("_ _ _ ");
				} else {
					if (yes){
						if(i<9)
						System.out.print(ray[i][j] + " |" + "\t");
						else
							System.out.print(ray[i][j] + "|" + "\t");
						yes=false;
					}
					else
						System.out.print(ray[i][j] + "\t");
				}
			}
			System.out.println();
			yes = true;
		}
	}

	public static void main(String[] args) {
		new CalculatorTimesFive();

	}

}
