package com.jordanmadrigal.upticknews;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Adapter for the recycle view of news cards. Generates news cards based on linked
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CardViewHolder>{

    private Context activity;
    private List<News> dataSet;


    public NewsAdapter(List<News> dataSet, Context activity) {
        this.dataSet = dataSet;
        this.activity = activity;
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder{

        public TextView headLineText, sourceText, dateText, learnText;
        public ImageView headlineImage;


        public CardViewHolder(View view) {
            super(view);
            headLineText = view.findViewById(R.id.dummy_headline_textview);
            sourceText = view.findViewById(R.id.dummy_news_source_textview);
            dateText = view.findViewById(R.id.dummy_date_textview);
            headlineImage = view.findViewById(R.id.dummy_imageview);
            learnText = view.findViewById(R.id.dummy_learn_more_textview);
        }


    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_items, null);

        CardViewHolder viewHolder = new CardViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

        final News cardData = dataSet.get(position);

        holder.headLineText.setText(cardData.getHeadline());

        holder.dateText.setText(cardData.getDate());

        holder.sourceText.setText(cardData.getNewsSource());

        try {
            Picasso.with(activity)
                    .load(cardData.getImageSource())
                    .error(R.drawable.news_update_pic)
                    .into(holder.headlineImage);
        }catch (Exception e){
            e.printStackTrace();
        }

        //On click listener for intent to open browser on learn text
        holder.learnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(cardData.getUrl()));
                    view.getContext().startActivity(browserIntent);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
            return dataSet.size();
    }



}
