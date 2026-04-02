package uo.mp.lab09.newsstand.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Magazine;
import uo.mp.lab09.newsstand.domain.Magazine.Frequency;
import uo.mp.lab09.newsstand.domain.Newspaper;

/**
 * Scenarios
 * 1- Newsstand with a newspaper that has enough copies in stock
 * 2- Newsstand with a newspaper with stock exactly at the limit
 * 3- Newsstand with a newspaper with less stock than the minimum required to place an order
 *
 * 4- Newsstand with a magazine that has more stock than the minimum required to place an order
 * 5- Newsstand with a magazine with stock exactly at the limit
 * 6- Newsstand with a magazine with less stock than the minimum required to place an order
 *
 * 7- Newsstand with more than one newspaper and one magazine
 * 8- Newsstand without publications
 */
class NewsstandCreateOrdersTests {
	
	private NewsstandService newsstand;

	@BeforeEach
	public void setUp() {
		newsstand = new NewsstandService();
	}

	/**
	 * GIVEN: Newsstand with a newspaper that has enough copies in stock
	 * WHEN: createOrders() is called
	 * THEN: No order is generated (0)
	 */
	@Test
	public void newspaperEnoughCopies() throws NewsstandException {
		newsstand.addPublication(new Newspaper("El Pais", 10, 5));
		
		newsstand.createOrders();
		
		assertEquals(0, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand with a newspaper with stock exactly at the limit
	 * WHEN: createOrders() is called
	 * THEN: An order is generated (1)
	 */
	@Test
	public void newspaperExactLimit() throws NewsstandException {
		newsstand.addPublication(new Newspaper("El Pais", 5, 5));
		
		newsstand.createOrders();
		
		assertEquals(1, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand with a newspaper with less stock than the minimum required to place an order
	 * WHEN: createOrders() is called
	 * THEN: An order is generated (1)
	 */
	@Test
	public void newspaperLessStock() throws NewsstandException {
		newsstand.addPublication(new Newspaper("El Pais", 2, 5));
		
		newsstand.createOrders();
		
		assertEquals(1, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand with a magazine that has more stock than the minimum required to place an order
	 * WHEN: createOrders() is called
	 * THEN: No order is generated (0)
	 */
	@Test
	public void magazineMoreThanLimit() throws NewsstandException {
		newsstand.addPublication(new Magazine("Hola", 10, 5, Frequency.WEEKLY));
		
		newsstand.createOrders();
		
		assertEquals(0, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand with a magazine with stock exactly at the limit
	 * WHEN: createOrders() is called
	 * THEN: An order is generated (1)
	 */
	@Test
	public void magazineExactLimit() throws NewsstandException {
		newsstand.addPublication(new Magazine("Hola", 5, 5, Frequency.WEEKLY));
		
		newsstand.createOrders();
		
		assertEquals(1, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand with a magazine with less stock than the minimum required to place an order
	 * WHEN: createOrders() is called
	 * THEN: An order is generated (1)
	 */
	@Test
	public void magazineLessStock() throws NewsstandException {
		newsstand.addPublication(new Magazine("Hola", 2, 5, Frequency.WEEKLY));
		
		newsstand.createOrders();
		
		assertEquals(1, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand with more than one newspaper and one magazine
	 * WHEN: createOrders() is called
	 * THEN: As many orders as publications that need it are generated (2)
	 */
	@Test
	public void multiplePublications() throws NewsstandException {
		newsstand.addPublication(new Newspaper("El Pais", 2, 5)); // Necesita pedido
		newsstand.addPublication(new Newspaper("El Mundo", 10, 5)); // No necesita pedido
		newsstand.addPublication(new Magazine("Hola", 5, 5, Frequency.WEEKLY)); // Necesita pedido
		
		newsstand.createOrders();
		
		assertEquals(2, newsstand.getOrders().size());
	}

	/**
	 * GIVEN: Newsstand without publications
	 * WHEN: createOrders() is called
	 * THEN: No orders are generated (0)
	 */
	@Test
	public void emptyNewsstand() {
		newsstand.createOrders();
		
		assertEquals(0, newsstand.getOrders().size());
	}
}