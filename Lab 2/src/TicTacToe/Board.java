package TicTacToe;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	private int[][] board = new int[3][4];
	private int currentGameState = 1;
	private boolean firsttime = true;
	private int moveCounter = 0;
	private int size = 4;

	public Board() {

		do {
			printBoard();
			makeMove();
			moveCounter++;
			
			if (checkWinner(1)){// == 1) {
				System.out.println("Player wins: See Below for Result");
				printBoard();
				currentGameState=0;
				
			} else if (checkWinner(2)){// == 2) {
				System.out.println("Computer Wins: See Below for Result");
				printBoard();
				currentGameState = 0;
			}else if(moveCounter >3 ){
				System.out.println("Draw Match!!");
				printBoard();
				currentGameState = 0;
			}

		} while (currentGameState == 1);

		/*
		 * for(int i=0;i<10;i++) computerMove();
		 */

	}

	// Ask for user input
	private int[] userInput() {
		String ans = "";
		int x = 0, y = 0;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("Pease enter your co-ordinates");
			ans = scan.next();

			// Checks for x
			x = Character.getNumericValue(ans.charAt(1)); // convert the character
			// Checks for y
			if (ans.charAt(0) == 'a')
				y = 1;
			else if (ans.charAt(0) == 'b')
				y = 2;
			else if (ans.charAt(0) == 'c')
				y = 3;
			else
				y = -1;

		} while ((ans.length() != 2 || y == -1) || (x < 0 || x > 2));

		int[] send = { x, y };
		return send;
	}

	// ===========
	public void makeMove() {
		int[] input = userInput();
		int x = input[0];
		int y = input[1];
		

		if (board[x][y] == 0 && board[x][y] != 2) {
			board[x][y] = 1; // sets the computer to position 1
			// Allow the computer to make a move
			if (checkWinner(1))// != 1)
				computerMove();
		} else {
			System.out.println("***************************");
			System.out.println("Sorry, this space is full, Please Enter again");
			System.out.println("***************************");
			moveCounter--;
		}

	}

	// ==========
	// Make the computer move
	private void computerMove() {
		boolean run = true;
		int xcordinates, ycordinates;
		do {
		
			if (firsttime) {
				xcordinates = 1;
				ycordinates = 2;
				firsttime = false;
			} else {
				xcordinates = (int) (Math.random() * 3);
				ycordinates = (int) (Math.random() * 3) + 1;
			}
			if (board[xcordinates][ycordinates] != 1
					&& board[xcordinates][ycordinates] != 2) {

				/*if ((checkHoriztonalLines() == true || checkVerticalLines() == true) || (checkOppoiste() == true || checkOppositeTwo() == true))
					run = false;
			
				else {*/
					board[xcordinates][ycordinates] = 2;
					run = false;
				}
			
		} while (run);

	}

	// ============
	void printBoard() {

		System.out.println("       A         B         C"); // 7 spaces
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 0)
					System.out.print(i + " |");
				else if (board[i][j] == 1)
					System.out.print("    " + "X" + "    |"); // 4*4
				else if (board[i][j] == 2)
					System.out.print("    " + "O" + "    |");
				else
					System.out.print("    " + " " + "    |");
			}
			System.out.println("\n  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			System.out.println();
		}

	}



	// =======================
	boolean checkHoriztonalLines() {
		int compCount = 0;
		int playerCount = 0;
		for (int k = 0; k < 3; k++) { //To check all 3 Horizontal lines
			for (int i = 1; i < 4; i++) { //To check the each box along the row 
				compCount +=increaseOccurence(k,i,2);
				playerCount += increaseOccurence(k, i, 1);	
			}
			if (compCount == 2 || playerCount == 2) {
				for (int j = 1; j < 4; j++) { //runs across the row and inserts
					checkRowAndInsert(k, j);
				}
			} else {
				compCount = 0;
				playerCount = 0;
			}
		}  

		return false;
	}

	int increaseOccurence(int x, int y, int player){
		if(board[x][y] == player){
			return 1;
		}
		return 0;
	}
	boolean checkRowAndInsert(int x, int y){
		if (board[x][y] == 0) {
			board[x][y] = 2;
		}
		return true;
	}
	// ==========================
	boolean checkVerticalLines() {
		int compCount = 0;
		int playerCount = 0;
		for (int k = 1; k < 4; k++) {
			for (int i = 0; i < 3; i++) {
				compCount +=increaseOccurence(i,k,2);
				playerCount += increaseOccurence(i, k, 1);
			}
			if (compCount == 2 || playerCount == 2) {
				for (int j = 0; j < 3; j++) {
					checkRowAndInsert(j, k);
				}
			}else {
				compCount = 0;
				playerCount = 0;
			}
		}

		return false;
	}

	// =====================
	boolean checkOppoiste() {
		int compCount = 0;
		int playerCount = 0;
		for (int i = 0; i < 3; i++) {
			/*if (board[i][i + 1] == 1)
				compCount++;
			else if (board[i][i + 1] == 2)
				playerCount++;*/
			compCount +=increaseOccurence(i,i+1,2);
			playerCount += increaseOccurence(i, i+1, 1);
		}
		if (compCount == 2 || playerCount == 2) {
			int x = 0, y = 1;
			for (int m = 0; m < 3; m++) {
				if (board[x + m][y + m] == 0) {
					board[x + m][y + m] = 2;
					return true;
				}
			}
		}
		return false;
	}
	
	boolean checkOppositeTwo(){
		int compCount = 0, num = 3, playerCount = 0;
		for (int i = 0; i < 3; i++) {
			if (board[i][num] == 1)
				compCount++;
			else if(playerCount == 2)
				playerCount++;
			num--;
		}
		if (compCount == 2 || playerCount == 2) {
			int x = 0, y = 3;
			for (int m = 0; m < 3; m++) {
				if (board[x + m][y - m] == 0) {
					board[x + m][y - m] = 2;
					return true;
				}
			}
		}
		return false;
	}
	
	 /**
	  * -1 is the looser
	  * 1 is the player wins
	  * 2 is the computer wins
	  * 
	  */
	boolean checkWinner(int player) {
		for(int i = 0; i <3;i++){
			if(winLine(player, i*3, 1) || winLine(player,i, 3))
				return true;
		}	
		return false;
	}
	
	boolean winLine(int player, int start, int step){
		Integer [] freeSpace = possibleMoves();
		for(int i=0;i<freeSpace.length;i++){
			if(freeSpace[start + step*i] != player)
				return false;
		}
		
		
		return true;
	}
	
	Integer[] possibleMoves(){
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for(int j = 1; j <4;j++){
				if(board[i][j] == 0)
				list.add(board[i][j]);
			}
		}
		
		Integer [] array = new Integer[list.size()];
		list.toArray(array);
		
		return array;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
