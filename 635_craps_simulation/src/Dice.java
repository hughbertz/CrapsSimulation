/**
 * Dice represents a single pair of rollable Die objects, randomly generating
 * sums of their two values
 * 
 * This is a Javadoc comment: add more to your finished class below
 * 
 * @author eric
 *
 */
public class Dice
{
	// Instance fields (variables) may be declared anywhere in class body
	// Convention: put at top

	private int lastRoll;
	private Die firstDie;
	private Die secondDie;

	// Constructors (object initializers) also can be declared anywhere
	// Convention: after instance fields/variables

	public Dice()
	{
		// initialize instance variables die1 and die2 by
		// creating a new instance of each

		this.firstDie  = new Die();
		this.secondDie = new Die();
		this.roll();
	}

	public Dice(Die firstDie, Die secondDie) // overloaded constructor
	{
		this.firstDie  = firstDie;
		this.secondDie = secondDie;
	}

	// Instance methods can also be declared anywhere
	// Convention: after constructors

	public int getLastRoll()
	{
		return this.lastRoll;
	}

	public void roll()
	{
		// roll each of die1, die2, sum their last rolls,
		// then set Dice.lastRoll to this value

		firstDie.roll();
		secondDie.roll();
		this.lastRoll = firstDie.getLastRoll() + secondDie.getLastRoll();

	}

	// the following method converts the internals of
	// this Dice object, and returns a descriptive String:
	//
	// Roll of 7 => 4 + 3
	//

	public String toString()
	{
		return "Roll of " + getLastRoll() + ": " + firstDie.getLastRoll() + " + " + secondDie.getLastRoll();

	}

	// static methods can go anywhere - but at end is standard

	public static final int NUMBER_OF_TRIALS = 360;

	public static void main(String[] args)
	{
		Dice dice1 = new Dice();
		int snakeEyesCount = 0;

		for (int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			dice1.roll();
			StdOut.println(dice1);
			
			if (dice1.getLastRoll() == 2)
			{
				snakeEyesCount++;
			}
				
		}

		StdOut.println("Actual count: " + snakeEyesCount);
		StdOut.println("Expected count: " + (NUMBER_OF_TRIALS / 36.0));
	}
}
