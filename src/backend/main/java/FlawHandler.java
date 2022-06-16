package backend.main.java;

import backend.main.java.database.DataAccessAdminPanel;

public class FlawHandler
{
	static DataAccessAdminPanel daap = new DataAccessAdminPanel();
	public static void emailWithoutAt(String remoteAddr)
	{
		daap.lookForClient(remoteAddr);
		daap.emailWithoutAt(remoteAddr);
	}

	public static void htmlCommentUser(String remoteAddr) {
		daap.lookForClient(remoteAddr);
		daap.putHtmlCommentUser(remoteAddr);
	}

	public static void putXSS(String remoteAddr)
	{
		daap.lookForClient(remoteAddr);
		daap.putXss(remoteAddr);
	}

	public static void priceOrder(String remoteAddr)
	{
		daap.lookForClient(remoteAddr);
		daap.putPriceOrderBug(remoteAddr);
	}
}
