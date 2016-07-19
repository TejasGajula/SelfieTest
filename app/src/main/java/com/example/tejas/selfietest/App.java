package com.example.tejas.selfietest; /**
 * Created by uytre_000 on 7/11/2016.
 */

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());

        AppEventsLogger.activateApp(this);
        String ApplicationID = "uQUTn8iMqVdNNyB68CpIRQhfXuDqhs1JsaNsjz1l";
        String ClientKey = "6TvHb8jGsjxaUhnhJvlxBe86icSLLd45HuIK3yzX";
        Parse.initialize(this, ApplicationID, ClientKey); // Your Application ID and Client Key are defined elsewher
    }
}
