package backend.main.java.api;

import backend.main.java.models.Image;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

@Path("images") public class ImageService
{
	@GET @Produces(MediaType.APPLICATION_JSON) public Response getImage(
		@QueryParam("name") final String name
	)
	{
		// find in database by name

		String imageBytes = getByteArrayFromImageURL("https://www.kindpng.com/picc/m/6-67785_broken-phone-png-broken-iphone-transparent-png-download.png");
		Image image = new Image(1, "iPhone 12", imageBytes);
		return Response.ok(image).build();
	}

	private String getByteArrayFromImageURL(String url) {

		try {
			URL imageUrl = new URL(url);
			URLConnection ucon = imageUrl.openConnection();
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
}
