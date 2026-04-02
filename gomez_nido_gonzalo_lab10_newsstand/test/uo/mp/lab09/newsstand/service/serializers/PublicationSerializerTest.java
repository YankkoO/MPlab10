package uo.mp.lab09.newsstand.service.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Magazine;
import uo.mp.lab09.newsstand.domain.Magazine.Frequency;
import uo.mp.lab09.newsstand.domain.Newspaper;
import uo.mp.lab09.newsstand.domain.Publication;

/**
 * Scenarios:
 * 1. Empty list
 * 2. List with multiple publications (Newspaper and Magazine)
 */
public class PublicationSerializerTest {

    private PublicationSerializer serializer;

    @BeforeEach
    public void setUp() {
        serializer = new PublicationSerializer();
    }

    /**
     * GIVEN: An empty list of publications.
     * WHEN: serialize() is invoked.
     * THEN: It returns an empty list of strings.
     */
    @Test
    public void testSerializeEmptyList() {
        List<Publication> publications = new ArrayList<>();
        
        List<String> result = serializer.serialize(publications);
        
        assertEquals(0, result.size());
    }

    /**
     * GIVEN: A list containing multiple valid publications of different types.
     * WHEN: serialize() is invoked.
     * THEN: It returns a list of strings correctly formatted according to each publication type.
     */
    @Test
    public void testSerializeMultiplePublications() {
        List<Publication> publications = new ArrayList<>();
        
        Publication p1 = new Newspaper("El Pais", 10, 5);
        Publication p2 = new Magazine("Hola", 20, 10, Frequency.WEEKLY);
        
        publications.add(p1);
        publications.add(p2);
        
        List<String> result = serializer.serialize(publications);
        
        assertEquals(2, result.size());
        
        
        assertEquals("newspaper\tEl Pais\t10\t5", result.get(0));
        assertEquals("magazine\tHola\t20\t10\tWEEKLY", result.get(1)); 
    }

}