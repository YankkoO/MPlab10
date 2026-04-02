package uo.mp.lab09.newsstand.service.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Newspaper;
import uo.mp.lab09.newsstand.domain.Order;

/**
 * Scenarios:
 * 1. Empty list
 * 2. List with multiple orders
 */
public class OrderSerializerTest {

    private OrderSerializer serializer;

    @BeforeEach
    public void setUp() {
        serializer = new OrderSerializer();
    }

    /**
     * GIVEN: An empty list of orders.
     * WHEN: serialize() is invoked.
     * THEN: It returns an empty list of strings.
     */
    @Test
    public void testSerializeEmptyList() {
        List<Order> orders = new ArrayList<>();
        
        List<String> result = serializer.serialize(orders);
        
        assertEquals(0, result.size());
    }

    /**
     * GIVEN: A list containing multiple valid orders.
     * WHEN: serialize() is invoked.
     * THEN: It returns a list of strings correctly formatted matching the serialization of each order.
     */
    @Test
    public void testSerializeMultipleOrders() {
        List<Order> orders = new ArrayList<>();
        
        Order order1 = new Newspaper("El Pais", 2, 5).generateOrder();
        Order order2 = new Newspaper("El Mundo", 1, 10).generateOrder();
        
        orders.add(order1);
        orders.add(order2);
        
        List<String> result = serializer.serialize(orders);
        
        assertEquals(2, result.size());
        assertEquals("El Pais\t9", result.get(0));
        assertEquals("El Mundo\t12", result.get(1));
    }

}