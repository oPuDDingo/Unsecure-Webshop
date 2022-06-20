package backend.main.java.api;

import backend.main.java.FlawHandler;
import backend.main.java.models.Commentary;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("comments")
public class CommentaryService {

	@POST public Response postCommentary(final Commentary commentary,
		@Context HttpServletRequest request) {
		if (commentary.getCommentText().contains("<script>")) {
			FlawHandler.commentXSS(request.getRemoteAddr());
		}
		return Response.noContent().build();
	}

}
