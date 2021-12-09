public class Game 
{
	private Deal deal;
	private PlayersList pList;
	private int dealNumber;
	
	//constructor
	public Game(int n)
	{
		pList = new PlayersList(n);
		dealNumber = 1;
	}
	
	//methods
	public boolean startGame()
	{
		boolean userLost = false;
		int n = pList.getNPlayers();
		
		while(!userLost && (n > 1))
		{
				Interface.delimiter();
				Interface.delimiter();
			Interface.dealAnnounce(dealNumber);
				Interface.delimiter();
			deal = new Deal(pList);
			n = deal.startDeal(pList);
			userLost = pList.checkUser();
			dealNumber++;
		}
		return userLost;
	}
	
	//main
	public static void main(String[] args)
	{
		char again;
		int n;
		Game game;
		boolean userLost;
		
		Interface.hello();
		do {
			n = Interface.enterN();
			game = new Game(n);
			userLost = game.startGame();
			if(!userLost)
			{
				Interface.youWon();
					Interface.delimiter();
			}
			else
			{
				Interface.youLost();
					Interface.delimiter();	
			}
			again = Interface.playAgain();
		} while(again == 'y');
			Interface.delimiter();
		Interface.goodbye();
	}
}
