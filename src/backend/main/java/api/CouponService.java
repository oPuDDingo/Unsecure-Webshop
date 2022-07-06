package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.FlawHandler;
import backend.main.java.SecurityBreachDetection;
import backend.main.java.models.Coupon;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("coupons") public class CouponService
{
	@Path("{name}") @GET @Produces(MediaType.APPLICATION_JSON) public Response getCoupon(
		@PathParam("name") final String name,
		@HeaderParam( "uuid" ) final String uuid,
		@Context HttpServletRequest request
	)
	{
		if ( SecurityBreachDetection.guessCoupon( name ) ) {
			FlawHandler.guessCoupon( uuid );
			return Response.ok( new Coupon( name, 40, true ) ).build();
		} else {
			final Coupon coupon = DataHandler.getCoupon(name);
			if (coupon == null) return Response.status(404).build();
			return Response.ok(coupon).build();
		}

	}
}
