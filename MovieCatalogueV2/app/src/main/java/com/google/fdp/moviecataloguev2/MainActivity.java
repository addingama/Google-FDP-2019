package com.google.fdp.moviecataloguev2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.fdp.moviecataloguev2.fragments.MovieFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.navView);
        navView.setOnNavigationItemSelectedListener(this);
        openFragment(MovieFragment.newInstance(MovieFragment.MOVIE_KEY, false));
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, fragment, fragment.getClass().getSimpleName()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_language_setting) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                openFragment(MovieFragment.newInstance(MovieFragment.MOVIE_KEY, false));
                return true;
            case R.id.navigation_tv_show:
                openFragment(MovieFragment.newInstance(MovieFragment.TV_SHOW_KEY, false));
                return true;
            case R.id.navigation_favorite:
                return true;
            default:
                return false;
        }
    }
}
