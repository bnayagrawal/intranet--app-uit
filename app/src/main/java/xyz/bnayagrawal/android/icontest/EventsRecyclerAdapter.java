package xyz.bnayagrawal.android.icontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by root on 30/9/17.
 */

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder>{
    //Hold the MainActivity context
    private Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    //random data
    private String[] titles =  { "Ethnic Day", "Teachers Day", "Freshers Party" };
    private String[] smalDec = {
            "Celebrating World Ethnic Day. 'Ethnic diversity adds richness to a society.' This sentence comes to life with the celebrations of World Ethnic Day. ",
            "Teachers' Day is a special day for the appreciation of teachers, and may include celebrations to honor them for their special contributions in a particular field area, or the community in general.",
            "The Freshers' Party was a night filled with talent, music, excitement and enthusiasm. Every year on Freshers' Party a boy and a girl from each stream is nominated for the prestigious title of Mr. & Ms. Fresher and for that they have to go through 3 rounds of different competitions."
    };
    private String[] dates = {"12/05/2017", "08/12/2011", "24/04/2016"};
    private String[] interested = { "56 Interested", "32 Interested", "16 Interested" };
    private String[] going = { "18 Going", "22 Going", "12 Going" };
    private String view = "View event";

    private int image = R.drawable.event_default_image;

    //public constructor just to get the activity context and store it.
    public EventsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.events_card_template, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(smalDec[i]);
        viewHolder.dates.setText(dates[i]);
        viewHolder.interested.setText(interested[i]);
        viewHolder.going.setText(going[i]);
        viewHolder.view.setText(view);

        //Set some properties of imageview (used to display event image)
        viewHolder.itemImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.itemImage.setVisibility(View.VISIBLE);

        //viewHolder.itemImage.setImageResource(image);

        //Picasso image loading and caching framework
        Picasso.with(context).load(image).resize(640,480).centerCrop().into(viewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    /**
     * Here is the key method to apply the animation (FROM STACK OVERFLOW)
     * This below method can be used to animate child views present inside cardView(list item).
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView interested;
        public TextView going;
        public TextView dates;
        public TextView view;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.imageViewEvent);
            itemTitle = (TextView)itemView.findViewById(R.id.textViewEventLabel);
            itemDetail = (TextView)itemView.findViewById(R.id.textViewEventShortDesc);
            dates = (TextView)itemView.findViewById(R.id.textViewTime);
            interested = (TextView)itemView.findViewById(R.id.textViewInterested);
            going = (TextView)itemView.findViewById(R.id.textViewGoing);
            view = (TextView)itemView.findViewById(R.id.textViewView);
        }
    }
}
