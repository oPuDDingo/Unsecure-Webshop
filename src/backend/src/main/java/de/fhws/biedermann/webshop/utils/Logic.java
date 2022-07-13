package de.fhws.biedermann.webshop.utils;

import de.fhws.biedermann.webshop.database.DataAccessAdminPanel;
import de.fhws.biedermann.webshop.database.DataAccessShopDatabase;

public class Logic
{
	static DataAccessShopDatabase Database = new DataAccessShopDatabase();
	static DataAccessAdminPanel daap = new DataAccessAdminPanel();

	public static boolean preventCheckXSS(int level, String request)
	{
		if (level == 1)
		{
			request = request.replace("script", "");
			return request.contains("<script>");
		}
		else if (level == 2)
		{
			request = request.replace("<script>", "");
			return request.contains("<script>");
		} else if (level == 3) {
			return request.contains("<img src=") && request.contains("onerror=");
		}
		return false;
	}



}
