import java.util.*;

/**
 * A collection of methods to run and play the game, handling user I/O
 * @author BARNESGE19
 *
 */
public class Game {
	private Display userGame;
	private boolean status;
	
	public Game()
	{
		userGame = new Display();
		status = false;
	}
	
	/**
	 * Runs the game loop for minesweeper once per user input
	 * handles user I/O for the spaces they pick
	 * uses display class to determine what numbers and spaces to output
	 * uses board class to know the orientation of the board
	 * terminates if the user runs into a mine and loses
	 * terminates when the user uncovers all squares and wins
	 */
	public void GameLoop()
	{
		System.out.println("Welcome to MineSweeper!");
		do {
			Scanner scn = new Scanner(System.in);
			System.out.println("Enter row number: ");
			
			//Ensure user input is an integer
		
			while(!scn.hasNextInt() ) {
				scn.nextLine();
				System.out.println("Invalid entry \nEnter row number: ");
			}
			//TODO check if the number is within the 2d parameters
			int rowGuess = scn.nextInt();
			
			System.out.println("Enter column number: ");
			while(!scn.hasNextInt()) {
				scn.nextLine();
				System.out.println("Invalid entry \nEnter column number: ");
				
			}
			//TODO check if the number is within the 2d parameters
			int colGuess = scn.nextInt();
			
			try {
				play(rowGuess, colGuess);
			} 
			
			catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			status = win();
			if(lose(rowGuess,colGuess)) {
				break;
			}
		} while(!status);
		
		
		if(status) {
			System.out.println("Congratulations, you won!");
		}
		else {
			System.out.println("You lost idiot, try again");
		}
	}
	
	/**
	 * Searches the board's 2D array and display's 2D array to see if they match
	 * If they all match, the user has uncovered all of the free squares and wins
	 * If they don't match, the user has not yet uncovered all free squares and hasn't won yet
	 * @return boolean true if win, false if no win
	 */
	public boolean win()
	{
		for (int r = 0; r < userGame.DIMENSION; r++) {
			for (int c = 0; c < userGame.DIMENSION; c++) {
				if (userGame.getOpen(r,c) !=  userGame.getSpace(r,c)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Throws exceptions for user input of row and column
	 * @param row integer that user inputs, represents row #
	 * @param col integer that user inputs, represents column #
	 * @throws Exception if number is out of bounds or if space is taken
	 */
	public void play(int r, int c) throws Exception {
		if (r < 0 || r > userGame.DIMENSION -1 || c < 0 || c > userGame.DIMENSION-1) {
			throw new Exception("Sorry, invalid row/column number. Try again.");
		}
		
		else if (userGame.getOpen(r, c)) {
			throw new Exception("That space is already open. Try another one.");
		}
		if(!lose(r,c)) {
			userGame.openSpace(r, c);
		}
	}
	
	public boolean lose(int r, int c) {
		return userGame.getSpace(r, c);
	}

}
