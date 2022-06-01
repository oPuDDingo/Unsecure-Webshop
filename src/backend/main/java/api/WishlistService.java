package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.ArticleVersion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("wishlist") public class WishlistService
{
	@GET @Path("items") public Response getWishlist(@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		List<ArticleVersion> articles = DataHandler.getWishlist(session);
		return Response.ok(articles).build();
	}

	@POST @Consumes(MediaType.APPLICATION_JSON) @Path("items") public Response createWishlistItem(
		final ArticleVersion articleVersion, @CookieParam("sessionID") final String session) throws URISyntaxException
	{
		if (session == null) return Response.status(403).build();
		DataHandler.createWishlistItem(articleVersion, session);
		return Response.created(new URI("placeholder-article-number")).build();
	}

	@PUT @Consumes(MediaType.APPLICATION_JSON) @Path("items/{id}") public Response modifyWishlistItem(
		@PathParam("id") final int id, final ArticleVersion articleVersion,
		@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		DataHandler.modifyWhishlistItem(articleVersion, session, id);
		return Response.ok(articleVersion).build();
	}

	@Path("items") @DELETE public Response deleteAllItems(@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		DataHandler.deleteWishlist(session);
		return Response.noContent().build();
	}

	@DELETE @Path("items/{id}") public Response deleteWishlistItem(@PathParam("id") final int id,
		@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		DataHandler.deleteWishlistItem(id);
		return Response.noContent().build();
	}
}
