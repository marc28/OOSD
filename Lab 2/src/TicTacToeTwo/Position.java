package TicTacToeTwo;

import java.util.LinkedList;
import java.util.List;

public class Position {

	public char turn;
	public char [] board;
	public int dim = 3;

	//====
	//CONSTRUCTORS
	public Position() {
		this.board = "         ".toCharArray();
		this.turn = 'x';
	}
	
	public Position(char [] board, char turn){
		this.board = board;
		this.turn = turn;
	}
	
	public Position(String str) {
		this.board = str.toCharArray();
		this.turn = 'x';
	}
	//END OF CONSTRUCTOR
	//====
	public String toString(){
		return new String(board);
	}

	/**
	 * Method to move around the array 0 - 8
	 */
	public Position move(int i) {
		char [] newBoard = board.clone(); //the board is just a char array
		newBoard[i] = turn;
		return new Position(newBoard, turn =='x' ? 'o' : 'x');
	}
	
	/**
	 * Returns an array with number between 0-8 if they are free to move to.
	 */
	public Integer[] possibleMoves() {
		List<Integer> list = new LinkedList<Integer>();
		for(int i =0;i<board.length;i++){
			if(board[i] == ' '){
				list.add(i); //adding actual numbers to the list {1,2,3 ...}
			}
		}
		Integer [] array = new Integer[list.size()];
		list.toArray(array);
		
		return array;
	}

	/**
	 * 
	 * 
	 */
	public boolean win(char turn) {
		for (int i = 0; i < dim; i++) {
			if(win_line(turn, i*dim,1)/*across*/ || win_line(turn, i, dim)/*down*/)
				return true;
		}
		if(win_line(turn,dim - 1, dim-1) || win_line(turn, 0, dim+1)){ //checking both diagnols
			return true;
		}
		
		return false;
	}
	
	public boolean win_line(char turn, int start, int step){
		
		for (int i = 0; i < 3; i++) {
			if(board[start + step*i] != turn){
				return false; //did not win
			}
		}
		
		return true;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
