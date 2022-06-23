package backend.main.java;

import backend.main.java.database.DataAccessShopDatabase;
import org.apache.commons.lang.StringUtils;

public class SecurityBreachDetection
{
	static DataAccessShopDatabase db = new DataAccessShopDatabase();

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

}
