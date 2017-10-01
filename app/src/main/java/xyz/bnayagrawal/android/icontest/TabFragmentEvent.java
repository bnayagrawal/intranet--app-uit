package xyz.bnayagrawal.android.icontest;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.OvershootInterpolator;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragmentEvent extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public TabFragmentEvent() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_event, container, false);

        //Initializing the RecyclerView Component
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Some dummy event data
        DummyEventDataProvider ed = new DummyEventDataProvider();

        //Dummy description
        String[] smalDec = {
                "Celebrating World Ethnic Day. 'Ethnic diversity adds richness to a society.' This sentence comes to life with the celebrations of World Ethnic Day. ",
                "Teachers' Day is a special day for the appreciation of teachers, and may include celebrations to honor them for their special contributions in a particular field area, or the community in general.",
                "The Freshers' Party was a night filled with talent, music, excitement and enthusiasm. Every year on Freshers' Party a boy and a girl from each stream is nominated for the prestigious title of Mr. & Ms. Fresher and for that they have to go through 3 rounds of different competitions."
        };
        ed.add("Ethnic Day",R.drawable.event_default_image,smalDec[0],"12/05/2017","56 Interested","18 Going","View event");
        ed.add("Teachers Day",R.drawable.event_default_image,smalDec[1],"08/12/2011","32 Interested","22 Going","View event");
        ed.add("Freshers Party",R.drawable.event_default_image,smalDec[2],"24/04/2016","16 Interested","12 Going","View event");

        adapter = new EventsRecyclerAdapter(getActivity(),ed.events);

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
