/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android0128.introductionmvp.moviesFavorite;

import android.support.annotation.NonNull;

import com.example.android0128.introductionmvp.BasePresenter;
import com.example.android0128.introductionmvp.BaseView;
import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.data.source.MovieDBResponse;

import java.util.ArrayList;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface FavoriteMoviesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMovies(ArrayList<MovieDBResponse> movies);

        void showMovieDetailsUi(MovieModel movie);

        void showLoadingMoviesError();

        void showLoadingMoviesError(String error);

        void showNoMovies();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void loadMovies(boolean forceUpdate);

        void openMovieDetails(@NonNull MovieModel requestedMovie);
    }
}
