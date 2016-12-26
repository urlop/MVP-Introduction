package com.example.android0128.introductionmvp.data.source.remote;

/**
 * Created by android0128 on 12/26/16.
 */

public interface QueryCallback {

    void onQueryCallSuccess(Object object);
    void onQueryCallError(Object object);

}
