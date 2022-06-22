package backend.main.java;

import backend.main.java.database.DataAccessAdminPanel;

public class FlawHandler
{
	static DataAccessAdminPanel daap = new DataAccessAdminPanel();

	public static void emailWithoutAt(String ip)
	{
		daap.lookForClient(ip);
		daap.emailWithoutAt(ip);
	}

	public static void htmlCommentUser(String ip)
	{
		daap.lookForClient(ip);
		daap.putHtmlCommentUser(ip);
	}

	public static void putXSS(String ip)
	{
		daap.lookForClient(ip);
		daap.putXss(ip);
	}

	public static void priceOrder(String ip)
	{
		daap.lookForClient(ip);
		daap.putPriceOrderBug(ip);
	}

	public static void guessCoupon(String ip)
	{
		daap.lookForClient(ip);
		daap.putGuessCoupon(ip);
	}

	public static void guessUserLogin(String ip)
	{
		daap.lookForClient(ip);
		daap.putGuessUserLogin(ip);
	}

	public static void commentXSS(String ip)
	{
		daap.lookForClient(ip);
		daap.putCommentXss(ip);
	}

	public static void sqlInjection(String ip){
		daap.lookForClient(ip);
		daap.putSqlInjection(ip);
	}
}
