package com.david.socialhere.cards;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.socialhere.R;
import com.david.socialhere.models.twitter.Status;
import com.david.socialhere.utils.RoundTransformation;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by davidhodge on 12/13/14.
 */
public class TwitterCard extends Card {
    Context mContext;
    Status tweet;
    ImageView image;
    TextView handle;
    TextView tweetText;
    TextView tweetTime;

    public TwitterCard(Context context, Status tweet) {
        super(context, R.layout.card_twitter);
        mContext = context;
        this.tweet = tweet;
    }

    public TwitterCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        image = (ImageView) parent.findViewById(R.id.image_user);
        handle = (TextView) parent.findViewById(R.id.handle);
        tweetText = (TextView) parent.findViewById(R.id.tweet_text);
        tweetTime = (TextView) parent.findViewById(R.id.tweet_time);

        String imageUrl = tweet.getUser().getProfileImageUrl();
        imageUrl = imageUrl.replace("normal", "bigger");
        Picasso.with(mContext).load(imageUrl).transform(new RoundTransformation(4,4)).into(image);

        tweetText.setText(tweet.getText());
        handle.setText(tweet.getUser().getScreenName());


        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd kk::mm:ss yyyy");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

            String dateString = tweet.getCreatedAt();
            dateString = dateString.replace("+0000", "");
            Date date = simpleDateFormat.parse(dateString);
            tweetTime.setText(simpleDateFormat1.format(date));
        }catch (Exception e){
            tweetTime.setText(removeLastChar(tweet.getCreatedAt()));
        }



        this.setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                String url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
                Intent aboutIntent = new Intent(Intent.ACTION_VIEW);
                aboutIntent.putExtra("link", url);
                mContext.startActivity(aboutIntent);
            }
        });
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-14);
    }
}
