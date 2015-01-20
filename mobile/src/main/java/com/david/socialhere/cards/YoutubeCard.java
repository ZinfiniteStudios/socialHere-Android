package com.david.socialhere.cards;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.youtube.loc.Item;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by davidhodge on 12/14/14.
 */
public class YoutubeCard extends Card {

    Context mContext;
    Item item;
    CardView cardView;
    TextView name;
    TextView length;
    TextView viewT;
    TextView uploader;
    ImageView spotImage;
    Target target;

    public YoutubeCard(Context context, Item item) {
        super(context, R.layout.item_youtube);
        mContext = context;
        this.item = item;
    }

    public YoutubeCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        cardView = (CardView) parent.findViewById(R.id.card_view);
        name = (TextView) parent.findViewById(R.id.video_title);
        length = (TextView) parent.findViewById(R.id.video_length);
        viewT = (TextView) parent.findViewById(R.id.video_view_count);
        uploader = (TextView) parent.findViewById(R.id.video_desc);
        spotImage = (ImageView) parent.findViewById(R.id.video_image);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                spotImage.setImageBitmap(bitmap);

                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        // Here's your generated palette
                        Palette.Swatch swatch = palette.getVibrantSwatch();

                        if (swatch != null) {
                            name.setTextColor(swatch.getRgb());
                            length.setTextColor(swatch.getRgb());
                        }
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        uploader.setText(item.getUploader());

        name.setText(item.getTitle());
        length.setText(getDurationString(item.getDuration()));
        viewT.setText("" + item.getViewCount());

        Picasso.with(mContext)
                .load(item.getThumbnail().getHqDefault())
                .into(target);

        this.setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getPlayer().getDefault())));
            }
        });
    }

    private String getDurationString(int seconds) {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + ":" + twoDigitString(minutes) + ":" + twoDigitString(seconds);
    }

    private String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 14);
    }
}
