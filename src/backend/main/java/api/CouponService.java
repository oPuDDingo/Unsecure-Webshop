package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.Coupon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("coupons") public class CouponService
{
	@Path("{name}") @GET @Produces(MediaType.APPLICATION_JSON) public Response getCoupon(
		@PathParam("name") final String name
	)
	{
		Coupon coupon = DataHandler.getCoupon(name);
		if (coupon == null) return Response.status(404).build();
		return Response.ok(coupon).build();

	}
}
