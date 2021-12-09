public class Player 
{
	private int num;
	private int chips;
	private int bet;
	private int allInBet;
	private Card[] hand;
	private boolean fold;
	private boolean allIn;
	private Combination comb;
	private Player next;
	private int pot;
	
	//constructor
	public Player(int number)
	{
		num = number;
		chips = 1000;
		hand = new Card[2];
	}
	
	//methods
	public void setNext(Player temp)
	{
		next = temp;
	}
	public void setBet(int newBet)
	{
		bet = newBet;
	}
	public void setFirstCard(Card card)
	{
		hand[0] = card;
	}
	public void setSecondCard(Card card)
	{
		hand[1] = card;
	}
	public void setSmallBlind()
	{
		if(chips < 10)
		{
			bet = chips;
			chips = 0;
			allIn = true;
			allInBet = bet;
		}
		else
		{
			bet = 10;
			chips -= bet;
		}
	}
	public void setBigBlind()
	{
		if(chips < 20)
		{
			bet = chips;
			chips = 0;
			allIn = true;
			allInBet = bet;
		}
		else
		{
			bet = 20;
			chips -= bet;
		}
	}
	public void setComb(Combination c)
	{
		comb = c;
	}
	public void setFold(boolean f)
	{
		fold = f;
	}
	public void setAllIn(boolean a)
	{
		allIn = a;
	}
	public void setPot(int p)
	{
		pot = p;
	}
	//-------------------------------------------------------------
	public Player getNext()
	{
		return next;
	}
	public int getNum()
	{
		return num;
	}
	public int getChips()
	{
		return chips;
	}
	public int getPot()
	{
		return pot;
	}
	public int getBet()
	{
		return bet;
	}
	public boolean getFold()
	{
		return fold;
	}
	public boolean getAllIn()
	{
		return allIn;
	}
	public Card getFirstCard()
	{
		return hand[0];
	}
	public Card getSecondCard()
	{
		return hand[1];
	}
	public Combination getComb()
	{
		return comb;
	}
	//-------------------------------------------------------------
	public void showChips()
	{
		Interface.showChips(num, chips);
	}
	public void showHand()
	{
		if(fold)
			Interface.showFold(num);
		else
			Interface.showHand(num, hand);
	}
	public void showBet()
	{
		if(fold)
			Interface.showFold(num);
		else if(allIn)
			Interface.showAllIn(num, allInBet);
		else if(bet == 0)
			Interface.showCheck(num);
		else 
			Interface.showBet(num, chips, bet);
	}
	public void showCombination()
	{
		if(fold)
			Interface.showFold(num);
		else
			Interface.showCombination(num, comb);
	}
	//-------------------------------------------------------------
	public void addPot(int add)
	{
		pot += add;
	}
	public void addChips(int add)
	{
		chips += add;
	}
	public int makeYourBet(int currentBet)
	{
		char choice;
		int raise;
		int dif;
		
		Interface.showYourChips(chips);
		if(bet < currentBet)
		{
			if((bet + chips) > currentBet)
			{
				choice = Interface.foldCallRaise();
				switch(choice)
				{
					case 'f':
						fold = true;
						pot = 0;
						break;
					case 'c':
						chips -= (currentBet - bet);
						bet = currentBet;
						break;
					case 'r':
						dif = currentBet - bet;
						raise = Interface.enterRaise(dif, chips);
						chips -= raise;
						bet += raise;
						if(chips == 0)
						{
							allIn = true;
							allInBet = bet;
						}					
						break;				
				}
			}
			else if((bet + chips) == currentBet)
			{
				choice = Interface.foldCall();
				switch(choice)
				{
					case 'f':
						fold = true;
						pot = 0;
						break;
					case 'c':
						chips = 0;
						allIn = true;
						bet = currentBet;
						allInBet = bet;
						break;
				}
			}
			else
			{
				choice = Interface.foldAllin();
				switch(choice)
				{
					case 'f':
						fold = true;
						pot = 0;
						break;
					case 'a':
						allIn = true;
						bet += chips;
						chips = 0;
						allInBet = bet;
						break;
				}
			}
		}
		else
		{
			if(chips != 0)
			{
				choice = Interface.checkRaise();
				switch(choice)
				{
					case 'c':
						break;
					case 'r':
						raise = Interface.enterRaise(chips);
						chips -= raise;
						bet += raise;
						if(chips == 0)
						{
							allIn = true;
							allInBet = bet;
						}
						break;
				}
			}
			else
			{
				Interface.youCheck();
			}
		}
		return bet;
	}
	public int randomBet(int currentBet)
	{
		int choice;
		int raise;
		int dif;
		
		if(bet < currentBet)
		{
			if((bet+chips) <= currentBet)
			{
				choice = (int)(Math.random()*2);
				if(choice == 0)
				{
					fold = true;
					pot = 0;
				}
				else
				{
					bet += chips;
					chips = 0;
					allIn = true;
					allInBet = bet;
				}
			}
			else
			{
				choice = (int)(Math.random()*3);
				if(choice == 0)
				{
					fold = true;
					pot = 0;
				}
				else if(choice == 1)
				{
					chips -= (currentBet - bet);
					bet = currentBet;
					if(chips == 0)
					{
						allIn = true;
						allInBet = bet;
					}
				}
				else
				{
					dif = currentBet - bet;
					raise = (int)(Math.random()*(chips-dif))+dif+1;
					chips -= raise;
					bet += raise;
					if(chips == 0)
					{
						allIn = true;
						allInBet = bet;
					}
				}
			}
		}
		else if(chips != 0)
		{
			choice = (int)(Math.random()*2);
			if(choice == 0)
			{
				raise = (int)(Math.random()*chips)+1;
				chips -= raise;
				bet += raise;
				if(chips == 0)
				{
					allIn = true;
					allInBet = bet;
				}
			}
		}
		return bet;
	}
}
