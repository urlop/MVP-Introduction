package com.example.android0128.introductionmvp.data.network;

import com.example.android0128.introductionmvp.data.MovieModel;

import java.util.List;

/**
 * Created by tk-0130 on 9/28/16.
 */
public class MoviesList {
    List<MovieModel> results;
    int page;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
