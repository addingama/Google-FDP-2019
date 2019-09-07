package com.google.fdp.moviecataloguev2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.fdp.moviecataloguev2.fragments.FavouriteFragment;
import com.google.fdp.moviecataloguev2.fragments.MovieFragment;
import com.google.fdp.moviecataloguev2.viewmodels.MovieViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navView;
    private MovieViewModel viewModel;
    private Fragment pageContent;
    public  String KEY_FRAGMENT = MovieFragment.MOVIE_KEY;
    public String TITLE_FRAGMENT = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.navView);
        navView.setOnNavigationItemSelectedListener(this);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        if (savedInstanceState == null) {
            openFragment(MovieFragment.newInstance(MovieFragment.MOVIE_KEY, false));
        } else {
            KEY_FRAGMENT = savedInstanceState.getString(TITLE_FRAGMENT);
            openFragment(Objects.requireNonNull(getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT)));
        }

    }

    private void openFragment(Fragment fragment) {
        pageContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, pageContent, fragment.getClass().getSimpleName()).commit();

    }

    private void searchMovie(String keyword) {
        viewModel.searchData(KEY_FRAGMENT, keyword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0) {
                    viewModel.updateItem(KEY_FRAGMENT);
                } else {
                    searchMovie(s);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_language_setting:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                break;
            case R.id.menu_reminder_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                KEY_FRAGMENT = MovieFragment.MOVIE_KEY;
                openFragment(MovieFragment.newInstance(MovieFragment.MOVIE_KEY, false));
                return true;
            case R.id.navigation_tv_show:
                KEY_FRAGMENT = MovieFragment.TV_SHOW_KEY;
                openFragment(MovieFragment.newInstance(MovieFragment.TV_SHOW_KEY, false));
                return true;
            case R.id.navigation_favorite:
                openFragment(new FavouriteFragment());
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TITLE_FRAGMENT, KEY_FRAGMENT);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }
}
