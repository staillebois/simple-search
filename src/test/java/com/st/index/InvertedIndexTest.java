package com.st.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;

import org.junit.Test;

public class InvertedIndexTest {

    @Test
    public void testEmptyCreate() throws IOException {
        File file = new File("src/test/resources/empty");
        Path emptyDirectory = file.toPath();
        Map<String, List<String>> invertedIndex = InvertedIndex.create(emptyDirectory);
        assertTrue(invertedIndex.isEmpty()); 
    }
    
    @Test
    public void testCreate() throws IOException {
        File file = new File("src/test/resources/files");
        Path multipleFilesDirectory = file.toPath();
        Map<String, List<String>> invertedIndex = InvertedIndex.create(multipleFilesDirectory);
        assertEquals(136, invertedIndex.size());
        assertTrue(invertedIndex.containsKey("lorem"));
        assertEquals(2, invertedIndex.get("lorem").size());
        assertTrue(invertedIndex.get("lorem").contains(multipleFilesDirectory.resolve("file1.txt").toString()));
        assertTrue(invertedIndex.get("lorem").contains(multipleFilesDirectory.resolve("subpath/file3.txt").toString()));
    }
}
