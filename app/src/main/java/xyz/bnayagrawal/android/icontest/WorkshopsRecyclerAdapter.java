package xyz.bnayagrawal.android.icontest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by root on 2/10/17.
 */

public class WorkshopsRecyclerAdapter extends RecyclerView.Adapter<WorkshopsRecyclerAdapter.ViewHolder>{
    private Context context;

    ArrayList<EWDataSet> ewds;

    public WorkshopsRecyclerAdapter(Context context, ArrayList<EWDataSet> ewds) {
        this.context = context;
        this.ewds = ewds;
    }

    @Override
    public WorkshopsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.workshops_card_template, viewGroup, false);
        WorkshopsRecyclerAdapter.ViewHolder viewHolder = new WorkshopsRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final WorkshopsRecyclerAdapter.ViewHolder viewHolder, int position) {
        EWDataSet eds = ewds.get(position);

        //limit 100 words for short description to show
        StringTokenizer st = new StringTokenizer(eds.getDescription()," ");
        String shortDesc = "";
        for(int i = 0; i < st.countTokens(); i++)
            if(i < 100) shortDesc += st.nextToken() + " ";
            else break;

        viewHolder.itemTitle.setText(eds.getTitle());
        viewHolder.itemDetail.setText(shortDesc + "...");
        viewHolder.dates.setText(eds.getNotification_date().toString());
        viewHolder.interested.setText(eds.getPeoples_interested() + " Intersted");
        viewHolder.going.setText(eds.getPeoples_going() + " Going");

        //Set some properties of imageview (used to display event image)
        viewHolder.itemImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //Picasso image loading and caching framework
        Picasso.with(context).load(R.drawable.workshop_event_image).resize(640,480).centerCrop().into(viewHolder.itemImage,new Callback(){
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
                Picasso.with(context).load(R.drawable.workshop_event_image).resize(640,480).centerCrop().into(viewHolder.itemImage);

                //animation using xml resource
                Animation image_scale = AnimationUtils.loadAnimation(context, R.anim.image_scale_anim);
                viewHolder.itemImage.startAnimation(image_scale);

                Toast.makeText(context,"Failed to load image!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ewds.size();
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
            itemImage = (ImageView)itemView.findViewById(R.id.imageViewWorkshop);
            itemTitle = (TextView)itemView.findViewById(R.id.textViewWorksopLabel);
            itemDetail = (TextView)itemView.findViewById(R.id.textViewWorksopShortDesc);
            dates = (TextView)itemView.findViewById(R.id.textViewTimeW);
            interested = (TextView)itemView.findViewById(R.id.textViewInterestedW);
            going = (TextView)itemView.findViewById(R.id.textViewGoingW);
            view = (TextView)itemView.findViewById(R.id.textViewViewW);
            imageLoadProgress = (ProgressBar) itemView.findViewById(R.id.imageLoadProgressW);

            //add onClick listener to view
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: open event detail activity
                    Intent intent = new Intent(context,event_detail_activity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
