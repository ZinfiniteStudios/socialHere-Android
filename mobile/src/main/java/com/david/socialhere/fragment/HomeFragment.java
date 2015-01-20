package com.david.socialhere.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
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
import com.david.socialhere.cards.home.WeatherCard;
import com.david.socialhere.models.flickr.FlickrResponse;
import com.david.socialhere.models.weather.current.CurrentResponse;
import com.david.socialhere.utils.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;

/**
 * Created by davidhodge on 1/9/15.
 */
public class HomeFragment extends BaseFragment implements LocationListener {

    //TODO
    //ADD WEATHER CARD - give links to free + paid
    //ADD AD CARD
    //RANDOM 4SQ Venue Card
    //Twitter activity rate card
    //ADD Cards for other apps

    LocationRequest mLocationRequest;
    // Milliseconds per second
    private static final int MILLISECONDS_PER_SECOND = 1000;
    // Update frequency in seconds
    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
    // Update frequency in milliseconds
    private static final long UPDATE_INTERVAL =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    // The fastest update frequency, in seconds
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    // A fast frequency ceiling in milliseconds
    private static final long FASTEST_INTERVAL =
            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
    private GoogleApiClient mGoogleApiClient;

    View view;
    Context mContext;
    @InjectView(R.id.card_list)
    GridViewWithHeaderAndFooter listView;
    @InjectView(R.id.loading)
    ProgressBar circularProgressBar;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    SharedPreferences sharedPreferences;

    ArrayList<Card> cardArrayList;
    CardArrayAdapter cardArrayAdapter;
    String requestUrl;

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

        swipeRefreshLayout.setColorSchemeResources(R.color.park_color, R.color.park_color_ab, R.color.white, R.color.white);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGoogleApiClient.connect();
            }
        });

        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);


//        cardArrayList = new ArrayList<Card>();
//        cardArrayAdapter = new CardArrayAdapter(mContext, cardArrayList);
////                            SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(cardArrayAdapter);
////                            swingBottomInAnimationAdapter.setInitialDelayMillis(300);
////                            swingBottomInAnimationAdapter.setAbsListView(listView);
//        listView.setAdapter(cardArrayAdapter);
//        swipeRefreshLayout.setRefreshing(false);

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        PendingResult<Status> result = LocationServices.FusedLocationApi
                                .requestLocationUpdates(
                                        mGoogleApiClient,   // your connected GoogleApiClient
                                        mLocationRequest,   // a request to receive a new location
                                        HomeFragment.this); // the listener which will receive updated locations

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                    }
                })
                .build();

    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        if (cardArrayList != null) {
            if (cardArrayList.size() > 0) {
                cardArrayList.clear();
            }
        }
        cardArrayList = new ArrayList<Card>();
        cardArrayAdapter = new CardArrayAdapter(mContext, cardArrayList);
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(cardArrayAdapter);
        swingBottomInAnimationAdapter.setAbsListView(listView);
        listView.setAdapter(swingBottomInAnimationAdapter);
        swipeRefreshLayout.setRefreshing(false);

        if (sharedPreferences.getBoolean("ads", true) == true) {
            AdCard adCard = new AdCard(mContext);
            if (Build.VERSION.SDK_INT < 21) {
                adCard.setShadow(false);
                adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
            }
            cardArrayList.add(adCard);
            cardArrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void getWeather(String lat, String lon) {
        if (sharedPreferences.getBoolean("mes", false) == true) {
            requestUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&units=metric";
        } else {
            requestUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&units=imperial";
        }
        Log.d("weather", requestUrl);

        Ion.with(mContext)
                .load(requestUrl)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String jsonContent) {
                        if (e != null) {
                            Log.e("weather", e.toString());
                        } else {

                            CurrentResponse currentResponse = new Gson().fromJson(jsonContent, CurrentResponse.class);
                            WeatherCard currentWeatherCard = new WeatherCard(mContext, currentResponse);
                            if (Build.VERSION.SDK_INT < 21) {
                                currentWeatherCard.setShadow(false);
                                currentWeatherCard.changeBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                currentWeatherCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                            }
                            cardArrayList.add(currentWeatherCard);
                            cardArrayAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    public void getFourSquarePlaces(String lat, String lon) {

    }

    public void getPictures(String lat, String lon) {
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

                                int min = 1;
                                int max = flickrResponse.getPhotos().getPhoto().size();
                                Random r = new Random();
                                int i1 = r.nextInt(max - min + 1) + min;
                                FlickrCard flickrCard = new FlickrCard(mContext, flickrResponse.getPhotos().getPhoto().get(i1));
                                if (Build.VERSION.SDK_INT < 21) {
                                    flickrCard.setBackgroundResource(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
                                    flickrCard.setShadow(false);
                                }
                                cardArrayList.add(flickrCard);
                                cardArrayAdapter.notifyDataSetChanged();

                            } catch (Exception e1) {
                                Log.e("auto", e1.toString());
                            }
                        }
                    }
                });
    }

    @Override
    public void onLocationChanged(Location location) {
        if (cardArrayList != null) {
            if (cardArrayList.size() > 0) {
                cardArrayList.clear();
            }
        }

        if (sharedPreferences.getBoolean("ads", true) == true) {
            AdCard adCard = new AdCard(mContext);
            if (Build.VERSION.SDK_INT < 21) {
                adCard.setShadow(false);
                adCard.setBackgroundResource(new ColorDrawable(android.R.color.transparent));
            }
            cardArrayList.add(adCard);
            cardArrayAdapter.notifyDataSetChanged();
        }

        getWeather(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
        getPictures(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
        getFourSquarePlaces(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
        swipeRefreshLayout.setRefreshing(false);
        mGoogleApiClient.disconnect();
    }
}
