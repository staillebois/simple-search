package com.st.index;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvertedIndex {

    private InvertedIndex(){}
    
    public static Map<String, List<String>> create(Path rootPath) throws IOException{
        Map<String, List<String>> invertedIndex = new HashMap<>();
        List<Path> files = getFilePaths(rootPath);
        addToIndex(invertedIndex, files);
        System.out.println(files.size() + " files indexed from root directory " + rootPath.toAbsolutePath());
        return invertedIndex;
    }

    private static void addToIndex(Map<String, List<String>> invertedIndex, List<Path> files) throws IOException {
        for (Path file : files) {
            System.out.println("indexing file: " + file);
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                // split("\\W+") will match any non-word character
                String[] terms = line.toLowerCase().split("\\W+");
                for (String term : terms) {
                    if (invertedIndex.containsKey(term)) {
                        List<String> filesList = invertedIndex.get(term);
                        if (!filesList.contains(file.toString())) {
                            filesList.add(file.toString());
                        }
                    } else {
                        List<String> filesList = new ArrayList<>();
                        filesList.add(file.toString());
                        invertedIndex.put(term, filesList);
                    }
                }
            }
        }
    }

    private static List<Path> getFilePaths(Path rootPath) throws IOException {
        return Files.walk(rootPath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }
}
