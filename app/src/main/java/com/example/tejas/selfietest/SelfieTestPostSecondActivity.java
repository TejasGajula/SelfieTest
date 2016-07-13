package com.example.tejas.selfietest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookDialog;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import io.fabric.sdk.android.Fabric;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetcomposer.TweetUploadService;
public class SelfieTestPostSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie_test_post_second);
        TextView welcomeMessage = (TextView) findViewById(R.id.JoinMessage);
        Typeface helvetica = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        welcomeMessage.setTypeface(helvetica);
        AppEventsLogger.activateApp(this);
        TwitterAuthConfig authConfig =  new TwitterAuthConfig("consumerKey", "consumerSecret");
        Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("Add me at username on Selfie!");
        //builder.show();
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sariassong);
        MediaPlayer yes = MediaPlayer.create(getApplicationContext(), R.raw.therealher);
        mediaPlayer.start(); yes.start();
    }
    public void facebook(View view)
    {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("joinselfie.com"))
                .setContentTitle("Download Selfie today!")
                .build();
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
    }

}
/*<com.facebook.share.widget.ShareButton
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:id="@+id/fb_share_button"
android:text="bingtao"
android:layout_below="@+id/JoinMessage"
android:layout_centerHorizontal="true" />
*/
