package com.st.search;

public class Result implements Comparable<Result>{
    private String file;
    private int score;

    public Result(String file, int score) {
        this.file=file;
        this.score=score;
    }

    public String getFile() {
        return file;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Result result) {
        return Integer.compare(result.score, this.score);
    }
}
