package uo.mp.lab09.util.file;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * A utility class to read/write text lines from/to a compressed text file
 * (.txt.gz)
 */
public class ZipFileUtil {

    /**
     * Fake method to read lines from a .gz file
     * 
     * Read all lines from a .gz file. A line is considered to be terminated by
     * a line feed ('\n').
     * Each item in the list (line) will contain the contents of a line, not
     * including any line-termination characters.
     * 
     * @param  pathToZippedFileName path to a compressed file
     * @return                      the lines from the file as a List
     * @throws FileNotFoundException 
     * @throws IOException
     */
    public List<String> readLines(String fileName) throws FileNotFoundException {
        List<String> res = new ArrayList<>();
        
        BufferedReader in = createBufferedReader(fileName);
        
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
    
    
    private BufferedReader createBufferedReader(String fileName) throws FileNotFoundException {
    	try {
    		return new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileName))));
    	} catch (FileNotFoundException e) {
    		throw e;
    	} catch ( IOException e) {
    		throw new UncheckedIOException(e);
    	}
    	
    }

    /**
     * Fake method to write lines to a .gz file
     * 
     * Write to a .gz file all strings in the list. Lines must be terminated by
     * a line feed ('\n').
     * Each item in the list (line) contains the contents for one line, not
     * including any
     * line-termination characters.
     * 
     * @param  pathToZippedFileName path to a compressed file
     * @param  lines                the List of strings to the written to the
     *                              file
     * @throws IOException
     */
    public void writeLines(String fileName, List<String> lines) {
    
    	try {
    		BufferedWriter out = new BufferedWriter(
    			new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(fileName))));
    	
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
