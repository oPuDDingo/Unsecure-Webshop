import de.fhws.biedermann.webshop.utils.logic.OrderLogic;
import org.junit.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class IbanTest
{

	//Portfolio Abgabe 1
	@Test
	public void test_iban_with_other_shipping_country() throws IOException
	{
		assertFalse(OrderLogic.checkIBAN( "DE44350700240388249600", "Frankreich" ) );
	}

	@Test
	public void test_iban_with_german_shipping_country() throws IOException
	{
		// test_iban_with_correct_length()
		assertTrue( OrderLogic.checkIBAN( "DE44350700240388249600", "Deutschland" ) );
	}

	@Test
	public void test_iban_with_to_short_length() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "DE443507002403882496", "Deutschland" ) );
	}

	@Test
	public void test_iban_with_to_long_length() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "DE44350700240388249613241234", "Deutschland" ) );
	}

	@Test
	public void test_iban_without_uppercase_second() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "De44350700240388249600", "Deutschland" ) );
	}

	@Test
	public void test_iban_without_uppercase_first() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "dE44350700240388249600", "Deutschland" ) );
	}

	@Test
	public void test_iban_without_uppercase_both() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "de44350700240388249600", "Deutschland" ) );
	}

	@Test
	public void test_iban_without_only_numbers_after_country_letters() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "DE44350700240388B49600", "Deutschland" ) );
	}

	@Test
	public void test_iban_with_only_numbers() throws IOException
	{
		assertFalse( OrderLogic.checkIBAN( "2344350700240388249600", "Deutschland" ) );
	}


}
