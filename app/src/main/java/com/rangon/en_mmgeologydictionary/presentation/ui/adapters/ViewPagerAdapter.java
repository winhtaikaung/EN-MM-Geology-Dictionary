package com.rangon.en_mmgeologydictionary.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.rangon.en_mmgeologydictionary.presentation.fragments.BookMarkFragment;
import com.rangon.en_mmgeologydictionary.presentation.fragments.SearchFragment;
import com.rangon.en_mmgeologydictionary.presentation.fragments.SettingFragment;

/**
 * Created by winhtaikaung on 8/10/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    String[] mtitles;
    FragmentManager fragManager;
    FragmentTransaction ft;

    public ViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.fragManager = fm;
        this.ft = fragManager.beginTransaction();

        this.mtitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0:
                return new SearchFragment();
            case 1:
                return new BookMarkFragment();

            case 2:
                return new SettingFragment();

        }
        return null;

    }

    @Override
    public int getCount() {
        return mtitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "";
    }
}
