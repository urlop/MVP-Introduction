package com.example.android0128.introductionmvp.moviesFavorite;

import android.support.annotation.NonNull;

import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.data.source.MovieDBResponse;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Retrofit reference: https://github.com/jeancsanchez/Simple-MVP-Retrofit-example/blob/master/app/src/main/java/com/example/jean/retrofitexample/Presenter/CountryPresenter.java
 * Realm reference: https://github.com/NileshJarad/android-mvp-material-and-realm/blob/master/MyApplication/android-mvp-material-and-realm-master/src/main/java/com/nileshjarad/realmdemo/presenters/ShowVisitingCardsInteractor.java
 */
public class FavoriteMoviesPresenter implements FavoriteMoviesContract.Presenter {

    //private final TasksRepository mTasksRepository;
    private final FavoriteMoviesContract.View mMoviesView;

    private boolean mFirstLoad = true;

    public FavoriteMoviesPresenter(@NonNull FavoriteMoviesContract.View view) {//@NonNull TasksRepository tasksRepository
        /*mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");*/
        mMoviesView = checkNotNull(view, "moviesView cannot be null!");
        mMoviesView.setPresenter(this);
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
        ArrayList<MovieDBResponse> favorite_ls = (ArrayList<MovieDBResponse>) MovieDBResponse.listAll(MovieDBResponse.class);
        processData(favorite_ls);
    }

    private void processData(ArrayList<MovieDBResponse> list) {
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
        //TODO Fill
    }

}
