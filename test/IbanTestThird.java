import de.fhws.biedermann.webshop.utils.logic.OrderLogic;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IbanTestThird
{

	//Portfolio Abgabe 3
	@ParameterizedTest
	@CsvSource({
		"T1.5, Deutschland, AE55125468975652565255",
		"T1.6, Deutschland, ZE55125468975652565255",
		"T1.11, Deutschland, DA55125468975652565255",
		"T1.12, Deutschland, DZ55125468975652565255"
	})
	void testIbanTrue(String testCaseId, String country, String iban){
		System.out.println(testCaseId + "\n" );
		assertTrue( OrderLogic.checkIBAN( iban, country ) );
	}
	@ParameterizedTest
	@CsvSource({
		"T1.1, Deutschland, ''",
		"T1.2, Deutschland, DE5512546897565256525",
		"T1.3, Deutschland, aE55125468975652565255",
		"T1.4, Deutschland, zE55125468975652565255",
		"T1.7, Deutschland, 0E55125468975652565255",
		"T1.8, Deutschland, 9E55125468975652565255",
		"T1.9, Deutschland, Da55125468975652565255",
		"T1.10, Deutschland, Dz55125468975652565255",
		"T1.13, Deutschland, D055125468975652565255",
		"T1.14, Deutschland, D955125468975652565255"
	})
	void testIbanFalse(String testCaseId, String country, String iban){
		System.out.println(testCaseId + "\n" );
		assertFalse(OrderLogic.checkIBAN( iban, country ) );
	}
}
