package com.david.socialhere.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.david.socialhere.R;
import com.david.socialhere.cards.AdCard;
import com.david.socialhere.cards.HashTagCard;
import com.david.socialhere.cards.TwitterCard;
import com.david.socialhere.models.twitter.Hashtag;
import com.david.socialhere.models.twitter.TwitterResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

import at.markushi.ui.RevealColorView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;

/**
 * Created by davidhodge on 12/13/14.
 */
public class TwitterNearbyFragment extends BaseFragment {

    View view;
    Context mContext;
    MapView mapView;
    GoogleMap mMap;
    CameraPosition HOME;

    @InjectView(R.id.card_list)
    GridViewWithHeaderAndFooter listView;
    @InjectView(R.id.loading)
    ProgressBar circularProgressBar;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.float_map)
    FloatingActionButton floatMap;
    @InjectView(R.id.float_settings)
    FloatingActionButton floatSetting;
    @InjectView(R.id.tag_list)
    GridViewWithHeaderAndFooter tagList;
    @InjectView(R.id.reveal)
    RevealColorView revealColorView;
//    @InjectView(R.id.holder)
//    FrameLayout holder;

    ArrayList<Card> cardArrayList;
    ArrayList<Card> hashCards;
    CardArrayAdapter cardArrayAdapter;
    CardArrayAdapter hashCardArrayAdapter;
    SharedPreferences sharedPreferences;
    ArrayList<Hashtag> hashtags;
    private View selectedView;
    private int backgroundColor;


    String lat;
    String lon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        backgroundColor = Color.parseColor("#00000000");

        lat = this.getArguments().getString("lat", null);
        lon = this.getArguments().getString("lon", null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_main, container, false);

        ButterKnife.inject(this, view);

        MapsInitializer.initialize(getActivity());

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
//        mapView.onResume();

//        floatMap.setVisibility(View.VISIBLE);
        floatSetting.setVisibility(View.VISIBLE);
