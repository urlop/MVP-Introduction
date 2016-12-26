package com.example.android0128.introductionmvp.util.network;

import com.example.android0128.introductionmvp.data.source.remote.QueryCallback;
import com.example.android0128.introductionmvp.util.network.APIError;
import com.example.android0128.introductionmvp.util.network.RequestManager;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by android0128 on 12/26/16.
 */

public class CustomCallback implements Callback {

    QueryCallback queryCallback;

    public CustomCallback(QueryCallback queryCallback) {
        this.queryCallback = queryCallback;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            queryCallback.onQueryCallSuccess(response.body());
        } else {
            String error;
            try {
                APIError apiError;
                Converter<ResponseBody, APIError> converter = RequestManager.getRetrofit().responseBodyConverter(APIError.class, new Annotation[0]);
                apiError = converter.convert(response.errorBody());
                error = apiError.getError();
                queryCallback.onQueryCallError(error);
            } catch (Exception e) {
                queryCallback.onQueryCallError(null);
            }
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        queryCallback.onQueryCallError("Error");
    }
}
