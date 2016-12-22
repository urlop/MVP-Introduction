package com.example.android0128.introductionmvp.util.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;


public class RetrofitAuthenticator implements Authenticator {
    Context context;

    public RetrofitAuthenticator(Context context) {
        this.context = context;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        try {
            /*
            TODO Call refresh token service using an asynchronous request
            Ex:
            token = call.execute().body().getToken();
             */

            //TODO save new token in SharedPreferences

            // Add new header to rejected request and retry it
            return response.request().newBuilder()
                    .header("Authorization", "New retrieved token HERE") //TODO Insert new retrieved token
                    .build();
        } catch (Exception e) {
            //TODO logout and render to initial view (like Splash) and delete previous activity history
            return null;
        }
    }
}