//        floatMap.attachToListView(listView);
        floatSetting.attachToListView(listView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setEmptyView(circularProgressBar);

        floatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int color = mContext.getResources().getColor(R.color.gplus_color_4);
                final Point p = getLocationInView(revealColorView, v);

                if (selectedView == v) {
                    revealColorView.hide(p.x, p.y, backgroundColor, 0, 300, null);
                    selectedView = null;
                    mapView.setVisibility(View.GONE);
                    floatSetting.setVisibility(View.VISIBLE);
                } else {
                    revealColorView.reveal(p.x, p.y, color, v.getHeight() / 2, 340, null);
                    selectedView = v;
                    floatSetting.setVisibility(View.GONE);
                    mapView.setVisibility(View.VISIBLE);
                }
            }
        });

        floatSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int color = mContext.getResources().getColor(R.color.gplus_color_4);
                final Point p = getLocationInView(revealColorView, v);

                if (selectedView == v) {
                    revealColorView.hide(p.x, p.y, backgroundColor, 0, 300, null);
                    selectedView = null;
                    tagList.setVisibility(View.GONE);
//                    floatMap.setVisibility(View.VISIBLE);
                } else {
                    revealColorView.reveal(p.x, p.y, color, v.getHeight() / 2, 340, null);
                    selectedView = v;
//                    floatMap.setVisibility(View.GONE);
                    tagList.setVisibility(View.VISIBLE);
                }
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.park_color, R.color.park_color_ab, R.color.white, R.color.white);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCreds();
            }
        });

        if (isNetworkConnectionAvailable()) {
            getCreds();
            setUpMapIfNeeded(view);
        } else {
            Toast.makeText(mContext, "Network Connection needed!", Toast.LENGTH_LONG).show();
        }
    }

    private void setUpMapIfNeeded(View view) {
        if (mMap == null) {
            mMap = ((MapView) view.findViewById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.setIndoorEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(false);

    }

    String accessToken;

    private void getCreds() {
        Ion.with(this)
                .load("https://api.twitter.com/oauth2/token")
                        // embedding twitter api key and secret is a bad idea, but this isn't a real twitter app :)
                .basicAuthentication(getString(R.string.twitter_key), getString(R.string.twitter_secret))
                .setBodyParameter("grant_type", "client_credentials")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(mContext, "Error loading tweets", Toast.LENGTH_LONG).show();
                            return;
                        }
                        accessToken = result.get("access_token").getAsString();
                        load();
                        loadMap();
                    }
                });
    }

    private void load() {
        // load the tweets
        String url = "https://api.twitter.com/1.1/search/tweets.json?q=&geocode=" + lat + "," + lon + ",20mi";

        Ion.with(mContext).load(url)
                .setHeader("Authorization", "Bearer " + accessToken)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            Toast.makeText(mContext, "Error loading tweets", Toast.LENGTH_LONG).show();
                            return;
                        }

                        Log.d("response", result);
                        cardArrayList = new ArrayList<Card>();
                        TwitterResponse twitterResponse = new Gson().fromJson(result, TwitterResponse.class);
                        cardArrayList = new ArrayList<Card>();

                        if (sharedPreferences.getBoolean("ads", true) == true) {
                            if (isNetworkConnectionAvailable()) {
                                AdCard adCard = new AdCard(mContext);
                                if (Build.VERSION.SDK_INT < 21) {
                                    adCard.setShadow(false);
                                    adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
                                }
                                cardArrayList.add(adCard);
                            }
                        }

                        for (int i = 0; i < twitterResponse.getStatuses().size(); i++) {
                            TwitterCard twitterCard = new TwitterCard(mContext, twitterResponse.getStatuses().get(i));
                            if (Build.VERSION.SDK_INT < 21) {
                                twitterCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                twitterCard.setShadow(false);
                            }
                            cardArrayList.add(twitterCard);
                        }

                        hashtags = new ArrayList<Hashtag>();
                        for (int i = 0; i < twitterResponse.getStatuses().size(); i++) {
                            if (twitterResponse.getStatuses().get(i).getEntities().getHashtags().size() > 0) {
                                for (int h = 0; h < twitterResponse.getStatuses().get(i).getEntities().getHashtags().size(); h++) {
                                    hashtags.add(twitterResponse.getStatuses().get(i).getEntities().getHashtags().get(h));
                                }
                            }
                        }

                        hashCards = new ArrayList<Card>();
                        if(hashtags.size() > 0){
                            for(int ht = 0; ht < hashtags.size(); ht++){
                                HashTagCard hashTagCard = new HashTagCard(mContext, hashtags.get(ht));
                                if (Build.VERSION.SDK_INT < 21) {
                                    hashTagCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                    hashTagCard.setShadow(false);
                                }
                                hashCards.add(hashTagCard);
                            }
                        }

                        if (sharedPreferences.getBoolean("ads", true) == true) {
                            if (isNetworkConnectionAvailable()) {
                                AdCard adCard = new AdCard(mContext);
                                if (Build.VERSION.SDK_INT < 21) {
                                    adCard.setShadow(false);
                                    adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
                                }
                                cardArrayList.add(adCard);
                            }
                        }

                        cardArrayAdapter = new CardArrayAdapter(mContext, cardArrayList);
                        hashCardArrayAdapter = new CardArrayAdapter(mContext, hashCards);
                        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(cardArrayAdapter);
                        swingBottomInAnimationAdapter.setAbsListView(listView);
                        listView.setAdapter(swingBottomInAnimationAdapter);
                        tagList.setAdapter(hashCardArrayAdapter);

                        HOME = new CameraPosition.Builder().target(new LatLng(twitterResponse.getStatuses().get(0).getGeo().getCoordinates().get(0),twitterResponse.getStatuses().get(0).getGeo().getCoordinates().get(0)))
                                .zoom(17)
                                .bearing(-100)
                                .tilt(25)
                                .build();

                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(HOME));

                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
    }

    private void loadMap() {
        // load the tweets
        String url = "https://api.twitter.com/1.1/geo/search.json?lat=" + lat + "&long=" + lon;

        Ion.with(mContext).load(url)
                .setHeader("Authorization", "Bearer " + accessToken)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            Toast.makeText(mContext, "Error loading tweets", Toast.LENGTH_LONG).show();
                            return;
                        }

                        Log.d("response", result);
//                        TwitterResponse twitterResponse = new Gson().fromJson(result, TwitterResponse.class);
//
//                        HOME = new CameraPosition.Builder().target(new LatLng(twitterResponse.getStatuses().get(0).getGeo().getCoordinates().get(0),twitterResponse.getStatuses().get(0).getGeo().getCoordinates().get(0)))
//                                .zoom(17)
//                                .bearing(-100)
//                                .tilt(25)
//                                .build();
//
//                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(HOME));

                    }
                });
    }


    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    private Point getLocationInView(View src, View target) {
        final int[] l0 = new int[2];
        src.getLocationOnScreen(l0);

        final int[] l1 = new int[2];
        target.getLocationOnScreen(l1);

        l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
        l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

        return new Point(l1[0], l1[1]);
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
