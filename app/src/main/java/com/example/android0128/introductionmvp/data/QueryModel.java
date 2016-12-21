package com.example.android0128.introductionmvp.data;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tk-0130 on 9/28/16.
 */
public class QueryModel extends RealmObject implements Parcelable {
    @PrimaryKey
    public String id;
    public String poster_path;
    public Double popularity;
    public String overview;
    public String media_type;
    public String first_air_date;
    public String name;
    public String profile_path;
    public String title;
    public String backdrop_path;

    public QueryModel() {

    }

    protected QueryModel(Parcel in) {
        id = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        media_type = in.readString();
        first_air_date = in.readString();
        name = in.readString();
        profile_path = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readDouble();
    }

    public static final Creator<QueryModel> CREATOR = new Creator<QueryModel>() {
        @Override
        public QueryModel createFromParcel(Parcel in) {
            return new QueryModel(in);
        }

        @Override
        public QueryModel[] newArray(int size) {
            return new QueryModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(media_type);
        dest.writeString(first_air_date);
        dest.writeString(name);
        dest.writeString(profile_path);
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeDouble(popularity);
    }
}
