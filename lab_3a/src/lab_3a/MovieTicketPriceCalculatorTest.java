package lab_3a;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.time.LocalTime;

public class MovieTicketPriceCalculatorTest {

	/*
	 * testIlligalArgException 
	 * Validates that a start > end time will throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgException() {
		LocalTime start = LocalTime.of(18, 0);
		LocalTime end = LocalTime.of(11, 0);
		MovieTicketPriceCalculator ticketCalc = new MovieTicketPriceCalculator(start, end, 12, 70);	
	}
	
	/*
	 * testNullInput 
	 * Insures MovieTicketPriceCalculator throws a NullPointerException 
	 * when null values are inserted for start or end times
	 */
	@Test (expected = NullPointerException.class)
	public void testNullInput() {
		
		LocalTime start = LocalTime.of(6, 0);
		LocalTime end = LocalTime.of(18, 0);
		MovieTicketPriceCalculator ticketCalc1 = new MovieTicketPriceCalculator(null, end, 12, 70);
		MovieTicketPriceCalculator ticketCalc2 = new MovieTicketPriceCalculator(start, null, 12, 70);
	}
	
	/*
	 * testAgeDiscounts
	 * Validates there is a discount (more than zero) for children and seniors, and not for adults
	 */ 
	@Test
	public void testAgeDiscounts() {
		LocalTime start = LocalTime.of(6, 0);
		LocalTime end = LocalTime.of(18, 0);
		MovieTicketPriceCalculator ticketCalc = new MovieTicketPriceCalculator(start, end, 12, 70);

		assertTrue(ticketCalc.computeDiscount(5) > 0);
		assertTrue(ticketCalc.computeDiscount(77) > 0);
		assertTrue(ticketCalc.computeDiscount(50) == 0);
	}
	
	/*
	 * testMatineeDiscount
	 * tests that matinee time is discounted from night time, and child price is discounted from adult
	 */
	@Test
	public void testMatineeDiscount() {
		LocalTime start = LocalTime.of(6, 0);
		LocalTime end = LocalTime.of(18, 0);
		MovieTicketPriceCalculator ticketCalc = new MovieTicketPriceCalculator(start, end, 12, 70);

		LocalTime matineeTime = LocalTime.of(10, 0);
		LocalTime nightTime = LocalTime.of(20, 0);
		assertTrue(ticketCalc.computePrice(matineeTime, 5) < ticketCalc.computePrice(matineeTime, 50));
		assertTrue(ticketCalc.computePrice(matineeTime, 50) < ticketCalc.computePrice(nightTime, 50));
		assertTrue(ticketCalc.computePrice(nightTime, 5) < ticketCalc.computePrice(nightTime, 50));


	}
}
