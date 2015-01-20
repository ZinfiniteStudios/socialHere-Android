package com.david.socialhere.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.places.Venue;

import java.util.List;

/**
 * Created by davidhodge on 1/8/15.
 */
public class FoursqAdapter extends BaseAdapter {

    private List<Venue> items;
    private LayoutInflater inflater;
    private Context mContext;

    public FoursqAdapter(Context context, List<Venue> items) {
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
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.card_foursq, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.place_name);
            holder.extra = (TextView) convertView.findViewById(R.id.place_extra);
            holder.type = (TextView) convertView.findViewById(R.id.place_type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(items.get(position).getName());
        holder.extra.setText(String.format("%.2f", Math.abs(items.get(position).getLocation().getDistance() * 0.00062137)) + " miles away");
        try {
            holder.type.setText(items.get(position).getCategories().get(0).getName());
        }catch (Exception e){
            holder.type.setText("Type not available");
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://foursquare.com/v/" + items.get(position).getId();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView type;
        TextView extra;
    }
}
