package de.fhws.biedermann.webshop.api;

import de.fhws.biedermann.webshop.FlawHandler;
import de.fhws.biedermann.webshop.VulnerabilityCheck;
import de.fhws.biedermann.webshop.database.DataAccessShopDatabase;
import de.fhws.biedermann.webshop.models.Commentary;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("comments")
public class CommentaryService {

	@POST public Response postCommentary(
		final Commentary commentary,
		@HeaderParam( "uuid" ) final String uuid,
		@Context HttpServletRequest request
	) {
		VulnerabilityCheck vc = new VulnerabilityCheck();
		DataAccessShopDatabase daap = new DataAccessShopDatabase();
		if(vc.checkXSS(commentary.getCommentText())){
			FlawHandler.commentXSS(uuid);
		}
		// todo articleId
		daap.postCommentary(commentary, -1,commentary.getUserId());
		return Response.noContent().build();
	}

}
