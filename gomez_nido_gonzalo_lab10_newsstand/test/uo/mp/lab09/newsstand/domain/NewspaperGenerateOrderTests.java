package uo.mp.lab09.newsstand.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Scenarios:
 * 1- stock and sales are positive
 * 2- stock is zero and sales are positive
 * 3- stock is positive and sales are zero
 */
class NewspaperGenerateOrderTests {

	/**
	 * GIVEN: A newspaper with positive sales and stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold plus twice the ones left are ordered
	 */
	@Test
	public void positiveSalesAndStock() {
		Publication np = new Newspaper("ABC", 12, 20);
		
		Order o = np.generateOrder();
		
		assertEquals(44, o.getQuantity());
	}
	
	/**
	 * GIVEN: A newspaper with positive sales and no stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold plus twice the ones left (2*0=0) are ordered
	 */
	@Test
	public void positiveSalesAndZeroStock() {
		Publication np = new Newspaper("ABC", 0, 10);
		
		Order o = np.generateOrder();
		
		assertEquals(10 , o.getQuantity());
	}
	
	/**
	 * GIVEN: A newspaper with zero sales and positive stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold (0) plus twice the ones left are ordered
	 */
	@Test
	public void positiveStockAndZeroSales() {
		Publication np = new Newspaper("ABC", 10, 0);
		
		Order o = np.generateOrder();
		
		assertEquals(20 , o.getQuantity());
	}
}
