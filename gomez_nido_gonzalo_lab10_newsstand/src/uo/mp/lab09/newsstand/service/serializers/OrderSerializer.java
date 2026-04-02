package uo.mp.lab09.newsstand.service.serializers;

import java.util.ArrayList;
import java.util.List;

import uo.mp.lab09.newsstand.domain.Order;
import uo.mp.util.check.ArgumentChecks;

public class OrderSerializer {

    /**
     * Converts a list of orders into a list of string representations.
     *
     * @param  orders                   the list of orders to convert
     * @return                          a list of serialized order strings
     * @throws IllegalArgumentException if the provided list is null
     */
    public List<String> serialize(List<Order> orders) {
    	ArgumentChecks.isNotNull(orders);
    	List<String> res = new ArrayList<>();
        for(Order o : orders) {
        	String line = o.serialize();
        	res.add(line);
        }
        return res;
    }

}
