package uo.mp.lab09.newsstand.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab09.newsstand.domain.Newspaper;
import uo.mp.lab09.newsstand.domain.Publication;

/**
 * Scenarios
 * 1. loadFileFileNotFound
 * 2. importZipFileNotFound
 * 3. importZipFileOverwrites
 */
public class NewsstandLoadFileTests {

    private NewsstandService newsstand;

    @BeforeEach
    public void setUp() {
        newsstand = new NewsstandService();
    }

    /**
     * GIVEN: A non-existent plain text file name.
     * WHEN: loadFile() is invoked.
     * THEN: A FileNotFoundException is thrown.
     */
    @Test
    public void testLoadFileFileNotFound() {
        String nonExistingFile = "non_existent_file.txt";

        assertThrows(FileNotFoundException.class, () -> {
            newsstand.loadFile(nonExistingFile);
        });
    }

    /**
     * GIVEN: A non-existent compressed file name.
     * WHEN: importPublicationsFromZipFile() is invoked.
     * THEN: A FileNotFoundException is thrown.
     */
    @Test
    public void testImportZipFileNotFound() {
        String nonExistingZip = "non_existent_archive.gz";

        assertThrows(FileNotFoundException.class, () -> {
            newsstand.importPublicationsFromZipFile(nonExistingZip);
        });
    }

    /**
     * GIVEN: A NewsstandService instance with prior publications in memory and a valid ZIP file.
     * WHEN: importPublicationsFromZipFile() is invoked.
     * THEN: The internal list is cleared and exclusively overwritten with the file's content.
     */
    @Test
    public void testImportZipFileOverwrites() throws NewsstandException, FileNotFoundException {
        
        String oldPublicationName = "Obsolete Publication";
        newsstand.addPublication(new Newspaper(oldPublicationName, 10, 5));

        
        String validZipFileName = "pubs.txt.gz";
        newsstand.importPublicationsFromZipFile(validZipFileName);

        boolean containsOld = false;
        for(Publication p : newsstand.getPublications()) {
        	if(p.getName().equals(oldPublicationName)) {
        		containsOld = true;
        	}
        }

        assertFalse(containsOld);
    }

}