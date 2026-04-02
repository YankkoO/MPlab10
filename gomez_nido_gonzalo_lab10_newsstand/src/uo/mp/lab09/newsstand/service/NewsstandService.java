package uo.mp.lab09.newsstand.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InvalidNameException;

import uo.mp.lab09.newsstand.domain.Order;
import uo.mp.lab09.newsstand.domain.Publication;
import uo.mp.lab09.newsstand.service.parsers.PublicationParser;
import uo.mp.lab09.newsstand.service.serializers.OrderSerializer;
import uo.mp.lab09.newsstand.service.serializers.PublicationSerializer;
import uo.mp.lab09.util.file.FileUtil;
import uo.mp.lab09.util.file.ZipFileUtil;
import uo.mp.lab09.util.log.Logger;
import uo.mp.util.check.ArgumentChecks;

/**
 * Core class of the project. Manages the newsstand.
 * Holds publications, together with the stock info, and generates orders to
 * replenish the stock
 * 
 * @version 2026
 *
 */
public class NewsstandService {

    private List<Publication> publications = new LinkedList<>();
    private List<Order> orders = new LinkedList<>();

    /**
     * Loads from a plain text file information about publications.
     * 
     * @param  FileName,              the name of the file, must be at least
     *                                  5 characters long
     * @return                          the number of publications loaded from
     *                                  file
     * @throws InvalidNameException     if the file name is invalid
     * @throws FileNotFoundException    if file does not exist (session 10)
     * @throws IllegalArgumentException if the parameter is null or empty
     * @throws NewsstandException       if the publication is duplicated
     */
    public void loadFile(String FileName) throws NewsstandException, FileNotFoundException {
    	ArgumentChecks.isNotEmpty(FileName);
    	ArgumentChecks.isNotNull(FileName);
    	if(FileName.length() < 5) {
    		throw new NewsstandException("Invalid file name");
    	}
        List<String> lines = new FileUtil().readLines(FileName);
        List<Publication> filePublications = new PublicationParser().parse(lines);
        addPublications(filePublications);
    }

    /**
     * Adds all publications from the given list to the current list.
     * Publications with duplicate names (case-insensitive) will be ignored, and
     * a message will be logged.
     *
     * @param newPublications the list of publications to add
     */
    private void addPublications(List<Publication> newPublications) {
        for (Publication p : newPublications) {
            try {
				addPublication(p);
			} catch (NewsstandException e) {
				Logger.log(e.getMessage());
			}
        }
    }

    /**
     * Adds the publication if it is name is not already registered
     * 
     * @param  p,                       the publication to be added
     * @throws NewsstandException       if the publication's name is already
     *                                  registered
     * @throws IllegalArgumentException if the argument is null
     * @throws NewsstandException       if the publication is duplicated
     */
    public void addPublication(Publication p) throws NewsstandException {
    	if(publications.contains(p)) {
    		throw new NewsstandException("Publication already existing.");
    	}
        publications.add(p);
    }

    /**
     * Removes the product matching the name passed as argument
     * 
     * @param  name                     of the publication to be removed
     * @throws NewsstandException       if the publication does not exist
     * @throws IllegalArgumentException if the parameter is null or empty
     */
    public void removePublication(String name) throws NewsstandException {
    	ArgumentChecks.isNotNull(name);
    	
        int pos = searchByName(name);
        
        if(pos == -1) {
        	throw new NewsstandException("The publication " + name + " does not exist");
        }
        
        publications.remove(pos);
    }

    private int searchByName(String name) {
        for (int i = 0; i < publications.size(); i++) {
            Publication p = publications.get(i);
            if ((p.getName().equals(name)) /* compare both names */ ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a shallow copy of the list of publications, to preserve
     * encapsulation.
     * A copy constructor is required when replacing java lists with our
     * list implementations.
     * 
     * @return a copy of the internal list of publications
     */
    public List<Publication> getPublications() {
        return new ArrayList<Publication>(publications);
    }

    /**
     * Generates a list of orders. One for every product with stock under limits
     */
    public void createOrders() {
    	orders.clear();
    	
        for(Publication p : publications) {
        	if(p.needsOrder()) {
        		orders.add(p.generateOrder());
        	}
        }
    }

    /**
     * Returns a shallow copy of the list of orders to preserve encapsulation.
     * A copy constructor is required when replacing Java lists with custom list
     * implementations.
     *
     * @return a copy of the internal list of orders
     */
    public List<Order> getOrders() {
        return new ArrayList<Order>(orders);
    }

    /**
     * Save all orders to a file with the given format
     * 
     * @param  fileName
     * @throws InvalidNameException     if the file name is invalid
     * @throws IllegalArgumentException if the parameter is null or empty
     */
    public void saveOrdersToFile(String fileName) {
    	ArgumentChecks.isNotEmpty(fileName);
    	ArgumentChecks.isNotNull(fileName);
    	
        OrderSerializer serializer = new OrderSerializer();
        List<String> lines = serializer.serialize(orders);
        new FileUtil().writeLines(fileName, lines);
    }

    /**
     * Imports all the publications from the zip file, overwriting the
     * previous ones.
     * 
     * @param  fileName
     * @throws NewsstandException 
     * @throws InvalidNameException     if the file name is invalid
     * @throws FileNotFoundException    if file does not exist (session 10)
     * @throws IllegalArgumentException if the parameter is null or empty
     */
    public void importPublicationsFromZipFile(String fileName) throws NewsstandException, FileNotFoundException {
    	ArgumentChecks.isNotNull(fileName);
    	ArgumentChecks.isNotEmpty(fileName);
    	
    	if(fileName.length() < 5) {
    		throw new NewsstandException("Invalid file name");
    	}
        List<String> lines = new ZipFileUtil().readLines(fileName);
        List<Publication> filePublications = new PublicationParser().parse(lines);
        addPublications(filePublications);
    }

    /**
     * Saves all the publications to a zip file. The file produced can be
     * read
     * by the method @see importPublicationsFromZipFile
     * 
     * @param  fileName
     * @throws IllegalArgumentException if the parameter is null or empty
     * @throws InvalidNameException     if the file name is invalid
     */
    public void exportPublicationsToZipFile(String fileName) {
    	ArgumentChecks.isNotNull(fileName);
    	ArgumentChecks.isNotEmpty(fileName);
        PublicationSerializer serializer = new PublicationSerializer();
        List<String> lines = serializer.serialize(publications);
        new ZipFileUtil().writeLines(fileName, lines);
    }

}
