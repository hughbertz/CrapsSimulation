import java.util.ArrayList;

/**
 * Starting application for running multiple simulations of CrapsGame. This
 * gathers statistics (how many games were a win, and how many were a loss), and
 * prints out the results.
 * <p/>
 * 
 * You can vary the behavior of this simulation by changing the Dice object that
 * is used in it. It is set up with two Die dice, but you can replace one or
 * both of these with CrookedDie1 or CrookedDie2 dice instead.
 * <p/>
 * 
 * This application (including supporting classes) is meant to illustrate how
 * inheritance can be used to build a simple OO framework which allows easy
 * changes of behavior *without* needing to change much code.
 * <p/>
 * 
 * @author Eric Level
 * 
 */
public class CrapsSimulation
{

	/**
	 * CrapsGame represents a single game of craps, which contains a single Dice
	 * object that is rolled when game.play() is called. This returns true if
	 * the game is a win, false otherwise.
	 */
	private CrapsGame game;

	/**
	 * Field <code>numWins</code> tracks the total number of wins out of
	 * <code>numPlays</code> total plays. 
	 * totalWinToPlay ratio
	 */
	private int numberOfWins;

	/**
	 * Field <code>numPlays</code> counts the total number of individual games
	 * to play.
	 */
	private int numPlays;

	/**
	 * int array field <code>winSteps</code> tracks number of wins w after
	 * exactly k rolls: <code>winsteps[k]==w </code>, out of
	 * <code>numPlays</code> total plays.
	 */
	private int[] winForNumberOfStepsOf = new int[100];
	private ArrayList<Integer> test;

	/**
	 * int array field <code>lossSteps</code> racks number of losses l after
	 * exactly k rolls: <code>lossSteps[k]==l</code>, out of
	 * <code>numPlays</code> total plays.
	 */
	private int[] lossSteps = new int[100];

	/**
	 * Constructor TestCraps() creates a new CrapsGame for subsequent play.
	 */
	public CrapsSimulation()
	{
		// game = new CrapsGame();

		this.game = new CrapsGame(new Die(), new Die()); // two "fair" Die objects

		// we'll use the above to substitute Die subclass objects,
		// thus affecting the outcome
	
	}

	/**
	 * Instance method <code>public void play(int)</code> plays n games of
	 * craps, tracking the results of each: number of total wins out of n, and
	 * for each win, how many games end in a win/loss after exactly k plays:
	 * winSteps[k], lossSteps[k]
	 * 
	 * @param gamesToPlay
	 *            total number of games to play
	 */

	public void play(int gamesToPlay)
	{
		this.numPlays 	= gamesToPlay;
		this.numberOfWins 			= 0;
		for (int i = 0; i < this.numPlays; i++)
		{
			if(this.game.playOneGame(winForNumberOfStepsOf, lossSteps))
			{
				this.numberOfWins++;
			}
		}
	}

	/**
	 * Instance method <code>public void reportStats</code> prints out final
	 * value of number of how many games end in a win/loss after exactly k
	 * plays: winSteps[k], lossSteps[k]total wins out of n, and
	 * 
	 * It then prints out the winning percentage for all games played.
	 * 
	 */

	public void reportStats()
	{
		StdOut.println("\nNumber of (wins,losses) for games of given length follow:\n");
		for (int numSteps = 1; numSteps < winForNumberOfStepsOf.length; numSteps++)
		{
			StdOut.println("(wins,losses) ending at " + numSteps + " roll" + ((numSteps > 1) ? "s" : "") + ": (" + winForNumberOfStepsOf[numSteps] + ","
					+ lossSteps[numSteps] + ")");
		}
		
		StdOut.println("\nPlayed " + this.numPlays + " games total.");
		
		StdOut.println("Won " + this.numberOfWins + "/" + this.numPlays + "==" + 100.0 * (0.0 + numberOfWins) / this.numPlays + "%");

	}

	/**
	 * TestCraps.main():
	 * 
	 * Build a TestCraps object, play NUM_TO_PLAY games, gathering and reporting
	 * the results.
	 * 
	 * We turn off console output by CrapsGame.showOutput = false, so that the
	 * simulation runs more quickly, then turn it back on to report the results
	 */
	public static final int NUM_TO_PLAY = 1000000;

	public static void main(String[] args)
	{
		CrapsSimulation crapsSimulation = new CrapsSimulation();

		StdOut.println("Starting simulation of " + NUM_TO_PLAY + " games...");

		CrapsGame.showOutput = false; // turn off output to speed up
		// simulation

		crapsSimulation.play(NUM_TO_PLAY); // play games of craps

		StdOut.println("Done.");

		crapsSimulation.reportStats(); // report the output

	}
}