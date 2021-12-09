public class Card 
{
	private int number;
	private String rank;
	private String suit;
	
	//constructor
	public Card(int n, String r, String s)
	{
		number = n;
		rank = r;
		suit = s;
	}
	
	//methods
	public int getNumber()
	{
		return number;
	}
	public String getRank()
	{
		return rank;
	}
	public String getSuit()
	{
		return suit;
	}
	public void show()
	{
		System.out.println(number + ") " + rank + " of " + suit);
	}
}
