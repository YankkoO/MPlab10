package uo.mp.lab09.newsstand.service.serializers;

import java.util.LinkedList;
import java.util.List;

import uo.mp.lab09.newsstand.domain.Publication;
import uo.mp.util.check.ArgumentChecks;

public class PublicationSerializer {


    /**
     * Converts a list of publications into a list of string representations.
     *
     * @param  publications             the list of publications to serialize
     * @return                          a list of serialized publication strings
     * @throws IllegalArgumentException if the provided list is null
     */
    public List<String> serialize(List<Publication> publications) {
    	ArgumentChecks.isNotNull(publications);
        List<String> res = new LinkedList<>();
        for (Publication p : publications) {
            res.add(p.serialize());
        }
        return res;
    }

}
