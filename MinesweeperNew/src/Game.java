import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * A collection of methods to run and play the game, handling user I/O
 * @author BARNESGE19
 *
 */
public class Game {
	private Display userGame;
	private boolean status;
	private boolean sameUser;
	HashMap<String, Integer> userScores;

	public Game()
	{
		userGame = new Display();
		status = false;
		sameUser = true;
		userScores = new HashMap<String, Integer>();
	}

	/**
	 * Runs the game loop for minesweeper once per user input
	 * handles user I/O for the spaces they pick
	 * uses display class to determine what numbers and spaces to output
	 * uses board class to know the orientation of the board
	 * terminates if the user runs into a mine and loses
	 * terminates when the user uncovers all squares and wins
	 * returns boolean, boolean is false if user wants to quit game
	 */
	public boolean GameLoop()
	{
		Scanner scn = new Scanner(System.in);
		System.out.println("Welcome to MineSweeper!");
		System.out.println("Please enter name of user: ");
		String userName = scn.nextLine();
		int wins = 0;

		do {
			do {
				scn = new Scanner(System.in);
				System.out.println(userName + ", please enter the row number: ");
				boolean valInput = false;
				String line = scn.nextLine();
				
				// Checks if valid input, if invalid input then this will loop until
				// input is valid
				while (!valInput) {
					if (line.length() != 1 || !Character.isDigit(line.charAt(0))) {
						System.out.println("Invalid entry \nEnter row number: ");
						line = scn.nextLine();
					}
					else {
						valInput = true;
					}
				}
				//recast line to Integer
				int rowGuess = Integer.parseInt(line);

				System.out.println(userName + ", please enter the column number: ");
				boolean valInput2 = false;
				String line2 = scn.nextLine();
				
				// Checks if valid input, if invalid input then this will loop until
				// input is valid
				while (!valInput2) {
					if (line2.length() != 1 || !Character.isDigit(line2.charAt(0))) {
						System.out.println("Invalid entry \nEnter column number: ");
						line2 = scn.nextLine();
					}

					else {
						valInput2 = true;
					}
				}
				int colGuess = Integer.parseInt(line2);

				// calls play method and catches exceptions from there
				try {
					play(rowGuess, colGuess);
				} 

				catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}

				// if win is true, status is true, if win is false, status is false
				status = win();
				
				// if user loses game, then it will stop asking for their
				// row/column guesses
				if(lose(rowGuess,colGuess)) {
					break;
				}

			} while(!status);

			// If user wins, status is true, sorted List is printed
			if(status) {
				System.out.println("Congratulations, " + userName + "!!! You won!");
				wins++;
			}

			// If user loses, sorted List is printed
			else {
				System.out.println("You lost, " + userName + "! :(");
			}

			userScores.put(userName, wins);
			printList();
			// Choice of what user wants to do after game ends
			System.out.println("Would you like to keep playing, " + userName + "? Y for yes, N to change users, Q to"
					+ " quit game, and C to clear the leaderboard.");
			System.out.println();

			// loops until user answers a correct input
			boolean validChoice = false;
			while (!validChoice) {
				String choice = scn.next();

				// If user puts N, then it will switch to new user
				if (choice.equals("N") || choice.equals("n")) {
					validChoice = true;
					sameUser = false;
				}

				// If user puts Y, they will play as the same user
				else if (choice.equals("Y") || choice.equals("y")) {
					validChoice = true;
					userGame = new Display();
					continue;
				}

				// If user puts C, then the leaderboard is cleared
				else if (choice.equals("C") || choice.equals("c")) {
					validChoice = true;
					clearLeaderboard(userScores);
				}

				// If user puts Q, then the entire game loop ends
				else if (choice.equals("Q") || choice.equals("q")) {
					validChoice = true;
					scn.close();
					return false;
				}

				else {
					System.out.println("Not a valid option.");
					System.out.println("Y for yes, N to change users, Q to "
							+ "quit game, and C to clear the leaderboard.");
				}
			}
		} while(sameUser);
		scn.close();
		return true;
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

	/**
	 * user loses game if they hit mine
	 * @param r = row
	 * @param c = column
	 * @return value of space; true if mine, false if not
	 */
	public boolean lose(int r, int c) {
		return userGame.getSpace(r, c);
	}

	/**
	 * Clears hashmap for when user wants to clear leaderboard
	 * @param scores is taken in
	 * @return empty hashmap
	 */
	public HashMap<String, Integer> clearLeaderboard(HashMap<String, Integer> scores) {
		scores.clear();
		return scores;
	}
	/**
	 * sorts userScores in descending order
	 * @param scores is HashMap taken in to be sorted
	 * @return mapList, a sorted List that has the elements of HashMap
	 */
	public List<Map.Entry<String, Integer>> sortScores(HashMap<String, Integer> scores) {
		// create arraylist that takes in elements of the hashmap
		// use collections sort method to sort the HashMap, comparing two elements
		List<Map.Entry<String, Integer>> mapList = new ArrayList<Map.Entry<String, Integer>>(scores.entrySet());
		Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			// method within sort method to return the greater element first, so that
			// list is sorted in descending order
			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
				return entry2.getValue().compareTo(entry1.getValue());
			}
		});
		return mapList;
	}

	/**
	 * Calls sort method to sort userScores, then prints userScores in descending order
	 */
	private void printList() {
		List<Map.Entry<String, Integer>> scoresList = sortScores(userScores);
		System.out.println("Leaderboard:");
		for (Map.Entry<String, Integer> entry : scoresList) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " wins.");
		}
	}
	
	/**
	 * Runs the game loop until the game is asked to stop
	 */
	public void playGame()
	{
		boolean keepPlaying = true;
		boolean gameStatus;

		do {
			gameStatus = GameLoop();
			if (!gameStatus) {
				keepPlaying = false;
				System.out.println("Thank you for playing!");
			}
		} while (keepPlaying);
	}
}
