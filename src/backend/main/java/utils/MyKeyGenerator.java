package backend.main.java.utils;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class MyKeyGenerator
{
	public static Key getNewKey( )
	{
		try
		{
			final SecureRandom rand = new SecureRandom( );
			final KeyGenerator keyGen = KeyGenerator.getInstance( "AES" );
			keyGen.init( 256, rand );
			return keyGen.generateKey( );
		}
		catch ( final Exception e )
		{
			e.printStackTrace( );
			return null;
		}
	}
}
