package xyz.bnayagrawal.android.icontest;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
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

    public TabFragmentWorkshops() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_workshops, container, false);

        //initialize swipe refresh function
        SwipeRefreshWorkshops = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshWorkshops);
        SwipeRefreshWorkshops.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);

        SwipeRefreshWorkshops.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeRefreshWorkshops.setRefreshing(true);
                // TODO: refreshing while fetching the data
                SwipeRefreshWorkshops.setRefreshing(false);
            }
        });

        //Initializing the RecyclerView Component
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_workshops);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Dummy data
        List<EWDataSet> ewds = new ArrayList<>();
        String desc = "Celebrating World Ethnic Day. 'Ethnic diversity adds richness to a society.' This sentence comes to life with the celebrations of World Ethnic Day. ";
        ewds.add(new EWDataSet("Hackathon",R.drawable.workshop_event_image,desc,"12/05/2017","56 Interested","18 Going","View event","12/02/2016","LG 01 LH 11"));

        adapter = new WorkshopsRecyclerAdapter(getActivity(),ewds);

        //custom recycler item animation by wasabeef(github)
        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        recyclerView.setItemAnimator(animator);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}
