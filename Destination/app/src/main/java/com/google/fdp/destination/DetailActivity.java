package com.google.fdp.destination;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivPhoto;
    private TextView tvName, tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(getString(R.string.detail));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ivPhoto = findViewById(R.id.iv_photo);
        tvName = findViewById(R.id.tv_name);
        tvLocation = findViewById(R.id.tv_location);

        showDetail();
    }

    private void showDetail() {
        Destination destination = getIntent().getParcelableExtra(Destination.EXTRA_NAME);
        tvName.setText(destination.getName());
        tvLocation.setText(destination.getLocation());
        Glide.with(this).load(destination.getImage()).into(ivPhoto);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
