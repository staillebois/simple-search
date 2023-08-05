package com.st;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.st.index.InvertedIndex;
import com.st.search.Result;
import com.st.search.Searcher;

public class SimpleSearch {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }
        Map<String, List<String>> invertedIndex = InvertedIndex.create(Paths.get(args[0]));
        searchForQuery(invertedIndex);
    }

    private static void searchForQuery(Map<String, List<String>> invertedIndex) {
        try (Scanner keyboard = new Scanner(System.in)) {
            while (true) {
                System.out.print("search> ");
                String query = keyboard.nextLine().toLowerCase();
                if (query.equals(":quit")) {
                    break;
                }
                List<Result> results = Searcher.run(invertedIndex, query);
                display(results);
            }
        }
    }

    private static void display(List<Result> results) {
        System.out.println("Results:");
        if (results.size() == 0) {
            System.out.println("No results found.");
        } else {
            for (Result result : results) {
                System.out.println(result.getFile() + ": " + result.getScore() + "%");
            }
        }
    }
}