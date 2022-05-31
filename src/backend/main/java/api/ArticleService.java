package backend.main.java.api;

import backend.main.java.DataHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("articles") public class ArticleService
{
	@GET @Produces(MediaType.APPLICATION_JSON) public Response getArticles(
		@DefaultValue("1") @QueryParam("page") final int page,
		@DefaultValue("false") @QueryParam("specifications") final boolean specifications,
		@DefaultValue("") @QueryParam("brand") final String brand,
		@DefaultValue("") @QueryParam("name") final String name
	)
	{ return Response.ok(DataHandler.getArticles(page, brand, name, specifications)).build(); }


	@GET @Path("{id}") @Produces(MediaType.APPLICATION_JSON) public Response getArticleByID(
		@PathParam("id") final int id
	)
	{ return Response.ok(DataHandler.getArticle(id)).build(); }
}
