package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.User;
import backend.main.java.models.Address;
import backend.main.java.models.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("user") public class UserService
{
	@GET @Produces(MediaType.APPLICATION_JSON) public Response getUser(
		@CookieParam("sessionID") final String session)
	{
		User user = DataHandler.getUser(session);
		return Response.ok(user).build();
	}

	@Path("{id}") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserById(
		@PathParam("id") final int id)
	{
		User user = DataHandler.getUserById(id);
		return Response.ok(user).build();
	}

	@POST @Consumes(MediaType.APPLICATION_JSON) public Response createUser(
		@Context UriInfo uriInfo,
		final User user)
	{
		int id = DataHandler.createUser(user);
		URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
		return Response.created(location).build();
	}

	@PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUser(
		@CookieParam("sessionID") final String session,
		final User user)
	{
		DataHandler.modifyUser(session, user);
		return Response.ok(user).build();
	}

	@DELETE public Response deleteUser(
		@CookieParam("sessionID") final String session)
	{
		DataHandler.deleteUser(session);
		return Response.noContent().build();
	}

	@Path("payment") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserPayment(
		@CookieParam("sessionID") String session)
	{
		Payment payment = DataHandler.getUserPayment(session);
		return Response.ok(payment).build();
	}

	@Path("payment") @POST @Consumes(MediaType.APPLICATION_JSON) public Response createUserPayment(
		@CookieParam("sessionID") String session,
		@Context UriInfo uriInfo,
		final Payment payment)
	{
		DataHandler.createUserPayment(session, payment);
		final URI locationURI = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(locationURI).build();
	}

	@Path("payment") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUserPayment(
		@CookieParam("sessionID") String session,
		final Payment payment)
	{
		// DataHandler.modifyUserPayment(session, payment);
		return Response.ok("momentan deaktiviert").build();
	}

	@Path("payment") @DELETE public Response deleteUserPayment(
		@CookieParam("sessionID") String session)
	{
		// DataHandler.deleteUserPayment(session);
		return Response.ok("momentan deaktiviert").build();
	}

	@GET @Path("addresses") @Produces(MediaType.APPLICATION_JSON) public Response getAllUserAddresses(
		@CookieParam("sessionID") String session)
	{
		// List<Address> userAddresses = DataHandler.getAllUserAddresses(session);
		return Response.ok("404").build();
	}

	@GET @Path("/addresses/{id}") @Produces(MediaType.APPLICATION_JSON) public Response getUserAddress(
		@CookieParam("sessionID") String session,
		@PathParam("id") final int id)
	{
		// Address userAddress = DataHandler.getUserAddress(session, id);
		return Response.ok("404").build();
	}

	@Path("addresses") @POST @Produces(MediaType.APPLICATION_JSON) public Response createUserAddress(
		@CookieParam("sessionID") String session,
		@Context UriInfo uriInfo,
		final Address address)
	{
		// int id = DataHandler.createUserAddress(session, address);
		return Response.ok("404").build();
	}

	@Path("addresses/{id}") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUserAddress(
		@CookieParam("sessionID") final String session,
		@PathParam("id") final int id,
		final Address address)
	{
		// DataHandler.modifyUserAddress(session, id, address);
		return Response.ok("404").build();
	}

	@Path("addresses/{id}") @DELETE public Response deleteUserAddress(
		@CookieParam("sessionID") String session,
		@PathParam("id") final int id)
	{
		// DataHandler.deleteUserAddress(session, id);
		return Response.ok("404").build();
	}

	@Path("mail") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserMail(
		@CookieParam("sessionID") String session)
	{
		String mail = DataHandler.getUserMail(session);
		return Response.ok(mail).build();
	}

	@Path("mail") @POST @Consumes(MediaType.APPLICATION_JSON) public Response createUserMail(
		@CookieParam("sessionID") String session,
		@Context UriInfo uriInfo,
		final String mail)
	{
		DataHandler.createUserMail(session, mail);
		final URI locationURI = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(locationURI).build();
	}

	@Path("newsletter") @POST public Response turnOnNewsletter(
		@CookieParam("sessionID") String session
	)
	{
		DataHandler.turnOnNewsletter(session);
		return Response.ok("signed up for newsletter").build();
	}

	@Path("newsletter") @DELETE public Response turnOffNewsletter(
		@CookieParam("sessionID") String session
	)
	{
		// turn off newsletter
		return Response.ok("cancelled newsletter subscription").build();
	}

	@Path("password") @Consumes(MediaType.TEXT_PLAIN) @PUT public Response modifyPassword(
		@CookieParam("sessionID") final String session,
		final String password)
	{
		// get user with sessionID
		// modify in database

		return Response.ok(password).build();
	}

}
