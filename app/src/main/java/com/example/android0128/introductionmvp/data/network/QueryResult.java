package com.example.android0128.introductionmvp.data.network;

import com.example.android0128.introductionmvp.data.QueryModel;

import java.util.List;

/**
 * Created by tk-0130 on 9/28/16.
 */
public class QueryResult {
    List<QueryModel> results;
    int page;

    public List<QueryModel> getResults() {
        return results;
    }

    public void setResults(List<QueryModel> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
