package com.st.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SearcherTest {
    
    @Test
    public void testSearcherEmptyIndex() {
        Map<String, List<String>> invertedIndex = new HashMap<>();
        List<Result> results = Searcher.run(invertedIndex, "test");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearcher() {
        Map<String, List<String>> invertedIndex = new HashMap<>();
        invertedIndex.put("test", List.of("file1"));
        invertedIndex.put("unit", List.of("file1", "file2"));
        invertedIndex.put("java", List.of("file1", "file2", "file3"));
        List<Result> results = Searcher.run(invertedIndex, "java unit test");
        assertEquals(3, results.size());
        assertEquals("file1", results.get(0).getFile());
        assertEquals(100, results.get(0).getScore());
        assertEquals("file2", results.get(1).getFile());
        assertEquals(66, results.get(1).getScore());
        assertEquals("file3", results.get(2).getFile());
        assertEquals(33, results.get(2).getScore());
    }

}

