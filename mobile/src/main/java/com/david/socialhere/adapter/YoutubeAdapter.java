package com.david.socialhere.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.youtube.loc.Item;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by davidhodge on 12/14/14.
 */
public class YoutubeAdapter extends BaseAdapter {

    private List<Item> items;
    private LayoutInflater inflater;
    private Context mContext;

    public YoutubeAdapter(Context context, List<Item> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_youtube, parent, false);
            holder.cardView = (CardView) convertView.findViewById(R.id.card_view);
            holder.name = (TextView) convertView.findViewById(R.id.video_title);
            holder.length = (TextView) convertView.findViewById(R.id.video_length);
            holder.view = (TextView) convertView.findViewById(R.id.video_view_count);
            holder.uploader = (TextView) convertView.findViewById(R.id.video_desc);
            holder.spotImage = (ImageView) convertView.findViewById(R.id.video_image);

            holder.target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.spotImage.setImageBitmap(bitmap);

                    Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            // Here's your generated palette
                            Palette.Swatch swatch = palette.getVibrantSwatch();

                            if (swatch != null) {
                                holder.name.setTextColor(swatch.getRgb());
                                holder.length.setTextColor(swatch.getRgb());
//                                holder.view.setTextColor(swatch.getTitleTextColor());
//                                holder.uploader.setTextColor(swatch.getTitleTextColor());
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

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.uploader.setText(items.get(position).getUploader());

        holder.name.setText(items.get(position).getTitle());
        holder.length.setText(getDurationString(items.get(position).getDuration()));
        holder.view.setText("" + items.get(position).getViewCount());

        Picasso.with(mContext)
                .load(items.get(position).getThumbnail().getHqDefault())
                .into(holder.target);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).getPlayer().getDefault())));
            }
        });


        return convertView;
    }

    class ViewHolder {
        CardView cardView;
        TextView name;
        TextView length;
        TextView view;
        TextView uploader;
        ImageView spotImage;
        Target target;
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

}
