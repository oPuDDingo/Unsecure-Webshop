import de.fhws.biedermann.webshop.utils.logic.OrderLogic;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;

public class testIbanSecond
{

	//Portfolio Abgabe 2
	@ParameterizedTest
	@CsvSource({
		"T1.1, Deutschland, DE55125468975652565255"
	})
	void testIbanTrue(String testCaseId, String country, String iban){
		System.out.println(testCaseId + "\n" );

		assertTrue(OrderLogic.checkIBAN( iban, country ) );
	}

	@ParameterizedTest
	@CsvSource({
		"T1.2, Frankreich, DE55125468975652565255",
		"T1.3, Deutschland, DE551254689756525652",
		"T1.4, Deutschland, DE551254689756525652553",
		"T1.5, Deutschland, dE55125468975652565255",
		"T1.6, Deutschland, 1E55125468975652565255",
		"T1.7, Deutschland, De55125468975652565255",
		"T1.8, Deutschland, D155125468975652565255",
		"T1.9, Deutschland, DE55125468975652565ee5"
	})
	void testIbanFalse(String testCaseId, String country, String iban){
		System.out.println(testCaseId + "\n" );

		assertFalse(OrderLogic.checkIBAN( iban, country ) );
	}
}
