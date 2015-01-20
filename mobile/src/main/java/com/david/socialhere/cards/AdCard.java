package com.david.socialhere.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.david.socialhere.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by davidhodge on 12/13/14.
 */
public class AdCard extends Card {

    Context mContext;
    AdView adView;
    LinearLayout failView;

    public AdCard(Context context) {
        super(context, R.layout.card_ad);
        this.mContext = context;
    }

    public AdCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        adView = (AdView) parent.findViewById(R.id.google_ad);
        failView = (LinearLayout) parent.findViewById(R.id.fail_view);

        adView.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                adView.setVisibility(View.GONE);
                failView.setVisibility(View.GONE);
            }
        });
        adView.loadAd(adRequest);
    }
}
