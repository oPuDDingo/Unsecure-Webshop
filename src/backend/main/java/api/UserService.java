package backend.main.java.api;

import backend.main.java.*;
import backend.main.java.models.*;

import javax.servlet.http.HttpServletRequest;
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
		@HeaderParam("sessionid") String session)
	{
		if (session == null) return Response.status(401).build();
		User user = DataHandler.getUser(session);
		return Response.ok(user).build();
	}

	@Path("{id}") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserById(
		@PathParam("id") final int id)
	{
		User user = DataHandler.getUserById(id);
		return Response.ok(user).build();
	}

	@POST @Path("register") @Consumes(MediaType.APPLICATION_JSON) public Response createUser(
		@Context UriInfo uriInfo,
		final User user,
		@Context HttpServletRequest request,
		@HeaderParam( "uuid" ) final String uuid
	)
	{
		DataHandler.createUser(user);
		return Logic.login(user.getMail(), user.getPassword(), uuid);
	}

	@GET @Path("login") @Produces(MediaType.TEXT_PLAIN) public Response checkLogin(
		@DefaultValue("") @QueryParam("mail") String mail,
		@DefaultValue("") @QueryParam("password") String password,
		@HeaderParam( "uuid" ) final String uuid,
		@Context HttpServletRequest request
	) {
		return Logic.login(mail, password, uuid);
	}

	@POST @Path("logout") public Response logout(
		@HeaderParam("sessionid") final String session
	) {
		if (session == null) return Response.status(401).build();
		return Logic.logout(session);
	}

	@PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUser(
		@HeaderParam("sessionid") String session,
		@HeaderParam( "uuid" ) final String uuid,
		final User user,
		@Context HttpServletRequest request)
	{
		if (user==null) return Response.status(400).build();
		if (session == null) return Response.status(401).build();

		if ( SecurityBreachDetection.isInvalidFileFormat( user.getProfilePicture() ) ) {
			FlawHandler.imageWithWrongDataType( uuid );
		}

		VulnerabilityCheck vCheck = new VulnerabilityCheck();
		UserVulnerability userVul = vCheck.checkSqlInjection(user.getDescription());
		if(userVul!=null) {
			FlawHandler.sqlInjection(uuid);
			return Response.ok(userVul).build();
		}
		else{
			if(vCheck.checkXSS(user.getDescription())){
				FlawHandler.xxs(uuid);
			}
			DataHandler.modifyUser(session, user, uuid);
			return Response.ok(user).build();
		}

	}

	@DELETE public Response deleteUser(
		@HeaderParam("sessionid") String session)
	{
		DataHandler.deleteUser(session);
		return Response.noContent().build();
	}

	@Path( "{id:\\d+}" )
	@DELETE public Response deleteUserById(
		@DefaultValue( "" ) @HeaderParam("sessionid") String session,
		@PathParam( "id" ) final long id,
		@Context HttpServletRequest req
	)
	{
		if (session == null)
			throw new BadRequestException( "Missing session key!" );

		DataHandler.deleteUserById(session, id, req.getRemoteAddr());
		return Response.noContent().build();
	}

	@Path("payment") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserPayment(
		@HeaderParam("sessionid") String session)
	{
		if (session == null) return Response.status(401).build();
		Payment payment = DataHandler.getUserPayment(session);
		return Response.ok(payment).build();
	}

	@Path("payment") @POST @Consumes(MediaType.APPLICATION_JSON) public Response createUserPayment(
		@HeaderParam("sessionid") String session,
		@Context UriInfo uriInfo,
		final Payment payment)
	{
		if (session == null) return Response.status(401).build();
		DataHandler.createUserPayment(session, payment);
		final URI locationURI = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(locationURI).build();
	}

	@Path("payment") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUserPayment(
		@HeaderParam("sessionid") String session,
		final Payment payment)
	{
		// DataHandler.modifyUserPayment(session, payment);
		return Response.ok("momentan deaktiviert").build();
	}

	@Path("payment") @DELETE public Response deleteUserPayment(
		@HeaderParam("sessionid") String session)
	{
		// DataHandler.deleteUserPayment(session);
		return Response.ok("momentan deaktiviert").build();
	}

	@GET @Path("addresses") @Produces(MediaType.APPLICATION_JSON) public Response getAllUserAddresses(
		@HeaderParam("sessionid") String session)
	{
		List<Address> userAddresses = DataHandler.getAllUserAddresses(session);
		return Response.ok(userAddresses).build();
	}

	@GET @Path("/addresses/{id}") @Produces(MediaType.APPLICATION_JSON) public Response getUserAddress(
		@HeaderParam("sessionid") String session,
		@PathParam("id") final int id)
	{
		return Response.ok(DataHandler.getUserAddress(session, id)).build();
	}

	@Path("addresses") @POST @Produces(MediaType.APPLICATION_JSON) public Response createUserAddress(
		@HeaderParam("sessionid") String session,
		@Context UriInfo uriInfo,
		final Address address)
	{
		return Response.ok(DataHandler.createAddress(session, address)).build();
	}

	@Path("addresses/{id}") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUserAddress(
		@HeaderParam("sessionid") final String session,
		@PathParam("id") final int id,
		final Address address)
	{
		if (id != address.getId()){
			return Response.status( 422 ).build();
		}
		return Response.ok(DataHandler.modifyAddress(address, session)).build();
	}

	@Path("addresses/{id}") @DELETE public Response deleteUserAddress(
		@HeaderParam("sessionid") String session,
		@PathParam("id") final int id)
	{
		DataHandler.deleteAddress(session, id);
		return Response.noContent().build();
	}

	@Path("mail") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserMail(
		@HeaderParam("sessionid") String session)
	{
		if (session == null) return Response.status(401).build();
		String mail = DataHandler.getUserMail(session);
		return Response.ok(mail).build();
	}

	@Path("mail") @POST @Consumes(MediaType.APPLICATION_JSON) public Response createUserMail(
		@HeaderParam("sessionid") String session,
		@Context UriInfo uriInfo,
		final String mail)
	{
		if (session == null) return Response.status(401).build();
		DataHandler.createUserMail(session, mail);
		final URI locationURI = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(locationURI).build();
	}

	@Path("newsletter") @GET public Response checkNewsletter(
		@HeaderParam("sessionid") String session
	)
	{
		if (session == null) return Response.status(401).build();
		boolean value = DataHandler.checkNewsletter(session);
		return Response.ok(value).build();
	}

	@Path("newsletter") @POST @Consumes(MediaType.APPLICATION_JSON) public Response turnOnNewsletter(
		@HeaderParam("sessionid") String session,
		@HeaderParam( "uuid" ) final String uuid,
		@Context HttpServletRequest request,
		final Nletter nletter
	)
	{
		if (session == null) return Response.status(401).build();
		if(!nletter.getEmail().contains("@")) {
			FlawHandler.emailWithoutAt(uuid);
		}
		DataHandler.turnOnNewsletter(session);
		return Response.noContent().build();
	}

	@Path("newsletter") @DELETE public Response turnOffNewsletter(
		@HeaderParam("sessionid") String session
	)
	{
		if (session == null) return Response.status(401).build();
		DataHandler.turnOffNewsletter(session);
		return Response.noContent().build();
	}

	@Path("password") @Consumes(MediaType.TEXT_PLAIN) @PUT public Response modifyPassword(
		@HeaderParam("sessionid") String session,
		final String password)
	{
		// TODO something with password here
		if (session == null) return Response.status(401).build();
		//toDO get user with sessionid
		// modify in database
		return Response.ok(password).build();
	}

	@Path( "me" )
	@GET public Response getNewUuid() {
		return Response.ok( Logic.createNewUser() ).build();
	}

}
