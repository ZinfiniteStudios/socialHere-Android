package com.david.socialhere.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.david.socialhere.R;
import com.david.socialhere.fragment.FlickrFragment;
import com.david.socialhere.fragment.FourSquareTipFragment;
import com.david.socialhere.fragment.TwitterNearbyFragment;
import com.david.socialhere.fragment.YoutubeFeedFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements LocationListener {

    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    Context mContext;
    WeatherPagerAdapter weatherPagerAdapter;
    ArrayList<Fragment> fragments;
    ArrayList<String> titles;
    ActionBar actionBar;

    InterstitialAd interstitial;
    AdRequest adRequest;

    MapView mapView;
    GoogleMap mMap;

    LocationRequest mLocationRequest;
    SharedPreferences sharedPreferences;
//    LocationClient mLocationClient;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mContext = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        actionBar = getSupportActionBar();

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-5640708555066600/7283621296");
        adRequest = new AdRequest.Builder().build();

        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        PendingResult<Status> result = LocationServices.FusedLocationApi
                                .requestLocationUpdates(
                                        mGoogleApiClient,   // your connected GoogleApiClient
                                        mLocationRequest,   // a request to receive a new location
                                        MainActivity.this); // the listener which will receive updated locations

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

    private void setUpWeather(String lat, String lon){
        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        titles.add("Twitter");
        titles.add("4sq");
        titles.add("Youtube");
        titles.add("Flickr");

        Bundle forecast = new Bundle();
        forecast.putString("lat", lat);
        forecast.putString("lon", lon);
        TwitterNearbyFragment twitterNearbyFragment = new TwitterNearbyFragment();
        twitterNearbyFragment.setArguments(forecast);
        FourSquareTipFragment fourSquareTipFragment = new FourSquareTipFragment();
        fourSquareTipFragment.setArguments(forecast);
        YoutubeFeedFragment youtubeFeedFragment = new YoutubeFeedFragment();
        youtubeFeedFragment.setArguments(forecast);
        FlickrFragment flickrFragment = new FlickrFragment();
        flickrFragment.setArguments(forecast);
        fragments.add(twitterNearbyFragment);
        fragments.add(fourSquareTipFragment);
        fragments.add(youtubeFeedFragment);
        fragments.add(flickrFragment);
        weatherPagerAdapter = new WeatherPagerAdapter(mContext, titles, fragments);
        viewPager.setAdapter(weatherPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Random random = new Random();
                if(random.nextBoolean()) {
                    interstitial.loadAd(adRequest);
                    interstitial.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            //TODO ADD BACK?
//                            if (interstitial.isLoaded()) {
//                                interstitial.show();
//                            }
                        }
                    });
                }
                actionBar.setTitle(titles.get(position));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        setUpWeather(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
        mGoogleApiClient.disconnect();
    }

    class WeatherPagerAdapter extends FragmentPagerAdapter {
        Context context;
        private LayoutInflater inflater;
        private ArrayList<String> titles;
        private ArrayList<Fragment> mFragments;

        public WeatherPagerAdapter(Context context, ArrayList<String> strings, ArrayList<Fragment> fragments){
            super(MainActivity.this.getSupportFragmentManager());
            this.context = context;
            this.titles = strings;
            this.mFragments = fragments;
            this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return this.titles.size();

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        public void setTitles(ArrayList<String> titles) {
            this.titles = titles;
        }

        public void setFragments(ArrayList<Fragment> fragments) {
            this.mFragments = fragments;
        }
    }

    ViewPager.OnPageChangeListener WeatherOPCL = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };

    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.david.ioweatherfree.service.WeatherService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
