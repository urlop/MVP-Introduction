package com.example.android0128.introductionmvp.data.source.remote;

import com.example.android0128.introductionmvp.util.Constants;
import com.example.android0128.introductionmvp.util.network.CustomCallback;
import com.example.android0128.introductionmvp.util.network.Services;

/**
 * Created by android0128 on 12/26/16.
 */

public class QueryInteractor {

    private final Services networkService;

    public QueryInteractor(Services networkService) {
        this.networkService = networkService;
    }

    public void requestMovies(String language, int page, final QueryCallback queryCallback) {
        networkService.getPopularMovies(Constants.API_KEY, language, page).enqueue(new CustomCallback(queryCallback));
    }
}
