package com.example.android0128.introductionmvp.movies;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android0128.introductionmvp.R;
import com.example.android0128.introductionmvp.data.QueryModel;
import com.example.android0128.introductionmvp.data.network.QueryResult;
import com.example.android0128.introductionmvp.data.source.QueryResponse;
import com.example.android0128.introductionmvp.util.Constants;
import com.example.android0128.introductionmvp.util.Network.APIError;
import com.example.android0128.introductionmvp.util.Network.RequestManager;
import com.example.android0128.introductionmvp.util.Network.Services;
import com.example.android0128.introductionmvp.util.UIHelper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

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
public class MoviesPresenter implements MoviesContract.Presenter {

    private final Services networkService;
    //private final TasksRepository mTasksRepository;
    private final MoviesContract.View mMoviesView;

    String language;
    private boolean mFirstLoad = true;
    int page = 1;

    boolean isInDB;
    QueryResponse sugarResponse = new QueryResponse();

    public MoviesPresenter(@NonNull MoviesContract.View view, String language) {//@NonNull TasksRepository tasksRepository, @NonNull TasksContract.View tasksView) {
        /*mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");*/
        mMoviesView = checkNotNull(view, "moviesView cannot be null!");
        mMoviesView.setPresenter(this);

        networkService = new RequestManager().getWebServices();
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
        /*if (forceUpdate) {
            mTasksRepository.refreshTasks();
        }*/

        Call<QueryResult> call = networkService.getPopularMovies(Constants.API_KEY, language, page);
        call.enqueue(new Callback<QueryResult>() {
            @Override
            public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                if (response.isSuccessful()) {
                    List<QueryModel> response_ls = response.body().getResults();
                    processData(response_ls);
                    page++;
                }

                //TODO CHECK WHERE TO PUT THIS REPEATED CODE
                if (!response.isSuccessful()) {
                    String error;
                    try {
                        APIError apiError;
                        Converter<ResponseBody, APIError> converter = RequestManager.getRetrofit().responseBodyConverter(APIError.class, new Annotation[0]);
                        apiError = converter.convert(response.errorBody());
                        error = apiError.getError();
                        mMoviesView.showLoadingMoviesError(error);
                    } catch (Exception e) {
                        mMoviesView.showLoadingMoviesError();
                    }
                }
            }

            @Override
            public void onFailure(Call<QueryResult> call, Throwable t) {
                if (!mMoviesView.isActive()) {
                    return;
                }
                mMoviesView.showLoadingMoviesError();
            }
        });

    }

    private void processData(List<QueryModel> list) {
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
    public void openMovieDetails(@NonNull QueryModel requestedMovie) {
        ArrayList<QueryResponse> r = (ArrayList<QueryResponse>) QueryResponse.find(QueryResponse.class,"responseId = " + requestedMovie.getId());
        if (r.size() > 0) {
            for (QueryResponse a : r) {
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

}
