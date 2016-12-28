package com.example.android0128.introductionmvp.movies;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by android0128 on 12/28/16.
 */
@Singleton
@Component(modules = MoviesPresenterModule.class)
public interface MoviesComponent {

    void inject(MovieActivity activity);
}
