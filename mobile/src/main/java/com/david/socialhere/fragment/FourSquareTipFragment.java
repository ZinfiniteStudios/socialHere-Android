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
import com.david.socialhere.cards.FourSquareTipCard;
import com.david.socialhere.models.places.Places;
import com.david.socialhere.models.tips.Tips;
import com.david.socialhere.utils.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import at.markushi.ui.RevealColorView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;

/**
 * Created by davidhodge on 12/14/14.
 */
public class FourSquareTipFragment extends BaseFragment {

    View view;
    Context mContext;
    @InjectView(R.id.card_list)
    GridViewWithHeaderAndFooter listView;
    @InjectView(R.id.loading)
    ProgressBar circularProgressBar;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.float_map)
    FloatingActionButton floatMap;
    @InjectView(R.id.reveal)
    RevealColorView revealColorView;
    MapView mapView;
    GoogleMap mMap;
    CameraPosition HOME;

    private View selectedView;
    private int backgroundColor;

    ArrayList<Card> cardArrayList;
    CardArrayAdapter cardArrayAdapter;
    SharedPreferences sharedPreferences;
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
        setUpMapIfNeeded(view);

        floatMap.setVisibility(View.VISIBLE);
        floatMap.attachToListView(listView);
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
                } else {
                    revealColorView.reveal(p.x, p.y, color, v.getHeight() / 2, 340, null);
                    selectedView = v;
                    mapView.setVisibility(View.VISIBLE);
                }
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.park_color, R.color.park_color_ab, R.color.white, R.color.white);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlaces();
            }
        });

        if (isNetworkConnectionAvailable()) {
            getPlaces();
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

        getPlacesMap();
    }

    public void getPlaces() {
        SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd");
        String now = parser.format(new Date());

        String testUrl = "https://api.foursquare.com/v2/tips/search?ll="
                + lat + "," + lon + "&client_id="
                + Constants.FOUR_CLIENT_ID + "&client_secret="
                + Constants.FOUR_CLIENT_SECRET
                + "&v=" + now;

        Ion.with(mContext)
                .load(testUrl)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> stringResponse) {
                        if (e != null) {
                            Log.e("parkfans", e.toString());
                        } else {
                            Log.d("parkfans", stringResponse.getResult().toString());

                            Tips places = new Gson().fromJson(stringResponse.getResult(), Tips.class);

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

                            for (int i = 0; i < places.getResponse().getTips().size(); i++) {
                                FourSquareTipCard fourSquareCard = new FourSquareTipCard(mContext, places.getResponse().getTips().get(i));
                                if (Build.VERSION.SDK_INT < 21) {
                                    fourSquareCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                    fourSquareCard.setShadow(false);
                                }
                                cardArrayList.add(fourSquareCard);
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
                            SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(cardArrayAdapter);
                            swingBottomInAnimationAdapter.setAbsListView(listView);
                            listView.setAdapter(swingBottomInAnimationAdapter);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }

    public void getPlacesMap() {
        SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd");
        String now = parser.format(new Date());

        String testUrl = "https://api.foursquare.com/v2/venues/search?ll="
                + lat + "," + lon + "&client_id="
                + Constants.FOUR_CLIENT_ID + "&client_secret="
                + Constants.FOUR_CLIENT_SECRET
                + "&v=" + now;

        Ion.with(mContext)
                .load(testUrl)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> stringResponse) {
                        if (e != null) {
                            Log.e("four", e.toString());
                        } else {
                            Log.d("fours", stringResponse.getResult().toString());

                            Places places = new Gson().fromJson(stringResponse.getResult(), Places.class);

                            HOME = new CameraPosition.Builder().target(new LatLng(places.getResponse().getVenues().get(0).getLocation().getLat(), places.getResponse().getVenues().get(0).getLocation().getLng()))
                                    .zoom(17)
                                    .bearing(-100)
                                    .tilt(25)
                                    .build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(HOME));

                            for (int i = 0; i < places.getResponse().getVenues().size(); i++) {
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(places.getResponse().getVenues().get(i).getLocation().getLat(), places.getResponse().getVenues().get(i).getLocation().getLng()))
                                        .title(places.getResponse().getVenues().get(i).getName()));
                            }

                        }
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
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
