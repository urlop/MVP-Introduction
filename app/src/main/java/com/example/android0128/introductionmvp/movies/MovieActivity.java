package com.example.android0128.introductionmvp.movies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android0128.introductionmvp.R;
import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.moviesFavorite.FavoriteMoviesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android0128.introductionmvp.util.Constants.SHOW_MORE_TYPE;
import static com.google.common.base.Preconditions.checkNotNull;

public class MovieActivity extends AppCompatActivity implements MoviesContract.View{

    @BindView(R.id.rv_show_more)
    RecyclerView rv_show_more;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_toolbar_title)
    RelativeLayout rl_toolbar_title;
    @BindView(R.id.tv_toolbar_title)
    TextView tv_toolbar_title;
    @BindView(R.id.ll_rv_show_more)
    LinearLayout ll_rv_show_more;
    @BindView(R.id.pb_show_more)
    ProgressBar pb_shows_more;
    @BindView(R.id.v_container)
    View v_container;

    private GridLayoutManager lManager;
    private ShowMoreAdapter adapter;
    ArrayList<MovieModel> query_ls = new ArrayList<>();
    ArrayList<MovieModel> new_query_ls = new ArrayList<>();
    int page = 1;
    int showMoreType = 0;

    Context context;
    SearchView searchView = null;

    private MoviesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        // Create the presenter
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }
        String language = locale.getLanguage();
        mPresenter = new MoviesPresenter(//Injection.provideTasksRepository(getApplicationContext()),
                this, language);


        ll_rv_show_more.setVisibility(View.GONE);
        pb_shows_more.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        context = this;

        getShowMoreType();

        setTitleInside();

        setupRv();
        setupRecyclerViewAdapter();

        rv_show_more.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (lManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                        mPresenter.loadMovies(false);

                        adapter.notifyItemRangeInserted(adapter.getItemCount(), adapter.getItemCount() + 20);
                        rv_show_more.scrollToPosition(adapter.getItemCount() - 1);
                    }
                }
            }
        });

        // TODO Load previously saved state, if available. Ex:
        /*if (savedInstanceState != null) {
            asksFilterType currentFiltering =
                    (TasksFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mTasksPresenter.setFiltering(currentFiltering);
        }*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_favorites:
                Intent intent = new Intent(this, FavoriteMoviesActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSearchView() {
        if (searchView != null) {
            searchView.setQueryHint(getString(R.string.search_hint));
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        rl_toolbar_title.setVisibility(View.GONE);
                    } else {
                        rl_toolbar_title.setVisibility(View.VISIBLE);
                    }
                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    //TODO Implement search
                    /*Intent i = new Intent(MovieActivity.this, SearchActivity.class);
                    i.putExtra(SEARCH_TYPE, showMoreType);
                    i.putExtra(QUERY_FOR_SEARCH, s);
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

    private void setupRv() {
        rv_show_more.setHasFixedSize(true);
        lManager = new GridLayoutManager(this, 3);
        rv_show_more.setLayoutManager(lManager);
    }

    private void setTitleInside() {
        tv_toolbar_title.setText(R.string.popular_movies);
        fillListPopular(page, showMoreType);
    }

    private void getShowMoreType() {
        if (getIntent().hasExtra(SHOW_MORE_TYPE)) {
            showMoreType = getIntent().getIntExtra(SHOW_MORE_TYPE, 0);
        }
    }

    private void fillListPopular(int page, int type) {
        //TODO fill
    }

    private void setupRecyclerViewAdapter() {
        adapter = new ShowMoreAdapter(this, query_ls, showMoreType, mItemListener);
        rv_show_more.setAdapter(adapter);
    }

    private void notifyToAdapter(ArrayList<MovieModel> new_query_ls) {
        adapter.addMoreToList(new_query_ls);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        //TODO check if used
    }

    @Override
    public void showMovies(List<MovieModel> movies) {
        notifyToAdapter((ArrayList<MovieModel>) movies);

        ll_rv_show_more.setVisibility(View.VISIBLE);
        pb_shows_more.setVisibility(View.GONE);
        /*mTasksView.setVisibility(View.VISIBLE);
        mNoTasksView.setVisibility(View.GONE);*/
    }

    @Override
    public void showMovieDetailsUi(MovieModel model) {
        //TODO fill
        /*MovieModel item = query_ls.get(position);
        Intent i = new Intent(context, ItemDetailActivity.class);
        i.putExtra("models",item);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);*/
    }

    @Override
    public void showFavoriteResult(boolean favoriteBefore) {
        Toast.makeText(this, favoriteBefore ? "Removed from favorite" : "Added to favorite", Toast.LENGTH_SHORT).show();
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

    @Override
    public void showFilteringPopUpMenu() {
        //TODO fill
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    ShowMoreAdapter.MovieItemListener mItemListener = new ShowMoreAdapter.MovieItemListener() {
        @Override
        public void onItemClick(MovieModel clicked) {
            mPresenter.openMovieDetails(clicked);
        }
    };

    private void showMessage(String message) {
        Snackbar.make(v_container, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    public void setPresenter(@NonNull MoviesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    //Very Important
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
