package com.example.android0128.introductionmvp.data.source.remote;

import com.example.android0128.introductionmvp.data.network.MoviesList;
import com.example.android0128.introductionmvp.util.Constants;
import com.example.android0128.introductionmvp.util.network.APIError;
import com.example.android0128.introductionmvp.util.network.RequestManager;
import com.example.android0128.introductionmvp.util.network.Services;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by android0128 on 12/26/16.
 */

public class QueryInteractor {

    private final Services networkService;

    public QueryInteractor(Services networkService) {
        this.networkService = networkService;
    }

    public void requestMovies(String language, int page, final QueryCallback queryCallback) {
        networkService.getPopularMovies(Constants.API_KEY, language, page).enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.isSuccessful()) {
                    queryCallback.onQueryCallSuccess(response.body());
                } else {
                    onNotSuccess(response, queryCallback);
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                queryCallback.onQueryCallError("Error");
            }
        });
    }

    private void onNotSuccess(Object response, QueryCallback queryCallback) {
        String error;
        try {
            APIError apiError;
            Converter<ResponseBody, APIError> converter = RequestManager.getRetrofit().responseBodyConverter(APIError.class, new Annotation[0]);
            apiError = converter.convert(((Response) response).errorBody());
            error = apiError.getError();
            queryCallback.onQueryCallError(error);
        } catch (Exception e) {
            queryCallback.onQueryCallError(null);
        }
    }
}
