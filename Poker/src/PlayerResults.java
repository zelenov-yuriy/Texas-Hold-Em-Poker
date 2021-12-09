public class PlayerResults 
{
	public static void setAllCombinations(Card[] allCards, Combination[] variants)
	{
		Card[][] five = new Card[21][5];
		
		int l = 0;
		for(int i=0; i<6; i++)
		{
			for(int j=i+1; j<7; j++)
			{
				int m = 0;
				for(int k=0; k<7; k++)
				{
					if((k != i) && (k != j))
					{
						five[l][m] = allCards[k];
						m++;
					}
				}
				l++;
			}
		}
		for(int i=0; i<21; i++)
			variants[i] = new Combination(five[i]);
	}
	public static Combination determineBest(Combination[] variants)
	{
		Combination best;
		
		for(int i=0; i<20; i++)
		{
			for(int j=0; j<(20-i); j++)
			{
				if(variants[j].getNumType() < variants[j+1].getNumType())
				{
					Combination temp = variants[j];
					variants[j] = variants[j+1];
					variants[j+1] = temp;
				}
			}
		}
		if(variants[0].getNumType() > variants[1].getNumType())
			best = variants[0];
		else
			best = bestOf(variants);
		return best;
	}
	public static Combination bestOf(Combination[] variants)
	{
		Combination best = variants[0];
		int numType = variants[0].getNumType();
		
		switch(numType)
		{
			case 0:
				best = bestOfHighestCard(variants);
				break;
			case 1:
				best = bestOfPair(variants);
				break;
			case 2:
				best = bestOfTwoPairs(variants);
				break;				
			case 3:
				best = bestOfThree(variants);
				break;
			case 4:
				best = bestOfStraight(variants);
				break;
			case 5:
				best = bestOfFlush(variants);
				break;
			case 6:
				best = bestOfFullHouse(variants);
				break;
			case 7:
				best = bestOfFour(variants);
				break;
			case 8:
				best = bestOfStraightFlush(variants);
				break;
		}			
		return best;
	}
	public static Combination bestOfHighestCard(Combination[] variants)
	{
		Combination best = variants[0];
		int rank1;
		int rank2;
		
		for(int i=1; i<21; i++)
		{
			rank1 = best.getCard(4).getNumber()/4;
			rank2 = variants[i].getCard(4).getNumber()/4;
			if(rank2 > rank1)
				best = variants[i];
			else if(rank2 == rank1)
			{
				rank1 = best.getCard(3).getNumber()/4;
				rank2 = variants[i].getCard(3).getNumber()/4;
				if(rank2 > rank1)
					best = variants[i];
				else if(rank2 == rank1)
				{
					rank1 = best.getCard(2).getNumber()/4;
					rank2 = variants[i].getCard(2).getNumber()/4;
					if(rank2 > rank1)
						best = variants[i];
					else if(rank2 == rank1)
					{
						rank1 = best.getCard(1).getNumber()/4;
						rank2 = variants[i].getCard(1).getNumber()/4;
						if(rank2 > rank1)
							best = variants[i];
						else if(rank2 == rank1)
						{
							rank1 = best.getCard(0).getNumber()/4;
							rank2 = variants[i].getCard(0).getNumber()/4;
							if(rank2 > rank1)
								best = variants[i];
						}
					}
				}
			}
		}
		return best;
	}
	public static Combination bestOfPair(Combination[] variants)
	{
		int i = 0;
		Combination best = variants[i++];
		int h, rank;
		int rankP1 = 0;
		int rankP2 = 0;						// ranks of pair
		int[] rankH1 = new int[3];
		int[] rankH2 = new int[3];			// ranks of highest cards
		
		while(variants[i].getNumType() == 1)
		{
			h = 0;
			for(int j=0; j<5; j++)
			{
				rank = best.getCard(j).getNumber()/4;
				if(j < 4)
				{
					if(best.getCard(j+1).getNumber()/4 == rank)
					{
						rankP1 = rank;
						j++;
					}
					else
						rankH1[h++] = rank;
				}
				else
					rankH1[h] = rank;							
			}
			h = 0;
			for(int j=0; j<5; j++)
			{
				rank = variants[i].getCard(j).getNumber()/4;
				if(j < 4)
				{
					if(variants[i].getCard(j+1).getNumber()/4 == rank)
					{
						rankP2 = rank;
						j++;
					}
					else
						rankH2[h++] = rank;
				}
				else
					rankH2[h] = rank;
			}
			if(rankP2 > rankP1)
				best = variants[i];
			else if(rankP2 == rankP1)
			{
				if(rankH2[2] > rankH1[2])
					best = variants[i];
				else if(rankH2[2] == rankH1[2])
				{
					if(rankH2[1] > rankH1[1])
						best = variants[i];
					else if(rankH1[1] == rankH1[1])
					{
						if(rankH2[0] > rankH1[0])
							best = variants[i];
					}
				}
			}
			i++;
		}
		return best;
	}
	public static Combination bestOfTwoPairs(Combination[] variants)
	{
		int i = 0;
		Combination best = variants[i++];
		int p, rank;
		int[] rankP1 = new int[2];
		int[] rankP2 = new int[2];			// ranks of pairs
		int rankH1 = 0;
		int rankH2 = 0;						// ranks of highest card
		
		while(variants[i].getNumType() == 2)
		{
			p = 0;
			for(int j=0; j<5; j++)
			{
				rank = best.getCard(j).getNumber()/4;
				if(j < 4)
				{
					if(best.getCard(j+1).getNumber()/4 == rank)
					{
						rankP1[p++] = rank;
						j++;
					}
					else
						rankH1 = rank;
				}
				else
					rankH1 = rank;	
			}
			p = 0;
			for(int j=0; j<5; j++)
			{
				rank = variants[i].getCard(j).getNumber()/4;
				if(j < 4)
				{
					if(variants[i].getCard(j+1).getNumber()/4 == rank)
					{
						rankP2[p++] = rank;
						j++;
					}
					else
						rankH2 = rank;
				}
				else
					rankH2 = rank;	
			}
			if(rankP2[1] > rankP1[1])
				best = variants[i];
			else if(rankP2[1] == rankP1[1])
			{
				if(rankP2[0] > rankP1[0])
					best = variants[i];
				else if(rankP2[0] == rankP1[0])
				{
					if(rankH2 > rankH1)
						best = variants[i];
				}
			}
			i++;
		}
		return best;
	}
	public static Combination bestOfThree(Combination[] variants)
	{
		int i = 0;
		Combination best = variants[i++];
		int h, rank;
		int rankT1 = 0;
		int rankT2 = 0;						// ranks of three
		int[] rankH1 = new int[2];
		int[] rankH2 = new int[2];			// ranks of highest cards
		
		while(variants[i].getNumType() == 3)
		{
			h = 0;
			for(int j=0; j<5; j++)
			{
				rank = best.getCard(j).getNumber()/4;
				if(j < 3)
				{
					if(best.getCard(j+1).getNumber()/4 == rank)
					{
						rankT1 = rank;
						j += 2;
					}
					else
						rankH1[h++] = rank;
				}
				else
					rankH1[h++] = rank;							
			}
			h = 0;
			for(int j=0; j<5; j++)
			{
				rank = variants[i].getCard(j).getNumber()/4;
				if(j < 3)
				{
					if(variants[i].getCard(j+1).getNumber()/4 == rank)
					{
						rankT2 = rank;
						j += 2;
					}
					else
						rankH2[h++] = rank;
				}
				else
					rankH2[h++] = rank;
			}
			if(rankT2 > rankT1)
				best = variants[i];
			else if(rankT2 == rankT1)
			{
				if(rankH2[1] > rankH1[1])
					best = variants[i];
				else if(rankH2[1] == rankH1[1])
				{
					if(rankH2[0] > rankH1[0])
						best = variants[i];
				}
			}
			i++;
		}
		return best;
	}
	public static Combination bestOfStraight(Combination[] variants)
	{
		int i = 0;
		int rank1;
		int rank2;
		Combination best = variants[i++];
		
		while(variants[i].getNumType() == 4)
		{
			if(best.getCard(0).getNumber()/4 == 0 && 
				best.getCard(4).getNumber()/4 == 12)
					rank1 = -1;
			else
					rank1 = best.getCard(0).getNumber()/4;
			if(variants[i].getCard(0).getNumber()/4 == 0 && 
				variants[i].getCard(4).getNumber()/4 == 12)
					rank2 = -1;
			else
					rank2 = variants[i].getCard(0).getNumber()/4;
			if(rank2 > rank1)
				best = variants[i];
			i++;
		}
		return best;
	}
	public static Combination bestOfFlush(Combination[] variants)
	{
		int i = 0;
		int[] rank1 = new int[5];
		int[] rank2 = new int[5];
		Combination best = variants[i++];
		
		while(variants[i].getNumType() == 5)
		{
			for(int j=0; j<5; j++)
			{
				rank1[j] = best.getCard(j).getNumber()/4;
				rank2[j] = variants[i].getCard(j).getNumber()/4;
			}
			if(rank2[4] > rank1[4])
				best = variants[i];
			else if(rank2[4] == rank1[4])
			{
				if(rank2[3] > rank1[3])
					best = variants[i];
				else if(rank2[3] == rank1[3])
				{
					if(rank2[2] > rank1[2])
						best = variants[i];
					else if(rank2[2] == rank1[2])
					{
						if(rank2[1] > rank1[1])
							best = variants[i];
						else if(rank2[1] == rank1[1])
						{
							if(rank2[0] > rank1[0])
								best = variants[i];
						}
					}
				}
			}
			i++;
		}
		return best;
	}
	public static Combination bestOfFullHouse(Combination[] variants)
	{
		int i = 0;
		Combination best = variants[i++];
		int rankP1 = 0;
		int rankP2 = 0;					// ranks of pair
		int rankT1 = 0;
		int rankT2 = 0;					// ranks of three
		
		while(variants[i].getNumType() == 6)
		{
			if(best.getCard(0).getNumber()/4 ==
			   	best.getCard(2).getNumber()/4)
			{
				rankT1 = best.getCard(0).getNumber()/4;
				rankP1 = best.getCard(3).getNumber()/4;
			}
			else
			{
				rankP1 = best.getCard(0).getNumber()/4;
				rankT1 = best.getCard(2).getNumber()/4;
			}
			if(variants[i].getCard(0).getNumber()/4 ==
			    variants[i].getCard(2).getNumber()/4)
			{
				rankT2 = variants[i].getCard(0).getNumber()/4;
				rankP2 = variants[i].getCard(3).getNumber()/4;
			}
			else
			{
				rankP2 = variants[i].getCard(0).getNumber()/4;
				rankT2 = variants[i].getCard(2).getNumber()/4;
			}
			if(rankT2 > rankT1)
				best = variants[i];
			else if(rankT2 == rankT1)
			{
				if(rankP2 > rankP1)
					best = variants[i];
			}
			i++;
		}
		return best;
	}
	public static Combination bestOfFour(Combination[] variants)
	{
		int i = 0;
		Combination best = variants[i++];
		int rankF1 = 0;
		int rankF2 = 0;						// ranks of four
		int rankH1 = 0;
		int rankH2 = 0;						// ranks of highest cards
		
		while(variants[i].getNumType() == 7)
		{
			if(best.getCard(0).getNumber()/4 ==
				best.getCard(3).getNumber()/4)
			{
				rankF1 = best.getCard(0).getNumber()/4;
				rankH1 = best.getCard(4).getNumber()/4;
			}
			else
			{
				rankH1 = best.getCard(0).getNumber()/4;
				rankF1 = best.getCard(1).getNumber()/4;
			}
			if(variants[i].getCard(0).getNumber()/4 ==
				variants[i].getCard(3).getNumber()/4)
			{
				rankF2 = variants[i].getCard(0).getNumber()/4;
				rankH2 = variants[i].getCard(4).getNumber()/4;
			}
			else
			{
				rankH2 = variants[i].getCard(0).getNumber()/4;
				rankF2 = variants[i].getCard(1).getNumber()/4;
			}	
			if(rankF2 > rankF1)
				best = variants[i];
			else if(rankF2 == rankF1)
			{
				if(rankH2 > rankH1)
					best = variants[i];
			}
			i++;
		}
		return best;
	}
	public static Combination bestOfStraightFlush(Combination[] variants)
	{
		int i = 0;
		int rank1;
		int rank2;
		Combination best = variants[i++];
		
		while(variants[i].getNumType() == 8)
		{
			if(best.getCard(0).getNumber()/4 == 0 && 
				best.getCard(4).getNumber()/4 == 12)
					rank1 = -1;
			else
					rank1 = best.getCard(0).getNumber()/4;
			if(variants[i].getCard(0).getNumber()/4 == 0 && 
				variants[i].getCard(4).getNumber()/4 == 12)
					rank2 = -1;
			else
					rank2 = variants[i].getCard(0).getNumber()/4;
			if(rank2 > rank1)
				best = variants[i];
			i++;
		}
		return best;
	}
	public static void main(String[] args)
	{
		Card[] allCards = new Card[7];
		Combination[] variants = new Combination[21];
		
		allCards[0] = new Card(35, "10", "Spades");
		allCards[1] = new Card(39, "Jack", "Spades");
		allCards[2] = new Card(51, "Ace", "Spades");
		allCards[3] = new Card(21, "7", "Diamonds");
		allCards[4] = new Card(18, "6", "Hearts");
		allCards[5] = new Card(45, "King", "Diamonds");
		allCards[6] = new Card(30, "9", "Hearts");
		
		setAllCombinations(allCards, variants);
		
		for(int i=0; i<21; i++)
		{
			variants[i].show();
			Interface.emptyString();
		}
		
		Combination best = determineBest(variants);
		System.out.println("Best: ");
		best.show();
	}
}
