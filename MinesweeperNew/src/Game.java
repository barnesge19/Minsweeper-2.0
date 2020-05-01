import java.util.*;

/**
 * A collection of methods to run and play the game, handling user I/O
 * @author BARNESGE19
 *
 */
public class Game {
	private Board userBoard;
	private Display userGame;
	
	public Game()
	{
		userGame = new Display();
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
				System.out.println("Invalid entry \nEnter row number: ");
				scn.next();
			}
			//TODO check if the number is within the 2d parameters
			int rowGuess = scn.nextInt();
			
			System.out.println("Enter column number: ");
			while(!scn.hasNextInt()) {
				System.out.println("Invalid entry \nEnter column number: ");
				scn.next();
			}
			//TODO check if the number is within the 2d parameters
			int colGuess = scn.nextInt();
			
			try {
				userGame.play(rowGuess, colGuess);
			} 
			
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			scn.close();
		} while(!userGame.win(userGame, userBoard));
		
		if(userGame.win(userGame, userBoard)) {
			System.out.println("Congratulations, you won!");
		}
	}
}
