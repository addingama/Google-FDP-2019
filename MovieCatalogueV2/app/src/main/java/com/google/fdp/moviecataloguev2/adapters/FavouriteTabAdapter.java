package com.google.fdp.moviecataloguev2.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gama on 2019-07-27.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class FavouriteTabAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fm;

    public FavouriteTabAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<String> titles = new ArrayList<String>();

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

}
