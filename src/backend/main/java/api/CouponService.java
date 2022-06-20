package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.FlawHandler;
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
		@Context HttpServletRequest request
	)
	{
		Coupon coupon = DataHandler.getCoupon(name);
		System.out.println(name);
		if (name.contains("example") || name.contains("discount")) {
			System.out.println("test");
			FlawHandler.guessCoupon(request.getRemoteAddr());
			coupon = new Coupon("error", 100, true);
		}
		if (coupon == null) return Response.status(404).build();
		return Response.ok(coupon).build();

	}
}
