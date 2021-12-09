import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interface 
{
	public static void hello()
	{
		System.out.println("Hello! Welcome to Texas Hold'em Poker!");
	}
	public static void goodbye()
	{
		System.out.println("Goodbye! See you soon!");
	}
	public static int enterN()
	{
		int n;
		
		System.out.print("Enter number of players: ");
		n = getInt();
		while((n < 2) || (n > 10))
		{
			System.out.println("Wrong input!");
			System.out.print("Enter number of players (from 2 to 10): ");
			n = getInt();
		}
		return n;
	}
	public static char playAgain()
	{
		char again;
		
		System.out.print("Would you like to play again? (yes/no): ");
		again = getChar();
		while((again != 'n') && (again != 'y'))
		{
			System.out.println("Wrong input!");
			System.out.print("Would you like to play again? (yes/no): ");
			again = getChar();
		}
		return again;
	}
	public static void youWon()
	{
		System.out.println("You won! Other players have no chips!");
	}
	public static void youLost()
	{
		System.out.println("You lost! You have no chips!");
	}
	public static void dealAnnounce(int dealNumber)
	{
		System.out.println("                          Deal ¹" + dealNumber);
	}
	public static void preflopBets()
	{
		System.out.println("Preflop Bets: ");
	}
	public static void displayPot()
	{
		System.out.println("Pot: ");
	}
	public static void showPot(int[] sPot)
	{
		boolean add = false;
		int temp = 0;
		
		for(int i=0; i<sPot.length; i++)
		{
			if(sPot[i] == 0)
				continue;
			if(!add)
			{
				System.out.println(sPot[i]);
				add = true;
				temp = sPot[i];
			}
			else
			{
				if(sPot[i] > temp)
				{
					System.out.println("Additional pot: ");
					System.out.println(sPot[i] - temp);
					temp = sPot[i];
				}  
			}
		}
	}
	public static void cardsOnTable()
	{
		System.out.println("Cards on the table: ");
	}
	public static void showFlop(Card[] flop)
	{
		for(int i=0; i<flop.length; i++)
			flop[i].show();
	}
	public static void flopBets()
	{
		System.out.println("Flop bets: ");
	}
	public static void showTurn(Card turn)
	{
		turn.show();
	}
	public static void turnBets()
	{
		System.out.println("Turn bets: ");
	}
	public static void showRiver(Card river)
	{
		river.show();
	}
	public static void riverBets()
	{
		System.out.println("River bets: ");
	}
	public static void showChips(int num, int chips)
	{
		if(num == 0)
			System.out.println("Player " + num + "(you): " + chips + " chips");
		else
			System.out.println("Player " + num + ": " + chips + " chips");
	}
	public static void showButton(int button)
	{
		if(button == 0)
			System.out.println("Button: Player " + button + "(you)");
		else
			System.out.println("Button: Player " + button);
	}
	public static void showBlinds(int small, int big)
	{
		if(small == 0)
			System.out.println("Small blind: Player " + small + "(you)");
		else
			System.out.println("Small blind: Player " + small);
		if(big == 0)
			System.out.println("Big blind: Player " + big + "(you)");
		else
			System.out.println("Big blind: Player " + big);
	}
	public static void showHand(int num, Card[] hand)
	{
		if(num == 0)
			System.out.println("Your hand: ");
		else
			System.out.println("Player " + num + "'s hand: ");
		hand[0].show();
		hand[1].show();
		
	}
	public static void showCombination(int num, Combination comb)
	{
		if(num == 0)
			System.out.println("Your combination: ");
		else
			System.out.println("Player " + num + "'s combination: ");
		comb.show();
	}
	public static void showFold(int num)
	{
		if(num == 0)
			System.out.println("Player " + num + "(you): fold");
		else
			System.out.println("Player " + num + ": fold");
	}
	public static void showAllIn(int num, int allInBet)
	{
		if(num == 0)
			System.out.println("Player " + num + "'s(you) bet: " + allInBet + " (all in)");
		else
			System.out.println("Player " + num + "'s bet: " + allInBet + " (all in)");
	}
	public static void showCheck(int num)
	{
		if(num == 0)
			System.out.println("Player " + num + "(you): check");
		else
			System.out.println("Player " + num + ": check");
	}
	public static void showBet(int num, int chips, int bet)
	{
		if(num == 0)
			System.out.println("Player " + num + "'s(you) bet: " + bet);
		else
			System.out.println("Player " + num + "'s bet: " + bet);
	}
	public static void showYourChips(int chips)
	{
		System.out.println("Your chips: " + chips);
	}
	public static void showdownAnnounce()
	{
		System.out.println("                          Showdown");
	}
	public static void combinationsAnnounce()
	{
		System.out.println("                        Combinations");
	}
	public static char foldCallRaise()
	{
		char choice;
		
		System.out.println("You can fold, call or raise.");
		System.out.print("Enter the first letter of one of these variants: ");
		choice = getChar();
		while((choice != 'f') && (choice != 'c') && (choice != 'r'))
		{
			System.out.println("Wrong input!");
			System.out.println("You can fold, call or raise.");
			System.out.print("Enter the first letter of one of these variants: ");
			choice = getChar();
		}
		return choice;
	}
	public static int enterRaise(int dif, int chips)
	{
		int raise;
		
		System.out.print("Enter number of chips you want to add: ");
		raise = getInt();
		while((raise <= dif) || (raise > chips))
		{
			if(raise <= (dif+1))
			{
				System.out.println("The number is too small!");
				System.out.print("Enter number of chips you want to add: ");
				raise = getInt();
			}
			else
			{
				System.out.println("The number is too big!");
				System.out.print("Enter number of chips you want to add: ");
				raise = getInt();
			}
		}
		return raise;
	}
	public static int enterRaise(int chips)
	{
		int raise;
		
		System.out.print("Enter number of chips you want to add: ");
		raise = getInt();
		while((raise < 1) || (raise > chips))
		{
			if(raise < 1)
			{
				System.out.println("The number is too small!");
				System.out.print("Enter number of chips you want to add: ");
				raise = getInt();
			}
			else
			{
				System.out.println("The number is too big!");
				System.out.print("Enter number of chips you want to add: ");
				raise = getInt();
			}
		}
		return raise;
	}
	public static char foldCall()
	{
		char choice;
		
		System.out.println("You can fold or call.");
		System.out.print("Enter the first letter of one of these variants: ");
		choice = getChar();
		while((choice != 'f') && (choice != 'c'))
		{
			System.out.println("Wrong input!");
			System.out.println("You can fold or call.");
			System.out.print("Enter the first letter of one of these variants: ");
			choice = getChar();
		}
		return choice;
	}
	public static char foldAllin()
	{
		char choice;
		
		System.out.println("You can fold or all in.");
		System.out.print("Enter the first letter of one of these variants: ");
		choice = getChar();
		while((choice != 'f') && (choice != 'a'))
		{
			System.out.println("Wrong input!");
			System.out.println("You can fold or all in.");
			System.out.print("Enter the first letter of one of these variants: ");
			choice = getChar();
		}
		return choice;
	}
	public static char checkRaise()
	{
		char choice;
		
		System.out.println("You can check or raise.");
		System.out.print("Enter the first letter of one of these variants: ");
		choice = getChar();
		while((choice != 'c') && (choice != 'r'))
		{
			System.out.println("Wrong input!");
			System.out.println("You can check or raise.");
			System.out.print("Enter the first letter of one of these variants: ");
			choice = getChar();
		}
		return choice;
	}
	public static void youCheck()
	{
		System.out.println("You check.");
	}
	public static void showOneWinner(Player temp)
	{
		if(temp.getNum() == 0)
		{
			System.out.println("You take " + temp.getPot() + " pot!");
			System.out.println("You get " + temp.getPot() + " chips!");
		}
		else
		{
			System.out.println("Player " + temp.getNum() + " takes " + temp.getPot() + " pot!");
			System.out.println("Player " + temp.getNum() + " gets " + temp.getPot() + " chips!");
		}
	}
	public static void showSomeWinners(int minPot, boolean[] winners, Player user)
	{
		boolean comma = false;
		int nWinners = 0;
		Player temp = user;
		
		for(int i=0; i<winners.length; i++)
			if(winners[i])
				nWinners++;
		
		if(nWinners == 1)
		{
			for(int i=0; i<winners.length; i++)
			{
				if(winners[i])
					break;
				temp = temp.getNext();
			}
			if(temp.getNum() == 0)
				System.out.println("You take " + minPot + " pot!");
			else
				System.out.println("Player " + temp.getNum() + " takes " + minPot + " pot!");
		}
		else
		{
			System.out.print("Players");		
			for(int i=0; i<winners.length; i++)
			{
				if(winners[i])
				{
					if(comma)
						System.out.print(",");
					System.out.print( " " + temp.getNum());
					if(temp.getNum() == 0)
						System.out.print("(you)");
					comma = true;
				}
				temp = temp.getNext();
			}
			System.out.println(" share " + minPot + " pot!");
		}
	}
	public static void playerGetsChips(Player temp, int add)
	{
		if(temp.getNum() == 0)
			System.out.println("You get " + add + " chips!");
		else
			System.out.println("Player " + temp.getNum() + " gets " + add + " chips!");
	}
	public static void playerGetsRest(Player temp, int rest)
	{
		if(temp.getNum() == 0)
			System.out.println("You get " + rest + " rest!");
		else
			System.out.println("Player " + temp.getNum() + " gets " + rest + " rest!");
	}
	public static void delimiter()
	{
		System.out.println("------------------------------------------------------------");
	}
	public static void emptyString()
	{
		System.out.println();
	}
	//-------------------------------------------------------------	
	public static String getString()
	{
	   InputStreamReader isr = new InputStreamReader(System.in);
	   BufferedReader br = new BufferedReader(isr);
	   String s = "";
	   try 
	   {
		   s = br.readLine();
	   } 
	   catch (IOException e) 
	   {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   return s;
	}
	public static char getChar()
	{
	   String s = getString();
	   return s.charAt(0);
	}
	public static int getInt()
	{
	   String s = getString();
	   return Integer.parseInt(s);
	}
}
