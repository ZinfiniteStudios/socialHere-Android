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
import com.david.socialhere.cards.YoutubeCard;
import com.david.socialhere.models.youtube.loc.YoutubeResponse;
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
 * Created by davidhodge on 12/14/14.
 */
public class YoutubeFeedFragment extends BaseFragment {

    View view;
    Context mContext;
    @InjectView(R.id.card_list)
    GridViewWithHeaderAndFooter listView;
    @InjectView(R.id.loading)
    ProgressBar circularProgressBar;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    String lat;
    String lon;
    SharedPreferences sharedPreferences;

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
                getVideos();
            }
        });

        getVideos();
    }

    public void getVideos() {
        Ion.with(mContext)
                .load("http://gdata.youtube.com/feeds/api/videos?v=2&prettyprint=true&location=" + lat + "," + lon + "!&location-radius=20mi&alt=jsonc")
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
                                YoutubeResponse youtubeResponse = new Gson().fromJson(stringResponse.getResult(), YoutubeResponse.class);
                                cardArrayList = new ArrayList<Card>();

                                if (sharedPreferences.getBoolean("ads", true) == true) {
                                    AdCard adCard = new AdCard(mContext);
                                    if (Build.VERSION.SDK_INT < 21) {
                                        adCard.setShadow(false);
                                        adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
                                    }
                                    cardArrayList.add(adCard);
                                }

                                for (int i = 0; i < youtubeResponse.getData().getItems().size(); i++) {
                                    YoutubeCard youtubeCard = new YoutubeCard(mContext, youtubeResponse.getData().getItems().get(i));
                                    if (Build.VERSION.SDK_INT < 21) {
                                        youtubeCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                        youtubeCard.setShadow(false);
                                    }
                                    cardArrayList.add(youtubeCard);
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