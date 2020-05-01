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
		Game MineSweeper = new Game();
		MineSweeper.GameLoop();
		System.out.println("test msg");
	}
}
