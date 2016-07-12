package com.example.tejas.selfietest;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SelfieTestSecondScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selfie_test_second_screen);
        TextView welcomeMessage = (TextView) findViewById(R.id.JoinMessage);
        Typeface helvetica = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        welcomeMessage.setTypeface(helvetica);


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

    }
}
