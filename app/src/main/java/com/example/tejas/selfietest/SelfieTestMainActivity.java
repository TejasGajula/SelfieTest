package com.example.tejas.selfietest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthConfig;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;
public class SelfieTestMainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Ix4z1rPUdVTwEaoSgo60syGKo";
    private static final String TWITTER_SECRET = "zBZA7Q7nS9XtGDrBsLwygDtlMNEZdshIn8PjJo4zuckuJDtT8m";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("SEMedia");
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selfie_test_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView welcomeMessage = (TextView) findViewById(R.id.WelcomeToMessage);
        Typeface hel = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        Typeface helvetica = hel.DEFAULT_BOLD;
        welcomeMessage.setTypeface(helvetica);
        TextView selfieMessage = (TextView) findViewById(R.id.SelfieMessage);
        selfieMessage.setTypeface(helvetica);
        TextView message = (TextView) findViewById(R.id.textView);
        message.setTypeface(hel);
        TextView privacy = (TextView) findViewById(R.id.textView2);
        privacy.setTypeface(hel);
        ImageView selfieLogo = (ImageView) findViewById(R.id.imageView);
       /*sAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "you are my son lolololololo0l", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SelfieTestMainActivity.this, SelfieTestSecondScreen.class));
                Log.i("Info", "Button Tapped, Selfie Joined");
                finish();

            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });
        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "you are my son lolololololo0l", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SelfieTestMainActivity.this, SelfieTestSecondScreen.class));
                Log.i("Info", "Button Tapped, Selfie Joined");
                finish();

            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });*/

    }


    public void clickFunction(View view) {

        // EditText myTextField = (EditText) findViewById(R.id.TextBox);
        // if (myTextField.length() == 10) {
        DigitsAuthConfig.Builder temp = new DigitsAuthConfig.Builder();
        temp = temp.withThemeResId(R.style.CustomDigitsTheme);
        temp.withEmailCollection();
        temp = temp.withAuthCallBack(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                startActivity(new Intent(SelfieTestMainActivity.this, SelfieTestSecondScreen.class));
                Toast.makeText(getApplicationContext(), "Authentication Verified", Toast.LENGTH_LONG).show();
                Log.i("Info", "Button Tapped, Selfie Joined");
                finish();


            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });
        Digits.authenticate(temp.build());

       /*Digits.authenticate(new AuthCallback() {
           @Override
           public void success(DigitsSession session, String phoneNumber) {
               // TODO: associate the session userID with your user model
               Toast.makeText(getApplicationContext(), "you are my son lolololololo0l", Toast.LENGTH_LONG).show();
               startActivity(new Intent(SelfieTestMainActivity.this, SelfieTestSecondScreen.class));
               Log.i("Info", "Button Tapped, Selfie Joined");
               finish();

           }

           @Override
           public void failure(DigitsException exception) {
               Log.d("Digits", "Sign in with Digits failure", exception);
           }
       });
            // Button secondPageButton = (Button)findViewById(R.id.JoinButton);
            //startActivity(new Intent(SelfieTestMainActivity.this, SelfieTestSecondScreen.class));
              secondPageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                }
            });
            */
        //Log.i("Number", myTextField.getText().toString());
        //Log.i("Info", "Button Tapped, Selfie Joined");
        //finish();
            /*switch(view.getId())
            {
            case R.id.JoinButton:
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(myTextField.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
               /* myInputText.setText("");
                responseText.setText("MySampleFile.txt saved to External Storage...");
                break;
                */



      /*  } else {
            Toast.makeText(getApplicationContext(), myTextField.getText().toString() + " is not a real number! Try again...", Toast.LENGTH_LONG).show();
            Log.e("Failure", "null");
            Log.e("Error", "Wrong Number");
        }
        */

    }
    @Override
    public void onBackPressed()
    {}
}
