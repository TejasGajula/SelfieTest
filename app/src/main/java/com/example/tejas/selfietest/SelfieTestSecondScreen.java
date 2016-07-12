package com.example.tejas.selfietest;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class SelfieTestSecondScreen extends Activity {
    //private int[] colors = {Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.MAGENTA,
            //Color.RED};
    private int index;
    private int[] buttonColors =    {R.color.selfieButtonDarkBlue, R.color.selfieButtonDarkGreen, R.color.selfieButtonDarkPurple,
                                    R.color.selfieButtonGreen, R.color.selfieButtonMagenta, R.color.selfieButtonOrange, R.color.selfieButtonPurple,
                                    R.color.selfieButtonRed, R.color.selfieButtonYellow};
    private int[] colors =    {R.color.selfieDarkBlue, R.color.selfieDarkGreen, R.color.selfieDarkPurple,
            R.color.selfieGreen, R.color.selfieMagenta, R.color.selfieOrange, R.color.selfiePurple,
            R.color.selfieRed, R.color.selfieYellow};
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selfie_test_second_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView welcomeMessage = (TextView) findViewById(R.id.JoinMessage);
        Typeface helvetica = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        welcomeMessage.setTypeface(helvetica);
        setBackgroundColor();
        timer = new Timer();
        timer.schedule(new updateBackgroundTask(), 0, 5000);
    }

    private void setBackgroundColor()
    {
        int newIndex = randInt(0, colors.length-1);

        int colorFrom = ContextCompat.getColor(getApplicationContext(), colors[index]);
        int colorTo = ContextCompat.getColor(getApplicationContext(), colors[newIndex]);
        int buttonColorFrom = ContextCompat.getColor(getApplicationContext(), buttonColors[index]);
        int buttonColorTo = ContextCompat.getColor(getApplicationContext(), buttonColors[newIndex]);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(500); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                RelativeLayout l = (RelativeLayout)(findViewById(R.id.main_view));
                l.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        ValueAnimator buttonColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), buttonColorFrom, buttonColorTo);
        buttonColorAnimation.setDuration(500); // milliseconds
        buttonColorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Button b = (Button)(findViewById(R.id.registerButton));
                b.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        buttonColorAnimation.start();
        //l.setBackgroundResource(colors[index]);
        //b.setBackgroundResource(buttonColors[newIndex]);
        index = newIndex;
    }

    private int randInt(int low, int high)
    {
        return (int)(Math.random()*(high-low+1)+low);
    }

    public void secondClickFunction(View view) {
        EditText username = (EditText) findViewById(R.id.textboxTwo);
        if(username.length()>16||username.length()<3)
        {
            Toast.makeText(getApplicationContext(), "That username is not eligible!! Try another one within the character limit.", Toast.LENGTH_LONG).show();
        }
        else
        {
            startActivity(new Intent(SelfieTestSecondScreen.this, SelfieTestPostSecondActivity.class));
            Log.i("Username", username.getText().toString());
            Log.i("Info", "Button Tapped, Selfie Joined");
        }
        //setBackgroundColor(colors[randInt(0, colors.length-1)]);
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
}
