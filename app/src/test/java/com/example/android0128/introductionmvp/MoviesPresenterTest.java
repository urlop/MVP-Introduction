package com.example.android0128.introductionmvp;

import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.data.source.remote.QueryCallback;
import com.example.android0128.introductionmvp.data.source.remote.QueryInteractor;
import com.example.android0128.introductionmvp.movies.MoviesContract;
import com.example.android0128.introductionmvp.movies.MoviesPresenter;
import com.example.android0128.introductionmvp.util.network.RequestManager;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import okhttp3.Call;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 01/01/2017.
 */

public class MoviesPresenterTest {

    private static List<MovieModel> MOVIES;

    @Mock
    private QueryInteractor queryInteractor;

    @Mock
    private MoviesContract.View mMoviesView;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    /*@Captor
    private ArgumentCaptor<LoadTasksCallback> mLoadMoviesCallbackCaptor;*/
    @Captor
    private ArgumentCaptor<QueryCallback> mLoadMoviesCallbackCaptor;

    private MoviesPresenter mMoviesPresenter;

    @Before
    public void setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mMoviesPresenter = new MoviesPresenter(mMoviesView, "en");

        queryInteractor = new QueryInteractor(new RequestManager().getWebServices());

        // The presenter won't update the view unless it's active.
        when(mMoviesView.isActive()).thenReturn(true);

        // We start the tasks to 3, with one active and two completed
        MOVIES = Lists.newArrayList(
                new MovieModel("1", "\\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg", 0.7, "Test", "Movie", "2016-12-14", "Test", "profile/", "Test", "\\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg"),
                new MovieModel("2", "\\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg", 0.6, "Test", "Movie", "2016-12-14", "Test", "profile/", "Test", "\\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg"),
                new MovieModel("3", "\\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg", 0.5, "Test", "Movie", "2016-12-14", "Test", "profile/", "Test", "\\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg")
        );
    }

    @Test
    public void loadAllTasksFromRepositoryAndLoadIntoView() {
        // Given an initialized TasksPresenter with initialized tasks
        // When loading of Tasks is requested
        mMoviesPresenter.loadMovies(true);

        // Callback is captured and invoked with stubbed tasks
        verify(queryInteractor).requestMovies("en", 1, mLoadMoviesCallbackCaptor.capture());
        mLoadMoviesCallbackCaptor.getValue().onQueryCallSuccess(MOVIES);

        // Then progress indicator is shown
        InOrder inOrder = inOrder(mMoviesView);
        inOrder.verify(mMoviesView).setLoadingIndicator(true);
        // Then progress indicator is hidden and all tasks are shown in UI
        inOrder.verify(mMoviesView).setLoadingIndicator(false);
        ArgumentCaptor<List> showMoviesArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mMoviesView).showMovies(showMoviesArgumentCaptor.capture());
        assertTrue(showMoviesArgumentCaptor.getValue().size() == 3);
    }

    @Test
    public void clickOnTask_AddFavorite() {
        // Given a stubbed active task
        MovieModel requestedMovie = new MovieModel("1", "\\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg", 0.7, "Test", "Movie", "2016-12-14", "Test", "profile/", "Test", "\\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg");

        // When open task details is requested
        mMoviesPresenter.openMovieDetails(requestedMovie);

        // Then task detail UI is shown
        verify(mMoviesView).showFavoriteResult(true);
    }
}
