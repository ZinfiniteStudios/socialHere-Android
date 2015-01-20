package com.david.socialhere.cards;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.twitter.Hashtag;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by davidhodge on 12/16/14.
 */
public class HashTagCard extends Card {

    Context mContext;
    CardView cardView;
    TextView name;
    Hashtag hashtag;

    public HashTagCard(Context context, Hashtag hashtag) {
        super(context, R.layout.card_hashtag);
        this.mContext = context;
        this.hashtag = hashtag;

    }

    public HashTagCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        cardView = (CardView) parent.findViewById(R.id.card_view);
        name = (TextView) parent.findViewById(R.id.hash_text);
        name.setText("#" + hashtag.getText() + " - " + hashtag.getIndices());
    }
}
