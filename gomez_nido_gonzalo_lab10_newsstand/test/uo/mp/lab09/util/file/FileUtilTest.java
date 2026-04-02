package uo.mp.lab09.util.file;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Scenarios:
 * 1. Read lines from a non-existent file throws FileNotFoundException.
 */
public class FileUtilTest {

    private FileUtil fileUtil;

    @BeforeEach
    public void setUp() {
        fileUtil = new FileUtil();
    }

    /**
     * GIVEN: A file name that does not exist in the file system.
     * WHEN: readLines() is invoked.
     * THEN: A FileNotFoundException is thrown.
     */
    @Test
    public void testReadLinesFileNotFound() {
        String nonExistentFileName = "non_existent_plain_text_file.txt";

        assertThrows(FileNotFoundException.class, () -> {
            fileUtil.readLines(nonExistentFileName);
        });
    }

}