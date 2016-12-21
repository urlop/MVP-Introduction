package com.example.android0128.introductionmvp.util.Network;

import com.example.android0128.introductionmvp.data.ActorResponse;
import com.example.android0128.introductionmvp.data.network.QueryResult;
import com.example.android0128.introductionmvp.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tk-0130 on 9/21/16.
 */
public interface Services {
    @GET(Constants.POPULAR_MOVIES)
    Call<QueryResult> getPopularMovies(@Query("api_key") String API_KEY, @Query("language") String language, @Query("page") int PAGE);

    @GET(Constants.POPULAR_MOVIES)
    Call<QueryResult> getPopularMoviesMore(@Query("api_key") String API_KEY, @Query("page") int PAGE, @Query("language") String language);

    @GET(Constants.POPULAR_TV_SHOWS)
    Call<QueryResult> getPopularTvShows(@Query("api_key") String API_KEY, @Query("language") String language, @Query("page") int PAGE);

    @GET(Constants.POPULAR_TV_SHOWS)
    Call<QueryResult> getPopularTvShowsMore(@Query("api_key") String API_KEY, @Query("page") int PAGE, @Query("language") String language);

    @GET(Constants.POPULAR_ACTORS)
    Call<QueryResult> getPopularActors(@Query("api_key") String API_KEY, @Query("language") String language, @Query("page") int PAGE);

    @GET(Constants.POPULAR_ACTORS)
    Call<QueryResult> getPopularActorsMore(@Query("api_key") String API_KEY, @Query("page") int PAGE, @Query("language") String language);

    @GET(Constants.QUERY_HOME_VIEW)
    Call<QueryResult> getQueryHomeView(@Query("api_key") String API_KEY, @Query("query") String query, @Query("language") String language, @Query("page") int PAGE);

    @GET(Constants.MOVIE_FILTER)
    Call<QueryResult> getQueryMovieFilter(@Query("api_key") String API_KEY, @Query("query") String query, @Query("page") int PAGE, @Query("language") String language);

    @GET(Constants.TV_SHOW_FILTER)
    Call<QueryResult> getQueryTvShowsFilter(@Query("api_key") String API_KEY, @Query("query") String query, @Query("page") int PAGE, @Query("language") String language);

    @GET(Constants.ACTOR_FILTER)
    Call<QueryResult> getQueryActorsFilter(@Query("api_key") String API_KEY, @Query("query") String query, @Query("page") int PAGE, @Query("language") String language);

    @GET(Constants.ACTOR_DETAIL)
    Call<ActorResponse> getActorDetail(@Query("api_key") String API_KEY, @Path("person_id") String person_id, @Query("language") String language);
}
