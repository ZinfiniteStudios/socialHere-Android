package com.david.socialhere.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.david.socialhere.R;
import com.david.socialhere.cards.AdCard;
import com.david.socialhere.cards.FlickrCard;
import com.david.socialhere.models.flickr.FlickrResponse;
import com.david.socialhere.utils.Utils;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;

/**
 * Created by davidhodge on 12/15/14.
 */
public class FlickrFragment extends BaseFragment {

    View view;
    Context mContext;
    @InjectView(R.id.card_list)
    GridViewWithHeaderAndFooter listView;
    @InjectView(R.id.loading)
    ProgressBar circularProgressBar;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    SharedPreferences sharedPreferences;
    String lat;
    String lon;

    ArrayList<Card> cardArrayList;
    CardArrayAdapter cardArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setEmptyView(circularProgressBar);
        circularProgressBar.setVisibility(View.VISIBLE);

        lat = this.getArguments().getString("lat", null);
        lon = this.getArguments().getString("lon", null);

        swipeRefreshLayout.setColorSchemeResources(R.color.park_color, R.color.park_color_ab, R.color.white, R.color.white);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPictures();
            }
        });

        getPictures();
    }

    public void getPictures() {
        String flickrUrl =
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=87d0ccf46bf73b10709e03d78edb0d25&accuracy=14&content_type=1&lat="
                        + lat + "&lon=" + lon + "&radius=5&radius_units=mi&extras=url_l%2C+owner_name&format=json&nojsoncallback=1";

        Ion.with(mContext)
                .load(flickrUrl)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> stringResponse) {
                        if (e != null) {
                            Log.e("auto", e.toString());
                        } else {
                            Utils.longInfo(stringResponse.getResult());
                            try {
                                FlickrResponse flickrResponse = new Gson().fromJson(stringResponse.getResult(), FlickrResponse.class);
                                cardArrayList = new ArrayList<Card>();

                                if (sharedPreferences.getBoolean("ads", true) == true) {
                                    AdCard adCard = new AdCard(mContext);
                                    if (Build.VERSION.SDK_INT < 21) {
                                        adCard.setShadow(false);
                                        adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
                                    }
                                    cardArrayList.add(adCard);
                                }

                                for (int i = 0; i < flickrResponse.getPhotos().getPhoto().size(); i++) {
                                    FlickrCard flickrCard = new FlickrCard(mContext, flickrResponse.getPhotos().getPhoto().get(i));
                                    if (Build.VERSION.SDK_INT < 21) {
                                        flickrCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                        flickrCard.setShadow(false);
                                    }
                                    cardArrayList.add(flickrCard);
                                }

                                if (sharedPreferences.getBoolean("ads", true) == true) {
                                    AdCard adCard = new AdCard(mContext);
                                    if (Build.VERSION.SDK_INT < 21) {
                                        adCard.setShadow(false);
                                        adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
                                    }
                                    cardArrayList.add(adCard);
                                }

                                cardArrayAdapter = new CardArrayAdapter(mContext, cardArrayList);
                                SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(cardArrayAdapter);
                                swingBottomInAnimationAdapter.setAbsListView(listView);
                                listView.setAdapter(swingBottomInAnimationAdapter);
                                swipeRefreshLayout.setRefreshing(false);
                            } catch (Exception e1) {
                                Log.e("auto", e1.toString());
                            }
                        }
                    }
                });
    }
}
