package com.google.fdp.moviecatalogue.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.fdp.moviecatalogue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvSeriesFragment extends Fragment {


    public TvSeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_series, container, false);
    }

}
