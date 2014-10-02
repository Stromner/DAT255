package com.adapter;

import com.hazard_eclipse.SearchFragment;
import com.hazard_eclipse.CurrentFragment;
import com.hazard_eclipse.HistoryFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new SearchFragment();
        case 1:
            // Games fragment activity
            return new CurrentFragment();
        case 2:
            // Movies fragment activity
            return new HistoryFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}
