package com.example.android0128.introductionmvp.util.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {

    @SerializedName("error")
    @Expose
    private String error;

    /**
     *
     * @return
     * The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(String error) {
        this.error = error;
    }
}