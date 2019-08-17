package com.google.fdp.destination;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DestinationAdapter.OnItemClickCallback {
    private RecyclerView rvDestinations;
    private ArrayList<Destination> destinations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
        }

        rvDestinations = findViewById(R.id.rv_destinations);
        rvDestinations.setHasFixedSize(true);

        destinations.addAll(DestinationData.getListData());
        showList();
    }

    private void showList() {
        rvDestinations.setLayoutManager(new LinearLayoutManager(this));
        DestinationAdapter adapter = new DestinationAdapter(destinations);
        adapter.setOnItemClickCallback(this);
        rvDestinations.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mn_about) {
            AboutActivity.start(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(Destination destination) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Destination.EXTRA_NAME, destination);
        startActivity(intent);
    }
}
