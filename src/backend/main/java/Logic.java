package backend.main.java;

import backend.main.java.models.Article;

import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;

public class Logic
{
	public static Response login(final String mail, final String password) {
		// String session = Database.login(mail, password)
		String session = "session38492387";
		if (session == "")
		{
			return Response.status(404).build(); // login data not found
		}
		return Response.ok(session).build();
	}

	protected static String getByteArrayFromPictureURL(String url) { // temp function

		try {
			URL pictureUrl = new URL(url);
			URLConnection ucon = pictureUrl.openConnection();
			InputStream is = ucon.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, read);
			}
			baos.flush();
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static int getUserIdFromSession(String session) {
		//return Database.getUserID(session)
		return 1;
	}

	public static double computePrice(List<Article> articles) {
		double sum = 0;
		for (int i = 0; i < articles.size(); i++)
		{
			sum += articles.get(i).getAmount();
		}
		return sum;
	}

	public static String preventSQL(int level, String request) {
		if (level == 1) {
			return request.replace("SELECT", "");
		} else if (level == 2) {
			return request.replace("'", "");
		}
		return request;
	}


	public static String preventXSS(int level, String request) {
		if (level == 1) {
			return request.replace("script", "");
		} else if (level == 2) {
			return request.replace("<script>", "");
		}
		return request;
	}
}
