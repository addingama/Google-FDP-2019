package com.google.fdp.moviecataloguev2.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.fdp.moviecataloguev2.R;
import com.google.fdp.moviecataloguev2.adapters.FavouriteTabAdapter;

public class FavouriteFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favourite, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            FavouriteTabAdapter tabAdapter = new FavouriteTabAdapter(fm);
            tabAdapter.addFragment(MovieFragment.newInstance(MovieFragment.MOVIE_KEY, true), getString(R.string.title_menu_movie));
            tabAdapter.addFragment(MovieFragment.newInstance(MovieFragment.TV_SHOW_KEY, true), getString(R.string.title_menu_tv_show));

            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
}
