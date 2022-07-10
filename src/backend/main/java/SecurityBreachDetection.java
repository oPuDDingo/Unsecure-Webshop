package backend.main.java;

import backend.main.java.database.DataAccessShopDatabase;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;

public class SecurityBreachDetection
{
	static DataAccessShopDatabase db = new DataAccessShopDatabase();
	static Map<String, Collection<LocalDateTime>> loginTrys = new HashMap<>();
	public static boolean matchSessionToUserId(final String session, final long userId) {
		return db.getUserId( session ) == userId;
	}

	public static boolean isInvalidFileFormat( final String file ) {
		return StringUtils.isNotEmpty( file ) && isNotAnImage( file );
	}

	private static boolean isNotAnImage( String file ) {
		file = file.replaceFirst( "data:image/", "" );
		return ! (file.startsWith( "png" )
			|| file.startsWith( "jpg" )
			|| file.startsWith( "jpeg" )
			|| file.startsWith( "tiff" )
			|| file.startsWith( "gif" )
			|| file.startsWith( "bmp" ));
	}

	public static boolean guessCoupon( final String coupon ) {
		final String lowerCoupon = coupon.toLowerCase().replace( " ", "" );
		final List<String> dummyCoupons = Arrays.asList( "blackfriday", "summersale", "summer", "ausverkauf", "neujahr", "newyear", "winter", "biedermann", "biedisshop" );
		// toDo: look for date (winter, summer)
		return dummyCoupons.stream( ).anyMatch( lowerCoupon::contains );
	}

	public static void detectDummyUserInHtmlSecurityBreach( final String mail, final String password, final String ip ) {
		detectHtmlCommentUser( mail, password, ip );
	}

	private static void detectHtmlCommentUser( final String mail, final String password, final String ip ) {
		if (mail.equals("dummy@user.com") && password.equals("MyPasswordIsSafe")) {
			FlawHandler.htmlCommentUser(ip);
		}
	}

	public static void detectLoginSecurityBreach( final String uuid )
	{
		if( loginTrys.containsKey( uuid ) )
		{
			ArrayList<LocalDateTime> dateTime = new ArrayList<>( loginTrys.get( uuid ) );
			dateTime.add( LocalDateTime.now() );
			loginTrys.put( uuid, dateTime );
		} else {
			loginTrys.put( uuid, List.of( LocalDateTime.now() ) );
		}

		if( loginTrys.get( uuid ).size() > 10 )
		{
			FlawHandler.guessUserLogin( uuid );
		}
		loginTrys.put( uuid, loginTrys.get( uuid ).stream( ).filter( byTimeDelta() ).toList() );
	}

	private static Predicate<LocalDateTime> byTimeDelta( )
	{
		final LocalDateTime now = LocalDateTime.now();
		return givenDate -> givenDate.until( now, ChronoUnit.SECONDS ) < 10;
	}
}
