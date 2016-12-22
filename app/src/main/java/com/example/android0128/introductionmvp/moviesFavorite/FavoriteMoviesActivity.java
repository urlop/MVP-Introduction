package com.example.android0128.introductionmvp.moviesFavorite;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android0128.introductionmvp.R;
import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.data.source.MovieDBResponse;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class FavoriteMoviesActivity extends AppCompatActivity implements FavoriteMoviesContract.View {

    @BindView(R.id.rv_show_more)
    RecyclerView rv_show_more;
    @BindView(R.id.toolbar_show_more_favorites)
    Toolbar toolbar_show_more_favorites;
    @BindView(R.id.rl_toolbar_title)
    RelativeLayout rl_toolbar_title;
    @BindView(R.id.tv_toolbar_title)
    TextView tv_toolbar_title;
    @BindView(R.id.v_container)
    View v_container;

    private Context context;
    ShowMoreFavoritesAdapter adapter;
    SearchView searchView = null;

    private FavoriteMoviesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar_show_more_favorites);

        // Create the presenter
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }
        String language = locale.getLanguage();
        mPresenter = new FavoriteMoviesPresenter(//Injection.provideTasksRepository(getApplicationContext()),
                this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar_show_more_favorites.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        context = this;

        setTitleInside();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favorite_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            setupSearchView();
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void setupSearchView() {
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    /*Intent i = new Intent(ShowMoreFavoritesActivity.this, SearchFavoriteActivity.class);
                    i.putExtra(QUERY_FOR_SEARCH,s);
                    i.putExtra(SEARCH_TYPE,showMoreType);
                    startActivity(i);*/
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    private void setupRecyclerView() {
        rv_show_more.setLayoutManager(new GridLayoutManager(this, 3));
        rv_show_more.setAdapter(adapter);
    }

    private void setTitleInside() {
        tv_toolbar_title.setText(R.string.favorite_movies);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        //TODO check if used
    }

    @Override
    public void showMovies(ArrayList<MovieDBResponse> movies) {
        adapter = new ShowMoreFavoritesAdapter(context, movies, 1);
        setupRecyclerView();
        /*mTasksView.setVisibility(View.VISIBLE);
        mNoTasksView.setVisibility(View.GONE);*/
    }

    @Override
    public void showMovieDetailsUi(MovieModel movie) {
        //TODO fill
    }

    @Override
    public void showLoadingMoviesError() {
        showMessage(getString(R.string.loading_tasks_error));
    }

    @Override
    public void showLoadingMoviesError(String error) {
        showMessage(error);
    }

    @Override
    public void showNoMovies() {
        showMessage("No data");
        //TODO show empty view
        /*mTasksView.setVisibility(View.GONE);
        mNoTasksView.setVisibility(View.VISIBLE);*/
    }

    private void showMessage(String message) {
        Snackbar.make(v_container, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    public void setPresenter(FavoriteMoviesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    //Very Important
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
