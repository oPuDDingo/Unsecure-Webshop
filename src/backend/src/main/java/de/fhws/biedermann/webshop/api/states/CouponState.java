package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.models.Coupon;
import de.fhws.biedermann.webshop.utils.SecurityBreachDetection;
import de.fhws.biedermann.webshop.utils.handler.FlawHandler;

import javax.ws.rs.core.Response;

public class CouponState extends AbstractState
{
	private CouponState( AbstractStateBuilder builder )
	{
		super( builder );
	}

	@Override void lookForFlaw( )
	{
//		if ( SecurityBreachDetection.guessCoupon( name ) ) {
//			FlawHandler.guessCoupon( uuid );
//			return Response.ok( new Coupon( name, 40, true ) ).build();
//		}
	}

	public static class Builder extends AbstractStateBuilder{
		@Override
		public AbstractState build(){
			return new CouponState( this );
		}
	}
}
