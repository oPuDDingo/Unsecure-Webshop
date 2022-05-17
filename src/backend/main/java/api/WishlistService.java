package backend.main.java.api;

import backend.main.java.models.ArticleVersion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("wishlist") public class WishlistService
{
	@GET @Path("items") public Response getWishlist() {
		List<ArticleVersion> articles = new ArrayList<>();
		for (int i = 0; i < 3; i++)
		{
			articles.add(ArticleVersion.getRandomArticleVersion());
		}
		return Response.ok(articles).build();
	}

	@POST @Consumes(MediaType.APPLICATION_JSON) @Path("items") public Response createWishlistItem(
		final ArticleVersion articleVersion
	) throws URISyntaxException
	{
		// create article in database
		return Response.created(new URI("placeholder-article-number")).build();
	}

	@PUT @Consumes(MediaType.APPLICATION_JSON) @Path("items/{id}") public Response modifyWishlistItem(
		@PathParam("id") final int id,
		final ArticleVersion articleVersion
	)
	{
		// modify article in database
		return Response.ok(articleVersion).build();
	}

	@DELETE public Response deleteAllItems() {
		// delete all items
		return Response.noContent().build();
	}

	@DELETE @Path("items/{id}") public Response deleteWishlistItem(
		@PathParam("id") final int id
	) {
		// get specific item and delete it
		return Response.noContent().build();
	}


}
