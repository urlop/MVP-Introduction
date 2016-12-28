package com.example.android0128.introductionmvp.movies;

import android.support.annotation.NonNull;

import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.data.network.MoviesList;
import com.example.android0128.introductionmvp.data.source.MovieDBResponse;
import com.example.android0128.introductionmvp.data.source.remote.QueryCallback;
import com.example.android0128.introductionmvp.data.source.remote.QueryInteractor;
import com.example.android0128.introductionmvp.util.Constants;
import com.example.android0128.introductionmvp.util.network.APIError;
import com.example.android0128.introductionmvp.util.network.RequestManager;
import com.example.android0128.introductionmvp.util.network.Services;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Retrofit reference: https://github.com/jeancsanchez/Simple-MVP-Retrofit-example/blob/master/app/src/main/java/com/example/jean/retrofitexample/Presenter/CountryPresenter.java
 * Realm reference: https://github.com/NileshJarad/android-mvp-material-and-realm/blob/master/MyApplication/android-mvp-material-and-realm-master/src/main/java/com/nileshjarad/realmdemo/presenters/ShowVisitingCardsInteractor.java
 */
final class MoviesPresenter implements MoviesContract.Presenter, QueryCallback {

    private final MoviesContract.View mMoviesView;
    private QueryInteractor queryInteractor;

    String language;
    private boolean mFirstLoad = true;
    int page = 1;

    MovieDBResponse sugarResponse = new MovieDBResponse();

    @Inject
    MoviesPresenter(@NonNull MoviesContract.View view, String language) {
        mMoviesView = checkNotNull(view, "moviesView cannot be null!");
        mMoviesView.setPresenter(this);

        queryInteractor = new QueryInteractor(new RequestManager().getWebServices());
        this.language = language;
    }

    @Override
    public void start() {
        loadMovies(false);
    }

    @Override
    public void loadMovies(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadMovies(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    public void loadMovies(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mMoviesView.setLoadingIndicator(true);
        }

        queryInteractor.requestMovies(language, page, this);
    }

    private void processData(List<MovieModel> list) {
        if (list.isEmpty()) {
            processEmptyData();
        } else {
            mMoviesView.showMovies(list);
            // TODO : NEXT Set the filter label's text.
        }
    }

    private void processEmptyData() {
        mMoviesView.showNoMovies();
    }

    @Override
    public void openMovieDetails(@NonNull MovieModel requestedMovie) {
        ArrayList<MovieDBResponse> r = (ArrayList<MovieDBResponse>) MovieDBResponse.find(MovieDBResponse.class, "responseId = " + requestedMovie.getId());
        if (r.size() > 0) {
            for (MovieDBResponse a : r) {
                a.delete();
            }
            mMoviesView.showFavoriteResult(true);
        } else {
            sugarResponse.setResponse_id(requestedMovie.getId());
            sugarResponse.setPoster_path(requestedMovie.getPoster_path());
            sugarResponse.setPopularity(requestedMovie.getPopularity());
            sugarResponse.setOverview(requestedMovie.getOverview());
            sugarResponse.setMedia_type(requestedMovie.getMedia_type());
            sugarResponse.setFirst_air_date(requestedMovie.getFirst_air_date());
            sugarResponse.setName(requestedMovie.getName());
            sugarResponse.setProfile_path(requestedMovie.getProfile_path());
            sugarResponse.setTitle(requestedMovie.getTitle());
            sugarResponse.setBackdrop_path(requestedMovie.getBackdrop_path());
            sugarResponse.setLanguage(language);

            sugarResponse.save();
            mMoviesView.showFavoriteResult(false);
        }
    }

    @Override
    public void onQueryCallSuccess(Object object) {
        List<MovieModel> response_ls = ((MoviesList) object).getResults();
        processData(response_ls);
        page++;
    }

    @Override
    public void onQueryCallError(Object object) {
        if (!mMoviesView.isActive()) {
            return;
        }
        if (object != null) {
            mMoviesView.showLoadingMoviesError((String) object);
        } else {
            mMoviesView.showLoadingMoviesError();
        }
    }
}
