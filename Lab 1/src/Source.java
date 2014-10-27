import java.util.Scanner;

public class Source {

	int[] array = new int[30];
	Scanner scan = new Scanner(System.in);
	int input;

	public Source() {
		while (true) {
			displayMenu();
			input = scan.nextInt();
			switch (input) {
			case 1:
				fillArrayWithRandomNums();
				break;
			case 2:
				printArray();
				break;
			case 3:
				System.out.println("The highest number: " + highestNumber());
				break;
			case 4:
				System.out.println("The lowest Number: " + lowestNumber());
				break;
			case 5:
				System.out.println("The average: " + average());
				break;
			case 6:
				return;
			default:
				System.out.println("Sorry invalid input, you input must be between 1 - 6");

			}
		}
	}

	public static void main(String[] args) {
		new Source();

	}

	public void displayMenu() {
		System.out.println();
		System.out.println("Enter 1 to Generate an array");
		System.out.println("Enter 2 to display the contents of the array");
		System.out.println("Enter 3 to show the highest value in the array");
		System.out.println("Enter 4 to show the lowest value in the array");
		System.out.println("Enter 5 to calculate the average");
		System.out.println("Enter 6 to exit");
		System.out.println();

	}

	// Method to fill the arry with random numbers
	public void fillArrayWithRandomNums() {
		for (int i = 0; i < array.length; i++)
			array[i] = (int) (Math.random() * 100) + 1;
		System.out.println("The array has been filled");
	}

	// print array
	public void printArray() {
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	// find highest number
	public int highestNumber() {
		int highest = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > highest)
				highest = array[i];

		}

		return highest;
	}

	// find the lowest
	public int lowestNumber() {
		int lowest = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < lowest)
				lowest = array[i];
		}
		return lowest;
	}

	// Calculate the average of the array
	public double average() {
		double total = 0;
		for (int i = 0; i < array.length; i++)
			total += array[i];

		return total / array.length;
	}

}
