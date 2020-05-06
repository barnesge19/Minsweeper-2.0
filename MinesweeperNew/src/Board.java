import java.util.*;
/**
 * A collection of methods to handle board of Minesweeper
 * @author BARNESGE19
 *
 */
public class Board {
	private boolean[][] mines;
	public final int DIMENSION = 10;
	
	/**
	 * randomize amount of mines and spread throughout board
	 * set Board dimensions to 10x10
	 */
	public Board() {
		mines = new boolean[DIMENSION][DIMENSION];
		Random rn = new Random();
		
		for(int i=0; i<DIMENSION; i++)
		{
			int temp = rn.nextInt(100);
			if(!(mines[temp / 10][temp % 10]))
				mines[temp / 10][temp % 10] = true;
			else
				i--;
		}
	}
	
	/**
	 * returns value of space
	 * @return true if cell is a mine, false if not
	 */
	public boolean getSpace(int row, int col) {
		return mines[row][col];
	}
}
