package com.example.tejas.selfietest;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
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
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selfie_test_second_screen);

        registerButton = (Button)(findViewById(R.id.registerButton));
        layout = (RelativeLayout)(findViewById(R.id.main_view));
        initializeBackgroundColor();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView welcomeMessage = (TextView) findViewById(R.id.JoinMessage);
        Typeface helvetica = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        welcomeMessage.setTypeface(helvetica);
        EditText user = (EditText)findViewById(R.id.editText);
        timer = new Timer();
        timer.schedule(new updateBackgroundTask(), getResources().getInteger(R.integer.animation_transition_length),
                                            getResources().getInteger(R.integer.animation_stay_length));

    }
    private void initializeBackgroundColor()
    {
        int newIndex = randInt(0, colors.length-1);
        layout.setBackgroundResource(colors[newIndex]);
        GradientDrawable buttonDrawable = (GradientDrawable)((DrawableContainer.DrawableContainerState) ((StateListDrawable)
                (registerButton.getBackground())).getConstantState()).getChildren()[0];
        buttonDrawable.setColor(ContextCompat.getColor(getApplicationContext(), buttonColors[newIndex]));
        buttonDrawable.setStroke(1, ContextCompat.getColor(getApplicationContext(), buttonColors[newIndex]));
        index=newIndex;
    }


    private void setBackgroundColor()
    {
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

    public static int randInt(int low, int high)
    {
        return (int)(Math.random()*(high-low+1)+low);
    }

    public void secondClickFunction(View view) {
        EditText user = (EditText) findViewById(R.id.textboxTwo);
        if(user.length()>16||user.length()<3)
        {
            Toast.makeText(getApplicationContext(), "That username is not eligible!! Try another one within the character limit.", Toast.LENGTH_LONG).show();
        }
        else
        {
            startActivity(new Intent(SelfieTestSecondScreen.this, SelfieTestPostSecondActivity.class));

            username = user.getText().toString();
            Log.i("Username", username);
            Log.i("Info", "Button Tapped, Selfie Joined");
        }
        //setBackgroundColor(colors[randInt(0, colors.length-1)]);
    }
    public static String getUsername()
    {
        return username;
    }
    public static int getColorIndex(){ return index;}

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
