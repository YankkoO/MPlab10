package uo.mp.lab09.newsstand.service.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Publication;

/**
 * Scenarios:
 * 1- Empty list of lines
 * 2- A valid newspaper
 * 3- Several valid newspapers
 * 4- An invalid newspaper (wrong data)
 * 5- An invalid newspaper (wrong number of fields)
 * 6- An invalid type of publication
 * 7- An empty line (must be ignored)
 */
class PublicationParserTests {

	private PublicationParser parser;
	private List<String> lines;

	@BeforeEach
	public void setUp() {
		parser = new PublicationParser();
		lines = new ArrayList<>();
	}

	/**
	 * GIVEN: An empty list of lines
	 * WHEN: parse() is called
	 * THEN: An empty list of publications is returned
	 */
	@Test
	public void emptyList() {
		List<Publication> result = parser.parse(lines);
		
		assertEquals(0, result.size());
	}

	/**
	 * GIVEN: A list with a valid newspaper line
	 * WHEN: parse() is called
	 * THEN: A list with 1 publication is returned
	 */
	@Test
	public void validNewspaper() {
		lines.add("newspaper\tEl Pais\t10\t5");
		
		List<Publication> result = parser.parse(lines);
		
		assertEquals(1, result.size());
	}

	/**
	 * GIVEN: A list with several valid newspaper lines
	 * WHEN: parse() is called
	 * THEN: A list with all the valid publications is returned
	 */
	@Test
	public void severalValidNewspapers() {
		lines.add("newspaper\tEl Pais\t10\t5");
		lines.add("newspaper\tEl Mundo\t5\t2");
		lines.add("newspaper\tABC\t12\t8");
		
		List<Publication> result = parser.parse(lines);
		
		assertEquals(3, result.size());
	}

	/**
	 * GIVEN: A list with a newspaper line containing invalid data
	 * WHEN: parse() is called
	 * THEN: The line is ignored and an empty list is returned
	 */
	@Test
	public void invalidNewspaperWrongData() {
		lines.add("newspaper\tEl Pais\t10\txxyz"); // Letras en las ventas
		
		List<Publication> result = parser.parse(lines);
		
		assertEquals(0, result.size());
	}

	/**
	 * GIVEN: A list with a newspaper line with too many fields
	 * WHEN: parse() is called
	 * THEN: The line is ignored and an empty list is returned
	 */
	@Test
	public void invalidNewspaperWrongFields() {
		lines.add("newspaper\tEl Pais\t10\t5\textra"); // 5 campos en vez de 4
		
		List<Publication> result = parser.parse(lines);
		
		assertEquals(0, result.size());
	}

	/**
	 * GIVEN: A list with a line starting with an unknown publication type
	 * WHEN: parse() is called
	 * THEN: The line is ignored and an empty list is returned
	 */
	@Test
	public void invalidType() {
		lines.add("comic\tSpiderman\t10\t5"); // Tipo 'comic' no existe
		
		List<Publication> result = parser.parse(lines);
		
		assertEquals(0, result.size());
	}

	/**
	 * GIVEN: A list with an empty line
	 * WHEN: parse() is called
	 * THEN: The line is ignored and an empty list is returned
	 */
	@Test
	public void emptyLine() {
		lines.add(""); 
		
		List<Publication> result = parser.parse(lines);
		
		assertEquals(0, result.size());
	}
}