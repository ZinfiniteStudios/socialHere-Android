package com.david.socialhere.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;

import com.david.socialhere.R;
import com.david.socialhere.fragment.FlickrFragment;
import com.david.socialhere.fragment.FourSquareNearbyFragment;
import com.david.socialhere.fragment.FourSquareTipFragment;
import com.david.socialhere.fragment.HomeFragment;
import com.david.socialhere.fragment.TwitterNearbyFragment;
import com.david.socialhere.fragment.YoutubeFeedFragment;
import com.david.socialhere.utils.Constants;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import it.neokree.materialnavigationdrawer.MaterialAccount;
import it.neokree.materialnavigationdrawer.MaterialAccountListener;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.MaterialSection;

/**
 * Created by davidhodge on 1/8/15.
 */
public class UserMainActivity extends MaterialNavigationDrawer implements MaterialAccountListener, LocationListener, MoPubInterstitial.InterstitialAdListener {

    MoPubInterstitial mInterstitial;

    Handler handler;
    Runnable runnable;
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
//    private GoogleApiClient mGoogleApiClient;

    MaterialSection section1, section2, section3, section4, section5, section6, recorder, night, last, settingsSection;

    @Override
    public void init(Bundle savedInstanceState) {

        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);


//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @Override
//                    public void onConnected(Bundle bundle) {
//                        PendingResult<Status> result = LocationServices.FusedLocationApi
//                                .requestLocationUpdates(
//                                        mGoogleApiClient,   // your connected GoogleApiClient
//                                        mLocationRequest,   // a request to receive a new location
//                                        UserMainActivity.this); // the listener which will receive updated locations
//
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//
//                    }
//                })
//                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(ConnectionResult connectionResult) {
//
//                    }
//                })
//                .build();

        SmartLocation.with(getApplicationContext())
                .location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        setUpDrawer(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                    }
                });

        // add first account
        MaterialAccount account = new MaterialAccount(" ", "  ", this.getResources().getDrawable(R.drawable.ic_launcher), this.getResources().getDrawable(R.drawable.blur));
        this.addAccount(account);
//
        // set listener
        this.setAccountListener(this);

        section5 = this.newSection("Home", new HomeFragment());

        this.addSection(section5);

        Intent i = new Intent(this, SettingActivity.class);
        settingsSection = this.newSection("About", this.getResources().getDrawable(android.R.drawable.ic_dialog_info), i);

        this.addBottomSection(settingsSection);

    }

    public void setUpDrawer(String lat, String lon) {
        Bundle forecast = new Bundle();
        forecast.putString("lat", lat);
        forecast.putString("lon", lon);
        TwitterNearbyFragment twitterNearbyFragment = new TwitterNearbyFragment();
        twitterNearbyFragment.setArguments(forecast);
        FourSquareTipFragment fourSquareTipFragment = new FourSquareTipFragment();
        fourSquareTipFragment.setArguments(forecast);
        FourSquareNearbyFragment fourSquareNearbyFragment = new FourSquareNearbyFragment();
        fourSquareNearbyFragment.setArguments(forecast);
        YoutubeFeedFragment youtubeFeedFragment = new YoutubeFeedFragment();
        youtubeFeedFragment.setArguments(forecast);
        FlickrFragment flickrFragment = new FlickrFragment();
        flickrFragment.setArguments(forecast);

        // create sections
        section1 = this.newSection("Nearby", twitterNearbyFragment);
        section2 = this.newSection("Tips", fourSquareTipFragment);
        section3 = this.newSection("Nearby", youtubeFeedFragment);
        section4 = this.newSection("Nearby", flickrFragment);
        section6 = this.newSection("Places", fourSquareNearbyFragment);

//        section2 = this.newSection("Section 2",new MaterialSectionListener() {
//            @Override
//            public void onClick(MaterialSection section) {
//                Toast.makeText(NavigationDrawerActivityRipple.this, "Section 2 Clicked", Toast.LENGTH_SHORT).show();
//
//                // deselect section when is clicked
//                section.unSelect();
//            }
//        });
//        // recorder section with icon and 10 notifications
//        recorder = this.newSection("Recorder",this.getResources().getDrawable(R.drawable.ic_mic_white_24dp),new FragmentIndex()).setNotifications(10);
//        // night section with icon, section color and notifications
//        night = this.newSection("Night Section", this.getResources().getDrawable(R.drawable.ic_hotel_grey600_24dp), new FragmentIndex())
//                .setSectionColor(Color.parseColor("#2196f3"),Color.parseColor("#1565c0")).setNotifications(150);
//        // night section with section color
//        last = this.newSection("Last Section", new FragmentButton()).setSectionColor(Color.parseColor("#ff9800"),Color.parseColor("#ef6c00"));


        // add your sections to the drawer
        this.addSubheader("Twitter");
        this.addSection(section1);
        this.addSubheader("Foursquare");
        this.addSection(section2);
        this.addSection(section6);
        this.addSubheader("Youtube");
        this.addSection(section3);
        this.addSubheader("Flickr");
        this.addSection(section4);
        this.addDivisor();

        this.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_ANYWHERE);

        mInterstitial = new MoPubInterstitial(this, Constants.MOPUB_ID);
        mInterstitial.setInterstitialAdListener(this);
        //TODO ADD BACK
//        startTimer();
    }

    public void startTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                mInterstitial.load();

            }
        };
        handler.postDelayed(runnable, 15000);
    }

    @Override
    protected void onDestroy() {
        mInterstitial.destroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
//        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        setUpDrawer(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
//        mGoogleApiClient.disconnect();
    }

    @Override
    public void onAccountOpening(MaterialAccount account) {
        // open profile activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onChangeAccount(MaterialAccount newAccount) {
        // when another account is selected
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
        if (mInterstitial.isReady()) {
            mInterstitial.show();
        } else {
            // Other code
        }
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
        startTimer();
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {

    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {

    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
        startTimer();
    }
}
