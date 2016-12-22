package com.example.android0128.introductionmvp.util.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android0128.introductionmvp.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rubymobile on 7/26/16.
 */
public class RequestManager {

    private static Services defaultRequestManager;
    private static Retrofit retrofit;

    public RequestManager(Context context) {
        retrofit = generateRetrofit(context);
        defaultRequestManager = retrofit.create(Services.class);
    }

    public RequestManager() {
        retrofit = generateRetrofit();
        defaultRequestManager = retrofit.create(Services.class);
    }

    public Services getWebServices(){
        return defaultRequestManager;
    }

    private static Retrofit generateRetrofit() {
        Gson gson = new GsonBuilder().create();

        final OkHttpClient client = getOkHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL);
        builder = builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.client(client).build();
    }

    private static Retrofit generateRetrofit(final Context context) {
        Gson gson = new GsonBuilder().create();

        final OkHttpClient client = getOkHttpClient(context);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL);
        builder = builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.client(client).build();
    }

    private static OkHttpClient getOkHttpClient(final Context context) {
        RetrofitAuthenticator authAuthenticator = new RetrofitAuthenticator(context);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(12, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .authenticator(authAuthenticator);

        //For adding logs of APIs requests & responses
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(interceptor);

        //General interceptor with authorization token
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                String token = getAuthToken(context);

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", token)
                        .method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });

        return builder.build();
    }

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(12, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS);

        //For adding logs of APIs requests & responses
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        //General interceptor
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });

        return builder.build();
    }

    private static String getAuthToken(Context context) {
        //TODO You have to replace this string with the authorization key from your session
        return "Replace this";
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
