package com.example.android0128.introductionmvp.movies;

import dagger.Module;
import dagger.Provides;

/**
 * Created by android0128 on 12/27/16.
 */
@Module
public class MoviesPresenterModule {

    private final MoviesContract.View mView;
    private final String language;

    public MoviesPresenterModule(MoviesContract.View mView, String language) {
        this.mView = mView;
        this.language = language;
    }

    @Provides
    MoviesContract.View provideMoviesContractView() {
        return mView;
    }

    @Provides
    MoviesContract.Presenter provideMoviesContractPresenter() {
        return new MoviesPresenter(mView, language);
    }
}
