package com.example.tejas.selfietest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
        setBackgroundColor(randInt(0, colors.length-1));
        timer = new Timer();
        timer.schedule(new updateBackgroundTask(), 0, 10000);
    }

    private void setBackgroundColor(int index)
    {
        RelativeLayout l = (RelativeLayout)(findViewById(R.id.main_view));
        l.setBackgroundResource(colors[index]);
        Button b = (Button)(findViewById(R.id.registerButton));
        b.setBackgroundResource(buttonColors[index]);
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
                    setBackgroundColor(randInt(0, colors.length-1));
                }
            });
        }
    }
}
