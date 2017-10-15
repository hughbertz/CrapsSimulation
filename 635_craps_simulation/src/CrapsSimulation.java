import java.util.ArrayList;

/**
 * Run Multiple Simulations of CrapsGame (class)
 * Stats: GamesWon, GamesLost (in multiple Simulations)
 * 
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
	//	 * we still dont really get what this is
	private int[] winForNumberOfStepsOf = new int[100];
	//private ArrayList<Integer> test;

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
		this.winForNumberOfStepsOf = new int[100];
	}

	
	
	public CrapsGame getGame()
	{
		return this.game;
	}



	public void setGame(CrapsGame game)
	{
		this.game = game;
	}

	public void setGame()
	{
		this.game = new CrapsGame();
	}


	public int getNumberOfWins()
	{
		return this.numberOfWins;
	}



	public void setNumberOfWins(int numberOfWins)
	{
		this.numberOfWins = numberOfWins;
	}



	public int getNumPlays()
	{
		return this.numPlays;
	}



	public void setNumPlays(int numPlays)
	{
		this.numPlays = numPlays;
	}



	public int[] getWinForNumberOfStepsOf()
	{
		return this.winForNumberOfStepsOf;
	}



	public void setWinForNumberOfStepsOf(int[] winForNumberOfStepsOf)
	{
		this.winForNumberOfStepsOf = winForNumberOfStepsOf;
	}



	public int[] getLossSteps()
	{
		return this.lossSteps;
	}

	private int winsAtRollIndex(int numSteps)
	{
		return getWinForNumberOfStepsOf()[numSteps];
	}

	public void setLossSteps(int[] lossSteps)
	{
		this.lossSteps = lossSteps;
	}

	private boolean playOneGame()
	{
		return getGame().playOneGame(getWinForNumberOfStepsOf(), getLossSteps());
	}

	private void incrementWins()
	{
		int wins = getNumberOfWins()+1;
		setNumberOfWins(wins);
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
		setNumPlays(gamesToPlay);
		setNumberOfWins(0);
		for (int i = 0; i < getNumPlays(); i++)
		{
			if(playOneGame())
			{
				incrementWins();
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
		for (int rollIndex = 1; rollIndex < getWinForNumberOfStepsOf().length; rollIndex++)
		{
			StdOut.println("(wins,losses) in " + rollIndex + " roll" + ((rollIndex > 1) ? "s" : "") + ": (" + winsAtRollIndex(rollIndex) + ","
					+ lossSteps[rollIndex] + ")");
		}
		
		StdOut.println("\nPlayed " + getNumPlays() + " games total.");
		
		StdOut.println("Won " + getNumberOfWins() + "/" + getNumPlays() + "==" + 100.0 * (0.0 + getNumberOfWins()) / getNumPlays() + "%");
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
	public static final int NUMBER_OF_SIMULATIONS = 1000000;

	public static void main(String[] args)
	{
		CrapsSimulation crapsSimulation = new CrapsSimulation();

		StdOut.println("Starting simulation of " + NUMBER_OF_SIMULATIONS + " games...");

		CrapsGame.setShowOutput(false); // turn off output to speed up simulation

		crapsSimulation.play(NUMBER_OF_SIMULATIONS); // play games of craps

		StdOut.println("Done.");

		crapsSimulation.reportStats(); // report the output

	}
}