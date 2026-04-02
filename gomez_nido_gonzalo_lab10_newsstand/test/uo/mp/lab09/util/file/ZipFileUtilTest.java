package uo.mp.lab09.util.file;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Scenarios:
 * 1. Read lines from a non-existent compressed file throws FileNotFoundException.
 */
public class ZipFileUtilTest {

    private ZipFileUtil zipFileUtil;

    @BeforeEach
    public void setUp() {
        zipFileUtil = new ZipFileUtil();
    }

    /**
     * GIVEN: A compressed file name (.gz) that does not exist in the file system.
     * WHEN: readLines() is invoked.
     * THEN: A FileNotFoundException is thrown.
     */
    @Test
    public void testReadLinesFileNotFound() {
        String nonExistentZipFileName = "non_existent_archive.gz";

        assertThrows(FileNotFoundException.class, () -> {
            zipFileUtil.readLines(nonExistentZipFileName);
        });
    }

}