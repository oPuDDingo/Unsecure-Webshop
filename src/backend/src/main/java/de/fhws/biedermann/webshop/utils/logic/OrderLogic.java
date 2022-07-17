package de.fhws.biedermann.webshop.utils.logic;

import de.fhws.biedermann.webshop.models.Order;
import de.fhws.biedermann.webshop.utils.handler.DataHandler;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class OrderLogic
{
	public static URI getUriLocation( boolean cleanup, String session, Order order, UriInfo uriInfo ) {
		int orderNumber = DataHandler.createOrder(order, session, cleanup);
		return uriInfo.getAbsolutePathBuilder().path("id").path(String.valueOf(orderNumber)).build();
	}
}
