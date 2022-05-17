package backend.main.java.api;

import backend.main.java.models.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("orders") public class OrderService
{
	@GET public Response getAllOrders() {
		//get all from database
		List<Order> orders = new ArrayList<>();
		orders.add(Order.getRandomOrder(1));
		return Response.ok(orders).build();
	}

	@GET @Path("{id}") public Response getOrderbyID(
		@PathParam("id") final int id
	)
	{
		//get from database
		return Response.ok(Order.getRandomOrder(id)).build();
	}

	@POST@Consumes(MediaType.APPLICATION_JSON) public Response createOrder(
		@QueryParam("cleanUpWishlist") final boolean cleanup,
		final Order order
	) throws URISyntaxException
	{
		if (cleanup) {
			// send delete request to wishlist
		}
		// create/move order in withlist
		return Response.created(new URI("placeholder")).build(); // placeholder for uri -> /orders/id
	}
}
