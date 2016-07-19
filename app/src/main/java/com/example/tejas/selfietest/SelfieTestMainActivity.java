package com.example.tejas.selfietest;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthConfig;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.Timer;
import java.util.TimerTask;

import io.fabric.sdk.android.Fabric;
public class SelfieTestMainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Ix4z1rPUdVTwEaoSgo60syGKo";
    private static final String TWITTER_SECRET = "zBZA7Q7nS9XtGDrBsLwygDtlMNEZdshIn8PjJo4zuckuJDtT8m";
    private static int index;
    private int[] buttonColors =    {R.color.selfieButtonDarkBlue, R.color.selfieButtonDarkGreen, R.color.selfieButtonDarkPurple,
            R.color.selfieButtonGreen, R.color.selfieButtonMagenta, R.color.selfieButtonOrange, R.color.selfieButtonPurple,
            R.color.selfieButtonRed, R.color.selfieButtonYellow};
    private int[] colors =    {R.color.selfieDarkBlue, R.color.selfieDarkGreen, R.color.selfieDarkPurple,
            R.color.selfieGreen, R.color.selfieMagenta, R.color.selfieOrange, R.color.selfiePurple,
            R.color.selfieRed, R.color.selfieYellow};
    private Timer timer;
    private Button registerButton;
    private RelativeLayout layout;

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
        TextView welcomeMessage = (TextView) findViewById(R.id.selfie);
        Typeface hel = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        Typeface helvetica = hel.DEFAULT_BOLD;
        welcomeMessage.setTypeface(helvetica);
        TextView selfieMessage = (TextView) findViewById(R.id.SeeYouSoon);
        selfieMessage.setTypeface(helvetica);
        /*TextView message = (TextView) findViewById(R.id.textView);
        message.setTypeface(hel);
        TextView privacy = (TextView) findViewById(R.id.textView2);
        privacy.setTypeface(hel);
        */
        ImageView selfieLogo = (ImageView) findViewById(R.id.white_image_view);
        registerButton = (Button)(findViewById(R.id.DigitsButton));
        layout = (RelativeLayout)(findViewById(R.id.Bingtao));
        initializeBackgroundColor();
        timer = new Timer();
        timer.schedule(new updateBackgroundTask(), getResources().getInteger(R.integer.animation_transition_length),
                getResources().getInteger(R.integer.animation_stay_length));
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


    private void initializeBackgroundColor()
    {
        index = randInt(0, colors.length-1);
        layout.setBackgroundResource(colors[index]);
        GradientDrawable buttonDrawable = (GradientDrawable)((DrawableContainer.DrawableContainerState) ((StateListDrawable)
                (registerButton.getBackground())).getConstantState()).getChildren()[0];
        buttonDrawable.setColor(ContextCompat.getColor(getApplicationContext(), buttonColors[index]));
        buttonDrawable.setStroke(1, ContextCompat.getColor(getApplicationContext(), buttonColors[index]));
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
    public static int getColorIndex(){ return index;}
    private void setBackgroundColor()
    {
//        int newIndex;
//        do
//        {
//            newIndex = randInt(0, colors.length-1);
//        }while (newIndex==index);
        int newIndex = randInt(0, colors.length-1);
        int colorFrom = ContextCompat.getColor(getApplicationContext(), colors[index]);
        int colorTo = ContextCompat.getColor(getApplicationContext(), colors[newIndex]);
        int buttonColorFrom = ContextCompat.getColor(getApplicationContext(), buttonColors[index]);
        int buttonColorTo = ContextCompat.getColor(getApplicationContext(), buttonColors[newIndex]);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(getResources().getInteger(R.integer.animation_transition_length)); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                layout.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        ValueAnimator buttonColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), buttonColorFrom, buttonColorTo);
        buttonColorAnimation.setDuration(2000); // milliseconds
        buttonColorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                GradientDrawable buttonDrawable = (GradientDrawable)((DrawableContainer.DrawableContainerState) ((StateListDrawable)
                        (registerButton.getBackground())).getConstantState()).getChildren()[0];
                buttonDrawable.setColor((int) animator.getAnimatedValue());
                buttonDrawable.setStroke(1, (int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        buttonColorAnimation.start();
        index = newIndex;
    }
    class updateBackgroundTask extends TimerTask
    {
        public void run()
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setBackgroundColor();
                }
            });
        }
    }
    public static int randInt(int low, int high)
    {
        return (int)(Math.random()*(high-low+1)+low);
    }

    @Override
    public void onBackPressed()
    {}
}
