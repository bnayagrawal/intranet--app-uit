package xyz.bnayagrawal.android.icontest;

/**
 * Created by root on 29/9/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentEvents extends Fragment {

    private MainActivity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (MainActivity)getActivity();
        context.setToolbar();
        initTabs();
    }

    protected void initTabs() {
        TabLayout tabLayout = (TabLayout) context.findViewById(R.id.event_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("All events"));
        tabLayout.addTab(tabLayout.newTab().setText("Workshops"));

        final ViewPager viewPager = (ViewPager) context.findViewById(R.id.pager);
        final PagerAdapter adapter = new EventsTabPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
