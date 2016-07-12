package com.example.tejas.selfietest;

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
        ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("joinselfie.com"))
                    .setContentTitle("Download Selfie today!")
                    .build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sariassong);
        MediaPlayer yes = MediaPlayer.create(getApplicationContext(), R.raw.therealher);
        mediaPlayer.start(); yes.start();
    }

}
