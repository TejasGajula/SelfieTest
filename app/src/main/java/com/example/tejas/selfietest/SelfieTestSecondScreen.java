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



public class SelfieTestSecondScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selfie_test_second_screen);
        TextView welcomeMessage = (TextView) findViewById(R.id.WelcomeMessage);
        Typeface helvetica = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        welcomeMessage.setTypeface(helvetica);


    }

    public void secondClickFunction(View view) {
        EditText myTextField = (EditText) findViewById(R.id.textboxTwo);
       // Toast.makeText(getApplicationContext(), "Congratulations! Thank you for signing up! Your new username is  " + myTextField.getText().toString() + "! Let's get started!!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SelfieTestSecondScreen.this, SelfieTestPostSecondActivity.class));
        Log.i("Number", myTextField.getText().toString());
        Log.i("Info", "Button Tapped, Selfie Joined");
    }
}
