// foo again
public class CrapsGame
{
	//INSTANCE VARIABLES
	private int point;
	private Dice dice;
	private static boolean showOutput;

	//CONSTRUCTORS
	public CrapsGame()
	{
		if(!isShowOutput())
		{
			setShowOutput(true);
		}		
		setDice();
	}

	public CrapsGame(Die firstDie, Die secondDie)
	{
		if(!isShowOutput())
		{
			setShowOutput(true);
		}		
		setDice(firstDie, secondDie);
	}
	
	//SETTERS
	public void  setPoint(int point)					{this.point = point;}
	private void setDice(Die firstDie, Die secondDie)	{this.dice = new Dice(firstDie, secondDie);} 
	private void setDice()								{this.dice = new Dice();}
	public static void setShowOutput(boolean showOutput){CrapsGame.showOutput = showOutput;} 
	
	//GETTERS
	public int getPoint()								{return this.point;}
	public Dice getDice()								{return this.dice;	}
	public static boolean isShowOutput()				{return showOutput;	}
	
	//CRAPS_GAME DICE OBJECT METHODS ... TODO: create interface if used in multiple classes
	private int getLastRoll()
	{
		return getDice().getLastRoll();
	}

	private void roll()
	{
		getDice().roll();
	}
	//END CRAPS_GAME DICE OBJECT METHODS 	
	
	//METHODS == CRAPS_GAME_FUNCTIONS	
	private boolean lossForPlayer(int[] losses, int rollCount)
	{
		println("Loss for player with " + getPoint());
		losses[rollCount] = losses[rollCount] + 1; // number of losses with rollCount rolls
		return false;
	}

	private void doFirstRoll()
	{
		int lastRollValue;
		roll();
		lastRollValue = getLastRoll();
		setPoint(lastRollValue);
		println("First roll is: " + lastRollValue);
	}

	private boolean winForPlayer(int[] wins, int rollCount)
	{
		println("Win for player with " + getPoint());
		wins[rollCount] = wins[rollCount] + 1; // number of wins with exactly #rollCount rolls
		return true;
	}
 	
	public static void println(String str)
	{
		if (isShowOutput()) 
		{
			StdOut.println(str);	
		}			
	}
	
	public boolean playOneGame(int[] wins, int[] losses)
	{
		int rollCount  = 1; 
		boolean result = false;
		doFirstRoll();
	
		if (getPoint() == 7 || getPoint() == 11)
		{
			return winForPlayer(wins, rollCount);
		}
		else if (getPoint() == 2 || getPoint() == 3 || getPoint() == 12)
		{
			return lossForPlayer(losses, rollCount);
		}
		else 
		{
			println("Point is: " + getPoint());
			
			int lastRollValue  = 0;
			while (lastRollValue  != 7 && lastRollValue  != getPoint())
			{
				roll();
				rollCount++;
				lastRollValue  = getLastRoll();
				println("Next roll is: " + lastRollValue );
			}

			if(lastRollValue == 7)
			{
				// loss: record losses and return false
				println("You lose throwing a 7.");
				losses[rollCount]++;
				//result = false;
			}
			else if (lastRollValue  == getPoint())
			{
				// win: record wins and return false
				println("You win by throwing your point " + lastRollValue );
				wins[rollCount]++;
				result = true;
			}
		}

		return result;
	}


}
