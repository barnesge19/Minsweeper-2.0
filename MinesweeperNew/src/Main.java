/**
 * Main class to run Minesweeper game
 * @author BARNESGE19
 *
 */
public class Main {
	/**
	 * Main method to create game object and run it
	 * Stores high scores in a Map collection
	 * @param args not a used parameter
	 */
	public static void main(String[] args)
	{
		boolean playGame = true;
		boolean gameStatus;
		Game MineSweeper = new Game();

		do {
			gameStatus = MineSweeper.GameLoop();
			if (!gameStatus) {
				playGame = false;
				System.out.println("Thank you for playing!");
			}
		} while (playGame);
	}
}
