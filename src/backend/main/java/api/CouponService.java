package api;

import models.Coupon;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("coupons") public class CouponService
{
	@GET @Path("{name}") public Response getWishlist(@PathParam("name") final String name) {
		// search by name in database
		return Response.ok(Coupon.getRandomCoupon(name)).build();
	}
}
