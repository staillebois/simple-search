package com.st.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Searcher {

    private Searcher() {
    }

    public static List<Result> run(Map<String, List<String>> invertedIndex, String query) {
        // split("\\W+") will match any non-word character
        String[] terms = query.split("\\W+");
        Map<String, Integer> scores = new HashMap<>();
        for (String term : terms) {
            if (invertedIndex.containsKey(term)) {
                List<String> documents = invertedIndex.get(term);
                for (String doc : documents) {
                    if (scores.containsKey(doc)) {
                        scores.put(doc, scores.get(doc) + 1);
                    } else {
                        scores.put(doc, 1);
                    }
                }
            }
        }
        List<Result> results = createResult(terms, scores);
        return results;
    }

    private static List<Result> createResult(String[] terms, Map<String, Integer> scores) {
        List<Result> results = new ArrayList<>();
        for (String file : scores.keySet()) {
            int rankScore = (scores.get(file) * 100) / terms.length;
            Result result = new Result(file, rankScore);
            results.add(result);
        }
        Collections.sort(results);
        return results.stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
