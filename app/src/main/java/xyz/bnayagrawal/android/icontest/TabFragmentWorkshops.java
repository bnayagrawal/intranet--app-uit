package xyz.bnayagrawal.android.icontest;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragmentWorkshops extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout SwipeRefreshWorkshops;
    private ArrayList<EWDataSet> ewds;

    public TabFragmentWorkshops() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_workshops, container, false);

        //Toolbar settings
        setHasOptionsMenu(true);

        //initialize swipe refresh function
        SwipeRefreshWorkshops = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshWorkshops);
        SwipeRefreshWorkshops.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);

        SwipeRefreshWorkshops.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeRefreshWorkshops.setRefreshing(true);
                // TODO: refreshing while fetching the data
                // currently adds dummy data
                ewds.remove(0);
                adapter.notifyItemRemoved(0);
                ewds.add(0,new EWDataSet("Fakathon","","blablablablba",new Date("12/05/2017"),56,18,new Date("12/05/2017"),"Karkala"));
                adapter.notifyItemInserted(0);
                SwipeRefreshWorkshops.setRefreshing(false);
            }
        });

        //Initializing the RecyclerView Component
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_workshops);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Dummy data
        ewds = new ArrayList<>();
        String default_image = String.valueOf(R.drawable.event_default_image);
        String desc = "Celebrating World Ethnic Day. 'Ethnic diversity adds richness to a society.' This sentence comes to life with the celebrations of World Ethnic Day. ";
        ewds.add(new EWDataSet("Android",default_image,"blablablabla",new Date("12/05/2017"),56,18,new Date("12/05/2017"),"Karkala"));
        ewds.add(new EWDataSet("Firebase",default_image,"blablablabla",new Date("12/05/2017"),56,18,new Date("12/05/2017"),"Karkala"));

        adapter = new WorkshopsRecyclerAdapter(getActivity(),ewds);

        //custom recycler item animation by wasabeef(github)
        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        animator.setChangeDuration(500);
        animator.setAddDuration(500);
        animator.setRemoveDuration(500);
        recyclerView.setItemAnimator(animator);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getContext(),"Menu click wtab ",Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.ew_filter_show_all:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            case R.id.ew_filter_interested:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            case R.id.ew_filter_going:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
