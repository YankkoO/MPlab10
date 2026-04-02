package uo.mp.lab09.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil {

    /**
     * Fake method to read lines from a file
     * 
     * Read all lines from a file. A line is considered to be terminated by a
     * line feed ('\n').
     * Each item in the list (line) will contain the contents of a line, not
     * including any line-termination characters.
     * 
     * @param  pathToTheFile path to a plain text file
     * @return               the lines from the file as a List
     * 
     * @param pathToTheFile
     * @return
     * @throws FileNotFoundException 
     */
    public List<String> readLines(String fileName) throws FileNotFoundException  {
        List<String> res = new ArrayList<>();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        
    	try {
			try {
				String str;
				while( (str = in.readLine()) != null ) {
					res.add(str);
				}
			} finally {
				in.close();
			}
			
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
        
        return res;
    }

    /**
     * Fake method to write lines to a file
     * 
     * Write to a plain text file all strings in the list. Lines will be
     * separated by a line feed ('\n').
     * Each item in the list (line) contains the contents for one line, not
     * including any
     * line-termination characters.
     * 
     * @param  pathToTheFile path to a plain text file
     * @param  lines         the List of Strings to be writen to the file
     * @throws IOException
     */
    public void writeLines(String fileName, List<String> lines) {
    	try {
    		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            try {
				for(String line : lines) {
					out.write(line);
					out.newLine();
				}
			} finally {
				out.close();
			}	
    	} catch (IOException e) {
    		throw new UncheckedIOException(e);
    	} 
    }

}
