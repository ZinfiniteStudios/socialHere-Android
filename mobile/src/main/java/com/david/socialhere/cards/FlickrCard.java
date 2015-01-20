package com.david.socialhere.cards;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.flickr.Photo;
import com.squareup.picasso.Picasso;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by davidhodge on 12/15/14.
 */
public class FlickrCard extends Card {

    Context mContext;
    Photo photo;
    CardView cardView;
    TextView name;
    TextView length;
    TextView viewT;
    TextView uploader;
    ImageView spotImage;

    public FlickrCard(Context context, Photo photo) {
        super(context, R.layout.card_flickr);
        this.mContext = context;
        this.photo = photo;
    }

    public FlickrCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        cardView = (CardView) parent.findViewById(R.id.card_view);
        uploader = (TextView) parent.findViewById(R.id.video_desc);
        spotImage = (ImageView) parent.findViewById(R.id.video_image);


        Picasso.with(mContext)
                .load(photo.getUrlL())
                .into(spotImage);

        uploader.setText(photo.getTitle());
    }
}
