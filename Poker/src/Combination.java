public class Combination 
{
	private Card[] cards;
	private String strType;
	private int numType;
	
	/*
	 * numType - strType
	 * -----------------
	 * 0 - Highest Card
	 * 1 - Pair
	 * 2 - Two Pairs
	 * 3 - Three
	 * 4 - Straight
	 * 5 - Flush
	 * 6 - Full House
	 * 7 - Four
	 * 8 - Straight Flush
	 */
	
	//constructor
	public Combination(Card[] five)
	{
		cards = new Card[5];
		for(int i=0; i<5; i++)
			cards[i] = five[i];
		sortCards();
		combIdent();
	}

	//methods
	public int getNumType()
	{
		return numType;
	}
	public Card getCard(int n)
	{
		return cards[n];
	}
	//-------------------------------------------------------------
	public void show()
	{
		System.out.println(strType);
		for(int i=0; i<cards.length; i++)
			cards[i].show();
	}
	//-------------------------------------------------------------
	public void sortCards()
	{
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<(4-i); j++)
			{
				if(cards[j].getNumber() > cards[j+1].getNumber())
				{
					Card temp = cards[j];
					cards[j] = cards[j+1];
					cards[j+1] = temp;
				}
			}
		}
	}
	public void combIdent()
	{
		if(isAPair())
		{
			if(isATwoPairs())
			{
				if(isAFullHouse())
				{
					strType = "Full House";
					numType = 6;
					return;
				}
				strType = "Two Pairs";
				numType = 2;
				return;
			}
			else if(isAThree())
			{
				if(isAFour())
				{
					strType = "Four";
					numType = 7;
					return;
				}
				strType = "Three";
				numType = 3;
				return;
			}
			strType = "Pair";
			numType = 1;
			return;
		}
		else if(isAStraight())
		{
			if(isAFlush())
			{
				strType = "Straight Flush";
				numType = 8;
				return;
			}
			strType = "Straight";
			numType = 4;
			return;
		}
		else if(isAFlush())
		{
			strType = "Flush";
			numType = 5;
			return;
		}
		strType = "Highest Card";
		numType = 0;
	}
	//-------------------------------------------------------------
	public boolean isAPair()
	{
		boolean result = false;
		
		for(int i=0; i<4; i++)
			if(cards[i].getNumber()/4 == cards[i+1].getNumber()/4)
				result = true;
		return result;
	}
	public boolean isATwoPairs()
	{
		boolean result = false;
		
		for(int i=0; i<2; i++)
		{
			if(cards[i].getNumber()/4 == cards[i+1].getNumber()/4)
			{
				for(int j=i+2; j<4; j++)
					if(cards[j].getNumber()/4 == cards[j+1].getNumber()/4)
						result = true;
			}
		}
		return result;
	}
	public boolean isAThree()
	{
		boolean result = false;
		
		for(int i=0; i<3; i++)
			if(cards[i].getNumber()/4 == cards[i+1].getNumber()/4 &&
				cards[i+1].getNumber()/4 == cards[i+2].getNumber()/4)
					result = true;
		return result;
	}
	public boolean isAStraight()
	{
		boolean result = true;
		
		if(cards[0].getNumber()/4 < 9)
		{
			for(int i=0; i<4; i++)
				if(cards[i+1].getNumber()/4 != cards[i].getNumber()/4+1)
					result = false;
			if(cards[0].getNumber()/4 == 0 && cards[1].getNumber()/4 == 1 && 
				cards[2].getNumber()/4 == 2 && cards[3].getNumber()/4 == 3 && 
				 cards[4].getNumber()/4 == 12)
					 result = true;
		}
		return result;
	}
	public boolean isAFlush()
	{
		boolean result = true;
		
		for(int i=0; i<4; i++)
			if(cards[i].getNumber()%4 != cards[i+1].getNumber()%4)
				result = false;
		return result;
	}
	public boolean isAFullHouse()
	{
		boolean result = false;
		
		if((cards[0].getNumber()/4 == cards[1].getNumber()/4) &&
			(cards[1].getNumber()/4 == cards[2].getNumber()/4))
				result = true;
		else if((cards[2].getNumber()/4 == cards[3].getNumber()/4) &&
			(cards[3].getNumber()/4 == cards[4].getNumber()/4))
				result = true;
		return result;
	}
	public boolean isAFour()
	{
		boolean result = false;
		
		if((cards[0].getNumber()/4 == cards[1].getNumber()/4) &&
			(cards[1].getNumber()/4 == cards[2].getNumber()/4) &&
			(cards[2].getNumber()/4 == cards[3].getNumber()/4))
				result = true;
		else if((cards[1].getNumber()/4 == cards[2].getNumber()/4) &&
			(cards[2].getNumber()/4 == cards[3].getNumber()/4) &&
			(cards[3].getNumber()/4 == cards[4].getNumber()/4))
				result = true;
		return result;
	}
}
