import java.util.*;
/**
 * A collection of methods to run and play the game, handling user I/O
 * @author BARNESGE19
 *
 */
public class Game extends Display {
	
	public Game()
	{
		//Call parent constructor
			super();
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
		
	}
	
	/**
	 * Searches the board's 2D array and display's 2D array to see if they match
	 * If they all match, the user has uncovered all of the free squares and wins
	 * If they don't match, the user has not yet uncovered all free squares and hasn't won yet
	 * @return boolean true if win, false if no win
	 */
	public boolean win()
	{
		return false;
	}
}
