package de.fhws.biedermann.webshop.utils.logic;

import de.fhws.biedermann.webshop.models.Order;
import de.fhws.biedermann.webshop.utils.handler.DataHandler;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.math.BigInteger;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderLogic
{
	public static URI createOrder( boolean cleanup, String session, Order order, UriInfo uriInfo ) {
		if(!checkIBAN(order.getPayment().getIban(), order.getAddress().getCountry())){
			throw new BadRequestException( Response.status( Response.Status.BAD_REQUEST).entity("Die IBAN " + order.getPayment().getIban() + " ist falsch!").build() );
		};
		if ( StringUtils.isEmpty( order.getDate( ) ) ) order.setDate( LocalDateTime.now( ).format( DateTimeFormatter.ISO_LOCAL_DATE ) );
		int orderNumber = DataHandler.createOrder(order, session, cleanup);
		return uriInfo.getAbsolutePathBuilder().path("id").path(String.valueOf(orderNumber)).build();
	}

	public static boolean checkIBAN(String iban, String country){

		char[] ibanArr = iban.toCharArray();

		if(!country.equals( "Deutschland" )){
			return false;
		}
		if(iban.length() != 22){
			return false;
		}

		if(!Character.isUpperCase( ibanArr[0] )){
			return false;
		}
		if(!Character.isUpperCase( ibanArr[1] )){
			return false;
		}

		String ibanWithOutLetters = iban.substring( 2 );

		if(!ibanWithOutLetters.matches( "\\d*" )){
			return false;
		}

		return true;
	}

	private static boolean testPZ(String iban){

		// 44
		String ibanArDEPZ = iban.substring( 2, 4 );
		System.out.println("ibanArDEPZ: " + ibanArDEPZ);

		// 35070024 0388249600 131400
		String ibanNum = iban.substring( 4 ) + "" + 131400;
		System.out.println("ibanNum: " + ibanNum);

		//					int number = Integer.parseInt(ibanNum);
		BigInteger number = new BigInteger( ibanNum );
		System.out.println("BigInteger number: " + number);

		// Zahl wird durch 97 geteilt
		//					int numberDividedly = number / 97;
		BigInteger numberDividedly = number.mod( BigInteger.valueOf( 97 ) );
		System.out.println("BigInteger numberDividedly: " + numberDividedly);

		// 98 - die Zahl
		//					long pz = 98 - numberDividedly;
		BigInteger subNum = BigInteger.valueOf( 98 );
		BigInteger pz = subNum.subtract( numberDividedly );
		System.out.println("BigInteger pz: " + pz);

		BigInteger vglPZ = BigInteger.valueOf(Integer.parseInt(ibanArDEPZ));

		if( vglPZ.equals( pz ) ){
			return true;
		} else {
			return false;
		}
	}
}
