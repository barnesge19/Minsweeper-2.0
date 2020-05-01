/**
 * A collection of methods to handle the game's display
 * @author BARNESGE19
 *
 */
public class Display extends Board {
	
	private boolean[][] open;
	
	//TESTING: 2D ARRAY FOR DISPLAY NUMS
	//private int[][] nums;

	/**
	 * Creates open 2D array for the board dimensions
	 * Calls Boared constructor
	 */
	public Display() {
		super();
		open = new boolean[DIMENSION][DIMENSION];
		//automatically fills array as false
	}
	 
	/**
	 * Makes the selected space visible, opens surrounding spaces as needed
	 * May need to call supporting methods.
	 * Will need to change the open 2d array
	 * @param coordinates, next guess location
	 */
	protected void openSpace(int row, int col) {
		open[row][col] = true;


		if(spaceNumber(row,col)==0)
		{
			openSurroundingSpaces(row,col);
		}
		
		//Determine what number to display
		//Determine whether to open other blocks or not
	}
	
	/**
	 * This method opens all 8 surrounding blocks of a block that has 0 mines nearby
	 * @param row an integer for the row number of the original block
	 * @param col an integer for the column number of the original block
	 */
	private void openSurroundingSpaces(int row, int col)
	{
		for(int r = (row+1); r<(row+2); r++)
		{
			for(int c=(col -1); c<(col+2); c++)
			{
				if(!(r==row) && !(c==col))
				{
					try
					{
						if(!(open[r][c]))
						{
							openSpace(r,c);
						}
					}
					catch (IllegalArgumentException e)
					{
						//If the number being accessed is off the board (out of bounds), then skip that row number
						continue;
					}
				}
			}
		}
	}
	
	/**
	 * Uses the coordinates and the 2d board to find the correct number of bombs
	 * in contact with each location.
	 * This would need to be displayed at the coordinates
	 * @param coordinates, any space/every space in the 2d array
	 * @return the number of bombs it is touching
	 */
	public int spaceNumber(int row, int col) {
		int nearMines = 0;
		
		for(int r = (row+1); r<(row+2); r++)
		{
			for(int c=(col -1); c<(col+2); c++)
			{
				if(!(r==row) && !(c==col))
				{
					try
					{
						if(getSpace(r,c))
						{
							nearMines++;
						}
					}
					catch (IllegalArgumentException e)
					{
						//If the number being accessed is off the board (out of bounds), then skip that row number
						continue;
					}
				}
			}
		}
		
		return nearMines;
	}
	
	/**
	 * Gets whether the block is open or hidden
	 * @param row integer for the row num you want to check
	 * @param col integer for the column num you want to check
	 * @return return true if the block is open, false if hidden
	 */
	public boolean getOpen(int row, int col) {
		return open[row][col];
	}
	
	/**
	 * Sets whether the block is open or hidden
	 * @param row integer for the row num you want to open
	 * @param col integer for the column num you want to open
	 */
	public void setOpen(int row, int col)
	{
		open[row][col]=true;
	}
	
	/**
	 * Throws exceptions for user input of row and column
	 * @param row integer that user inputs, represents row #
	 * @param col integer that user inputs, represents column #
	 * @throws Exception if number is out of bounds or if space is taken
	 */
	public void play(int row, int col) throws Exception {
		if (row < 0 || row > 9 || col < 0 || col > 9) {
			throw new Exception("Sorry, invalid row/column number. Try again.");
		}
		
		else if (getOpen(row, col)) {
			throw new Exception("That space is already open. Try another one.");
		}
		
		openSpace(row, col);
	}
	
	/**
	 * Searches the board's 2D array and display's 2D array to see if they match
	 * If they all match, the user has uncovered all of the free squares and wins
	 * If they don't match, the user has not yet uncovered all free squares and hasn't won yet
	 * @return boolean true if win, false if no win
	 */
	public boolean win(final Display display, final Board board)
	{
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if (display.open[r][c] !=  board.mines[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
}