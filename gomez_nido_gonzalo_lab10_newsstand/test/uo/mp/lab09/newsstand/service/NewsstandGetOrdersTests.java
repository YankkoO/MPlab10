package uo.mp.lab09.newsstand.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Magazine;
import uo.mp.lab09.newsstand.domain.Magazine.Frequency;
import uo.mp.lab09.newsstand.domain.Newspaper;

/**
 * Scenarios
 * 1- Empty list
 * 2- List with several elements
 * 3- Returns a copy of the list
 */
class NewsstandGetOrdersTests {

	private NewsstandService newsstand;

	@BeforeEach
	public void setUp() {
		newsstand = new NewsstandService();
	}

	/**
	 * GIVEN: A newsstand without any orders generated
	 * WHEN: getOrders() is called
	 * THEN: An empty list (size 0) is returned
	 */
	@Test
	public void emptyList() {
		assertEquals(0, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: A newsstand with publications that need stock
	 * WHEN: getOrders() is called after creating orders
	 * THEN: A list with the corresponding orders is returned
	 */
	@Test
	public void listWithSeveralElements() throws NewsstandException {
		newsstand.addPublication(new Newspaper("El Pais", 2, 5));
		newsstand.addPublication(new Magazine("Hola", 2, 5, Frequency.WEEKLY));
		
		newsstand.createOrders();
		
		assertEquals(2, newsstand.getOrders().size());
	}
	
	/**
	 * GIVEN: A newsstand with generated orders.
	 * WHEN: getOrders() is invoked and the returned list is subsequently modified.
	 * THEN: The internal list of orders within the service remains unaffected.
	 */
	@Test
	public void getOrderReturnsCopy() throws NewsstandException {
		
		newsstand.addPublication(new Newspaper("El Pais", 2, 5));
		newsstand.createOrders();
		
		int expectedSize = newsstand.getOrders().size();
		
		newsstand.getOrders().clear();
		
		assertEquals(expectedSize, newsstand.getOrders().size());
	}
}