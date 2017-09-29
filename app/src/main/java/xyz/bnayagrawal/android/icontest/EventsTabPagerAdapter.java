package xyz.bnayagrawal.android.icontest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 30/9/17.
 */

public class EventsTabPagerAdapter extends FragmentPagerAdapter {
    int tabCount;

    public EventsTabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabFragmentEvent tabEvent = new TabFragmentEvent();
                return tabEvent;
            case 1:
                TabFragmentWorkshops tabWorkshop = new TabFragmentWorkshops();
                return tabWorkshop;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
