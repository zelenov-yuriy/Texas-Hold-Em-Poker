public class Deal 
{
	private Deck deck;
	private Card[] flop;
	private Card turn;
	private Card river;
	
	//constructor
	public Deal(PlayersList pList)
	{
		deck = new Deck();
		deck.shuffle();
	}
	
	//methods
	public int startDeal(PlayersList pList)
	{
		int a;
		int r;
		int cardNum;
		
		pList.setBlinds();
		pList.showChips();
			Interface.delimiter();
		pList.showButton();
		pList.showBlinds();
			Interface.delimiter();
		cardNum = pList.setHands(deck);
		pList.showUsersHand();
			Interface.delimiter();
		pList.setZeroBets();
		blindBets(pList);
			Interface.delimiter();
		Interface.preflopBets();
		pList.showBets();
			Interface.delimiter();
		pList.setPot();
		Interface.displayPot();
		pList.showPot();
		
		a = pList.getAllinPlayers();
		r = pList.getRemainPlayers();
		
		if((a + r) > 1)
		{
			setFlop(cardNum);
			cardNum += 3;
			Interface.delimiter();
			Interface.cardsOnTable();
			Interface.showFlop(flop);
			if(r <= 1)
			{
				setTurn(cardNum++);
				Interface.showTurn(turn);
				setRiver(cardNum);
				Interface.showRiver(river);
					Interface.delimiter();
				Interface.showdownAnnounce();
					Interface.delimiter();
				pList.showdown();
				setCombinations(pList);
			}
			else
			{
					Interface.delimiter();
				pList.showUsersHand();
					Interface.delimiter();
				pList.setZeroBets();
				noBlindBets(pList);
					Interface.delimiter();
				Interface.flopBets();
				pList.showBets();
					Interface.delimiter();
				pList.setPot();
				Interface.displayPot();
				pList.showPot();
				
				a = pList.getAllinPlayers();
				r = pList.getRemainPlayers();
				
				if((a + r) > 1)
				{
					setTurn(cardNum++);
					Interface.delimiter();
					Interface.cardsOnTable();
					Interface.showFlop(flop);
					Interface.showTurn(turn);
					if(r <= 1)
					{
						setRiver(cardNum);
						Interface.showRiver(river);
							Interface.delimiter();
						Interface.showdownAnnounce();
							Interface.delimiter();
						pList.showdown();
						setCombinations(pList);
					}
					else
					{
							Interface.delimiter();
						pList.showUsersHand();
						pList.setZeroBets();
							Interface.delimiter();
						noBlindBets(pList);
							Interface.delimiter();
						Interface.turnBets();
						pList.showBets();
							Interface.delimiter();
						pList.setPot();
						Interface.displayPot();
						pList.showPot();
						
						a = pList.getAllinPlayers();
						r = pList.getRemainPlayers();
						
						if((a + r) > 1)
						{
							setRiver(cardNum);
							Interface.delimiter();
							Interface.cardsOnTable();
							Interface.showFlop(flop);
							Interface.showTurn(turn);
							Interface.showRiver(river);
								Interface.delimiter();
							if(r <= 1)
							{
								Interface.showdownAnnounce();
									Interface.delimiter();
								pList.showdown();
								setCombinations(pList);
							}
							else
							{
								pList.showUsersHand();
								pList.setZeroBets();
									Interface.delimiter();
								noBlindBets(pList);
									Interface.delimiter();
								Interface.riverBets();
								pList.showBets();
									Interface.delimiter();
								pList.setPot();
								Interface.displayPot();
								pList.showPot();
								
								a = pList.getAllinPlayers();
								r = pList.getRemainPlayers();
								
								if((a + r) > 1)
								{
										Interface.delimiter();
									Interface.showdownAnnounce();
										Interface.delimiter();
									Interface.cardsOnTable();
									Interface.showFlop(flop);
									Interface.showTurn(turn);
									Interface.showRiver(river);
										Interface.delimiter();
									pList.showdown();
									setCombinations(pList);
								}
							}
						}
					}
				}
			}
		}
			Interface.delimiter();
		if((a + r) > 1)
		{
			Interface.combinationsAnnounce();
				Interface.delimiter();
			pList.showCombinations();
				Interface.delimiter();
		}
		pList.setWinner();	
		pList.resetPlayers();
		pList.shiftButton();
			Interface.delimiter();
		pList.showChips();
			Interface.delimiter();
		pList.thinPlayers();
		int n = pList.getNPlayers();
		return n;
	}
	public void blindBets(PlayersList pList)
	{
		boolean raise = true;
		int currentBet = 20;
		int tempBet = 0;
		Player temp = pList.bigBlind.getNext();
		Player point = pList.bigBlind;
		boolean shiftPoint = true;
		
		pList.smallBlind.setSmallBlind();
		pList.smallBlind.showBet();
		pList.bigBlind.setBigBlind();
		pList.bigBlind.showBet();		
				
		do {
			if(!temp.getFold() && !temp.getAllIn())
			{
				if(temp.getNum() == 0)
					tempBet = temp.makeYourBet(currentBet);
				else
				{
					tempBet = temp.randomBet(currentBet);
					temp.showBet();
				}
				
			}
			if(!temp.getFold() && shiftPoint)
			{
				point = point.getNext();
				shiftPoint = false;
			}	
			if(temp.getNext() == point)
				raise = false;
			if(tempBet > currentBet)
			{
				currentBet = tempBet;
				raise = true;
				point = temp;
			}
			temp = temp.getNext();
		} while(raise);
	}
	public void noBlindBets(PlayersList pList)
	{
		boolean raise = true;
		int currentBet = 0;
		int tempBet = 0;
		Player temp = pList.button.getNext();
		Player point; 
		
		while(temp.getFold())
			temp = temp.getNext();
		point = temp;
		
		do {
			if(temp.getNext() == point)
				raise = false;
			if(!temp.getFold() && !temp.getAllIn())
			{
				if(temp.getNum() == 0)
					tempBet = temp.makeYourBet(currentBet);
				else
				{
					tempBet = temp.randomBet(currentBet);
					temp.showBet();
				}
			}
			if(tempBet > currentBet)
			{
				currentBet = tempBet;
				raise = true;
				point = temp;
			}
			temp = temp.getNext();
		} while(raise);
	}
	public void setFlop(int cardNum)
	{
		flop = new Card[3];
		flop[0] = deck.getCard(cardNum++);
		flop[1] = deck.getCard(cardNum++);
		flop[2] = deck.getCard(cardNum);
	}
	public void setTurn(int cardNum)
	{
		turn = deck.getCard(cardNum);
	}
	public void setRiver(int cardNum)
	{
		river = deck.getCard(cardNum);
	}
	public void setCombinations(PlayersList pList)
	{
		Player temp = pList.user;
		Card[] allCards;
		Combination[] variants;
		
		for(int i=0; i<pList.nPlayers; i++)
		{
			if(!temp.getFold())
			{
				allCards = new Card[7];
				for(int j=0; j<7; j++)
				{
					allCards[0] = temp.getFirstCard();
					allCards[1] = temp.getSecondCard();
					allCards[2] = flop[0];
					allCards[3] = flop[1];
					allCards[4] = flop[2];
					allCards[5] = turn;
					allCards[6] = river;
				}
				variants = new Combination[21];
				PlayerResults.setAllCombinations(allCards, variants);
				temp.setComb(PlayerResults.determineBest(variants));
			}
			temp = temp.getNext();
		}
	}
}
