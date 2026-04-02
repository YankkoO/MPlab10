package uo.mp.lab09.newsstand.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Magazine.Frequency;

/**
 * Scenarios:
 * 1- stock less than 5
 * 
 * 2- stock equal to 5 and periodicity WEEKLY
 * 3- stock equal to 5 and periodicity MONTHLY
 * 4- stock equal to 5 and periodicity QUARTERLY
 * 
 * 5- stock greater than 5 and periodicity WEEKLY
 * 6- stock greater than 5 and periodicity MONTHLY
 * 7- stock greater than 5 and periodicity QUARTERLY
 */
class MagazineGenerateOrderTests {
	
	
	/**
	 * GIVEN:A magazine with less than 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: Alert quantity is ordered (20)
	 */
	@Test
	public void stockLessThanFive() {
		Publication magazine = new Magazine("Hola", 3, 12, Frequency.MONTHLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals( 20 , o.getQuantity());
	}
	
	/**
	 * GIVEN: A weekly magazine with 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold are ordered
	 */
	@Test
	public void weeklyFiveInStock() {
		Publication magazine = new Magazine("Hola", 5, 10, Frequency.WEEKLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals(10, o.getQuantity());
	}
	
	/**
	 * GIVEN: A monthly magazine with 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold plus the ones left in the stock are ordered
	 */
	@Test
	public void monthlyFiveInStock() {
		Publication magazine = new Magazine("Hola", 5, 10, Frequency.MONTHLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals(15, o.getQuantity());
	}
	
	/**
	 * GIVEN: A quarterly magazine with 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold plus the ones left in the stock are ordered
	 */
	@Test
	public void quarterlyFiveInStock() {
		Publication magazine = new Magazine("Hola", 5, 10, Frequency.QUARTERLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals(15, o.getQuantity());
	}
	
	/**
	 * GIVEN: A weekly magazine with mare than 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold are ordered
	 */
	@Test
	public void weeklyMoreThanFive() {
		Publication magazine = new Magazine("Hola", 8, 10, Frequency.WEEKLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals(10, o.getQuantity());
	}
	
	/**
	 * GIVEN: A monthly magazine with mare than 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold plus the ones sold are ordered
	 */
	@Test
	public void monthlyMoreThanFive() {
		Publication magazine = new Magazine("Hola", 8, 10, Frequency.MONTHLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals(18, o.getQuantity());
	}
	
	/**
	 * GIVEN: A monthly magazine with mare than 5 in stock
	 * WHEN: generateOrder() is called
	 * THEN: As many as there were sold plus the ones sold are ordered
	 */
	@Test
	public void quarterlyMoreThanFive() {
		Publication magazine = new Magazine("Hola", 8, 10, Frequency.QUARTERLY);
		
		Order o = magazine.generateOrder();
		
		assertEquals(18, o.getQuantity());
	}
}