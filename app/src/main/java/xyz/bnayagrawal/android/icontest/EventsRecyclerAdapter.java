package xyz.bnayagrawal.android.icontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by root on 30/9/17.
 */

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder>{
    //Hold the MainActivity context
    private Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    // Dummy data
    List<DummyEventDataProvider.EventDataSet> detp;

    //public constructor to get the activity context and event data.
    public EventsRecyclerAdapter(Context context,List<DummyEventDataProvider.EventDataSet> detp) {
        this.context = context;
        this.detp = detp;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.events_card_template, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    //this method will be called automatically by recyclerview before showing the card(list item) to user, the passed viewholder object will be populated with data to display.
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        DummyEventDataProvider.EventDataSet eds = detp.get(position);

        viewHolder.itemTitle.setText(eds.getTitle());
        viewHolder.itemDetail.setText(eds.getDescription());
        viewHolder.dates.setText(eds.getNotification_date());
        viewHolder.interested.setText(eds.getPeoples_interested());
        viewHolder.going.setText(eds.getPeoples_going());
        viewHolder.view.setText(eds.getCaption());

        //Set some properties of imageview (used to display event image)
        viewHolder.itemImage.setScaleType(ImageView.ScaleType.CENTER_CROP);


        //Picasso image loading and caching framework
        Picasso.with(context).load(eds.getImage()).resize(640,480).centerCrop().into(viewHolder.itemImage,new Callback(){
            @Override
            public void onSuccess() {
                //hide the progress bar
                viewHolder.imageLoadProgress.setVisibility(View.GONE);
                viewHolder.itemImage.setVisibility(View.VISIBLE);

                //animation using xml resource
                Animation image_scale = AnimationUtils.loadAnimation(context, R.anim.image_scale_anim);
                viewHolder.itemImage.startAnimation(image_scale);
            }

            @Override
            public void onError() {
                viewHolder.imageLoadProgress.setVisibility(View.GONE);
                viewHolder.itemImage.setVisibility(View.VISIBLE);
                Picasso.with(context).load(R.drawable.event_default_image).resize(640,480).centerCrop().into(viewHolder.itemImage);
                Toast.makeText(context,"Failed to load image!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return detp.size();
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
        public ProgressBar imageLoadProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.imageViewEvent);
            itemTitle = (TextView)itemView.findViewById(R.id.textViewEventLabel);
            itemDetail = (TextView)itemView.findViewById(R.id.textViewEventShortDesc);
            dates = (TextView)itemView.findViewById(R.id.textViewTime);
            interested = (TextView)itemView.findViewById(R.id.textViewInterested);
            going = (TextView)itemView.findViewById(R.id.textViewGoing);
            view = (TextView)itemView.findViewById(R.id.textViewView);
            imageLoadProgress = (ProgressBar) itemView.findViewById(R.id.imageLoadProgress);

            //add onClick listener to view
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: open event detail activity
                }
            });
        }
    }
}
