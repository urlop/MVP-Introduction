package com.example.android0128.introductionmvp.data.source;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

@Table(name = "queryResponse")
public class QueryResponse extends SugarRecord implements Parcelable {

    @SerializedName("id")
    public String response_id;
    public String poster_path;
    public Double popularity;
    public String overview;
    public String media_type;
    public String first_air_date;
    public String name;
    public String profile_path;
    public String title;
    public String backdrop_path;
    public String language;

    protected QueryResponse(Parcel in) {
        response_id = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        media_type = in.readString();
        first_air_date = in.readString();
        name = in.readString();
        profile_path = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        language = in.readString();
    }

    public QueryResponse() {
    }
    public static final Creator<QueryResponse> CREATOR = new Creator<QueryResponse>() {
        @Override
        public QueryResponse createFromParcel(Parcel in) {
            return new QueryResponse(in);
        }

        @Override
        public QueryResponse[] newArray(int size) {
            return new QueryResponse[size];
        }
    };

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(response_id);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(media_type);
        parcel.writeString(first_air_date);
        parcel.writeString(name);
        parcel.writeString(profile_path);
        parcel.writeString(title);
        parcel.writeString(backdrop_path);
        parcel.writeString(language);
    }
}
