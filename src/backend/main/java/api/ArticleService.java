package backend.main.java.api;

import backend.main.java.models.Article;
import backend.main.java.models.ArticleShort;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("articles") public class ArticleService
{
	@GET @Produces(MediaType.APPLICATION_JSON) public Response getArticles(
		@DefaultValue("1") @QueryParam("page") final int page,
		@DefaultValue("false") @QueryParam("specifications") final boolean specifications,
		@DefaultValue("") @QueryParam("brand") final String brand,
		@DefaultValue("") @QueryParam("name") final String name
	)
	{
		//get from page
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < 9; i++)
		{
			if (specifications)
			{
				list.add(Article.getRandomArticle());
			} else {
				list.add(ArticleShort.getRandomArticleShort());
			}
		}

		return Response.ok(list).build();
	}


	@GET @Path("{id}") @Produces(MediaType.APPLICATION_JSON) public Response getArticleByID(
		@PathParam("id") final int id
	)
	{
		return Response.ok(Article.getRandomArticle()).build();
	}
}
