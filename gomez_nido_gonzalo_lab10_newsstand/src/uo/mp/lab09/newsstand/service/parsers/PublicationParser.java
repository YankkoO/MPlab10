package uo.mp.lab09.newsstand.service.parsers;


import java.util.ArrayList;
import java.util.List;

import uo.mp.lab09.newsstand.domain.Magazine;
import uo.mp.lab09.newsstand.domain.Magazine.Frequency;
import uo.mp.lab09.newsstand.domain.Newspaper;
import uo.mp.lab09.newsstand.domain.Publication;
import uo.mp.lab09.util.log.Logger;
import uo.mp.util.check.ArgumentChecks;

public class PublicationParser {

    

		

	/**
     * Converts a list of strings into a list of Publication instances.
     * The following lines are considered invalid in the input file:
     * - Incorrect publication type
     * - Incorrect number of fields
     * - Invalid data format in numeric fields
     * 
     * Invalid lines will not result in a Publication instance being created.
     * Instead, an InvalidLineFormatException will be thrown. A message will
     * then be logged (using Log) as part of the exception handling.
     * 
     * @param  lines                    the list of strings, which may be empty,
     *                                  where each string represents a
     *                                  publication.
     *                                  Each line should follow this format:
     *                                  type_of_publication \t
     *                                  name_of_publication \t sales \t stock \t
     *                                  frequency
     * @return                          a list of publications created from the
     *                                  valid lines
     * @throws IllegalArgumentException if the provided list is null
     */
    public List<Publication> parse(List<String> lines) {
    	
    	ArgumentChecks.isNotNull(lines); 
    	List<Publication> res = new ArrayList<>();
    	
    	for(String line : lines) {
    		try {
    		Publication p = parseLine(line);
    		res.add(p);
    		} catch (InvalidLineException e) {
    			Logger.log(e.getMessage()); // "Line " + ln + " " + 
    		} catch (IllegalArgumentException e){
    			Logger.log(e.getMessage());
    		}
    	}
   
    	return res;
    }

	private Publication parseLine(String line) throws InvalidLineException {
		String[] parts = line.split("\t");
		
		String type = parts[0].trim();
		
		switch(type) {
		case "newspaper": return parseNewspaper(parts);
		case "magazine": return parseMagazine(parts);
		}
		
		throw new InvalidLineException("Unknown type");
	}

	private Publication parseMagazine(String[] parts) throws InvalidLineException {
		
		if(parts.length != 5) {
			throw new InvalidLineException("Wrong number of fields for a magazine");
		}
		
		String name = parts[1].trim();
		int stock = toInteger(parts[2].trim());
		int sales = toInteger(parts[3].trim());
		Frequency frequency = toFrequency(parts[4].trim());
		
		
		return new Magazine(name, stock,sales,frequency);
	}

	private Frequency toFrequency(String string) throws InvalidLineException {
		
		if(!Frequency.getNames().contains(string.toLowerCase())) {
			throw new InvalidLineException("Unknown type");
		}
		
		return Frequency.valueOf(string.toUpperCase());
	}

	private Publication parseNewspaper(String[] parts) throws InvalidLineException {
		
		if(parts.length != 4) {
			throw new InvalidLineException("Wrong number of fields for a newspaper");
		}
		
		String name = parts[1].trim();
		int stock = toInteger(parts[2].trim());
		int sales = toInteger(parts[3].trim());
		return new Newspaper(name, stock, sales);
	}

	private int toInteger(String string) throws InvalidLineException {
		try {
		return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new InvalidLineException( e );
		}
	}
	
	
	
	@SuppressWarnings("serial")
	public class InvalidLineException extends Exception {
	
	public InvalidLineException() {
	}

	public InvalidLineException(String message) {
		super(message);
	}

	public InvalidLineException(Throwable cause) {
		super(cause);
	}

	public InvalidLineException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLineException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

    }
}
	


