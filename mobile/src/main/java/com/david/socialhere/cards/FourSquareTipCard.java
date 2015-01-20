package com.david.socialhere.cards;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.tips.Tip;
import com.david.socialhere.utils.RoundTransformation;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by davidhodge on 12/14/14.
 */
public class FourSquareTipCard extends Card {

    Context mContext;
    Tip tip;
    TextView tipTop;
    TextView tipText;
    TextView tipTime;
    ImageView tipImgUser;

    public FourSquareTipCard(Context context, Tip tip) {
        super(context, R.layout.card_foursquare_tip);
        mContext = context;
        this.tip = tip;
    }

    public FourSquareTipCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        tipTop = (TextView) parent.findViewById(R.id.tip_top);
        tipText = (TextView) parent.findViewById(R.id.tip_text);
        tipTime = (TextView) parent.findViewById(R.id.tip_time);
        tipImgUser = (ImageView) parent.findViewById(R.id.image_user);

        String userImg = tip.getUser().getPhoto().getPrefix() + "120x120" + tip.getUser().getPhoto().getSuffix();
        Picasso.with(mContext).load(userImg).transform(new RoundTransformation(4,4)).into(tipImgUser);

        try {
            tipTop.setText(tip.getUser().getFirstName() + " @ " + tip.getVenue().getName());
        }catch (Exception e){
            Log.e("parkfans", e.toString());
            tipTop.setVisibility(View.GONE);
        }

        tipText.setText(tip.getText());
        long dv = Long.valueOf(tip.getCreatedAt())*1000;
        Date date = new Date(dv);
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd hh:mma");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        tipTime.setText(format.format(date));

        this.setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(tip.getCanonicalUrl()));
                mContext.startActivity(i);
            }
        });
    }
}
