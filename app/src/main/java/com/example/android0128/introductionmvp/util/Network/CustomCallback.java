package com.example.android0128.introductionmvp.util.Network;

import android.content.Context;
import android.util.Log;

import com.example.android0128.introductionmvp.R;
import com.example.android0128.introductionmvp.util.UIHelper;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by tk-0130 on 11/8/16.
 */

public class CustomCallback<T> implements Callback<T> {

    private final Context context;
    private boolean showAPIError;

    public CustomCallback(Context context, boolean showAPIError) {
        this.context = context;
        this.showAPIError = showAPIError;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(!response.isSuccessful() && showAPIError){
            String error;
            try {
                APIError apiError;
                Converter<ResponseBody, APIError> converter = RequestManager.getRetrofit().responseBodyConverter(APIError.class, new Annotation[0]);
                apiError = converter.convert(response.errorBody());
                error = apiError.getError();
            } catch (Exception e) {
                error = context.getString(R.string.ws_error);
            }
            UIHelper.createErrorDialog(null,error,context);
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("ERROR", t.getMessage());
    }
}
